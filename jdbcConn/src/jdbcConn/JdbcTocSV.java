package jdbcConn;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JdbcTocSV {

	public static void main(String[] args) {
		String jdbcURL = "jdbc:postgresql://localhost:5432/akshay";
		String username = "trainee";
		String password = "akshay";

		try {
			Connection connection = DriverManager.getConnection(jdbcURL, username, password);
			System.out.println("connected");
			
			String sql = "SELECT * FROM studentdata";
			Statement statement = connection.createStatement();
			ResultSet result = statement.executeQuery(sql);

			BufferedWriter fileWriter = new BufferedWriter(
					new FileWriter("E:\\Data\\Trainee4\\workspace/AkshayOutput.csv"));

			fileWriter.write("id,name,age");//to create header in csv file

			while (result.next()) {
				int id = result.getInt("id");
				String name = result.getString("name");
				int age = result.getInt("age");

				String line = String.format(" %s\",%s,%s", id, name, age);
				fileWriter.newLine();
				fileWriter.write(line);
				System.out.println(id + "," + name + "," + age);
			}
			statement.close();
			fileWriter.close();

		} catch (SQLException e) {
			System.out.println("data base error");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("file error");
			e.printStackTrace();
		}

	}

}
