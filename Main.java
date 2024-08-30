import java.io.File;

import java.util.HashMap;

/**
 * Main to run the program
 */
public class Main {

    public static void main(String[] args) {
        File f = new File("./lookup_table.csv");
        File flowLogFile = new File("./flow_log.txt");

        LookupTable table = new LookupTable(f);
        table.setHashMap();

        System.out.println("--- LOOK UP TABLE: " + table.getTable());
        FlowLog flowLog = new FlowLog(flowLogFile, table);
        flowLog.countTags();

        HashMap<String, Integer> tagCount = flowLog.getTagCount();
        FileCreation fCreation = new FileCreation();
        fCreation.createTagCountFile(tagCount);
        System.out.println("---- TAG COUNT: " + tagCount);

        HashMap<Protocol, HashMap<Integer, Integer>> portProtoCombo = flowLog.getPortProtoCombo();
        fCreation.createPortProtoComboFile(portProtoCombo);
        System.out.println("--- PORT & PROTCOL COMBO: " + portProtoCombo);

    }

}
