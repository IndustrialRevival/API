/**
 * Represents a GitHub repository with its owner and name.
 */
package org.irmc.industrialrevival.api.data.github;

import lombok.Data;

/**
 * The {@code GitHubRepository} class provides a structured way to store
 * information about a GitHub repository, specifically the owner and repository name.
 *
 * @author balugaq
 */
@Data
public class GitHubRepository {
    /**
     * The owner of the GitHub repository.
     * This field holds the username or organization name that owns the repository.
     */
    private String owner;

    /**
     * The name of the GitHub repository.
     * This field holds the actual name of the repository on GitHub.
     */
    private String repoName;
}