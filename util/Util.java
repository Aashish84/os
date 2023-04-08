package util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class Util {
    public void sortByArrivalTime(ArrayList<int[]> fileList) {
        for (int i = 0; i < fileList.size() - 1; i++) {
            for (int j = 0; j < fileList.size() - i - 1; j++) {
                int[] arr1 = fileList.get(j);
                int[] arr2 = fileList.get(j + 1);

                if (arr1[1] > arr2[1]) {
                    Collections.swap(fileList, j, j + 1);
                }
            }
        }
    }
    public void displayFileList(ArrayList<int[]> fileList) {
        System.out.println("printing all file content ... ");
        System.out.println("id,Arrival time,CPU Burst time");
        for (int[] arr : fileList) {
            System.out.println(arr[0] + "," + arr[1] + "," + arr[2]);
        }
    }

    public void print(StringBuilder ganttChart , HashMap<String, Integer> waitingTime){
        System.out.println("==================");
        System.out.println("Gant chart : ");
        System.out.println(ganttChart);
        //        waiting time and total time
        System.out.println("==================");
        System.out.println("waiting time");
        int totalTime = 0;
        for (Map.Entry<String, Integer> entry : waitingTime.entrySet()) {
            totalTime += entry.getValue();
            System.out.println(entry.getKey() + "=" + entry.getValue());
        }
        double avg = (double) totalTime / waitingTime.size();
        System.out.println("average wait time : " + avg);
    }
}

