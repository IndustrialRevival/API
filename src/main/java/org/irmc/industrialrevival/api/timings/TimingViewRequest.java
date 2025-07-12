package org.irmc.industrialrevival.api.timings;

import lombok.Getter;
import org.bukkit.command.CommandSender;

@Getter
public class TimingViewRequest {
    private final CommandSender requester;
    private final long requestTime;
    private final boolean summaryToRequester;

    public TimingViewRequest(CommandSender requester, boolean summaryToRequester) {
        this.requester = requester;
        this.requestTime = System.currentTimeMillis();
        this.summaryToRequester = summaryToRequester;
    }
}
