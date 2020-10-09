import javax.print.attribute.standard.NumberUp;
import javax.swing.*;
import java.awt.*;

class NumberGUI{
    JFrame jf;
    JPanel number;
    NumberGUI(){
        jf = new JFrame();
        number = new JPanel();
        number.setLayout(new GridLayout(4,6,2,2));
        number.add(new JButton("MC"));
        number.add(new JButton("7"));
        number.add(new JButton("8"));
        number.add(new JButton("9"));
        number.add(new JButton("/"));
        number.add(new JButton("sqrt"));
        number.add(new JButton("MR"));
        number.add(new JButton("4"));
        number.add(new JButton("5"));
        number.add(new JButton("6"));
        number.add(new JButton("*"));
        number.add(new JButton("%"));
        number.add(new JButton("M-"));
        number.add(new JButton("1"));
        number.add(new JButton("2"));
        number.add(new JButton("3"));
        number.add(new JButton("-"));
        number.add(new JButton("1/x"));
        number.add(new JButton("M+"));
        number.add(new JButton("0"));
        number.add(new JButton("+/-"));
        number.add(new JButton("."));
        number.add(new JButton("+"));
        number.add(new JButton("="));
        jf.add(number);
        jf.setDefaultCloseOperation(2);
        jf.setSize(600,400);
        jf.setVisible(true);
    }
}
public class Number {
    public static void main(String [] args){
        new NumberGUI();
    }
}
