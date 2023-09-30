package util;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class ReadFile {
    private final ArrayList<int []> fileList;
    public ReadFile (String path) {
        fileList = new ArrayList<>();
        readFile(path);
    }
    public void readFile(String path){
        System.out.println("file reading .. ");
        File myFile = new File(path);
        try {
            Scanner myReader = new Scanner(myFile);
            myReader.nextLine();
            while (myReader.hasNextLine()){
                String data = myReader.nextLine();
                String []split = data.split(",");
                fileList.add(new int[] {stringToInt(split[0]) ,stringToInt(split[1]) , stringToInt(split[2])});
            }
            myReader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("file reading complete ...");
    }
    public ArrayList<int[]> getFileList() {
        return fileList;
    }
    public int stringToInt(String str){
        return Integer.parseInt(str);
    }
}

