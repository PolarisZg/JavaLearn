import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

public class PracticeDialog {
    JFrame jFrame;
    JButton jButton1;
    JButton jButton2;
    JButton jButton3;

    PracticeDialog(){
        jFrame = new JFrame("对话框练习");
        jFrame.setLayout(new FlowLayout());
        jFrame.add(jButton1 = new JButton("第一个"));
        jFrame.add(jButton2 = new JButton("第二个"));
        jFrame.add(jButton3 = new JButton("第三个"));

        jFrame.setSize(400,100);
        jFrame.setDefaultCloseOperation(3);
        jFrame.setVisible(true);
        jButton1.addActionListener(new actionPerformed());
        jButton2.addActionListener(new actionPerformed());
        jButton3.addActionListener(new actionPerformed());
    }

    static class actionPerformed implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            if(Objects.equals(e.getActionCommand(), "第一个")){
                JDialog jDialog;
                jDialog = new JDialog();
                jDialog.setLayout(new GridLayout(2,1));
                jDialog.add(new JLabel("第一个对话框的标签"));
                jDialog.add(new JButton("确定"));
                jDialog.setSize(200,200);
                jDialog.setVisible(true);
            }
            if(Objects.equals(e.getActionCommand(), "第二个")){
                JOptionPane.showMessageDialog(
                        null,
                        "消息对话框のMessage",
                        "消息对话框のTitle",
                        JOptionPane.INFORMATION_MESSAGE
                );
            }
            if(Objects.equals(e.getActionCommand(), "第三个")){
                JOptionPane.showConfirmDialog(
                        null,
                        "确认对话框のMessage"
                );
            }
        }
    }

    public static void main(String [] args){
        new PracticeDialog();
    }
}
