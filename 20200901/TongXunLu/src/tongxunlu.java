import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class tongxunlu {
    public static void main(String[] args) {
        new BorderLayoutDemo("通讯录");
    }
}

class BorderLayoutDemo {
    JFrame frame;
    JPanel panel1, panel2, panel3;
    JTextArea text;
    JTextField text1, text2, text3, text4;
    JLabel label1, label2, label3, label4;
    JScrollPane scrollPane;
    JButton button1, button2;

    public BorderLayoutDemo(String title) {
        frame = new JFrame(title);
        frame.setSize(400, 300);
        Container container = frame.getContentPane();
        container.setLayout(new BorderLayout(2, 2));
        panel1 = new JPanel(new BorderLayout());
        panel2 = new JPanel(new FlowLayout());
        panel3 = new JPanel(new FlowLayout());
        label1 = new JLabel("姓名");
        label2 = new JLabel("性别");
        label3 = new JLabel("电话");
        label4 = new JLabel("所在地");
        text1 = new JTextField(15);
        text2 = new JTextField(15);
        text3 = new JTextField(15);
        text4 = new JTextField(15);
        text = new JTextArea(5, 50);
        button1 = new JButton("添加");
        button2 = new JButton("清空");
        scrollPane = new JScrollPane(text);
        panel1.setSize(400, 250);
        panel2.setSize(400, 50);
        panel3.setSize(200, 250);
        frame.add(BorderLayout.CENTER, panel1);
        frame.add(BorderLayout.SOUTH, panel2);
        panel1.add(BorderLayout.EAST, scrollPane);
        panel1.add(BorderLayout.CENTER, panel3);
        panel3.add(label1);
        panel3.add(text1);
        panel3.add(label2);
        panel3.add(text2);
        panel3.add(label3);
        panel3.add(text3);
        panel3.add(label4);
        panel3.add(text4);
        panel2.add(button1);
        panel2.add(button2);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }
}
