import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.*;
import java.util.Random;

public class BoilerData {
    public static void main(String [] args){
        new BoilerDataGui();
    }
}
class BoilerDataGui{
    JFrame jFrame;
    JPanel jPanelButtons;
    JButton jButtonStart;
    JButton jButtonEnd;
    JTable jTable;
    DefaultTableModel defaultTableModel;
    File file;
    BufferedReader bufferedReader;
    BufferedWriter bufferedWriter;
    Boolean aBooleanWork = true;
    BoilerDataGui(){
        jFrame = new JFrame("锅炉数据");
        jFrame.setLayout(new BorderLayout());
        (jPanelButtons = new JPanel()).setLayout(new GridLayout(1,2));
        (jButtonStart = new JButton("启动锅炉")).addActionListener(e -> {
            try {
                bufferedReader = new BufferedReader(new FileReader(file));
                bufferedWriter = new BufferedWriter(new FileWriter(file,true));
            } catch (IOException i) {
                i.printStackTrace();
            }
            aBooleanWork = true;
            (new Thread(() -> {
                while(aBooleanWork) {
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException i) {
                        i.printStackTrace();
                    }
                    Write();
                }
            })).start();
            (new Thread(() -> {
                while (aBooleanWork) {
                    try {
                        Thread.sleep(3000);
                    } catch (InterruptedException i) {
                        i.printStackTrace();
                    }
                    Read();
                }
            })).start();
        });
        (jButtonEnd = new JButton("停止锅炉工作")).addActionListener(e -> aBooleanWork = false);
        jPanelButtons.add(jButtonStart);
        jPanelButtons.add(jButtonEnd);
        jFrame.add(jPanelButtons,BorderLayout.NORTH);
        defaultTableModel = new DefaultTableModel(new String[]{"A", "B", "C", "D", "E"},0);
        jTable = new JTable(defaultTableModel);
        jTable.getTableHeader().setReorderingAllowed(false);
        jFrame.add(new JScrollPane(jTable),BorderLayout.CENTER);

        jFrame.setSize(1000,500);
        jFrame.setVisible(true);
        jFrame.setDefaultCloseOperation(3);

        file = new File("data.txt");
        try {
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    synchronized void Write(){
        Random r = new Random();
        String s = "压力 : " + Math.abs( ( r.nextInt() % 100 ) + ( r.nextInt() % 100 ) * 0.01 ) + " 温度 : " + Math.abs( ( r.nextInt() % 100 ) + ( r.nextInt() % 100 ) * 0.01 ) + "#"
                + "压力 : " + Math.abs( ( r.nextInt() % 100 ) + ( r.nextInt() % 100 ) * 0.01 ) + " 温度 : " + Math.abs( ( r.nextInt() % 100 ) + ( r.nextInt() % 100 ) * 0.01 ) + "#"
                + "压力 : " + Math.abs( ( r.nextInt() % 100 ) + ( r.nextInt() % 100 ) * 0.01 ) + " 温度 : " + Math.abs( ( r.nextInt() % 100 ) + ( r.nextInt() % 100 ) * 0.01 ) + "#"
                + "压力 : " + Math.abs( ( r.nextInt() % 100 ) + ( r.nextInt() % 100 ) * 0.01 ) + " 温度 : " + Math.abs( ( r.nextInt() % 100 ) + ( r.nextInt() % 100 ) * 0.01 ) + "#"
                + "压力 : " + Math.abs( ( r.nextInt() % 100 ) + ( r.nextInt() % 100 ) * 0.01 ) + " 温度 : " + Math.abs( ( r.nextInt() % 100 ) + ( r.nextInt() % 100 ) * 0.01 ) + "#";
        try {
            bufferedWriter.write(s);
            bufferedWriter.newLine();
            bufferedWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    synchronized void Read(){
        try {
            String[] data = (bufferedReader.readLine()).split("#");
            defaultTableModel.addRow(data);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}