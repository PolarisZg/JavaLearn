import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ButtonThis implements ActionListener {
    JFrame jFrame;
    JButton jButtonOK;
    JButton jButtonCancel;

    ButtonThis(){
        jFrame = new JFrame("请点击");
        jButtonOK = new JButton("确定");
        jButtonCancel = new JButton("取消");

        jFrame.setLayout(new FlowLayout());
        jFrame.add(jButtonOK);
        jFrame.add(jButtonCancel);
        jFrame.setDefaultCloseOperation(3);
        jFrame.setSize(400,200);
        jFrame.setVisible(true);

        jButtonOK.addActionListener(this);
        jButtonCancel.addActionListener(this);
    }

    public static void main(String [] args){
        new ButtonThis();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand() == "确定" || e.getActionCommand() == "OK"){
            jButtonOK.setText("OK");
            jFrame.setTitle("你点击了确定按钮");
        }
        if(e.getActionCommand() == "取消" || e.getActionCommand() == "Cancel"){
            jButtonCancel.setText("Cancel");
            jFrame.setTitle("你点击了取消按钮");
        }
    }
}