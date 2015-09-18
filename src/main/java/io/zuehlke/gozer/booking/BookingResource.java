package io.zuehlke.gozer.booking;

import io.undertow.server.HttpServerExchange;
import io.undertow.util.Headers;
import io.zuehlke.gozer.Json;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

public class BookingResource {

    private final BookingDao bookingDao;

    public BookingResource(BookingDao bookingDao) {
        this.bookingDao = bookingDao;
    }

    public void getBookings(HttpServerExchange exchange) {
        List<Booking> bookings = bookingDao.getBookings();

        exchange.getResponseHeaders().put(Headers.CONTENT_TYPE, "application/json");
        exchange.getResponseSender().send(Json.toJson(bookings));
    }

    public void getBookingByCardcode(HttpServerExchange exchange) {
        String id = getLastPartFromUri(exchange.getRequestURI());

        Booking booking = bookingDao.getBookingByCardcode(Long.parseLong(id));

        exchange.getResponseHeaders().put(Headers.CONTENT_TYPE, "application/json");
        exchange.getResponseSender().send(Json.toJson(booking));
    }

    public void createBooking(HttpServerExchange exchange) {
        Booking booking = Json.readInputStream(exchange.getInputStream(), Booking.class);

        bookingDao.create(booking);

        exchange.getResponseHeaders().put(Headers.CONTENT_TYPE, "application/json");
        exchange.getResponseSender().send(Json.toJson(booking));
    }

    private static String getLastPartFromUri(String requestUri) {
        try {
            URI uri = new URI(requestUri);
            String path = uri.getPath();
            return path.substring(path.lastIndexOf('/') + 1);
        } catch (URISyntaxException e) {
            e.printStackTrace();
            return null;
        }
    }
}
