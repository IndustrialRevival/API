/**
 * Represents a contributor to a GitHub repository.
 */
package org.irmc.industrialrevival.api.data.github;

import lombok.Data;

/**
 * The {@code Contributor} class provides a structured way to store
 * information about a contributor to a GitHub repository.
 *
 * @author balugaq
 */
@Data
public class Contributor {
    /**
     * The name of the contributor.
     * This typically corresponds to the GitHub username of the contributor.
     */
    private String name;

    /**
     * The URL to the contributor's GitHub profile.
     * This can be used to directly link to the contributor's GitHub page.
     */
    private String url;
}