import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.*;
public class JPanelDemo {
    public static void main(String [] args){
        JFrame jf = new JFrame("Second GUI Program Of Java");
        jf.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        jf.setBounds(300,100,400,200);
        JPanel jp = new JPanel();
        JLabel j1 = new JLabel("The Tag on JPanel");
        jp.setBackground(Color.orange);
        jp.add(j1);
        jf.add(jp);
        jf.setVisible(true);
    }
}
