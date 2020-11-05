/*******************************************
 将学生信息存入文件
 使用了图形界面，但是并没有去考虑某个对话框如果没有数据录入时的情况
 第一个是录入学生信息，需要用户自行在输入框中输入各种信息，而且年龄和成绩只能输入数字（输入其他符号无反应）
 输入完一个学生信息点击“将该学生的信息写入文件的位置”按钮会弹出对话框选择保存的位置（只允许选择目录）学生信息会追加在该目录下的StudentInformation.txt文件中
 再次点击该按钮，不会再选择保存位置
 第二个显示一个txt文件的信息，没有去考虑txt文件中录入的不是学生信息这种情况
 由于不知道何时文件读取完毕，因此使用死循环读取文件，但会在读取文件结束时通过抛出异常来结束循环
 ******************************************/

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.*;

public class StudentInformationObjectStream {
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
    Boolean booleanFirst;
    String Location = null;

    StudentInformationObjectStream() {
        jFrame = new JFrame("学生信息");
        jFrame.setLayout(new BorderLayout());
        (jButtonInput = new JButton("向文件中写数据")).addActionListener(e -> cardLayout.show(jPanelWorkSpace, "Input"));
        (jButtonOutput = new JButton("从文件中读取数据")).addActionListener(e -> {
            cardLayout.show(jPanelWorkSpace, "Output");
            JFileChooser jFileChooser = new JFileChooser();
            if (jFileChooser.showOpenDialog(jFrame) == JFileChooser.APPROVE_OPTION) {
                file = jFileChooser.getSelectedFile();
                Student.studentRead(file,jTextAreaStudent);
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
                    booleanFirst = false;
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
        new StudentInformationObjectStream();
    }
}

class Student implements Serializable {
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
            File file = new File(Location + "/StudentInformation" + ".txt");
            if(file.length() < 1) {
                ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(file, true));
                objectOutputStream.writeObject(this);
                objectOutputStream.close();
            }
            else{
                MyObjectOutputStream myObjectOutputStream = new MyObjectOutputStream(new FileOutputStream(file, true));
                myObjectOutputStream.writeObject(this);
                myObjectOutputStream.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static void studentRead(File file,JTextArea jTextArea) {
        try {
            ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(file));
            while (true) {
                Student student = (Student) objectInputStream.readObject();
                jTextArea.append(student.Name + " " + student.Collage + " " + student.Year + " " + student.Grade + System.lineSeparator());
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    static class MyObjectOutputStream  extends ObjectOutputStream{

        public MyObjectOutputStream(OutputStream out) throws IOException {
            super(out);
        }

        public void writeStreamHeader() {
        }
    }
}