import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.*;

public class StudentInformationBuffer {
}
class StudentInformationBufferGui{
    JFrame jFrame;
    JPanel jPanelFunction;
    JButton jButtonInput;
    JButton jButtonOutput;
    JPanel jPanelWorkSpace;
    CardLayout cardLayout;
    JPanel jPanelInput;
    JTextField jTextFieldName;
    JTextField jTextFieldCollage;
    JTextField jTextFieldYear;
    JPanel jPanelGrade;
    JTextField jTextFieldGradeInt;
    JTextField jTextFieldGradeDouble;
    JPanel jPanelOutput;
    JTextArea jTextAreaStudent;
    File file;
    String Location = null;
    StudentInformationBufferGui(){
        jFrame = new JFrame("学生信息");
        jFrame.setLayout(new BorderLayout());
        (jButtonInput = new JButton("向文件中写数据")).addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(jPanelWorkSpace,"Input");
                JFileChooser jFileChooser = new JFileChooser();
                if(jFileChooser.showSaveDialog(jFrame) == JFileChooser.APPROVE_OPTION){
                    file = jFileChooser.getSelectedFile();
                    Location = file.getAbsolutePath();
                }
            }
        });
        (jButtonOutput = new JButton("从文件中读取数据")).addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(jPanelWorkSpace,"Output");
                JFileChooser jFileChooser = new JFileChooser();
                if(jFileChooser.showOpenDialog(jFrame) == JFileChooser.APPROVE_OPTION){
                    file = jFileChooser.getSelectedFile();
                    Location = file.getAbsolutePath();
                    Reader reader = null;
                    try {
                        reader = new FileReader(file);
                    } catch (FileNotFoundException fileNotFoundException) {
                        fileNotFoundException.printStackTrace();
                    }
                    BufferedReader bufferedReader = new BufferedReader(reader);
                    String s;
                    while (true) {
                        try {
                            if (((s = bufferedReader.readLine())!=null)) {
                                jTextAreaStudent.append(s + "\r\n");
                            }
                        } catch (IOException ioException) {
                            ioException.printStackTrace();
                        }
                    }
                }
            }
        });
        (jPanelFunction = new JPanel()).setLayout(new GridLayout(1,2));
        jPanelFunction.add(jButtonInput);
        jPanelFunction.add(jButtonOutput);
        jFrame.add(jPanelFunction,BorderLayout.NORTH);
        (jPanelWorkSpace = new JPanel()).setLayout(cardLayout = new CardLayout());
        (jPanelInput = new JPanel()).setLayout(new GridLayout(4,2));
        jPanelInput.add(new JLabel("学生姓名"));
        jPanelInput.add(jTextFieldName = new JTextField());
        jPanelInput.add(new JLabel("院系名称"));
        jPanelInput.add(jTextFieldCollage = new JTextField());
        jPanelInput.add(new JLabel("年龄 ( 整数 )"));
        (jTextFieldYear = new JTextField()).addKeyListener(new KeyInt());
        jPanelInput.add(jTextFieldYear);
        jPanelInput.add(new JLabel("平均成绩 ( 实数 )"));
        (jPanelGrade = new JPanel()).setLayout(new GridLayout(1,3));
        (jTextFieldGradeInt = new JTextField()).addKeyListener(new KeyInt());
        jPanelGrade.add(jTextFieldGradeInt);
        jPanelGrade.add(new JLabel("."));
        (jTextFieldGradeDouble =new JTextField()).addKeyListener(new KeyInt());
        jPanelGrade.add(jTextFieldGradeDouble);
        jPanelInput.add(jPanelGrade);
        jPanelWorkSpace.add(jPanelInput,"Input");
        (jPanelOutput = new JPanel()).setLayout(new BorderLayout());
        jTextAreaStudent = new JTextArea();
        JScrollPane jScrollPane = new JScrollPane(jTextAreaStudent);
        jPanelOutput.add(jScrollPane);
        jPanelWorkSpace.add(jPanelOutput,"Output");
        jFrame.add(jPanelWorkSpace,BorderLayout.CENTER);

        jFrame.setSize(400,200);
        jFrame.setDefaultCloseOperation(3);
        jFrame.setVisible(true);
    }
    class KeyInt extends KeyAdapter{
        @Override
        public void keyTyped(KeyEvent e) {
            char KeyChar = e.getKeyChar();
            if(!(KeyChar >= '0' && KeyChar <= '9')){
                e.consume();
            }
        }
    }
    public static void main(String [] args){
        new StudentInformationBufferGui();
    }
}

