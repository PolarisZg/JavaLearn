import javax.swing.*;
import java.time.LocalTime;

public class TimeWatch {
    JFrame jFrame;
    JLabel jLabelWatch;
    TimeWatch(){
        jFrame = new JFrame("Watch");
        jLabelWatch = new JLabel();
        Thread thread = new Thread(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            while (true) {
                LocalTime localTime = LocalTime.now();
                jLabelWatch.setText(localTime.getHour() + " " + localTime.getMinute() + " " + localTime.getSecond());
            }
        });
        thread.start();
        jFrame.add(jLabelWatch);
        jFrame.setSize(200,200);
        jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        jFrame.setVisible(true);
    }
    public static void main(String [] args){
        new TimeWatch();
    }
}
