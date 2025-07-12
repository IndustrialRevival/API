package org.irmc.industrialrevival.core.services;

import org.irmc.industrialrevival.api.objects.Contributor;
import org.irmc.industrialrevival.api.objects.GitHubRepository;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Range;

import java.util.List;

/**
 * Interface for GitHub service.
 * Provides methods for accessing GitHub repository information.
 */
public interface IGitHubService {
    /**
     * Gets the list of contributors for a repository.
     *
     * @param repo the repository to check
     * @return a list of contributors for the repository
     */
    @NotNull List<Contributor> getContributors(@NotNull GitHubRepository repo);

    /**
     * Gets the number of stars for a repository.
     *
     * @param repo the repository to check
     * @return the number of stars
     */
    @Range(from = 0, to = Integer.MAX_VALUE)
    int getStars(@NotNull GitHubRepository repo);

    /**
     * Gets the number of forks for a repository.
     *
     * @param repo the repository to check
     * @return the number of forks
     */
    @Range(from = 0, to = Integer.MAX_VALUE)
    int getForks(@NotNull GitHubRepository repo);

    /**
     * Gets the number of open issues for a repository.
     *
     * @param repo the repository to check
     * @return the number of open issues
     */
    @Range(from = 0, to = Integer.MAX_VALUE)
    int getOpenIssues(@NotNull GitHubRepository repo);

    /**
     * Gets the number of closed issues for a repository.
     *
     * @param repo the repository to check
     * @return the number of closed issues
     */
    @Range(from = 0, to = Integer.MAX_VALUE)
    int getClosedIssues(@NotNull GitHubRepository repo);

    /**
     * Gets the number of open pull requests for a repository.
     *
     * @param repo the repository to check
     * @return the number of open pull requests
     */
    @Range(from = 0, to = Integer.MAX_VALUE)
    int getOpenPullRequests(@NotNull GitHubRepository repo);

    /**
     * Gets the number of closed pull requests for a repository.
     *
     * @param repo the repository to check
     * @return the number of closed pull requests
     */
    @Range(from = 0, to = Integer.MAX_VALUE)
    int getClosedPullRequests(@NotNull GitHubRepository repo);

    /**
     * Gets the total number of commits for a repository.
     *
     * @param repo the repository to check
     * @return the total number of commits
     */
    @Range(from = 0, to = Integer.MAX_VALUE)
    int getTotalCommits(@NotNull GitHubRepository repo);
}