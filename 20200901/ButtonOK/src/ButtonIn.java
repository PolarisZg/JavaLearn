import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ButtonIn {
    JFrame jFrame;
    JButton jButtonOK;
    JButton jButtonCancel;

    ButtonIn(){
        jFrame = new JFrame("请单击按钮");
        jButtonOK = new JButton("确定");
        jButtonCancel = new JButton("取消");

        jFrame.setLayout(new FlowLayout());
        jFrame.add(jButtonOK);
        jFrame.add(jButtonCancel);
        jFrame.setSize(400,200);
        jFrame.setDefaultCloseOperation(3);
        jFrame.setVisible(true);

        ButtonInAction buttonInActionOK = new ButtonInAction("Ok");
        ButtonInAction buttonInActionCancel = new ButtonInAction("Cancel");
        jButtonOK.addActionListener(buttonInActionOK);
        jButtonCancel.addActionListener(buttonInActionCancel);
    }

    class ButtonInAction implements ActionListener{
        String string;
        ButtonInAction(String s){
            string = s;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if(string == "Ok" ){
                jFrame.setTitle("你单击了确定按钮");
                jButtonOK.setText("OK");
            }
            if(string == "Cancel" ){
                jFrame.setTitle("你单击了取消按钮");
                jButtonCancel.setText("Cancel");
            }
        }
    }

    public static void main(String [] args){
        new ButtonIn();
    }
}
