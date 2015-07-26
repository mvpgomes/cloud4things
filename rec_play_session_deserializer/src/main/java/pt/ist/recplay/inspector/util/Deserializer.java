package pt.ist.recplay.inspector.util;

import pt.utl.ist.rfidtoys.recplay.SerializableSessionObject;

import java.io.FileInputStream;
import java.io.ObjectInputStream;

public class Deserializer {

    private final String currentPath = System.getProperty("user.dir");

    private static Deserializer instance = null;

    public SerializableSessionObject deserializeSessionObject(String basePath, String path, String extension) {

        SerializableSessionObject sessionObject;

        try {
            FileInputStream fis = new FileInputStream(currentPath + "/src/main/resources/" + basePath + path + extension);
            ObjectInputStream ois = new ObjectInputStream(fis);
            sessionObject = (SerializableSessionObject) ois.readObject();
            ois.close();

            return sessionObject;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Deserializer getDeserializer() {
        if(instance == null) {
            instance = new Deserializer();
        }
        return instance;
    }

}
