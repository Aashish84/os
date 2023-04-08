import config.FilePath;
import util.ReadFile;
import util.Util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Stack;

public class RoundRobin {
    public static void main(String[] args) {
        ReadFile readFile = new ReadFile(FilePath.ROUND_ROBIN_PATH);
        Util util = new Util();
        util.sortByArrivalTime(readFile.getFileList());
        util.displayFileList(readFile.getFileList());
        calculate(readFile.getFileList());
    }

    public static void calculate(ArrayList<int[]> fieldList) {
        if (fieldList.isEmpty()) {
            System.out.println("field list is empty");
            return;
        }

        HashMap<String, Integer> waitingTime = new HashMap<>();
        HashMap<String, Integer> lastTime = new HashMap<>();
        StringBuilder ganttChart = new StringBuilder();
        int currentClockTime = 0;
        int timeQuantam = 3;
        int prev = -1;

        while (!fieldList.isEmpty()) {
            int[] remove = fieldList.remove(0);
            ganttChart.append(currentClockTime).append(",").append("p").append(remove[0]).append(",");

//            waiting time calculation
            if(prev != remove[0] && prev!= -1)
                lastTime.put("p"+prev , currentClockTime);
            if(!waitingTime.containsKey("p"+remove[0]))
                waitingTime.put("p"+remove[0] , currentClockTime - remove[1]);
            if(lastTime.containsKey("p"+remove[0])){
                waitingTime.put("p"+remove[0] , waitingTime.get("p"+remove[0]) + (currentClockTime - lastTime.get("p"+remove[0])));
                lastTime.remove("p"+remove[0]);
            }

// round robin impl
            if (remove[2] <= timeQuantam) {
                currentClockTime += remove[2];
            } else {
                currentClockTime += timeQuantam;
                remove[2] -= timeQuantam;
                fieldList.add(remove);
            }
            prev = remove[0];
        }
        ganttChart.append(currentClockTime);
        new Util().print(ganttChart,waitingTime);
    }
}
