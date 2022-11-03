package at.fhooe.server;

import at.fhooe.eventbus.EventBus;
import io.javalin.Javalin;
import io.javalin.http.HttpStatus;

public class Server {
    public static void main(String[] args) {
        EventBus eventBus = new EventBus();
        LifeCycleBean registerService = new LifeCycleBean(eventBus);
        registerService.register();
        var app = Javalin.create(/*config*/)
                .before(ctx -> eventBus.post(new SiteVisitedEvent()))
                .get("/", ctx -> ctx.result("Hello World"))
                .post("/stats", ctx -> eventBus.post(new PrintVisitCounterEvent()))
                .error(HttpStatus.NOT_FOUND, o -> eventBus.post(new SiteNotFoundEvent()))
                .events(event -> {
                    event.serverStarting(() -> eventBus.post(new ServerStartingEvent()));
                    event.serverStarted(() -> eventBus.post(new ServerStartedEvent()));
                    event.serverStopping(() -> eventBus.post(new ServerStoppingEvent()));
                    event.serverStopped(() -> eventBus.post(new ServerStoppedEvent()));
                });
        app.post("/shutdown", ctx ->
                        new Thread(() -> {
                            try {
                                Thread.sleep(10);
                            } catch (InterruptedException e) {
                                throw new RuntimeException(e);
                            }
                            app.stop();
                        }).start())
                .start(7070);

    }

    public record SiteVisitedEvent() {
    }

    public record ServerStartingEvent() {
    }

    public record ServerStartedEvent() {
    }

    public record ServerStoppingEvent() {
    }

    public record ServerStoppedEvent() {
    }

    public record SiteNotFoundEvent() {
    }

    public record PrintVisitCounterEvent() {
    }
}
