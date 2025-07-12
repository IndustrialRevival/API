package org.irmc.industrialrevival.core.services;

import org.irmc.industrialrevival.api.objects.Contributor;
import org.irmc.industrialrevival.api.objects.GitHubRepository;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Range;

import java.util.List;

/**
 * Service interface for accessing GitHub repository information and statistics.
 * Provides methods to retrieve various metrics and data about GitHub repositories.
 *
 * @author balugaq
 */
public interface IGitHubService {
    /**
     * Retrieves a list of contributors for the specified GitHub repository.
     *
     * @param repo the GitHub repository to get contributors for
     * @return a list of contributors for the repository
     */
    @NotNull List<Contributor> getContributors(@NotNull GitHubRepository repo);

    /**
     * Gets the number of stars for a GitHub repository.
     *
     * @param repo the GitHub repository to check
     * @return the number of stars (0 or more)
     */
    @Range(from = 0, to = Integer.MAX_VALUE)
    int getStars(@NotNull GitHubRepository repo);

    /**
     * Gets the number of forks for a GitHub repository.
     *
     * @param repo the GitHub repository to check
     * @return the number of forks (0 or more)
     */
    @Range(from = 0, to = Integer.MAX_VALUE)
    int getForks(@NotNull GitHubRepository repo);

    /**
     * Gets the number of open issues for a GitHub repository.
     *
     * @param repo the GitHub repository to check
     * @return the number of open issues (0 or more)
     */
    @Range(from = 0, to = Integer.MAX_VALUE)
    int getOpenIssues(@NotNull GitHubRepository repo);

    /**
     * Gets the number of closed issues for a GitHub repository.
     *
     * @param repo the GitHub repository to check
     * @return the number of closed issues (0 or more)
     */
    @Range(from = 0, to = Integer.MAX_VALUE)
    int getClosedIssues(@NotNull GitHubRepository repo);

    /**
     * Gets the number of open pull requests for a GitHub repository.
     *
     * @param repo the GitHub repository to check
     * @return the number of open pull requests (0 or more)
     */
    @Range(from = 0, to = Integer.MAX_VALUE)
    int getOpenPullRequests(@NotNull GitHubRepository repo);

    /**
     * Gets the number of closed pull requests for a GitHub repository.
     *
     * @param repo the GitHub repository to check
     * @return the number of closed pull requests (0 or more)
     */
    @Range(from = 0, to = Integer.MAX_VALUE)
    int getClosedPullRequests(@NotNull GitHubRepository repo);

    /**
     * Gets the total number of commits for a GitHub repository.
     *
     * @param repo the GitHub repository to check
     * @return the total number of commits (0 or more)
     */
    @Range(from = 0, to = Integer.MAX_VALUE)
    int getTotalCommits(@NotNull GitHubRepository repo);
}