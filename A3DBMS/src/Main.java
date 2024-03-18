import javax.xml.transform.Result;
import java.sql.*;

public class A3 {
    static String url = "jdbc:postgresql://localhost:5432/A3";
    static String user = "postgres";
    static String pass = "Brendan2003";
    public static void main (String args[]) {

        //function calls are here:
          //addStudent("Brendan", "Bower", "BB@email.com", Date.valueOf("2024-03-18"));
          //updateStudentEmail(11, "bb@gmail.com");
          //deleteStudent(11);
          getAllStudents();
    }

    //prints all students and info in the database
    public static void getAllStudents() {
        try {
            //create the connections to the Database
            Class.forName("org.postgresql.Driver");
            Connection conn = DriverManager.getConnection(url, user, pass);

            //create the connection
            Statement statement = conn.createStatement();
            statement.executeQuery("SELECT * FROM students"); //query statement
            ResultSet rs = statement.getResultSet(); //get the results of the query

            //printing each value from the query result
            while (rs.next()) {
                System.out.println("id: " + rs.getInt("id") + ", First Name: " + rs.getString("first_name") +
                        ", Last Name: " + rs.getString("last_name") + ", Email: " + rs.getString("email") +
                        ", Enrollment Date: " + rs.getDate("enrollment_date"));
            }
            conn.close();
        } catch(ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        };
    }
    //add a student to the database
    public static void addStudent( String first_name, String last_name, String email, Date enrollment_date) {
        try {
            Class.forName("org.postgresql.Driver");
            Connection conn = DriverManager.getConnection(url, user, pass);

            //query statement
            PreparedStatement ps = conn.prepareStatement("INSERT INTO students (first_name, last_name, email, enrollment_date) VALUES (?, ?, ?, ?)");

            //set each value to the correct parameter
            ps.setString(1, first_name);
            ps.setString(2, last_name);
            ps.setString(3, email);
            ps.setDate(4, enrollment_date);
            ps.executeUpdate();

            System.out.println("added new student");

        } catch(ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        };
    }
    //change a studefnt's email
    public static void updateStudentEmail(int student_id, String new_email) {
        try {
            Class.forName("org.postgresql.Driver");
            Connection conn = DriverManager.getConnection(url, user, pass);

            //query statement
            PreparedStatement ps = conn.prepareStatement("UPDATE students SET email = ? WHERE id = ?");
            ps.setString(1, new_email);
            ps.setInt(2, student_id);
            ps.executeUpdate();

            System.out.println("email updated");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
    //delete a student from the database
    public static void deleteStudent(int student_id) {
        try {
            Class.forName("org.postgresql.Driver");
            Connection conn = DriverManager.getConnection(url, user, pass);

            //query statement
            PreparedStatement ps = conn.prepareStatement("DELETE FROM students WHERE id = ?");

            ps.setInt(1, student_id);
            ps.executeUpdate();

            System.out.println("deleted");

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
}
