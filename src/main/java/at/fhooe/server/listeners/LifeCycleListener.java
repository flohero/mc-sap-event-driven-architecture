package at.fhooe.server.listeners;

import at.fhooe.eventbus.Listener;
import at.fhooe.server.Server;
import at.fhooe.server.utils.Stopwatch;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LifeCycleListener {
    private final Logger logger = LoggerFactory.getLogger(LifeCycleListener.class);
    private final Stopwatch stopwatch = new Stopwatch();

    @Listener
    public void starting(Server.ServerStartingEvent event) {
        stopwatch.start();
        logger.info("Server starting... ");
    }

    @Listener
    public void started(Server.ServerStartedEvent event) {
        stopwatch.stop();
        logger.info("Server started in {}s", stopwatch.getElapsedTimeSecs());
    }

    @Listener
    public void stopping(Server.ServerStoppingEvent event) {
        stopwatch.start();
        // Do other stuff like killing DB connections
        logger.info("Server stopping... ");
    }

    @Listener
    public void stopped(Server.ServerStoppedEvent event) {
        stopwatch.stop();
        logger.info("Server stopped in {}s", stopwatch.getElapsedTimeSecs());
    }
}
