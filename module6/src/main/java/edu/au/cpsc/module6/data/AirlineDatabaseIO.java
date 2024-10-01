package edu.au.cpsc.module6.data;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;

public class AirlineDatabaseIO {

    public static void save(AirlineDatabase database, OutputStream strm) throws IOException {
        ObjectOutputStream oos = new ObjectOutputStream(strm);
        oos.writeObject(database);
    }

    public static AirlineDatabase load(InputStream strm) throws IOException, ClassNotFoundException {
        ObjectInputStream ois = new ObjectInputStream(strm);
        return (AirlineDatabase) ois.readObject();
    }


}
