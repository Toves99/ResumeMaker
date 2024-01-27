
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author toves
 */
@WebServlet(name="LoginUser",urlPatterns={"/validateUser"})
public class LoginUser extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String username=request.getParameter("username");
        String password=request.getParameter("password");
        
        // JDBC URL, username, and password of MySQL server
        String jdbcUrl = "jdbc:mysql://localhost:3306/userdb";
        String dbUser = "root";
        String dbPassword = "";
        
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            try (Connection connection = DriverManager.getConnection(jdbcUrl, dbUser, dbPassword)){
                if (isValidated(connection, username,password)) {
                    // Email already exists, send a response
                    response.setContentType("text/plain");
                    response.getWriter().println("Logged In Successfully.\n Status Code: " + HttpServletResponse.SC_CREATED);
                }else{
                    response.setContentType("text/plain");
                    response.getWriter().println("Incorrect user details.\n Status Code: " + HttpServletResponse.SC_CONFLICT);
                }
            
            
            }
        }catch(ClassNotFoundException | SQLException e){
            e.printStackTrace();
            System.out.print(e.getMessage());
        }
    }
     private boolean isValidated(Connection connection, String username,String password) throws SQLException {
        String checkEmailQuery = "SELECT * FROM users WHERE username = ? AND password=?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(checkEmailQuery)) {
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                // If resultSet has any rows, the email already exists
                return resultSet.next();
            }
        }
    }
    
}
