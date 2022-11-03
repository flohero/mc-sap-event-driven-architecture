package at.fhooe.server.listeners;

import at.fhooe.eventbus.Listener;
import at.fhooe.server.Server;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class SiteInformation {
    private int visitCounter = 0;
    private int notFoundCounter = 0;
    private final Logger logger = LoggerFactory.getLogger(SiteInformation.class);

    @Listener
    public void siteVisited(Server.SiteVisitedEvent event) {
        visitCounter++;
    }

    @Listener
    public void siteNotFound(Server.SiteNotFoundEvent event) {
        notFoundCounter++;
    }

    @Listener
    public void printStats(Server.PrintVisitCounterEvent event) {
        logger.info("Sites visited: {}", visitCounter);
        logger.info("Sites not found: {}", notFoundCounter);
    }
}
