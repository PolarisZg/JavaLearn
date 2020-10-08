import javax.swing.*;
import java.awt.*;

public class JFrameDemo extends JFrame {
    public JFrameDemo(){
        setTitle("My Java First Program");
        setSize(400,200);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        JLabel j1 = new JLabel("This Window Uses JFrame");
        Container c = getContentPane();
        c.add(j1);
        setVisible(true);
    }

    public static void main(String [] args){
        new JFrameDemo();
    }
}
