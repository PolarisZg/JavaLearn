import java.sql.*;

public class useJDBC {
    
    useJDBC(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/student?serverTimezone=Asia/Shanghai";
            Connection connection = DriverManager.getConnection(url,"PolarisZg","8896859ab");
            Statement statement = connection.createStatement();
            statement.addBatch();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM `学生成绩表`;");
            while(resultSet.next()){
                System.out.println(resultSet.getString(2));
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String [] args){
        new useJDBC();
    }
}
