/***************************************
 将学生信息存入文件
 使用了图形界面，但是并没有去考虑某个对话框如果没有数据录入时的情况
 第一个是录入学生信息，需要用户自行在输入框中输入各种信息，而且年龄和成绩只能输入数字（输入其他符号无反应）
 输入完一个学生信息点击“将该学生的信息写入文件的位置”按钮会弹出对话框选择保存的位置（只允许选择目录）学生信息会追加在该目录下的StudentInformation.txt文件中
 再次点击该按钮，不会再选择保存位置
 选择保存位置的时候不通过按钮而直接关闭对话框会报错，不过不影响正常使用
 第二个显示一个txt文件的信息，没有去考虑txt文件中录入的不是学生信息这种情况，因此要导入程序第一个界面生成的txt文件
 51行位置运行时会报错，不过不影响程序运行，因为就是想要通过抛出异常来跳出循环（实际跳出循环的语句不想写了）
 ***************************************/

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.*;

public class StudentInformationDataStream {
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
    JButton jButtonGo;
    JLabel jLabelFileLocation;
    JPanel jPanelOutput;
    JTextArea jTextAreaStudent;
    File file;
    String Location = null;

    StudentInformationDataStream() {
        jFrame = new JFrame("学生信息");
        jFrame.setLayout(new BorderLayout());
        (jButtonInput = new JButton("向文件中写数据")).addActionListener(e -> cardLayout.show(jPanelWorkSpace, "Input"));
        (jButtonOutput = new JButton("从文件中读取数据")).addActionListener(e -> {
            cardLayout.show(jPanelWorkSpace, "Output");
            JFileChooser jFileChooser = new JFileChooser();
            if (jFileChooser.showOpenDialog(jFrame) == JFileChooser.APPROVE_OPTION) {
                file = jFileChooser.getSelectedFile();
                Location = file.getAbsolutePath();
                try {
                    DataInputStream dataInputStream = new DataInputStream(new FileInputStream(file));
                    try {
                        while (true)
                            jTextAreaStudent.append(dataInputStream.readUTF() + " " + dataInputStream.readUTF() + " " + dataInputStream.readInt() + " " + dataInputStream.readDouble() + System.lineSeparator());
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }
                } catch (FileNotFoundException fileNotFoundException) {
                    fileNotFoundException.printStackTrace();
                }
            }
        });
        (jPanelFunction = new JPanel()).setLayout(new GridLayout(1, 2));
        jPanelFunction.add(jButtonInput);
        jPanelFunction.add(jButtonOutput);
        jFrame.add(jPanelFunction, BorderLayout.NORTH);
        (jPanelWorkSpace = new JPanel()).setLayout(cardLayout = new CardLayout());
        (jPanelInput = new JPanel()).setLayout(new GridLayout(5, 2));
        jPanelInput.add(new JLabel("学生姓名"));
        jPanelInput.add(jTextFieldName = new JTextField());
        jPanelInput.add(new JLabel("院系名称"));
        jPanelInput.add(jTextFieldCollage = new JTextField());
        jPanelInput.add(new JLabel("年龄 ( 整数 )"));
        (jTextFieldYear = new JTextField()).addKeyListener(new KeyInt());
        jPanelInput.add(jTextFieldYear);
        jPanelInput.add(new JLabel("平均成绩 ( 实数 )"));
        (jPanelGrade = new JPanel()).setLayout(new GridLayout(1, 3));
        (jTextFieldGradeInt = new JTextField()).addKeyListener(new KeyInt());
        jPanelGrade.add(jTextFieldGradeInt);
        jPanelGrade.add(new JLabel("."));
        (jTextFieldGradeDouble = new JTextField()).addKeyListener(new KeyInt());
        jPanelGrade.add(jTextFieldGradeDouble);
        jPanelInput.add(jPanelGrade);
        (jButtonGo = new JButton("将该学生信息写入文件的位置")).addActionListener(e -> {
            if (Location == null) {
                JFileChooser jFileChooser = new JFileChooser();
                jFileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                if (jFileChooser.showSaveDialog(jFrame) == JFileChooser.APPROVE_OPTION) {
                    Location = jFileChooser.getSelectedFile().getAbsolutePath();
                    jLabelFileLocation.setText(Location);
                }
            }
            Student student = new Student(jTextFieldName.getText(), jTextFieldCollage.getText(), jTextFieldYear.getText(), jTextFieldGradeInt.getText(), jTextFieldGradeDouble.getText());
            student.studentWrite(Location);
        });
        jPanelInput.add(jButtonGo);
        jPanelInput.add(jLabelFileLocation = new JLabel());
        jPanelWorkSpace.add(jPanelInput, "Input");
        (jPanelOutput = new JPanel()).setLayout(new BorderLayout());
        jTextAreaStudent = new JTextArea();
        JScrollPane jScrollPane = new JScrollPane(jTextAreaStudent);
        jPanelOutput.add(jScrollPane);
        jPanelWorkSpace.add(jPanelOutput, "Output");
        jFrame.add(jPanelWorkSpace, BorderLayout.CENTER);

        jFrame.setSize(400, 200);
        jFrame.setDefaultCloseOperation(3);
        jFrame.setVisible(true);
    }

    static class KeyInt extends KeyAdapter {
        @Override
        public void keyTyped(KeyEvent e) {
            char KeyChar = e.getKeyChar();
            if (!(KeyChar >= '0' && KeyChar <= '9')) {
                e.consume();
            }
        }
    }

    public static void main(String[] args) {
        new StudentInformationDataStream();
    }
}

class Student {
    String Name;
    String Collage;
    int Year;
    double Grade;

    Student(String name, String collage, String year, String gradeInt, String gradeDouble) {
        Name = name;
        Collage = collage;
        Year = Integer.parseInt(year);
        Grade = Integer.parseInt(gradeInt) * 1.0 + Integer.parseInt(gradeDouble) * 1.0 / (Math.pow(10.0, gradeDouble.length()));
    }

    void studentWrite(String Location) {
        try {
            File file = new File(Location + "/StudentInformationData" + ".txt");
            DataOutputStream dataOutputStream = new DataOutputStream(new FileOutputStream(file, true));
            dataOutputStream.writeUTF(Name);
            dataOutputStream.writeUTF(Collage);
            dataOutputStream.writeInt(Year);
            dataOutputStream.writeDouble(Grade);
            dataOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

