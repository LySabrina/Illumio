import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import java.util.HashMap;

/**
 * 
 */
public class FlowLog {
    private HashMap<String, Integer> tagCount;
    private HashMap<Protocol, HashMap<Integer, Integer>> portProtoCombo;
    private LookupTable table;
    private File file;

    public FlowLog(File file, LookupTable table) {
        this.file = file;
        this.table = table;
        tagCount = new HashMap<>();
        portProtoCombo = new HashMap<>();
    }

    /**
     * From the given flow_log.txt file, read each linee and use the LookupTable to
     * count the number of tags and port + protocol combo
     */
    public void countTags() {

        try (BufferedReader br = new BufferedReader(new FileReader(file));) {

            String input;

            while ((input = br.readLine()) != null) {
                String[] words = input.split(" ");
                int dstport = Integer.parseInt(words[6]);
                Protocol protocol = Protocol.getFromNum(Integer.parseInt(words[7]));

                portProtoCombo.putIfAbsent(protocol, new HashMap<>()); // adds the protocol if its not inside the
                                                                       // portProtoCombo map

                HashMap<Integer, String> portTag = table.getPortTag(protocol);

                if (portTag != null) {
                    // Getting the associated Tag from Protocol + Port
                    if (portTag.containsKey(dstport)) {
                        String tag = portTag.get(dstport);

                        // Checking inside TagCount to see if the tag exist
                        if (tagCount.containsKey(tag)) {
                            tagCount.put(tag, tagCount.get(tag) + 1);
                        }
                        // if it doesn't add it to the hashmap
                        else {
                            tagCount.put(tag, 1);
                        }
                    }

                    // if the dstport + the protocol does not exist within the look_up table,
                    // catgorize it as untagged
                    else {
                        if (tagCount.containsKey("untagged")) {
                            tagCount.put("untagged", tagCount.get("untagged") + 1);
                        } else {
                            tagCount.put("untagged", 1);
                        }
                    }
                }

                // Handling port and protocol combo counting
                if (portProtoCombo.containsKey(protocol)) {
                    HashMap<Integer, Integer> portCount = portProtoCombo.get(protocol);

                    if (portCount.containsKey(dstport)) {
                        int count = portCount.get(dstport);
                        count += 1;
                        portCount.put(dstport, count);
                        portProtoCombo.put(protocol, portCount);
                    } else {
                        portCount.put(dstport, 1);
                        portProtoCombo.put(protocol, portCount);
                    }
                }
            }

        } catch (FileNotFoundException foundException) {
            foundException.printStackTrace();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

    public HashMap<String, Integer> getTagCount() {
        return tagCount;
    }

    public HashMap<Protocol, HashMap<Integer, Integer>> getPortProtoCombo() {
        return portProtoCombo;
    }
}
