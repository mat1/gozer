package io.zuehlke.gozer;

import org.jboss.logging.Logger;
import org.skife.jdbi.v2.DBI;
import org.skife.jdbi.v2.Handle;
import org.skife.jdbi.v2.util.StringMapper;

import javax.sql.DataSource;

public class App {
    private static final Logger log = Logger.getLogger(App.class);

    public static void main(String[] args) throws Exception {
        Gozer gozer = new Gozer("localhost", 80);

        DBI dbi = new DBI("jdbc:mysql://localhost/sef",
                "root",
                "root");

        try(Handle h = dbi.open()) {
            String name = h.createQuery("select id from voting")
                    .map(StringMapper.FIRST)
                    .first();

            System.out.println(name);
        }

        gozer.get("/users", exchange -> exchange.getResponseSender().send("Hello world"));
        gozer.post("/users", exchange -> exchange.getResponseSender().send("Thanks for the post"));

        gozer.start();
    }
}
