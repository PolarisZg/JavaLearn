import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.*;
import java.util.Objects;

public class JDBCStudentTable {
    public static void main(String [] args){
        new JDBCStudentTableGui();
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

class JDBCStudentTableGui{
    JFrame jFrame;
    JTextField jTextFieldSchema;
    JTextField jTextFieldSchemaUser;
    JPasswordField jPasswordFieldSchema;
    JButton jButtonConnect;
    JPanel jPanelConnect;
    JTable jTableStudent;
    DefaultTableModel defaultTableModel;
    JDBCSchema jdbcSchema;

    JDBCStudentTableGui(){
        jFrame = new JFrame("未连接数据库");
        jFrame.setLayout(new BorderLayout());
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
                ResultSet resultSet = jdbcSchema.statement.executeQuery("SHOW TABLES ;");
                boolean aBoolean = false;
                while (resultSet.next()) {
                    if (Objects.equals(resultSet.getString(1), "学生表")) {
                        aBoolean = true;
                    }
                }
                if (!aBoolean) {
                    JOptionPane.showMessageDialog(jFrame, "所选数据库中无 学生表");
                }
            } catch (SQLException throwable) {
                throwable.printStackTrace();
            }
            try {
                PreparedStatement preparedStatement = jdbcSchema.connection.prepareStatement("SELECT * FROM `学生表` ;");
                preparedStatement.execute();
                ResultSet resultSet = preparedStatement.getResultSet();
                while(resultSet.next()){
                    defaultTableModel.addRow(new String[]{
                            resultSet.getString(1),
                            resultSet.getString(2),
                            resultSet.getString(3),
                            resultSet.getString(4),
                            resultSet.getString(5)
                    });
                }
            } catch (SQLException throwable) {
                throwable.printStackTrace();
            }
        });
        jFrame.add(jPanelConnect, BorderLayout.NORTH);

        defaultTableModel = new DefaultTableModel(null, new String[]{"姓名", "性别", "院系", "出生日期", "平均成绩"});
        jTableStudent = new JTable(defaultTableModel);
        jFrame.add(new JScrollPane(jTableStudent),BorderLayout.CENTER);

        jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        jFrame.setSize(800,300);
        jFrame.setVisible(true);
    }
}