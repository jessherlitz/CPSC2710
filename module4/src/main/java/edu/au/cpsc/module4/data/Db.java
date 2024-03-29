package edu.au.cpsc.module4.data;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class Db {

    public static final File DEFAULT_FILE = new File("scheduledFlights.dat");

    private static Database flightsDatabase = null;

    public static Database getDatabase() {

        if (flightsDatabase == null)
            flightsDatabase = loadDatabase();
        return flightsDatabase;
    }

    private static Database loadDatabase() {
        try (InputStream is = new FileInputStream(DEFAULT_FILE)) {
            return DatabaseIO.load(is);
        } catch (IOException|ClassNotFoundException ex) {
            return new Database();
        }
    }

    public static void saveDatabase() {
        try (OutputStream os = new FileOutputStream(DEFAULT_FILE)) {
            DatabaseIO.save(getDatabase(), os);
        } catch (IOException ex) {
            System.err.println("Error saving database: "+DEFAULT_FILE);
            ex.printStackTrace();
            System.exit(-1);
        }
    }

}