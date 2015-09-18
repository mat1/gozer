package io.zuehlke.gozer.booking;

import org.junit.Before;
import org.junit.Test;
import org.skife.jdbi.v2.DBI;
import org.skife.jdbi.v2.tweak.SQLLog;

import java.util.Calendar;

import static org.junit.Assert.*;

/**
 * Created by mabr on 18.09.2015.
 */
public class BookingDaoTest {

    private BookingDao bookingDao;

    @Before
    public void setUp() throws Exception {
        DBI dbi = new DBI("jdbc:mysql://localhost/sef",
                "root",
                "root");


        bookingDao = new BookingDao(dbi);
    }

    @Test
    public void testGetBookings() throws Exception {
        System.out.println(bookingDao.getBookings());
    }

    @Test
    public void testCreateBooking() throws Exception {
        Booking booking = new Booking();
        booking.setItemCode("test");
        booking.setEnd(Calendar.getInstance());
        booking.setStart(Calendar.getInstance());
        booking.setLocation("blub");
        booking.setTitle("blub");
        booking.setCardcode(101);
        bookingDao.create(booking);
        System.out.println(bookingDao.getBookings());
    }
}