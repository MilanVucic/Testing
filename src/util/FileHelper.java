package util;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
public class FileHelper {
    public static List<String> getFileLines(String filename) {
        List<String> list = new ArrayList<>();
        try {
            BufferedReader br = new BufferedReader(new FileReader(filename));
            String line = br.readLine();


            while (line != null) {
                list.add(line);
                line = br.readLine();
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());

        }

        return list;
    }

    public static void writeLinesToFile(List<String> linesToWrite, String filename) {
        BufferedWriter output = null;
        try {
            File file = new File(filename);
            output = new BufferedWriter(new FileWriter(file));

            for (int i = 0; i < linesToWrite.size(); i++) {
                output.write(linesToWrite.get(i));
                if (i != linesToWrite.size() - 1) {
                    output.write("\n");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (output != null) {
                try {
                    output.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
