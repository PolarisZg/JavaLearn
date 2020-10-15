import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
        jFrame.setLayout(new GridLayout(4,3));
        jLabelMath = new JLabel("数学");
        jLabelChinese = new JLabel("语文");
        jLabelSum = new JLabel("总分");
        jTextFieldMath = new JTextField(4);
        jTextFieldChinese = new JTextField(4);
        jTextFieldSum = new JTextField(4);
        jButtonSum = new JButton("计算总分");

        jFrame.add(jLabelMath);
        jFrame.add(new JPanel());
        jFrame.add(jTextFieldMath);
        jFrame.add(jLabelChinese);
        jFrame.add(new JPanel());
        jFrame.add(jTextFieldChinese);
        jFrame.add(jLabelSum);
        jFrame.add(new JPanel());
        jFrame.add(jTextFieldSum);
        jFrame.add(new JPanel());
        jFrame.add(jButtonSum);

        jFrame.setSize(300,200);
        jFrame.setResizable(false);
        jFrame.setDefaultCloseOperation(3);
        jFrame.setVisible(true);
        jButtonSum.addActionListener(new actionPerformed());
    }

    class actionPerformed implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            int mathScore = 0;
            int chineseScore = 0;
            try {
                mathScore = Integer.valueOf(jTextFieldMath.getText());
            }
            catch (NumberFormatException n){
                mathScore = 0;
            }
            try {
                chineseScore = Integer.valueOf(jTextFieldChinese.getText());
            }
            catch(NumberFormatException n){
                chineseScore = 0;
            }
            String sumScore = Integer.toString(mathScore + chineseScore,10);
            //String sumScore = "40";
            jTextFieldSum.setText(sumScore);
        }
    }

    public static void main(String [] args){
        new ScoreGui();
    }
}
