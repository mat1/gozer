package io.zuehlke.gozer;

import io.undertow.Undertow;
import org.jboss.logging.Logger;

import java.util.HashMap;
import java.util.Map;

public class Gozer {

    private static final Logger log = Logger.getLogger(Gozer.class);
    private final Map<Route, RequestHandler> routes = new HashMap<>();
    private final String host;
    private final int port;

    public Gozer(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public void get(String route, RequestHandler requestHandler) {
        routes.put(Route.createRoute(HttpMethod.GET, route), requestHandler);
    }

    public void post(String route, RequestHandler requestHandler) {
        routes.put(Route.createRoute(HttpMethod.POST, route), requestHandler);
    }

    public void start() {
        log.infof("Start server %s:%s", host, port);

        RequestRouter requestRouter = new RequestRouter(routes);

        DefaultHttpHandler httpHandler = new DefaultHttpHandler(requestRouter);

        Undertow server = Undertow.builder()
                .addHttpListener(port, host)
                .setHandler(httpHandler)
                .build();

        server.start();
    }

}