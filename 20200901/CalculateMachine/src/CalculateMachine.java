import java.awt.*;
import javax.swing.*;
class CalculateMachineDemo{
    CalculateMachineDemo(){
        JFrame jf = new JFrame("CalculateMachine");
        GridBagLayout gridBagLayout = new GridBagLayout();
        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        jf.setLayout(gridBagLayout);
        gridBagConstraints.fill = GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.0;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.weighty = 0.2;

        gridBagConstraints.gridwidth = GridBagConstraints.REMAINDER;
        JTextField jTextField = new JTextField();
        gridBagLayout.setConstraints(jTextField,gridBagConstraints);
        jf.add(jTextField);

        gridBagConstraints.gridheight = 1;
        gridBagConstraints.gridwidth = GridBagConstraints.REMAINDER;
        JPanel function;
        JPanel functionLeft;
        JPanel functionRight;
        function = new JPanel();
        function.setLayout(new BorderLayout());
        functionRight = new JPanel();
        functionLeft = new JPanel();
        functionRight.setBorder(BorderFactory.createTitledBorder("Function"));
        functionRight.setLayout(new GridLayout(1,3,2,2));
        functionRight.add(new JButton("Backspace"));
        functionRight.add(new JButton("CE"));
        functionRight.add(new JButton("C"));
        function.add(functionLeft,BorderLayout.WEST);
        function.add(functionRight,BorderLayout.CENTER);
        gridBagLayout.setConstraints(function,gridBagConstraints);
        jf.add(function);

//        gridBagConstraints.gridwidth = 6;
        gridBagConstraints.gridheight = 4;
        gridBagConstraints.gridwidth = GridBagConstraints.REMAINDER;
        JPanel number;
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
        gridBagLayout.setConstraints(number,gridBagConstraints);
        jf.add(number);

        jf.setSize(600,200);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jf.setVisible(true);
    }
}
public class CalculateMachine {
    public static void main(String [] args){
        new CalculateMachineDemo();
    }
}
