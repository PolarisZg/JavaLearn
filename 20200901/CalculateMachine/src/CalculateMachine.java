import java.awt.*;
import javax.swing.*;
class CalculateMachineDemo{
    JFrame jf;
    Dimension ScreenSize = Toolkit.getDefaultToolkit().getScreenSize();
    int WindowSizeWidth = ((int)ScreenSize.width) / 4;
    int WindowSizeHeight = 3 * ((int)ScreenSize.height) / 4;
    public CalculateMachineDemo(){
        jf = new JFrame("Calculate Machine");
        jf.setSize(WindowSizeWidth,WindowSizeHeight);
        jf.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        jf.setLayout(new BorderLayout(2,2));
        JPanel jTop = new JPanel();
        JPanel jBottom = new JPanel();
    }
}
public class CalculateMachine {
}
