
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author toves
 */
@WebServlet(name="Information",urlPatterns = {"/personalInfo","/educationInfo","/professionalExperience","/projects","/skills","/language","/link","/reference"})
public class Information extends HttpServlet  {
    // JDBC URL, username, and password of MySQL server
        String jdbcUrl = "jdbc:mysql://localhost:3306/resumedb";
        String dbUser = "root";
        String dbPassword = "";
        
        
        @Override
        protected void doPost(HttpServletRequest request,HttpServletResponse response) throws IOException{
            String path = request.getServletPath();
            
            if ("/personalInfo".equals(path)){
                addPersonalInfo(request,response);
            }else if("/educationInfo".equals(path)){
                addEducationInfo(request,response);
            }else if("/professionalExperience".equals(path)){
                professionalExperience(request,response);
            }else if("/projects".equals(path)){
                projects(request,response);
            }else if("/skills".equals(path)){
                skills(request,response);
            }else if("/language".equals(path)){
                language(request,response);
            }else if("/link".equals(path)){
                link(request,response);
            }else if("/reference".equals(path)){
                reference(request,response);
            }
            else{
                response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            }
        }
        private void addPersonalInfo(HttpServletRequest request, HttpServletResponse response) throws IOException {
          String fullname = request.getParameter("fullname");
          String email = request.getParameter("email");
          String phone = request.getParameter("phone");
          String location = request.getParameter("location");
          String objective = request.getParameter("objective");
          try {
         // Load the JDBC driver
          Class.forName("com.mysql.cj.jdbc.Driver");
         // Establish a connection
           try (Connection connection = DriverManager.getConnection(jdbcUrl, dbUser, dbPassword)) {
            // Proceed with the insertion without checking if the email already exists
            String sql = "INSERT INTO personalinfo (fullname, email, phone,location,objective) VALUES (?, ?, ?,?,?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setString(1, fullname);
                preparedStatement.setString(2, email);
                preparedStatement.setString(3, phone);
                preparedStatement.setString(4, location);
                preparedStatement.setString(5, objective);
                // Execute the statement
                preparedStatement.executeUpdate();

                // Send a success response
                response.setContentType("text/plain");
                response.getWriter().println("User added successfully!\nFullname: " + fullname + "\nEmail: " + email + "\nPhone: " + phone+"\nLocation"+location+"\nObjective"+objective+"\nStatus Code"+HttpServletResponse.SC_CREATED);
            }
        }
    }   catch (ClassNotFoundException | SQLException e) {
           System.out.print(e.getMessage());
    }
  }
    private void addEducationInfo(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String schoolname = request.getParameter("schoolname");
          String course = request.getParameter("course");
          String startdate = request.getParameter("startdate");
          String enddate = request.getParameter("enddate");
          String location = request.getParameter("location");

          

          try {
         // Load the JDBC driver
          Class.forName("com.mysql.cj.jdbc.Driver");
         // Establish a connection
           try (Connection connection = DriverManager.getConnection(jdbcUrl, dbUser, dbPassword)) {
            // Proceed with the insertion without checking if the email already exists
            String sqlquery = "INSERT INTO educationinfo (schoolname, course,startdate,enddate,location) VALUES (?, ?, ?,?,?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sqlquery)) {
                preparedStatement.setString(1, schoolname);
                preparedStatement.setString(2, course);
                preparedStatement.setString(3, startdate);
                preparedStatement.setString(4, enddate);
                preparedStatement.setString(5, location);
                // Execute the statement
                preparedStatement.executeUpdate();

                // Send a success response
                response.setContentType("text/plain");
                response.getWriter().println("Education Information Saved!\nSchool Name: " + schoolname + "\nCourse: " + course + "\nStartDate: " + startdate+"\nEndDate"+enddate+"\nLocation"+location+"\nStatus Code"+HttpServletResponse.SC_CREATED);
            }
        }
    }   catch (ClassNotFoundException | SQLException e) {
           System.out.print(e.getMessage());
    }
    }
    
    private void professionalExperience(HttpServletRequest request, HttpServletResponse response) throws IOException {
          String companyname = request.getParameter("companyname");
          String jobtitle = request.getParameter("jobtitle");
          String startdate = request.getParameter("startdate");
          String enddate = request.getParameter("enddate");
          String location = request.getParameter("location");
          String description = request.getParameter("description");
          try {
         // Load the JDBC driver
          Class.forName("com.mysql.cj.jdbc.Driver");
         // Establish a connection
           try (Connection connection = DriverManager.getConnection(jdbcUrl, dbUser, dbPassword)) {
            // Proceed with the insertion without checking if the email already exists
            String sqlquery = "INSERT INTO professionalexperience (companyname, jobtitle,startdate,enddate,location,description) VALUES (?, ?, ?,?,?,?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sqlquery)) {
                preparedStatement.setString(1, companyname);
                preparedStatement.setString(2, jobtitle);
                preparedStatement.setString(3, startdate);
                preparedStatement.setString(4, enddate);
                preparedStatement.setString(5, location);
                preparedStatement.setString(6, description);
                // Execute the statement
                preparedStatement.executeUpdate();
                // Send a success response
                response.setContentType("text/plain");
                response.getWriter().println("Professional Information Saved!\nCompany Name: " + companyname + "\nJob title: " + jobtitle + "\nStartDate: " + startdate+"\nEndDate"+enddate+"\nLocation"+location+"\nDescription"+description+"\nStatus Code:"+HttpServletResponse.SC_CREATED);
            }
        }
    }   catch (ClassNotFoundException | SQLException e) {
           System.out.print(e.getMessage());
    }
    }
    private void projects(HttpServletRequest request, HttpServletResponse response) throws IOException {
          String projectname = request.getParameter("projectname");
          String link = request.getParameter("link");
          String description = request.getParameter("description");
          try {
         // Load the JDBC driver
          Class.forName("com.mysql.cj.jdbc.Driver");
         // Establish a connection
           try (Connection connection = DriverManager.getConnection(jdbcUrl, dbUser, dbPassword)) {
            // Proceed with the insertion without checking if the email already exists
            String sqlquery = "INSERT INTO projects (projectname,link,description) VALUES (?,?,?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sqlquery)) {
                preparedStatement.setString(1, projectname);
                preparedStatement.setString(2, link);
                preparedStatement.setString(3, description);
                // Execute the statement
                preparedStatement.executeUpdate();
                // Send a success response
                response.setContentType("text/plain");
                response.getWriter().println("Project Information Saved!\nProject Name: " + projectname + "\nLink: " + link +"\nDescription"+description+"\nStatus Code:"+HttpServletResponse.SC_CREATED);
            }
        }
    }   catch (ClassNotFoundException | SQLException e) {
           System.out.print(e.getMessage());
    }
    }
    private void skills(HttpServletRequest request, HttpServletResponse response) throws IOException {
          String category = request.getParameter("category");
          String skillname = request.getParameter("skillname");
          try {
         // Load the JDBC driver
          Class.forName("com.mysql.cj.jdbc.Driver");
         // Establish a connection
           try (Connection connection = DriverManager.getConnection(jdbcUrl, dbUser, dbPassword)) {
            // Proceed with the insertion without checking if the email already exists
            String sqlquery = "INSERT INTO skill (category,skillname) VALUES (?,?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sqlquery)) {
                preparedStatement.setString(1, category);
                preparedStatement.setString(2, skillname);
                // Execute the statement
                preparedStatement.executeUpdate();
                // Send a success response
                response.setContentType("text/plain");
                response.getWriter().println("Skills  Saved!\nCategory Name: " + category + "\nSkill Name: " + skillname +"\nStatus Code:"+HttpServletResponse.SC_CREATED);
            }
        }
    }   catch (ClassNotFoundException | SQLException e) {
           System.out.print(e.getMessage());
    }
    }
    private void language(HttpServletRequest request, HttpServletResponse response) throws IOException {
          String languagename = request.getParameter("languagename");
          String level = request.getParameter("level");
          try {
         // Load the JDBC driver
          Class.forName("com.mysql.cj.jdbc.Driver");
         // Establish a connection
           try (Connection connection = DriverManager.getConnection(jdbcUrl, dbUser, dbPassword)) {
            // Proceed with the insertion without checking if the email already exists
            String sqlquery = "INSERT INTO language (languagename,level) VALUES (?,?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sqlquery)) {
                preparedStatement.setString(1, languagename);
                preparedStatement.setString(2, level);
                // Execute the statement
                preparedStatement.executeUpdate();
                // Send a success response
                response.setContentType("text/plain");
                response.getWriter().println("Langauge  Saved!\nLanguage Name: " + languagename + "\nLevel: " + level +"\nStatus Code:"+HttpServletResponse.SC_CREATED);
            }
        }
    }   catch (ClassNotFoundException | SQLException e) {
           System.out.print(e.getMessage());
    }
    }
    private void link(HttpServletRequest request, HttpServletResponse response) throws IOException {
          String linkname = request.getParameter("linkname");
          String link = request.getParameter("link");
          try {
         // Load the JDBC driver
          Class.forName("com.mysql.cj.jdbc.Driver");
         // Establish a connection
           try (Connection connection = DriverManager.getConnection(jdbcUrl, dbUser, dbPassword)) {
            // Proceed with the insertion without checking if the email already exists
            String sqlquery = "INSERT INTO link (linkname,link) VALUES (?,?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sqlquery)) {
                preparedStatement.setString(1, linkname);
                preparedStatement.setString(2, link);
                // Execute the statement
                preparedStatement.executeUpdate();
                // Send a success response
                response.setContentType("text/plain");
                response.getWriter().println("Langauge  Saved!\nLink Name: " + linkname + "\nLink: " + link +"\nStatus Code:"+HttpServletResponse.SC_CREATED);
            }
        }
    }   catch (ClassNotFoundException | SQLException e) {
           System.out.print(e.getMessage());
    }
    }
    private void reference(HttpServletRequest request, HttpServletResponse response) throws IOException {
          String fullname = request.getParameter("fullname");
          String email = request.getParameter("email");
          String phone = request.getParameter("phone");
          String relation = request.getParameter("relation");
          try {
         // Load the JDBC driver
          Class.forName("com.mysql.cj.jdbc.Driver");
         // Establish a connection
           try (Connection connection = DriverManager.getConnection(jdbcUrl, dbUser, dbPassword)) {
            // Proceed with the insertion without checking if the email already exists
            String sqlquery = "INSERT INTO reference (fullname,email,phone,relation) VALUES (?,?,?,?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sqlquery)) {
                preparedStatement.setString(1, fullname);
                preparedStatement.setString(2, email);
                preparedStatement.setString(3, phone);
                preparedStatement.setString(4, relation);
                // Execute the statement
                preparedStatement.executeUpdate();
                // Send a success response
                response.setContentType("text/plain");
                response.getWriter().println("Langauge  Saved!\nFullName: " + fullname + "\nEmail: " + email +"\nPhone:"+phone+"\nRelation:"+relation+"\nStatus Code:"+HttpServletResponse.SC_CREATED);
            }
        }
    }   catch (ClassNotFoundException | SQLException e) {
           System.out.print(e.getMessage());
    }
    }
    public static void main(String[]args){
        
    }
     
}
