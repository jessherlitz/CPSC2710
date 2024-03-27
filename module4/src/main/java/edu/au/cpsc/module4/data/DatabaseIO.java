package edu.au.cpsc.module4.data;

import edu.au.cpsc.module4.model.ScheduledFlight;
import java.io.*;
import java.util.List;

public class DatabaseIO {

    public static void save(Database ad, OutputStream strm) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(strm)) {
            oos.writeObject(ad.getScheduledFlights());
        }
    }

    public static Database load(InputStream strm) throws IOException, ClassNotFoundException {
        Database ad = new Database();
        try (ObjectInputStream ois = new ObjectInputStream(strm)) {
            List<ScheduledFlight> flights = (List<ScheduledFlight>) ois.readObject();
            ad.getScheduledFlights().addAll(flights);
        }
        return ad;
    }
}
