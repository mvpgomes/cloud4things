package pt.ist.recplay.inspector.util;

import pt.utl.ist.rfidtoys.recplay.ObjectEntry;
import pt.utl.ist.rfidtoys.recplay.SerializableSessionObject;

import java.io.*;
import java.util.concurrent.ConcurrentLinkedQueue;

public class Serializer {

    private long lowerTime, higherTime, totalEvents = 0;

    private final String currentPath = System.getProperty("user.dir");

    private static Serializer instance = null;

    public void serializeObjectDataToFile(SerializableSessionObject sessionObject, String outputPath, String extension) throws FileNotFoundException, UnsupportedEncodingException {

        Writer writer = null;

        try {

            // Creates a new file
            writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(currentPath + "/src/main/resources/" + outputPath + extension), "utf-8"));
            // Register the session ID and creation time
            writer.write("SessionID: " + sessionObject.getSessionID() + "\n");
            writer.write("- Creation Time: " + sessionObject.getCreationTime() + " Sequence Number: " + sessionObject.getSequenceNumber() + "\n");
            // Register the object entries of the session
            writer.write("Object entries:" + "\n");
            ConcurrentLinkedQueue<ObjectEntry> entries = sessionObject.getEntries();
            for(ObjectEntry entry : entries) {
                totalEvents++;
                lowerTime = entry.getCreated() > lowerTime && lowerTime != 0 ? lowerTime : entry.getCreated();
                higherTime = entry.getCreated() > higherTime ? entry.getCreated() : higherTime;
                writer.write("- Session ID: " + entry.getSessionID() + " Created: " + entry.getCreated() + " Sequence Number: " + entry.getSequenceNumber() + "\n");
            }
            System.out.println("Lower Time: " + lowerTime);
            System.out.println("Higher Time: " + higherTime);
            // Calculate the average time between each request
            writer.write("Number of events: " + totalEvents + " Average time between events: " + ((higherTime - lowerTime)/totalEvents) + "\n");
            // Update the variables to the original state
            lowerTime = 0;
            higherTime = 0;
            totalEvents = 0;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                writer.close();
            } catch (Exception e) {

            }
        }
    }

    public static Serializer getSerializer() {
        if(instance == null) {
            instance = new Serializer();
        }
        return instance;
    }

}
