package io.zuehlke.gozer;

import io.undertow.server.HttpServerExchange;

/**
 * Created by mabr on 17.09.2015.
 */
@FunctionalInterface
public interface RequestHandler {
    void execute(HttpServerExchange exchange);
}
