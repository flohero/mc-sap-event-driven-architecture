package at.fhooe.server.listeners;

import at.fhooe.eventbus.Listener;
import at.fhooe.server.Server;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NotFoundEventListener {
    private final Logger logger = LoggerFactory.getLogger(NotFoundEventListener.class);

    @Listener
    public void listener(Server.SiteNotFoundEvent event) {
        logger.warn("NOT FOUND!!!!11!!1");
    }
}
