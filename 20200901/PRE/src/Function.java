import javax.swing.*;
import java.awt.*;

class FunctionGUI{
    JFrame jf;
    JPanel function;
    JPanel functionLeft;
    JPanel functionRight;
    FunctionGUI(){
        jf = new JFrame();
        function = new JPanel();
        function.setLayout(new BorderLayout());
        functionLeft = new JPanel();
        functionRight = new JPanel();
        functionRight.setLayout(new GridLayout(1,3,2,2));
        functionRight.add(new JButton("Backspace"));
        functionRight.add(new JButton("CE"));
        functionRight.add(new JButton("C"));
        function.add(functionLeft,BorderLayout.WEST);
        function.add(functionRight,BorderLayout.CENTER);
        jf.add(function);
        jf.setSize(600,100);
        jf.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        jf.setVisible(true);
    }
}
public class Function {
    public static void main(String [] args){
        new FunctionGUI();
    }
}
