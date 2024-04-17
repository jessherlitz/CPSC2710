package au.edu.cpsc.module7;

import java.io.*;

public class DatabaseIO {
    public static void save(Database database, OutputStream strm) throws IOException {
        ObjectOutputStream oos = new ObjectOutputStream(strm);
        oos.writeObject(database);
    }

    public static Database load(InputStream strm) throws IOException, ClassNotFoundException {
        ObjectInputStream ois = new ObjectInputStream(strm);
        return (Database) ois.readObject();
    }
}