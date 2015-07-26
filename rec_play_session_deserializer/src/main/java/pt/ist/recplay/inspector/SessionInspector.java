package pt.ist.recplay.inspector;

import pt.ist.recplay.inspector.util.Deserializer;
import pt.ist.recplay.inspector.util.Serializer;
import pt.utl.ist.rfidtoys.recplay.SerializableSessionObject;

import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;

public class SessionInspector {


    private static final String INPUT_BASE_PATH = "session_records/";

    private static final String OUTPUT_BASE_PATH = "session_data/";

    private static final String READER_CLIENT = "pt.utl.ist.rfidtoys.recplay.Session-session-ReaderClient";

    private static final String READER_WAREHOUSE = "pt.utl.ist.rfidtoys.recplay.Session-session-ReaderWarehouse";


    private static final HashMap<String, ArrayList<String>> sessions = new HashMap<String, ArrayList<String>>() {{
            put("ClosedCircuit_1Lap", new ArrayList<String>() {{
                add("run_01");
                add("run_02");
                add("run_03");
                add("run_04");
                add("run_05");
                add("run_06");
                add("run_07");
                add("run_08");
                add("run_09");
                add("run_10");
            }});
            put("ClosedCircuit_3Lap", new ArrayList<String>() {{
                add("run_01");
                add("run_02");
                add("run_03");
                add("run_04");
                add("run_05");
                add("run_06");
                add("run_07");
                add("run_08");
                add("run_09");
                add("run_10");
            }});
            put("ClosedCircuit_3Lap_InterferenceSegment04InRange", new ArrayList<String>() {{
                add("run_01");
                add("run_02");
                add("run_03");
                add("run_04");
                add("run_05");
                add("run_06");
                add("run_07");
            }});
    }};

    private static void saveSessionDataToFile(SerializableSessionObject sessionObject, String outputPath, String extension) throws FileNotFoundException, UnsupportedEncodingException {
        Serializer.getSerializer().serializeObjectDataToFile(sessionObject, outputPath, extension);
    }


    private static void inspectRuns(String basePath, String sessionName) {

        for(String run : sessions.get(sessionName)) {
            try {
                System.out.println("- Inspecting Reader Client of run " + run);
                SerializableSessionObject readerClient = Deserializer.getDeserializer().deserializeSessionObject(basePath, sessionName + "/" + run + "/" + READER_CLIENT, ".dat");
                saveSessionDataToFile(readerClient, OUTPUT_BASE_PATH + sessionName + "/" + run + "/" + READER_CLIENT, ".txt");
                System.out.println("- Inspecting Reader Warehouse of run " + run);
                SerializableSessionObject readerWarehouse = Deserializer.getDeserializer().deserializeSessionObject(basePath, sessionName + "/" + run + "/" + READER_WAREHOUSE, ".dat");
                saveSessionDataToFile(readerWarehouse, OUTPUT_BASE_PATH + sessionName + "/" + run + "/" + READER_WAREHOUSE, ".txt");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    public static void main(String args[]) {

        for(String session : sessions.keySet()) {
            System.out.println("Inspecting session " + session + " :");
            inspectRuns(INPUT_BASE_PATH, session);
        }
    }
}
