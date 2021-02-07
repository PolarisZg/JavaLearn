import java.sql.*;

public class JDBC {
    public static void main(String [] args){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        String schemaName = "lessondesign";
        String user = "PolarisZg";
        String password = "8896859ab";
        String url = "jdbc:mysql://localhost:3306/" + schemaName + "?serverTimezone=Asia/Shanghai";
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(url, user, password);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        try {
            Statement statement = connection.createStatement();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        String sql = "insert into icq(nickname,password,email,info,place,pic) values(?,?,?,?,?,?)";
        String nickname = "PolarisZg";
        password = "123456";
        String email = "123.com";
        String info = "qwert";
        String place = "汉";
        int picindex = 1;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.clearParameters();
            preparedStatement.setString(1,nickname);
            preparedStatement.setString(2,password);
            preparedStatement.setString(3,email);
            preparedStatement.setString(4,info);
            preparedStatement.setString(5,place);
            preparedStatement.setInt(6,picindex);
            preparedStatement.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }
}
