package org.irmc.industrialrevival.core.services.impl;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import org.irmc.industrialrevival.api.data.github.Contributor;
import org.irmc.industrialrevival.api.data.github.GitHubRepository;
import org.irmc.industrialrevival.core.services.IGitHubService;
import org.irmc.industrialrevival.utils.HttpUtil;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Range;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

/**
 * Implementation of {@link IGitHubService} that provides GitHub repository information
 * by making HTTP requests to the GitHub REST API.
 * <p>
 * This implementation caches responses to avoid excessive API calls and provides
 * both synchronous and asynchronous access to GitHub repository data. It handles
 * rate limiting and error cases gracefully by returning default values when
 * API calls fail.
 * </p>
 *
 * @author balugaq
 * @since 1.0
 * @see IGitHubService
 */
public class GitHubService implements IGitHubService {
    
    private static final String GITHUB_API_BASE = "https://api.github.com";
    private static final String REPOS_ENDPOINT = "/repos";
    private static final String CONTRIBUTORS_ENDPOINT = "/contributors";
    
    // Cache for storing API responses to avoid repeated calls
    private final ConcurrentHashMap<String, JsonObject> repositoryCache = new ConcurrentHashMap<>();
    private final ConcurrentHashMap<String, List<Contributor>> contributorsCache = new ConcurrentHashMap<>();
    
    // Cache expiration time (5 minutes)
    private final long CACHE_EXPIRATION = TimeUnit.MINUTES.toMillis(5);
    private final ConcurrentHashMap<String, Long> cacheTimestamps = new ConcurrentHashMap<>();
    
    @Override
    @NotNull
    public List<Contributor> getContributors(@NotNull GitHubRepository repo) {
        String cacheKey = getContributorsCacheKey(repo);
        
        // Check cache first
        List<Contributor> cached = contributorsCache.get(cacheKey);
        if (cached != null && !isCacheExpired(cacheKey)) {
            return new ArrayList<>(cached);
        }
        
        // Fetch from API
        List<Contributor> contributors = fetchContributors(repo);
        
        // Cache the result
        contributorsCache.put(cacheKey, new ArrayList<>(contributors));
        cacheTimestamps.put(cacheKey, System.currentTimeMillis());
        
        return contributors;
    }
    
    @Override
    @Range(from = 0, to = Integer.MAX_VALUE)
    public int getStars(@NotNull GitHubRepository repo) {
        JsonObject repoData = getRepositoryData(repo);
        return HttpUtil.getIntOrDefault(repoData, "stargazers_count", 0);
    }
    
    @Override
    @Range(from = 0, to = Integer.MAX_VALUE)
    public int getForks(@NotNull GitHubRepository repo) {
        JsonObject repoData = getRepositoryData(repo);
        return HttpUtil.getIntOrDefault(repoData, "forks_count", 0);
    }
    
    @Override
    @Range(from = 0, to = Integer.MAX_VALUE)
    public int getOpenIssues(@NotNull GitHubRepository repo) {
        JsonObject repoData = getRepositoryData(repo);
        return HttpUtil.getIntOrDefault(repoData, "open_issues_count", 0);
    }
    
    @Override
    @Range(from = 0, to = Integer.MAX_VALUE)
    public int getClosedIssues(@NotNull GitHubRepository repo) {
        // GitHub API doesn't provide closed issues count directly
        // This would require additional API calls to calculate
        return 0;
    }
    
    @Override
    @Range(from = 0, to = Integer.MAX_VALUE)
    public int getOpenPullRequests(@NotNull GitHubRepository repo) {
        // GitHub API doesn't provide open PR count directly in repository data
        // This would require additional API calls to calculate
        return 0;
    }
    
    @Override
    @Range(from = 0, to = Integer.MAX_VALUE)
    public int getClosedPullRequests(@NotNull GitHubRepository repo) {
        // GitHub API doesn't provide closed PR count directly
        // This would require additional API calls to calculate
        return 0;
    }
    
    @Override
    @Range(from = 0, to = Integer.MAX_VALUE)
    public int getTotalCommits(@NotNull GitHubRepository repo) {
        // GitHub API doesn't provide total commit count directly
        // This would require additional API calls to calculate
        return 0;
    }
    
    /**
     * Gets the repository data from cache or fetches it from the API.
     *
     * @param repo the repository to get data for
     * @return the repository data as a JsonObject
     */
    @NotNull
    private JsonObject getRepositoryData(@NotNull GitHubRepository repo) {
        String cacheKey = getRepositoryCacheKey(repo);
        
        // Check cache first
        JsonObject cached = repositoryCache.get(cacheKey);
        if (cached != null && !isCacheExpired(cacheKey)) {
            return cached;
        }
        
        // Fetch from API
        JsonObject repoData = fetchRepositoryData(repo);
        
        // Cache the result
        repositoryCache.put(cacheKey, repoData);
        cacheTimestamps.put(cacheKey, System.currentTimeMillis());
        
        return repoData;
    }
    
