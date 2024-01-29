package main.test;

import lombok.Getter;
import lombok.Setter;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class Main2 {

    public static void main(String[] args) {
        // 加载驱动信息
        try {
            Class.forName("org.mariadb.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        // 数据库连接信息
        String url = "jdbc:mariadb://192.168.3.169:3306/testdb";
        String username = "root";
        String password = "Changeme_123";

        // 尝试连接数据库
        try (Connection connection = DriverManager.getConnection(url, username, password)) {
            System.out.println("Database connected!");

            // 执行查询
            try (Statement statement = connection.createStatement();
                 ResultSet resultSet = statement.executeQuery("SELECT * FROM courses")) {
                // 处理查询结果
                while (resultSet.next()) {
                    String column1Value = resultSet.getString("course_id");
                    String column2Value = resultSet.getString("course_name");
//                    int studentId = resultSet.getInt("student_id");
                    Object studentId = resultSet.getObject("student_Id");
                    Integer studentId1 = (Integer)studentId;
//                    String studentId = resultSet.getString("student_id");
                    System.out.println("Column1: " + column1Value
                            + ", ----Column2: " + column2Value
                            + ", ----studentId: " + studentId1);
                }
//                String insertSql = "insert into courses values(\"105\", \"Physics\", 5)";
//                statement.execute(insertSql);
            }

            String insert = "insert into courses(course_id, course_name, student_id) values(?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(insert);
//            preparedStatement.setString(1, "106");
//            preparedStatement.setString(2, "ZhengZhi");
//            preparedStatement.setString(3, "6");

            preparedStatement.setInt(1, 108);
            preparedStatement.setString(2, "DiLi");
            preparedStatement.setInt(3, 8);


            preparedStatement.execute();
            int updateCount = preparedStatement.getUpdateCount();
            if (updateCount == 1) {
                System.out.println("insert success!");
            } else {
                System.out.println("insert failed");
            }

        } catch (SQLException e) {
            System.out.println("Database connection failed!");
            e.printStackTrace();
        }
    }
}

