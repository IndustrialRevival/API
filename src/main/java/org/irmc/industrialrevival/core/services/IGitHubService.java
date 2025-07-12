package org.irmc.industrialrevival.core.services;

import org.irmc.industrialrevival.api.objects.Contributor;
import org.irmc.industrialrevival.api.objects.GitHubRepository;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Range;

import java.util.List;

public interface IGitHubService {
    @NotNull List<Contributor> getContributors(@NotNull GitHubRepository repo);
    @Range(from = 0, to = Integer.MAX_VALUE) int getStars(@NotNull GitHubRepository repo);
    @Range(from = 0, to = Integer.MAX_VALUE) int getForks(@NotNull GitHubRepository repo);
    @Range(from = 0, to = Integer.MAX_VALUE) int getOpenIssues(@NotNull GitHubRepository repo);
    @Range(from = 0, to = Integer.MAX_VALUE) int getClosedIssues(@NotNull GitHubRepository repo);
    @Range(from = 0, to = Integer.MAX_VALUE) int getOpenPullRequests(@NotNull GitHubRepository repo);
    @Range(from = 0, to = Integer.MAX_VALUE) int getClosedPullRequests(@NotNull GitHubRepository repo);
    @Range(from = 0, to = Integer.MAX_VALUE) int getTotalCommits(@NotNull GitHubRepository repo);
}
