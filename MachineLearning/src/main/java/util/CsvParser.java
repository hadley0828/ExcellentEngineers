package util;

import java.io.*;

/**
 * Created by loohaze on 2017/7/27.
 */
public class CsvParser {

    public void CsvReader(String file){
        try {
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line = "";
            String tempDescription = "";
            while((line = bufferedReader.readLine()) != null){
                String[] items = line.split("~");
                for (String s : items){
                    System.out.println(s);
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        CsvParser csvParser = new CsvParser();
        csvParser.CsvReader("/Users/loohaze/Downloads/tflearn-master/examples/nlp/dataset.txt");

    }
}
