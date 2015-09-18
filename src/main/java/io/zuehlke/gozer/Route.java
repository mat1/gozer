package io.zuehlke.gozer;

import java.util.regex.Pattern;

/**
 * Created by mabr on 17.09.2015.
 */
public class Route {

    private final Pattern route;
    private final HttpMethod method;

    public Route(HttpMethod method, Pattern route) {
        this.route = route;
        this.method = method;
    }

    public boolean matches(HttpMethod requestMethod, String requestUri) {
        return method == requestMethod && route.matcher(requestUri).matches();
    }

    public static Route createRoute(HttpMethod method, String route) {
        return new Route(method, Pattern.compile(route));
    }
}
