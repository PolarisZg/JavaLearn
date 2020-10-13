import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ButtonAlone {
    JFrame jFrame;
    JButton jButtonOK;
    JButton jButtonCancel;

    ButtonAlone(){
        jFrame = new JFrame("请点击按钮");
        jButtonOK = new JButton("确定");
        jButtonCancel = new JButton("取消");

        jFrame.setLayout(new FlowLayout());
        jFrame.add(jButtonOK);
        jFrame.add(jButtonCancel);
        jFrame.setSize(400,200);
        jFrame.setDefaultCloseOperation(3);
        jFrame.setVisible(true);

        jButtonOK.addActionListener(new ButtonEvent(this));
        jButtonCancel.addActionListener((new ButtonEvent(this)));
    }

    public static void main(String [] args){
        new ButtonAlone();
    }
}

class ButtonEvent implements ActionListener{
    ButtonAlone buttonAlone;
    ButtonEvent(ButtonAlone b){
        buttonAlone = b;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand() == "OK" || e.getActionCommand() == "确定") {
            buttonAlone.jFrame.setTitle("你单击了确定按钮");
            buttonAlone.jButtonOK.setText("OK");
        }
        if(e.getActionCommand() == "Cancel" || e.getActionCommand() == "取消"){
            buttonAlone.jFrame.setTitle("你单击了取消按钮");
            buttonAlone.jButtonCancel.setText("Cancel");
        }
    }
}