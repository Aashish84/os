import config.FilePath;
import util.ReadFile;
import util.Util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.PriorityQueue;

public class SJFMain {
    public static void main(String[] args) {
        ReadFile readFile = new ReadFile(FilePath.PATH);
        Util util = new Util();
        util.sortByArrivalTime(readFile.getFileList());
        util.displayFileList(readFile.getFileList());
        calculate(readFile.getFileList());
    }
    public static void calculate(ArrayList<int []> fileList){
        if(fileList.isEmpty()){
            System.out.println("empty fileList");
            return;
        }

        PriorityQueue<int []> pq = new PriorityQueue<>((int[] i1 , int[] i2) -> Integer.compare(i1[2] , i2[2]));
        StringBuilder ganttChart = new StringBuilder();
//        for waiting time
        HashMap<String, Integer> waitingTime = new HashMap<>();
        HashMap<String , Integer> lastTime = new HashMap<>();
        int prev = -1;
        int currentClockTime = 0;

        pq.add(fileList.get(0));

        while (!pq.isEmpty()){
            int[] arr = pq.poll();
            ganttChart.append(currentClockTime);
            if(currentClockTime < arr[1]){
                currentClockTime = arr[1];
                ganttChart.append(",").append(currentClockTime);
            }

//            waiting time calculation
            if(prev != arr[0] && prev!= 1){
                lastTime.put("p"+prev , currentClockTime);
            }
            if(!waitingTime.containsKey("p"+arr[0])){
                waitingTime.put("p"+arr[0] , (currentClockTime - arr[1]));
            }else if(lastTime.containsKey("p"+arr[0])){
                waitingTime.put("p"+arr[0] , waitingTime.get("p"+arr[0]) + (currentClockTime - lastTime.get("p"+arr[0])));
                lastTime.remove("p"+arr[0]);
            }

            prev = arr[0];
            ganttChart.append(",p").append(arr[0]).append(",");
            arr[2]--;
            currentClockTime++;

            if(arr[2] > 0)
                pq.add(arr);
            pq.addAll( findByArrivalTime(currentClockTime, fileList));
        }

        ganttChart.append(currentClockTime);
        new Util().print(ganttChart,waitingTime);
    }

    public static ArrayList<int []> findByArrivalTime(int id , ArrayList<int []> fileList){
        ArrayList<int []> arrList= new ArrayList<>();
        for(int []arr : fileList){
            if(arr[1] == id){
                arrList.add(arr);
            }
        }
        return arrList;
    }
}