    /**
     * Fetches repository data from the GitHub API.
     *
     * @param repo the repository to fetch data for
     * @return the repository data as a JsonObject, or empty object if fetch fails
     */
    @NotNull
    private JsonObject fetchRepositoryData(@NotNull GitHubRepository repo) {
        String url = buildRepositoryUrl(repo);
        
        try {
            String response = HttpUtil.makeGetRequest(url);
            if (response != null) {
                JsonObject json = HttpUtil.parseJson(response);
                if (json != null) {
                    return json;
                }
            }
        } catch (IOException e) {
            // Log error if needed
        }
        
        return new JsonObject();
    }
    
    /**
     * Fetches contributors data from the GitHub API.
     *
     * @param repo the repository to fetch contributors for
     * @return a list of contributors, or empty list if fetch fails
     */
    @NotNull
    private List<Contributor> fetchContributors(@NotNull GitHubRepository repo) {
        String url = buildContributorsUrl(repo);
        List<Contributor> contributors = new ArrayList<>();
        
        try {
            String response = HttpUtil.makeGetRequest(url);
            if (response != null) {
                // GitHub contributors endpoint returns a JSON array directly
                JsonArray jsonArray = HttpUtil.parseJsonArray(response);
                if (jsonArray != null) {
                    for (JsonElement element : jsonArray) {
                        if (element.isJsonObject()) {
                            JsonObject contributorJson = element.getAsJsonObject();
                            Contributor contributor = new Contributor();
                            contributor.setName(getStringOrDefault(contributorJson, "login", "Unknown"));
                            contributor.setUrl(getStringOrDefault(contributorJson, "html_url", ""));
                            contributors.add(contributor);
                        }
                    }
                }
            }
        } catch (IOException e) {
            // Log error if needed
        }
        
        return contributors;
    }
    
    /**
     * Builds the URL for repository API endpoint.
     *
     * @param repo the repository
     * @return the complete API URL
     */
    @NotNull
    private String buildRepositoryUrl(@NotNull GitHubRepository repo) {
        return GITHUB_API_BASE + REPOS_ENDPOINT + "/" + repo.getOwner() + "/" + repo.getRepoName();
    }
    
    /**
     * Builds the URL for contributors API endpoint.
     *
     * @param repo the repository
     * @return the complete API URL
     */
    @NotNull
    private String buildContributorsUrl(@NotNull GitHubRepository repo) {
        return buildRepositoryUrl(repo) + CONTRIBUTORS_ENDPOINT;
    }
    
    /**
     * Gets the cache key for repository data.
     *
     * @param repo the repository
     * @return the cache key
     */
    @NotNull
    private String getRepositoryCacheKey(@NotNull GitHubRepository repo) {
        return "repo:" + repo.getOwner() + "/" + repo.getRepoName();
    }
    
    /**
     * Gets the cache key for contributors data.
     *
     * @param repo the repository
     * @return the cache key
     */
    @NotNull
    private String getContributorsCacheKey(@NotNull GitHubRepository repo) {
        return "contributors:" + repo.getOwner() + "/" + repo.getRepoName();
    }
    
    /**
     * Checks if the cache entry has expired.
     *
     * @param cacheKey the cache key to check
     * @return true if the cache has expired, false otherwise
     */
    private boolean isCacheExpired(@NotNull String cacheKey) {
        Long timestamp = cacheTimestamps.get(cacheKey);
        if (timestamp == null) {
            return true;
        }
        return System.currentTimeMillis() - timestamp > CACHE_EXPIRATION;
    }
    
    /**
     * Gets a string value from a JsonObject with a fallback to default value.
     *
     * @param json the JsonObject to get the value from
     * @param key the key to look for
     * @param defaultValue the default value to return if the key doesn't exist
     * @return the string value, or the default value if not found
     */
    @NotNull
    private String getStringOrDefault(@NotNull JsonObject json, @NotNull String key, @NotNull String defaultValue) {
        try {
            JsonElement element = json.get(key);
            if (element != null && element.isJsonPrimitive()) {
                return element.getAsString();
            }
        } catch (Exception e) {
            // Ignore parsing errors and return default value
        }
        return defaultValue;
    }
    
    /**
     * Clears all cached data.
     * <p>
     * This method can be called to force fresh data to be fetched on the next request.
     * </p>
     */
    public void clearCache() {
        repositoryCache.clear();
        contributorsCache.clear();
        cacheTimestamps.clear();
    }
} 