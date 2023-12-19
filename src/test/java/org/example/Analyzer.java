package org.example;
import java.io.IOException;
import java.util.*;

public class Analyzer {
    public Analyzer() {

    }

    public static LinkedHashMap<String, HashMap<String, String>> analyzeTable(String fileName) throws IOException {
        LinkedHashMap<String, HashMap<String, String>> resultMap = new LinkedHashMap<>();
        Parser parser = new Parser(fileName);

        LinkedHashMap<String, LinkedHashMap<String, ArrayList<Integer>>> mapRoot = new LinkedHashMap<>(parser.parseTable());
        for(Map.Entry<String, LinkedHashMap<String, ArrayList<Integer>>> e : mapRoot.entrySet()) {
            HashMap<String, String> tempMap = new HashMap<>();
            String user = e.getKey();
            LinkedHashMap<String, ArrayList<Integer>> map = new LinkedHashMap<>(e.getValue());

            double MarksCounter = 0;
            for (Map.Entry<String, ArrayList<Integer>> arrE : map.entrySet()) {
                for (Integer item : arrE.getValue()) {
                    MarksCounter += item;
                }
                int avrgMark = (int) MarksCounter / (arrE.getValue()).size();
                if (avrgMark < 6)
                    tempMap.put(arrE.getKey(), "RED");
                else if (avrgMark >= 8)
                    tempMap.put(arrE.getKey(), "GREEN");
                else
                    tempMap.put(arrE.getKey(), "YELLOW");
            }
            resultMap.put(user, tempMap);
        }
        return resultMap;
    }
}
