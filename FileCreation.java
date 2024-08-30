import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;

/**
 * Class for creating the tag count and portProtoCombo
 * 
 */
public class FileCreation {
    public FileCreation() {
    }

    /**
     * Creates the tag_count.txt and writes to a file the count of each tag
     * 
     * @param tagCount the tag and its count
     */
    public void createTagCountFile(HashMap<String, Integer> tagCount) {
        BufferedWriter bw = null;
        try {
            File tagCountFile = new File("./tag_count.txt");
            tagCountFile.createNewFile();
            bw = new BufferedWriter(new FileWriter(tagCountFile));
            bw.write("Tag,Count");
            bw.newLine();
            for (String key : tagCount.keySet()) {
                int count = tagCount.get(key);

                bw.write(key + "," + count);
                bw.newLine();
            }

            System.out.println("tag_count.txt WRITTEN TO");
        } catch (IOException iException) {
            iException.printStackTrace();
        } finally {
            try {
                if (bw != null) {
                    bw.close();
                }
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }

        }
    }

    /**
     * Creates the port_proto_combo.txt and writes to the file the count of port +
     * protocol combo
     * 
     * @param portProtoComboMap the protocol and the port and its count
     */
    public void createPortProtoComboFile(HashMap<Protocol, HashMap<Integer, Integer>> portProtoComboMap) {
        BufferedWriter bw = null;

        try {
            File portProtoCombo = new File("./port_proto_combo.txt");
            portProtoCombo.createNewFile();
            bw = new BufferedWriter(new FileWriter(portProtoCombo));
            bw.write("Port,Protocol,Count");
            bw.newLine();
            for (Protocol p : portProtoComboMap.keySet()) {

                HashMap<Integer, Integer> ports = portProtoComboMap.get(p);
                for (Integer i : ports.keySet()) {

                    bw.write(i + "," + p.name() + "," + ports.get(i));
                    bw.newLine();
                }
            }
            System.out.println("port_proto_combo.txt WRITTEN TO");
        } catch (IOException iException) {
            iException.printStackTrace();
        } finally {
            try {
                if (bw != null) {
                    bw.close();
                }

            } catch (IOException iexIoException) {
                iexIoException.printStackTrace();
            }

        }
    }
}
