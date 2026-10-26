import java.sql.Statement;
import java.util.Scanner;

public class InsertRecord extends DatabaseConnection {

    public void insertData() {
        try {
            Scanner sc = new Scanner(System.in);
            Statement st = con.createStatement();

            System.out.print("Enter Username : ");
            String username = sc.next();

            System.out.print("Enter Password : ");
            String password = sc.next();

            System.out.print("Enter Email : ");
            String email = sc.next();

            System.out.print("Enter Phone : ");
            String phone = sc.next();

            System.out.print("Enter Gender : ");
            String gender = sc.next();

            System.out.print("Enter Language : ");
            String language = sc.next();

            System.out.print("Enter Address : ");
            sc.nextLine();
            String address = sc.nextLine();

            String sql =
                "INSERT INTO Register(username,password,email,phone,gender,language,address) VALUES('"
                + username + "','"
                + password + "','"
                + email + "','"
                + phone + "','"
                + gender + "','"
                + language + "','"
                + address + "')";

            st.executeUpdate(sql);

            System.out.println("Record Inserted Successfully");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}