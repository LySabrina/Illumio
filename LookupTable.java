import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

/**
 * Read through a lookup_table.csv and creates a hashmap
 * In the hashmap, the key is th protocol and its value is the port and
 * resulting tag
 * 
 */
class LookupTable {
    private File file;
    private HashMap<Protocol, HashMap<Integer, String>> map;

    public LookupTable(File file) {
        this.file = file;
        map = new HashMap<>();
    }

    /**
     * Reads through the lookup_table.csv and updates the map with its value
     */
    public void setHashMap() {
        try (BufferedReader br = new BufferedReader(new FileReader(file));) {

            String line;
            line = br.readLine(); // to start at the beginning and while-loop will start at the next line

            while ((line = br.readLine()) != null) {
                String[] row = line.split(",");
                int dstport = Integer.parseInt(row[0]);
                Protocol protocol = Protocol.valueOf(Protocol.class, row[1].toUpperCase());
                String tag = row[2].replaceAll("\\s", "");

                // check if the protocol is already in the map, then check if the port number

                // updating map key
                if (map.containsKey(protocol)) {
                    HashMap<Integer, String> map2 = map.get(protocol);
                    map2.put(dstport, tag);
                    map.put(protocol, map2);
                } else {
                    // adding a new map key
                    HashMap<Integer, String> map2 = new HashMap<>();
                    map2.put(dstport, tag);
                    map.put(protocol, map2);
                }
            }

        } catch (FileNotFoundException fException) {
            fException.printStackTrace();
        } catch (IOException iException) {
            iException.printStackTrace();
        } catch (NumberFormatException nException) {
            nException.printStackTrace();
        }
    }

    public HashMap<Protocol, HashMap<Integer, String>> getTable() {
        return map;
    }

    public HashMap<Integer, String> getPortTag(Protocol p) {
        if (map.containsKey(p)) {
            return map.get(p);
        }
        return null;
    }
}