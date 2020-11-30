import javax.swing.*;
import java.awt.*;
import java.sql.*;
import java.util.Objects;

public class JDBClogIn {
    public static void main(String[] args) {
        new JDBCGui();
    }
}

class JDBCSchema {
    Statement statement;

    JDBCSchema(String schemaName, String user, String password) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        String url = "jdbc:mysql://localhost:3306/" + schemaName + "?serverTimezone=Asia/Shanghai";
        Connection connection = DriverManager.getConnection(url, user, password);
        statement = connection.createStatement();
    }
}

class JDBCGui {
    JFrame jFrame;
    JTextField jTextFieldSchema;
    JTextField jTextFieldSchemaUser;
    JPasswordField jPasswordFieldSchema;
    JButton jButtonConnect;
    JPanel jPanelConnect;
    JTextField jTextFieldUser;
    JPasswordField jPasswordField;
    JPanel jPanelLogIn;
    JButton jButtonLogIn;
    JDBCSchema jdbcSchema;

    JDBCGui() {
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
                ResultSet resultSet = jdbcSchema.statement.executeQuery("SHOW TABLES");
                boolean aBoolean = false;
                while (resultSet.next()) {
                    if (Objects.equals(resultSet.getString(1), "user")) {
                        aBoolean = true;
                    }
                }
                if (!aBoolean) {
                    JOptionPane.showMessageDialog(jFrame, "所选数据库中无 user 表");
                }
            } catch (SQLException throwable) {
                throwable.printStackTrace();
            }
        });
        jFrame.add(jPanelConnect, BorderLayout.NORTH);

        jPanelLogIn = new JPanel();
        jPanelLogIn.setLayout(new GridLayout(2, 2));
        jPanelLogIn.add(new JLabel("用户名"));
        jPanelLogIn.add(jTextFieldUser = new JTextField());
        jPanelLogIn.add(new JLabel("密码"));
        jPanelLogIn.add(jPasswordField = new JPasswordField());
        jFrame.add(jPanelLogIn);

        jFrame.add(jButtonLogIn = new JButton("登录"), BorderLayout.SOUTH);
        jButtonLogIn.addActionListener(e -> {
            String user = jTextFieldUser.getText();
            String password = String.valueOf(jPasswordField.getPassword());
            try {
                ResultSet resultSet = jdbcSchema.statement.executeQuery("SELECT * FROM `USER` ;");
                boolean userBoolean = false;
                boolean passwordBoolean = false;
                while (resultSet.next()) {
                    if (Objects.equals(resultSet.getString(1), user)) {
                        userBoolean = true;
                        if(Objects.equals(resultSet.getString(2), password)) {
                            passwordBoolean = true;
                            JOptionPane.showMessageDialog(jFrame, "登录成功");
                            break;
                        }
                    }
                }
                if(!userBoolean){
                    JOptionPane.showMessageDialog(jFrame,"无此用户");
                }
                else if (!passwordBoolean){
                    JOptionPane.showMessageDialog(jFrame,"密码错误");
                }
            } catch (SQLException throwable) {
                throwable.printStackTrace();
            }
        });

        jFrame.setSize(800, 200);
        jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        jFrame.setVisible(true);
    }
}