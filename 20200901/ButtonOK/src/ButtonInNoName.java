import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ButtonInNoName {
    JFrame jFrame;
    JButton jButtonOK;
    JButton jButtonCancel;

    ButtonInNoName(){
        jFrame = new JFrame("请单击按钮");
        jButtonOK = new JButton("确定");
        jButtonCancel = new JButton("取消");

        jFrame.setLayout(new FlowLayout());
        jFrame.add(jButtonOK);
        jFrame.add(jButtonCancel);
        jFrame.setSize(400,200);
        jFrame.setDefaultCloseOperation(3);
        jFrame.setVisible(true);

        ActionListener buttonInAction = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(e.getActionCommand() == "确定" || e.getActionCommand() == "OK"){
                    jFrame.setTitle("你单击了确定按钮");
                    jButtonOK.setText("OK");
                }
                if(e.getActionCommand() == "Cancel" || e.getActionCommand() == "取消"){
                    jFrame.setTitle("你单击了取消按钮");
                    jButtonCancel.setText("Cancel");
                }
            }
        };
        jButtonOK.addActionListener(buttonInAction);
        jButtonCancel.addActionListener(buttonInAction);

    }

    public static void main(String [] args){
        new ButtonIn();
    }
}