import com.income.engine.ReaderEngine;

import java.io.IOException;
import java.util.Map;

public class ReadSheetData {

    public static void main(String[] args) throws IOException {

        ReaderEngine r = new ReaderEngine();

      /*  Map<String, Map<Integer, String>> mapOfHeaders =    r.readCompleteFullSheet("Sheet1");

        System.out.println("mapOfHeaders "+ mapOfHeaders.size());

        for (Map.Entry<String, Map<Integer, String>> entry : mapOfHeaders.entrySet()) {
            System.out.println(entry.getKey()+" : "+entry.getValue());
        }
        System.out.println("====End=======");


        Map<Integer, String> map = mapOfHeaders.get("Age");

        for (Map.Entry<Integer, String> entry : map.entrySet()) {
            System.out.println(entry.getKey()+" : "+entry.getValue());
        }
*/

        Map<String, Map<String, Map<Integer, String>>> fullExcel = r.readCompleteExcel("");

        System.out.println("fullExcel "+ fullExcel.size());

        for (Map.Entry<String, Map<String, Map<Integer, String>>> entry : fullExcel.entrySet()) {
            System.out.println(entry.getKey()+" : "+entry.getValue());
        }
        System.out.println("====End=======");

        Map<String, Map<Integer, String>> mapOfHeaders = fullExcel.get("Sheet1");

        for (Map.Entry<String, Map<Integer, String>> entry : mapOfHeaders.entrySet()) {
            System.out.println(entry.getKey()+" : "+entry.getValue());
        }


        Map<Integer, String> map = mapOfHeaders.get("Age");

        for (Map.Entry<Integer, String> entry : map.entrySet()) {
            System.out.println(entry.getKey()+" : "+entry.getValue());
        }










    }





}
