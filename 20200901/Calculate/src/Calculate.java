import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

public class Calculate {
    JFrame jFrame;
    JPanel jPanelScreen;
    JPanel jPanelButton;
    JButton jButton0;
    JButton jButton1;
    JButton jButton2;
    JButton jButton3;
    JButton jButton4;
    JButton jButton5;
    JButton jButton6;
    JButton jButton7;
    JButton jButton8;
    JButton jButton9;
    JButton jButtonLeft;
    JButton jButtonRight;
    JButton jButtonAC;
    JButton jButtonDot;
    JButton jButtonDEL;
    JButton jButtonPlus;
    JButton jButtonSub;
    JButton jButtonMul;
    JButton jButtonDvi;
    JButton jButtonEnter;
    JTextField jTextField;

    Calculate(){
        jFrame = new JFrame("Calculate Machine");
        jFrame.setLayout(new BorderLayout());
        jPanelScreen = new JPanel();
        jTextField = new JTextField(40);
        jPanelScreen.add(jTextField);
        jFrame.add(jPanelScreen,BorderLayout.NORTH);
        jPanelButton = new JPanel();
        jPanelButton.setLayout(new GridLayout(5,4));
        jPanelButton.add(jButtonLeft = new JButton("("));
        jPanelButton.add(jButtonRight = new JButton(")"));
        jPanelButton.add(jButtonDEL = new JButton("DEL"));
        jPanelButton.add(jButtonAC = new JButton("AC"));
        jPanelButton.add(jButton7 = new JButton("7"));
        jPanelButton.add(jButton8 = new JButton("8"));
        jPanelButton.add(jButton9 = new JButton("9"));
        jPanelButton.add(jButtonEnter = new JButton("="));
        jPanelButton.add(jButton4 = new JButton("4"));
        jPanelButton.add(jButton5 = new JButton("5"));
        jPanelButton.add(jButton6 = new JButton("6"));
        jPanelButton.add(jButtonPlus = new JButton("+"));
        jPanelButton.add(jButton1 = new JButton("1"));
        jPanelButton.add(jButton2 = new JButton("2"));
        jPanelButton.add(jButton3 = new JButton("3"));
        jPanelButton.add(jButtonSub = new JButton("-"));
        jPanelButton.add(jButton0 = new JButton("0"));
        jPanelButton.add(jButtonDot = new JButton("."));
        jPanelButton.add(jButtonMul = new JButton("*"));
        jPanelButton.add(jButtonDvi = new JButton("/"));
        jFrame.add(jPanelButton,BorderLayout.CENTER);

        jButton9.addActionListener(new actionPerformed());
        jButton8.addActionListener(new actionPerformed());
        jButton7.addActionListener(new actionPerformed());
        jButton6.addActionListener(new actionPerformed());
        jButton5.addActionListener(new actionPerformed());
        jButton4.addActionListener(new actionPerformed());
        jButton3.addActionListener(new actionPerformed());
        jButton2.addActionListener(new actionPerformed());
        jButton1.addActionListener(new actionPerformed());
        jButton0.addActionListener(new actionPerformed());
        jButtonPlus.addActionListener(new actionPerformed());
        jButtonSub.addActionListener(new actionPerformed());
        jButtonMul.addActionListener(new actionPerformed());
        jButtonDvi.addActionListener(new actionPerformed());
        jButtonEnter.addActionListener(new actionPerformed());
        jButtonDot.addActionListener(new actionPerformed());
        jButtonDEL.addActionListener(new actionPerformed());
        jButtonAC.addActionListener(new actionPerformed());
        jButtonLeft.addActionListener(new actionPerformed());
        jButtonRight.addActionListener(new actionPerformed());

        jFrame.setSize(500,300);
        jFrame.setDefaultCloseOperation(3);
        jFrame.setResizable(false);
        jFrame.setVisible(true);
    }

