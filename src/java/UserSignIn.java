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

@WebServlet(name = "UserSignIn", urlPatterns = {"/addUser"})
public class UserSignIn extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        // JDBC URL, username, and password of MySQL server
        String jdbcUrl = "jdbc:mysql://localhost:3306/resumedb";
        String dbUser = "root";
        String dbPassword = ""; // You may need to set a password if you've configured MySQL with one

        try {
            // Load the JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            // Establish a connection
            try (Connection connection = DriverManager.getConnection(jdbcUrl, dbUser, dbPassword)) {
                // Check if the email already exists
                if (isEmailExists(connection, email)) {
                    // Email already exists, send a response
                    response.setContentType("text/plain");
                    response.getWriter().println("Email already exists. User not added.\n Status Code: " + HttpServletResponse.SC_CONFLICT);
                } else {
                    // Email doesn't exist, proceed with the insertion
                    String sql = "INSERT INTO users (username, email, password) VALUES (?, ?, ?)";
                    try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                        preparedStatement.setString(1, username);
                        preparedStatement.setString(2, email);
                        preparedStatement.setString(3, password);
                        // Execute the statement
                        preparedStatement.executeUpdate();

                        // Send a success response
                        response.setContentType("text/plain");
                        response.getWriter().println("User added successfully!\nUsername: " + username + "\nEmail: " + email + "\nStatus Code: " + HttpServletResponse.SC_CREATED);
                    }
                }
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            System.out.print(e.getMessage());
        }
    }

    private boolean isEmailExists(Connection connection, String email) throws SQLException {
        String checkEmailQuery = "SELECT * FROM users WHERE email = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(checkEmailQuery)) {
            preparedStatement.setString(1, email);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                // If resultSet has any rows, the email already exists
                return resultSet.next();
            }
        }
    }
}
