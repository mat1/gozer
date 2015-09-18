package io.zuehlke.gozer.booking;

import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;

public class Booking implements Serializable {

    private static final long serialVersionUID = 1L;

    private long cardcode;
    private String itemCode;

    private Calendar start;

    private Calendar end;

    private String title;

    private String location;

    public long getCardcode() {
        return cardcode;
    }

    public void setCardcode(long cardcode) {
        this.cardcode = cardcode;
    }

    public Calendar getStart() {
        return start;
    }

    public void setStart(Calendar start) {
        this.start = start;
    }

    public Calendar getEnd() {
        return end;
    }

    public void setEnd(Calendar end) {
        this.end = end;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @Override
    public String toString() {
        return "Booking [cardcode=" + cardcode + ", start=" + start + ", end=" + end + ", title=" + title
                + ", location=" + location + ", itemCode=" + itemCode + "]";
    }

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public static class BookingMapper implements ResultSetMapper<Booking>
    {
        public Booking map(int index, ResultSet r, StatementContext ctx) throws SQLException
        {
            Booking booking = new Booking();
            booking.cardcode = r.getLong("cardcode");
            booking.start = dateToCalendar(r.getDate("start"));
            booking.end = dateToCalendar(r.getDate("end"));
            booking.title = r.getString("title");
            booking.location = r.getString("location");
            booking.itemCode = r.getString("itemCode");
            return booking;
        }

        public Calendar dateToCalendar(Date date){
            Calendar cal = Calendar.getInstance();
            cal.setTime(date);
            return cal;
        }
    }

}

