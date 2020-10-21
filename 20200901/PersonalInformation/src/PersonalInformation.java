import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Objects;
import java.util.Vector;

class StudentInformation{
    String Name;
    String Num;
    String Sex;
    String Hobby;
    String Year;
    String PassWord;
    StudentInformation(String num,String name,String sex,String hobby,String year,String passWord){
        Name = name;
        Num = num;
        Sex = sex;
        Hobby = hobby;
        Year = year;
        PassWord = passWord;
    }

    public StudentInformation() {

    }
}
public class PersonalInformation {
    JFrame jFrame;
    JTextArea jTextArea;
    JScrollPane jScrollPane;                // 文本区滚动条
    JPanel jPanelScanner;
    JTextField jTextFieldStuNum;            // 学号
    Boolean aBooleanStuNum;
    JTextField jTextFieldName;              // 姓名
    Boolean aBooleanName;
    JPasswordField jPasswordField;          // 密码
    Boolean aBooleanPassword;
    JRadioButton jRadioButtonSexMale;       // 性别男
    JRadioButton jRadioButtonSexFemale;     // 性别女
    ButtonGroup buttonGroupSex;
    JPanel jPanelSex;                       // 性别
    Boolean aBooleanSex;
    JComboBox jComboBoxYear;                // 出生年份
    JCheckBox jCheckBoxHobby1;              // 兴趣爱好1
    JCheckBox jCheckBoxHobby2;              // 兴趣爱好2
    JCheckBox jCheckBoxHobby3;              // 兴趣爱好3
    JPanel jPanelHobby;                     // 兴趣爱好
    Boolean aBooleanHobby;
    JButton jButtonSingIn;                  // 注册按钮
    JButton jButtonDel;                     // 重置按钮
    JPanel jPanelButton;

    Vector<StudentInformation> Student;

    PersonalInformation(){
        jFrame = new JFrame("用户注册");
        jFrame.setLayout(new BorderLayout());
        jTextArea = new JTextArea(5,20);
        jScrollPane = new JScrollPane(jTextArea);
        jFrame.add(jScrollPane,BorderLayout.NORTH);
        jPanelScanner = new JPanel();
        jPanelScanner.setLayout(new GridLayout(6,2));
        jPanelScanner.add(new JLabel(" 学号 "));
        jPanelScanner.add(jTextFieldStuNum = new JTextField(15));
        jPanelScanner.add(new JLabel(" 姓名 "));
        jPanelScanner.add(jTextFieldName = new JTextField(15));
        jPanelScanner.add(new JLabel(" 密码 "));
        jPanelScanner.add(jPasswordField = new JPasswordField(15));
        jPanelSex = new JPanel();
        jPanelSex.setLayout(new GridLayout(1,2));
        jPanelSex.add(jRadioButtonSexMale = new JRadioButton(" 男 "));
        jPanelSex.add(jRadioButtonSexFemale = new JRadioButton(" 女 "));
        buttonGroupSex = new ButtonGroup();
        buttonGroupSex.add(jRadioButtonSexMale);
        buttonGroupSex.add(jRadioButtonSexFemale);
        jPanelScanner.add(new JLabel(" 性别 "));
        jPanelScanner.add(jPanelSex);
        jComboBoxYear = new JComboBox();
        for(int i = 0;i < 20;i++)
            jComboBoxYear.addItem(String.valueOf(2015 - i));
        jComboBoxYear.setEditable(false);
        jPanelScanner.add(new JLabel(" 出生年份 "));
        jPanelScanner.add(jComboBoxYear);
        jPanelHobby = new JPanel();
        jPanelHobby.setLayout(new GridLayout(1,3));
        jPanelHobby.add(jCheckBoxHobby1 = new JCheckBox(" 篮球 "));
        jPanelHobby.add(jCheckBoxHobby2 = new JCheckBox(" 棒球 "));
        jPanelHobby.add(jCheckBoxHobby3 = new JCheckBox(" 麻将 "));
        jPanelScanner.add(new JLabel(" 爱好 "));
        jPanelScanner.add(jPanelHobby);
        jFrame.add(jPanelScanner,BorderLayout.CENTER);
        jPanelButton = new JPanel();
        jPanelButton.setLayout(new GridLayout(1,2));
        jPanelButton.add(jButtonSingIn = new JButton(" 注册 "));
        jPanelButton.add(jButtonDel = new JButton(" 重置 "));
        jFrame.add(jPanelButton,BorderLayout.SOUTH);

        jFrame.setSize(600,400);
        jFrame.setVisible(true);
        jFrame.setResizable(false);
        jFrame.setDefaultCloseOperation(3);

        Student = new Vector<>();
        jButtonSingIn.addActionListener(new actionPerformed());
        jButtonDel.addActionListener(new actionPerformed());

    }

    class actionPerformed implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            if(Objects.equals(e.getActionCommand(), " 注册 ")){
                String Num = jTextFieldStuNum.getText();
                String Name = jTextFieldName.getText();
                String PassWord = String.valueOf(jPasswordField.getPassword());
                String Sex;
                if(jRadioButtonSexMale.isSelected()){
                    Sex = "男";
                }
                else{
                    Sex = "女";
                }
                String Year = (String) jComboBoxYear.getSelectedItem();
                StringBuffer s = new StringBuffer();
                String Hobby;
                if(jCheckBoxHobby1.isSelected())
                    s.append(jCheckBoxHobby1.getText());
                if(jCheckBoxHobby2.isSelected())
                    s.append(jCheckBoxHobby2.getText());
                if(jCheckBoxHobby3.isSelected())
                    s.append(jCheckBoxHobby3.getText());
                Hobby = new String(s);
                jTextArea.append("姓名:" + Name + " 学号:" + Num + " 密码:" + PassWord + " 性别:" + Sex + " 出生年份:" + Year + " 爱好:" + Hobby + "\r\n");
                DEL();
            }
            if(Objects.equals(e.getActionCommand(), " 重置 ")){
                DEL();
            }
        }
    }

    void DEL(){
        jTextFieldName.setText(null);
        aBooleanName = false;
        jTextFieldStuNum.setText(null);
        aBooleanStuNum = false;
        jPasswordField.setText(null);
        aBooleanPassword = false;
        jRadioButtonSexMale.setSelected(false);
        jRadioButtonSexFemale.setSelected(false);
        aBooleanSex = false;
        jComboBoxYear.setSelectedIndex(0);
        jCheckBoxHobby1.setSelected(false);
        jCheckBoxHobby2.setSelected(false);
        jCheckBoxHobby3.setSelected(false);
        aBooleanHobby = false;
    }

    public static void main(String [] args){
        new PersonalInformation();
    }
}
