package edu.au.cpsc.module4.data;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class Db {
    public static final File DEFAULT_FILE = new File("flights.dat");

    private static AirlineDatabase airlineDatabase = null;

    public static AirlineDatabase getDatabase() {
        if (airlineDatabase == null)
            airlineDatabase = loadDatabase();
        return airlineDatabase;
    }

    private static AirlineDatabase loadDatabase() {
        try (InputStream is = new FileInputStream(DEFAULT_FILE)) {
            return AirlineDatabaseIO.load(is);
        } catch (IOException|ClassNotFoundException ex) {
            return new AirlineDatabase();
        }
    }

    public static void saveDatabase() {
        try (OutputStream os = new FileOutputStream(DEFAULT_FILE)) {
            AirlineDatabaseIO.save(getDatabase(), os);
        } catch (IOException ex) {
            System.err.println("Error saving database: "+DEFAULT_FILE);
            ex.printStackTrace();
            System.exit(-1);
        }
    }

}
