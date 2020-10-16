//用户名 : Linda
//密码 : 123456
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoadGui {
    JFrame jFrame;
    JPanel jPanelFirst;
    JPanel jPanelSuccess;
    JPanel jPanelWarn;
    JPanel jPanelTry;
    JLabel jLabelTry;
    JTextField jTextFieldSignIn;
    JPasswordField jPasswordFieldPass;
    JButton jButtonSignIn;
    static int i = 0;

    LoadGui(){
        jFrame = new JFrame("登录");
        jPanelFirst = new JPanel();
        jPanelSuccess = new JPanel();
        jPanelWarn = new JPanel();
        jPanelTry = new JPanel();
        jLabelTry = new JLabel();
        jTextFieldSignIn = new JTextField();
        jPasswordFieldPass = new JPasswordField();
        jButtonSignIn = new JButton("登录");

        jPanelFirst.setLayout(new GridLayout(4,3));
        jPanelWarn.add(new JLabel("输入账户名或密码错误"));
        jPanelFirst.add(jPanelWarn);
        jPanelSuccess.add(new JLabel("登录成功!"));
        jPanelFirst.add(jPanelSuccess);
        jPanelTry.add(jLabelTry);
        jPanelFirst.add(jPanelTry);
        jPanelFirst.add(new JLabel("用户名"));
        jPanelFirst.add(new JPanel());
        jPanelFirst.add(jTextFieldSignIn);
        jPanelFirst.add(new JLabel("密码"));
        jPanelFirst.add(new JPanel());
        jPanelFirst.add(jPasswordFieldPass);
        jPanelFirst.add(new JPanel());
        jPanelFirst.add(jButtonSignIn);
        jPanelFirst.add(new JPanel());

        jPanelWarn.setVisible(false);
        jPanelSuccess.setVisible(false);
        jPanelTry.setVisible(false);

        jFrame.add("First",jPanelFirst);
        jFrame.setDefaultCloseOperation(3);
        jFrame.setSize(400,150);
        jFrame.setResizable(false);
        jFrame.setLocationByPlatform(true);
        jFrame.setVisible(true);

        jButtonSignIn.addActionListener(new actionPerformed());
    }

    class actionPerformed implements ActionListener{
        String Name;
        String PassWord;
        @Override
        public void actionPerformed(ActionEvent e) {
            Name = jTextFieldSignIn.getText();
            PassWord = String.valueOf(jPasswordFieldPass.getPassword());
            if(Name.equals("Linda") && PassWord.equals("123456")){
                jPanelSuccess.setVisible(true);
                jPanelWarn.setVisible(false);
                jPanelWarn.setVisible(false);
                jPanelTry.setVisible(false);
            }
            else {
                i++;
                if(i == 3){
                    System.exit(0);
                }
                jPanelWarn.setVisible(true);
                jLabelTry.setText("你还可以尝试" + (3 - i) + "次");
                jPanelTry.setVisible(true);
            }
        }
    }
    public static void main(String [] args){
        new LoadGui();
    }
}