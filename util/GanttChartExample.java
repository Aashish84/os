package util;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class GanttChartExample extends JFrame {

    private final List<Task> tasks;

    public GanttChartExample(String input) {

        String[] taskData = input.split(",");
        // System.out.println(Arrays.toString(taskData));
        tasks = new ArrayList<>();

        for (int i = 0; i < taskData.length; i = i + 2) {
            if (i >= taskData.length - 1)
                break;
            // System.out.println(i);
            int start = Integer.parseInt(taskData[i]);
            String name = taskData[i + 1];
            int duration = Integer.parseInt(taskData[i + 2]);
            // System.out.println(start+name+duration);
            tasks.add(new Task(name, start, duration));
        }
        setTitle("Gantt Chart Example");
        setSize(800, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        add(new GanttChartPanel(), BorderLayout.CENTER);
    }

    class Task {
        String name;
        int start;
        int duration;

        public Task(String name, int start, int duration) {
            this.name = name;
            this.start = start;
            this.duration = duration;
        }
    }

    class GanttChartPanel extends JPanel {

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);

            int xOffset = 50;
            int yOffset = 50;
            int barHeight = 30;

            boolean f = true;
            for (Task task : tasks) {
                int x, y, width;
                    x = xOffset + task.start * 20;
                    y = yOffset;
                    width = task.duration * 20;
                if (f) {
                    g.setColor(Color.BLUE);
                    f = false;
                } else {
                    g.setColor(Color.RED);
                    f = true;
                }
                g.fillRect(x, y, width, barHeight);

                g.setColor(Color.BLACK);
                g.drawString(task.name, x + 3, y + barHeight + 10);
            }
        }
    }

    // public static void main(String[] args) {
    // SwingUtilities.invokeLater(() -> {
    // GanttChartExample example = new GanttChartExample();
    // example.setVisible(true);
    // });
    // }
}
