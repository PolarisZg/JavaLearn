import java.sql.*;
import java.util.Scanner;

public class useJDBC {
    
    useJDBC(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("输入数据库名:");
            String schemaName = new Scanner(System.in).nextLine();
            String url = "jdbc:mysql://localhost:3306/" + schemaName + "?serverTimezone=Asia/Shanghai";
            System.out.println("输入用户名:");
            String user = new Scanner(System.in).nextLine();
            System.out.println("输入密码:");
            String password = new Scanner(System.in).nextLine();
            Connection connection = DriverManager.getConnection(url,user,password);
            Statement statement = connection.createStatement();
            statement.execute("DROP TABLE IF EXISTS 学生表;" );
            statement.execute("CREATE TABLE 学生表 ( 姓名 VARCHAR (10) PRIMARY KEY NOT NULL," +
                    "性别 VARCHAR (2) NULL," +
                    "院系 VARCHAR(10) NULL," +
                    "出生日期 DATE NULL," +
                    "平均成绩 DOUBLE NULL );");
            statement.execute("INSERT INTO 学生表 VALUES ('张蔷' , '男' , '控计学院' , '2001-05-01' , '80.2');");
            statement.execute("INSERT INTO 学生表 VALUES ('田宇' , '男' , '电力学院' , '2001-06-01' , '82.4');");
            statement.execute("INSERT INTO 学生表 VALUES ('张良' , '男' , '控计学院' , '2002-05-01' , '40.2');");
            statement.execute("INSERT INTO 学生表 VALUES ('李红' , '女' , '控计学院' , '2002-05-01' , '84.6');");
            ResultSet resultSet = statement.executeQuery("SELECT * FROM 学生表 WHERE 性别 = '男';");
            while(resultSet.next()){
                for(int i = 1 ; i <= 5 ; i++ )
                    System.out.print(resultSet.getString(i) + "    ");
                System.out.println();
            }
            statement.execute("UPDATE 学生表 SET 平均成绩 = 平均成绩 * 0.7 + 30 WHERE 院系 = '控计学院';");
            statement.execute("DELETE  FROM 学生表 WHERE 平均成绩 <= 60;");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String [] args){
        new useJDBC();
    }
}