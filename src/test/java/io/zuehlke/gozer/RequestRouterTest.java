package io.zuehlke.gozer;

import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

/**
 * Created by mabr on 17.09.2015.
 */
public class RequestRouterTest {

    @Before
    public void setUp() throws Exception {

    }

    @Test
    public void route_GetUsers_CallsRequestHandler() throws Exception {
        // arrange
        final boolean[] wasCalled = {false};

        Map<Route, RequestHandler> routes = new HashMap<>();
        routes.put(Route.createRoute(HttpMethod.GET, "/users"), exchange -> wasCalled[0] = true);

        RequestRouter router = new RequestRouter(routes);

        // act
        router.route(HttpMethod.GET, "/users", null);

        // assert
        assertTrue(wasCalled[0]);
    }

    @Test
    public void route_GetUsersById_CallsRequestHandler() throws Exception {
        // arrange
        final boolean[] wasCalled = {false};

        Map<Route, RequestHandler> routes = new HashMap<>();
        routes.put(Route.createRoute(HttpMethod.GET, "/users"), null);
        routes.put(Route.createRoute(HttpMethod.GET, "/users/[0-9]+"), exchange -> wasCalled[0] = true);

        RequestRouter router = new RequestRouter(routes);

        // act
        router.route(HttpMethod.GET, "/users/1232", null);

        // assert
        assertTrue(wasCalled[0]);
    }

    private Map<String, RequestHandler> createRoutes(RequestHandler usersRequestHandler, RequestHandler userByIdRequestHandler) {
        Map<String, RequestHandler> routes = new HashMap<>();
        routes.put("/users", usersRequestHandler);
        routes.put("/users/[0-9]+", userByIdRequestHandler);
        return routes;
    }


}