    class actionPerformed implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            if(Objects.equals(e.getActionCommand(), "9")){
                jTextField.setText(jTextField.getText() + "9");
            }
            else if(Objects.equals(e.getActionCommand(), "8")){
                jTextField.setText(jTextField.getText() + "8");
            }
            else if(Objects.equals(e.getActionCommand(), "7")){
                jTextField.setText(jTextField.getText() + "7");
            }
            else if(Objects.equals(e.getActionCommand(), "6")){
                jTextField.setText(jTextField.getText() + "6");
            }
            else if(Objects.equals(e.getActionCommand(), "5")){
                jTextField.setText(jTextField.getText() + "5");
            }
            else if(Objects.equals(e.getActionCommand(), "4")){
                jTextField.setText(jTextField.getText() + "4");
            }
            else if(Objects.equals(e.getActionCommand(), "3")){
                jTextField.setText(jTextField.getText() + "3");
            }
            else if(Objects.equals(e.getActionCommand(), "2")){
                jTextField.setText(jTextField.getText() + "2");
            }
            else if(Objects.equals(e.getActionCommand(), "1")){
                jTextField.setText(jTextField.getText() + "1");
            }
            else if(Objects.equals(e.getActionCommand(), "0")){
                jTextField.setText(jTextField.getText() + "0");
            }
            else if(Objects.equals(e.getActionCommand(), "+")){
                jTextField.setText(jTextField.getText() + "+");
            }
            else if(Objects.equals(e.getActionCommand(), "-")){
                jTextField.setText(jTextField.getText() + "-");
            }
            else if(Objects.equals(e.getActionCommand(), "*")){
                jTextField.setText(jTextField.getText() + "*");
            }
            else if(Objects.equals(e.getActionCommand(), "/")){
                jTextField.setText(jTextField.getText() + "/");
            }
            else if(Objects.equals(e.getActionCommand(), "(")){
                jTextField.setText(jTextField.getText() + "(");
            }
            else if(Objects.equals(e.getActionCommand(), ")")){
                jTextField.setText(jTextField.getText() + ")");
            }
            else if(Objects.equals(e.getActionCommand(), ".")){
                jTextField.setText(jTextField.getText() + ".");
            }
            else if(Objects.equals(e.getActionCommand(), "DEL")){
                jTextField.setText(jTextField.getText().substring(0,jTextField.getText().length() - 1));
            }
            else if(Objects.equals(e.getActionCommand(), "AC")){
                jTextField.setText(null);
            }
            else if(Objects.equals(e.getActionCommand(), "=")){
                try{
                    Double answer = Answer(jTextField.getText());
                    jTextField.setText(String.valueOf(answer));
                }
                catch (Exception E){
                    jTextField.setText("ERROR");
                }
            }
        }
    }

    public static void main(String [] args){
        new Calculate();
    }

    static Double Answer(String s) {
        try {
            return Integer.parseInt(s) * 1.0;
        } catch (NumberFormatException e) {
            int Sub = -1;
            int Plus = -1;
            int Dvi = -1;
            int Mul = -1;
            int Dot = -1;
            int n = 0;//记录括号
            char[] data = s.toCharArray();

            for (int i = 0; i < s.length(); i++) {
                if (data[i] == '(') {
                    n++;
                }
                if (data[i] == ')') {
                    n--;
                }
                if (n == 0) {
                    if (data[i] == '+') {
                        Plus = i;
                    }
                    if (data[i] == '-') {
                        Sub = i;
                    }
                    if (data[i] == '*') {
                        Mul = i;
                    }
                    if (data[i] == '/') {
                        Dvi = i;
                    }
                    if (data[i] == '.') {
                        Dot = i;
                    }
                }
            }
            if (Plus == -1 && Sub == -1 && Mul == -1 && Dvi == -1 && Dot == -1) {
                return Answer(s.substring(1, s.length() - 1));
            }
            else {
                if (Plus != -1) {
                    return Answer(s.substring(0, Plus)) + Answer(s.substring(Plus + 1));
                } else if (Sub != -1) {
                    return Answer(s.substring(0, Sub)) - Answer(s.substring(Sub + 1));
                } else if (Mul != -1) {
                    return Answer(s.substring(0, Mul)) * Answer(s.substring(Mul + 1));
                } else if (Dvi != -1) {
                    return Answer(s.substring(0, Dvi)) / Answer(s.substring(Dvi + 1));
                } else if (Dot != -1) {
                    return Answer(s.substring(0, Dot)) + Answer(s.substring(Dot + 1)) / (10 * (s.length() - Dot - 1));
                } else {
                    return Integer.parseInt(s) * 1.0;
                }
            }
        }
    }
}
