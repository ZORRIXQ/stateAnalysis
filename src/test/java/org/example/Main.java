package org.example;
import java.io.IOException;
import java.util.*;

class Main{
    public static void main(String[] args) throws IOException {
        LinkedHashMap<String, HashMap<String, String>> map = new LinkedHashMap<>(Analyzer.analyzeTable("test.xlsx"));
        for(Map.Entry<String, HashMap<String, String>> e : map.entrySet()){
            System.out.println(e.getKey().toString());
            for (Map.Entry<String, String> elem : e.getValue().entrySet()){
                System.out.println(elem.getKey()+": "+elem.getValue());
            }
        }
    }
}