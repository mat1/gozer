package io.zuehlke.gozer.booking;

import org.skife.jdbi.v2.DBI;
import org.skife.jdbi.v2.Handle;
import org.skife.jdbi.v2.util.StringMapper;

import java.util.List;

/**
 * Created by mabr on 18.09.2015.
 */
public class BookingDao {

    private final DBI dbi;

    public BookingDao(DBI dbi) {
        this.dbi = dbi;
    }

    public List<Booking> getBookings() {
        try (Handle h = dbi.open()) {
            return h.createQuery("select * from booking")
                    .map(new Booking.BookingMapper())
                    .list();
        }
    }

    public Booking getBookingByCardcode(long cardcode) {
        try (Handle h = dbi.open()) {
            return h.createQuery("select * from booking where cardcode = :cardcode")
                    .bind("cardcode", cardcode)
                    .map(new Booking.BookingMapper())
                    .first();
        }
    }

    public void create(Booking booking) {
        try (Handle h = dbi.open()) {
            h.execute("insert into booking (cardcode, start, end, title, location, itemCode) values (?, ?, ?, ?, ?, ?)",
                    booking.getCardcode(), booking.getStart().getTime(), booking.getEnd().getTime(),
                    booking.getTitle(), booking.getLocation(), booking.getItemCode());
        }
    }
}
