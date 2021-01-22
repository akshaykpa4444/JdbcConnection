package jdbcConn;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class JdbcConnection {

	public static void main(String[] args) throws SQLException, IOException {

		String jdbcURL = "jdbc:postgresql://localhost:5432/akshay";
		String username = "trainee";
		String password = "akshay";

		try {

			Connection connection = DriverManager.getConnection(jdbcURL, username, password);
			System.out.println("connected");

			String sql = "INSERT INTO studentdata(id,name,age)" + "VALUES(?,?,?)";
			PreparedStatement statement = connection.prepareStatement(sql);

			BufferedReader readingInfo = new BufferedReader(
					new FileReader("E:\\Data\\Trainee4\\workspace/StudentInfo.csv"));

			String readData = null;
			readingInfo.readLine(); //to skip the header
			while ((readData = readingInfo.readLine()) != null) {
				String[] ArrayData = readData.split(",");

				statement.setLong(1, Long.valueOf(ArrayData[0]));
				statement.setString(2, ArrayData[1]);
				statement.setLong(3, Long.valueOf(ArrayData[2]));
				statement.executeUpdate();
				
				

			}
			statement.close();
			connection.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}

}
