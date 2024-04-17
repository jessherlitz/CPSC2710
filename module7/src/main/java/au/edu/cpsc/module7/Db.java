package au.edu.cpsc.module7;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class Db {

    public static final File DEFAULT_FILE = new File("notes.txt");

    private static Database database = null;

    public static Database getDatabase() {
        if (database == null)
            database = loadDatabase();
        return database;
    }

    private static Database loadDatabase() {
        try (InputStream is = new FileInputStream(DEFAULT_FILE)) {
            return DatabaseIO.load(is);
        } catch (IOException|ClassNotFoundException e) {
            return new Database();
        }
    }

    public static void saveDatabase() {
        try (OutputStream os = new FileOutputStream(DEFAULT_FILE)) {
            DatabaseIO.save(getDatabase(), os);
        } catch (IOException e) {
            System.err.println("Error saving to database: " + DEFAULT_FILE);
            e.printStackTrace();
            System.exit(-1);
        }
    }

}