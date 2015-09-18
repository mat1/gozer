package io.zuehlke.gozer;

import io.undertow.server.HttpServerExchange;
import org.jboss.logging.Logger;

import java.util.Map;
import java.util.Optional;

public class RequestRouter {

    private static final Logger log = Logger.getLogger(RequestRouter.class);

    private final Map<Route, RequestHandler> routes;

    public RequestRouter(Map<Route, RequestHandler> routes) {
        this.routes = routes;
    }

    public void route(HttpMethod requestMethod, String requestUri, HttpServerExchange exchange) {
        Optional<RequestHandler> requestHandler = routes.entrySet().stream()
                .filter(e -> e.getKey().matches(requestMethod, requestUri))
                .map(e -> e.getValue())
                .findFirst();

        if (!requestHandler.isPresent()) {
            log.infof("No request handler found for %s %s", requestMethod, requestUri);
            return;
        }

        requestHandler.get().execute(exchange);
    }

}
