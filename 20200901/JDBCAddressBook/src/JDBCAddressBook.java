import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.*;
import java.util.Objects;

public class JDBCAddressBook {
    public static void main(String [] args){
        new JDBCAddressBookGui();
    }
}
class JDBCSchema {
    Statement statement;
    Connection connection;

    JDBCSchema(String schemaName, String user, String password) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        String url = "jdbc:mysql://localhost:3306/" + schemaName + "?serverTimezone=Asia/Shanghai";
        connection = DriverManager.getConnection(url, user, password);
        statement = connection.createStatement();
    }
}

class JDBCAddressBookGui{
    JFrame jFrame;
    JDBCSchema jdbcSchema;
    JTable jTable;
    DefaultTableModel defaultTableModel;

    JDBCAddressBookGui(){
        jFrame = new JFrame("未连接数据库");
        jFrame.setLayout(new BorderLayout());
        {
            JTextField jTextFieldSchema;
            JTextField jTextFieldSchemaUser;
            JPasswordField jPasswordFieldSchema;
            JButton jButtonConnect;
            JPanel jPanelConnect;

            jPanelConnect = new JPanel();
            jPanelConnect.setLayout(new GridLayout(1, 7));
            jPanelConnect.add(new JLabel("数据库名"));
            jPanelConnect.add(jTextFieldSchema = new JTextField());
            jPanelConnect.add(new JLabel("数据库用户名"));
            jPanelConnect.add(jTextFieldSchemaUser = new JTextField());
            jPanelConnect.add(new JLabel("数据库密码"));
            jPanelConnect.add(jPasswordFieldSchema = new JPasswordField());
            jPanelConnect.add(jButtonConnect = new JButton("链接数据库"));
            jButtonConnect.addActionListener(e -> {
                defaultTableModel.setRowCount(0);
                String name = jTextFieldSchema.getText();
                String user = jTextFieldSchemaUser.getText();
                String password = String.valueOf(jPasswordFieldSchema.getPassword());
                try {
                    jdbcSchema = new JDBCSchema(name, user, password);
                } catch (ClassNotFoundException | SQLException classNotFoundException) {
                    JOptionPane.showMessageDialog(jFrame, "输入的数据库信息错误");
                    classNotFoundException.printStackTrace();
                }
                jFrame.setTitle("已连接数据库_" + name);
                try {
                    ResultSet resultSet = jdbcSchema.statement.executeQuery("SHOW TABLES");
                    boolean aBoolean = false;
                    while (resultSet.next()) {
                        if (Objects.equals(resultSet.getString(1), "addressbook")) {
                            aBoolean = true;
                        }
                    }
                    if (!aBoolean) {
                        JOptionPane.showMessageDialog(jFrame, "所选数据库中无 通讯录");
                    }
                } catch (SQLException throwable) {
                    throwable.printStackTrace();
                }
                try {
                    ResultSet resultSet = jdbcSchema.statement.executeQuery("SELECT * FROM `addressbook` ;");
                    while(resultSet.next()){
                        defaultTableModel.addRow(new String[]{
                                resultSet.getString(1),
                                resultSet.getString(2),
                                resultSet.getString(3),
                                resultSet.getString(4)});
                    }
                } catch (SQLException throwable) {
                    throwable.printStackTrace();
                }
            });
            jFrame.add(jPanelConnect,BorderLayout.NORTH);
        } 

        {
            JPanel jPanelAddressBook;
            JTextField jTextFieldName;
            JRadioButton jRadioButtonMale;
            JRadioButton jRadioButtonFeMale;
            JPanel jPanelSex;
            JTextField jTextFieldTeleNum;
            JComboBox<String> jComboBox;
            (jPanelAddressBook = new JPanel()).setLayout(new BorderLayout());
            {
                JPanel jPanelAdd;
                (jPanelAdd = new JPanel()).setLayout(new GridLayout(1, 8));
                jPanelAdd.add(new JLabel("姓名"));
                jPanelAdd.add(jTextFieldName = new JTextField());
                jPanelAdd.add(new JLabel("性别"));
                (jPanelSex = new JPanel()).setLayout(new GridLayout(1, 2));
                jPanelSex.add(jRadioButtonMale = new JRadioButton("男"));
                jPanelSex.add(jRadioButtonFeMale = new JRadioButton("女"));
                ButtonGroup buttonGroup = new ButtonGroup();
                buttonGroup.add(jRadioButtonMale);
                buttonGroup.add(jRadioButtonFeMale);
                jPanelAdd.add(jPanelSex);
                jPanelAdd.add(new JLabel("电话号码"));
                jPanelAdd.add(jTextFieldTeleNum = new JTextField());
                jTextFieldTeleNum.addKeyListener(new KeyAdapter() {
                    @Override
                    public void keyTyped(KeyEvent e) {
                        if (e.getKeyChar() < '0' || e.getKeyChar() > '9') {
                            e.consume();
                        }
                    }
                }); // 仅能输入数字
                jPanelAdd.add(new JLabel("关系"));
                jComboBox = new JComboBox<>();
                jComboBox.addItem("同学");
                jComboBox.addItem("朋友");
                jComboBox.addItem("家人");
                jPanelAdd.add(jComboBox);
                jPanelAddressBook.add(jPanelAdd, BorderLayout.NORTH);
            }

            {
                defaultTableModel = new DefaultTableModel(null, new String[]{"姓名", "性别", "电话号码", "类型"});
                jTable = new JTable(defaultTableModel);
                jPanelAddressBook.add(new JScrollPane(jTable));
            }

            {
                JButton jButtonAdd;
                JButton jButtonDel;
                JPanel jPanelButtons;
                (jPanelButtons = new JPanel()).setLayout(new GridLayout(1, 2));
                jPanelButtons.add(jButtonAdd = new JButton("添加"));
                jButtonAdd.addActionListener(e -> {
                    String Name = jTextFieldName.getText();
                    String Sex = "男";
                    if(jRadioButtonFeMale.isSelected()){
                        Sex = "女";
                    }
                    String TeleNum = jTextFieldTeleNum.getText();
                    String Relationship = (String) jComboBox.getSelectedItem();
                    if(Objects.equals(Name, "")){
                        JOptionPane.showMessageDialog(jFrame,"姓名字段不能为空 ! ");
                    }
                    else{
                        try {
                            //jdbcSchema.statement.execute("INSERT INTO `addressbook` VALUES ('" + Name + "' , '" + Sex + "' , '" + TeleNum + "' , '" + Relationship +"') ;");
                            String sql = "INSERT INTO `addressbook` VALUES (? , ? , ? , ?);";
                            PreparedStatement preparedStatement = jdbcSchema.connection.prepareStatement(sql);
                            preparedStatement.setString(1,Name);
                            preparedStatement.setString(2,Sex);
                            preparedStatement.setString(3,TeleNum);
                            preparedStatement.setString(4,Relationship);
                            preparedStatement.execute();
                            defaultTableModel.addRow(new String[]{Name, Sex, TeleNum, Relationship});
                        } catch (SQLException throwable) {
                            JOptionPane.showMessageDialog(jFrame,"姓名出现重复");
                            throwable.printStackTrace();
                        }
                    }
                });
                jPanelButtons.add(jButtonDel = new JButton("删除"));
                jButtonDel.addActionListener(e -> {
                    if(JOptionPane.showConfirmDialog(jFrame,"是否删除所选行") == JOptionPane.OK_OPTION) {
                        int[] selected = jTable.getSelectedRows();
                        for (int i = selected.length - 1; i >= 0; i--) {
                            String name = (String) jTable.getValueAt(selected[i], 0);
                            String sql = "DELETE FROM `addressbook` WHERE `Name` =?;";
                            try {
                                PreparedStatement preparedStatement = jdbcSchema.connection.prepareStatement(sql);
                                preparedStatement.setString(1, name);
                                preparedStatement.execute();
                            } catch (SQLException throwable) {
                                throwable.printStackTrace();
                            }
                            defaultTableModel.removeRow(selected[i]);
                        }
                    }
                });
                jPanelAddressBook.add(jPanelButtons,BorderLayout.SOUTH);
            }
            jFrame.add(jPanelAddressBook,BorderLayout.CENTER);
        }
        jFrame.setSize(700,300);
        jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        jFrame.setVisible(true);
    }
}
