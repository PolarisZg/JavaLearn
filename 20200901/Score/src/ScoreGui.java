import javax.swing.*;
import java.awt.*;

public class ScoreGui {
    JFrame jFrame;
    JLabel jLabelMath;
    JLabel jLabelChinese;
    JLabel jLabelSum;
    JTextField jTextFieldMath;
    JTextField jTextFieldChinese;
    JTextField jTextFieldSum;
    JButton jButtonSum;

    ScoreGui(){
        jFrame = new JFrame("计算总分");
        jFrame.setLayout(new FlowLayout());
        jLabelMath = new JLabel("数学");
        jLabelChinese = new JLabel("语文");
        jLabelSum = new JLabel("总分");
        jTextFieldMath = new JTextField(4);
        jTextFieldChinese = new JTextField(4);
        jTextFieldSum = new JTextField(4);
        jButtonSum = new JButton("总分");


    }
}
