package io.zuehlke.gozer;

import io.undertow.server.HttpHandler;
import io.undertow.server.HttpServerExchange;
import org.jboss.logging.Logger;

/**
 * Created by mabr on 17.09.2015.
 */
public class DefaultHttpHandler implements HttpHandler {

    private static final Logger log = Logger.getLogger(DefaultHttpHandler.class);

    private final RequestRouter requestRouter;

    public DefaultHttpHandler(RequestRouter requestRouter) {
        this.requestRouter = requestRouter;
    }

    @Override
    public void handleRequest(HttpServerExchange exchange) throws Exception {
        if (exchange.isInIoThread()) {
            exchange.dispatch(this);
            return;
        }

        exchange.startBlocking();

        String requestURI = exchange.getRequestURI();
        HttpMethod httpMethod = HttpMethod.valueOf(exchange.getRequestMethod().toString());

        log.infof("Handle request %s %s", httpMethod, requestURI);

        requestRouter.route(httpMethod, exchange.getRequestURI(), exchange);
    }
}
