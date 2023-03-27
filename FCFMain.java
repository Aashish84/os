import config.FilePath;
import util.ReadFile;
import util.Util;

import java.util.ArrayList;
import java.util.HashMap;

public class FCFMain {
//    arr[0] = id
//    arr[1] = arrival time
//    arr[2] = cpu burst time
    public static void main(String[] args) {
        ReadFile readFile = new ReadFile(FilePath.PATH);
        Util util = new Util();
        util.sortByArrivalTime(readFile.getFileList());
        util.displayFileList(readFile.getFileList());
        new FCFMain().calculate(readFile.getFileList());
    }
    public void calculate(ArrayList<int[]> fileList) {
        int currentClockTime = 0;
        HashMap<String, Integer> waitingTime = new HashMap<>();
        StringBuilder ganttChart = new StringBuilder();
        for (int[] ints : fileList) {
            if(currentClockTime < ints[1]){
                currentClockTime=ints[1];
            }
            ganttChart.append(currentClockTime);
            ganttChart.append(",p").append(ints[0]).append(",");
            waitingTime.put("p" + ints[0], currentClockTime - ints[1]);
            currentClockTime += ints[2];
        }
//        print gantt chart
        System.out.println("==================");
        ganttChart.append(currentClockTime);
        new Util().print(ganttChart,waitingTime);
    }
}

