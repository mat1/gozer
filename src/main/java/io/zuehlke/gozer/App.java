package io.zuehlke.gozer;

import io.zuehlke.gozer.booking.BookingDao;
import io.zuehlke.gozer.booking.BookingResource;
import org.jboss.logging.Logger;
import org.skife.jdbi.v2.DBI;

public class App {
    private static final Logger log = Logger.getLogger(App.class);

    public static void main(String[] args) throws Exception {
        BookingResource bookingResource = createBookingResource();

        Gozer gozer = new Gozer("localhost", 80);

        gozer.get("/bookings", bookingResource::getBookings);
        gozer.get("/bookings/[0-9]+", bookingResource::getBookingByCardcode);
        gozer.post("/bookings", bookingResource::createBooking);

        gozer.start();
    }

    private static BookingResource createBookingResource() {
        DBI dbi = new DBI("jdbc:mysql://localhost/sef",
                "root",
                "root");

        BookingDao bookingDao = new BookingDao(dbi);
        return new BookingResource(bookingDao);
    }

}
