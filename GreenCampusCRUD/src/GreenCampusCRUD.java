import java.sql.*;
import java.util.Scanner;

public class GreenCampusCRUD{

    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        int choice;

        do {

            System.out.println(
                    "\n===== GREEN CAMPUS PORTAL ====="
            );

            System.out.println("1. Resource Management");
            System.out.println("2. Consumption Records");
            System.out.println("3. Sustainability Metrics");
            System.out.println("4. Exit");

            System.out.print("Enter choice: ");

            choice = sc.nextInt();

            switch (choice) {

                case 1 -> resourceMenu();

                case 2 -> consumptionMenu();

                case 3 -> metricMenu();

                case 4 -> System.out.println("Application Closed.");

                default -> System.out.println("Invalid Choice");
            }

        } while (choice != 4);
    }


    // =====================================
    // RESOURCE MENU
    // =====================================

    static void resourceMenu() {

        System.out.println("\n1. Add Resource");
        System.out.println("2. View Resources");
        System.out.println("3. Update Resource");
        System.out.println("4. Delete Resource");

        System.out.print("Choice: ");

        int ch = sc.nextInt();

        switch (ch) {

            case 1:
                addResource();
                break;

            case 2:
                viewResources();
                break;

            case 3:
                updateResource();
                break;

            case 4:
                deleteResource();
                break;
        }
    }


    // CREATE

    static void addResource() {

        try {

            Connection con =
                    DBConnection.getConnection();

            String sql =
                    "INSERT INTO resources(name,type,quantity) "
                    + "VALUES(?,?,?)";

            PreparedStatement ps =
                    con.prepareStatement(sql);

            sc.nextLine();

            System.out.print("Resource Name: ");
            String name = sc.nextLine();

            System.out.print("Resource Type: ");
            String type = sc.nextLine();

            System.out.print("Quantity: ");
            double quantity = sc.nextDouble();

            ps.setString(1, name);
            ps.setString(2, type);
            ps.setDouble(3, quantity);

            ps.executeUpdate();

            System.out.println(
                    "Resource added successfully."
            );

            con.close();

        } catch (Exception e) {

            System.out.println(e.getMessage());
        }
    }


    // READ

    static void viewResources() {

        try {

            Connection con =
                    DBConnection.getConnection();

            Statement st =
                    con.createStatement();

            ResultSet rs =
                    st.executeQuery(
                        "SELECT * FROM resources"
                    );

            System.out.println(
                    "\nID\tName\tType\tQuantity"
            );

            while (rs.next()) {

                System.out.println(
                        rs.getInt("id") + "\t"
                        + rs.getString("name") + "\t"
                        + rs.getString("type") + "\t"
                        + rs.getDouble("quantity")
                );
            }

            con.close();

        } catch (Exception e) {

            System.out.println(e.getMessage());
        }
    }


    // UPDATE

    static void updateResource() {

        try {

            Connection con =
                    DBConnection.getConnection();

            String sql =
                    "UPDATE resources "
                    + "SET quantity=? WHERE id=?";

            PreparedStatement ps =
                    con.prepareStatement(sql);

            System.out.print("Enter Resource ID: ");
            int id = sc.nextInt();

            System.out.print("Enter New Quantity: ");
            double quantity = sc.nextDouble();

            ps.setDouble(1, quantity);
            ps.setInt(2, id);

            int rows = ps.executeUpdate();

            if (rows > 0)
                System.out.println(
                        "Resource updated successfully."
                );
            else
                System.out.println(
                        "Resource not found."
                );

            con.close();

        } catch (Exception e) {

            System.out.println(e.getMessage());
        }
    }


    // DELETE

    static void deleteResource() {

        try {

            Connection con =
                    DBConnection.getConnection();

            String sql =
                    "DELETE FROM resources WHERE id=?";

            PreparedStatement ps =
                    con.prepareStatement(sql);

            System.out.print("Enter Resource ID: ");

            int id = sc.nextInt();

            ps.setInt(1, id);

            ps.executeUpdate();

            System.out.println(
                    "Resource deleted successfully."
            );

            con.close();

        } catch (Exception e) {

            System.out.println(e.getMessage());
        }
    }


    // =====================================
    // CONSUMPTION RECORDS
    // =====================================

    static void consumptionMenu() {

        System.out.println("\n1. Add Consumption");
        System.out.println("2. View Consumption");
        System.out.println("3. Update Consumption");
        System.out.println("4. Delete Consumption");

        System.out.print("Choice: ");

        int ch = sc.nextInt();

        switch (ch) {

            case 1:
                addConsumption();
                break;

            case 2:
                viewConsumption();
                break;

            case 3:
                updateConsumption();
                break;

            case 4:
                deleteConsumption();
                break;
        }
    }


    static void addConsumption() {

        try {

            Connection con =
                    DBConnection.getConnection();

            String sql =
                    "INSERT INTO consumption_records"
                    + "(resource_name,consumption,record_date)"
                    + " VALUES(?,?,?)";

            PreparedStatement ps =
                    con.prepareStatement(sql);

            sc.nextLine();

            System.out.print("Resource Name: ");
            String name = sc.nextLine();

            System.out.print("Consumption: ");
            double value = sc.nextDouble();

            sc.nextLine();

            System.out.print("Date: ");
            String date = sc.nextLine();

            ps.setString(1, name);
            ps.setDouble(2, value);
            ps.setString(3, date);

            ps.executeUpdate();

            System.out.println(
                    "Consumption record added."
            );

            con.close();

        } catch (Exception e) {

            System.out.println(e.getMessage());
        }
    }


    static void viewConsumption() {

        try {

            Connection con =
                    DBConnection.getConnection();

            Statement st =
                    con.createStatement();

            ResultSet rs =
                    st.executeQuery(
                    "SELECT * FROM consumption_records"
                    );

            while (rs.next()) {

                System.out.println(

                        rs.getInt("id") + " | "
                        + rs.getString("resource_name")
                        + " | "
                        + rs.getDouble("consumption")
                        + " | "
                        + rs.getString("record_date")
                );
            }

            con.close();

        } catch (Exception e) {

            System.out.println(e.getMessage());
        }
    }


    static void updateConsumption() {

        try {

            Connection con =
                    DBConnection.getConnection();

            String sql =
                    "UPDATE consumption_records "
                    + "SET consumption=? WHERE id=?";

            PreparedStatement ps =
                    con.prepareStatement(sql);

            System.out.print("Record ID: ");
            int id = sc.nextInt();

            System.out.print("New Consumption: ");
            double value = sc.nextDouble();

            ps.setDouble(1, value);
            ps.setInt(2, id);

            ps.executeUpdate();

            System.out.println(
                    "Consumption updated."
            );

            con.close();

        } catch (Exception e) {

            System.out.println(e.getMessage());
        }
    }


    static void deleteConsumption() {

        try {

            Connection con =
                    DBConnection.getConnection();

            PreparedStatement ps =
                    con.prepareStatement(
                    "DELETE FROM consumption_records "
                    + "WHERE id=?"
                    );

            System.out.print("Record ID: ");

            ps.setInt(1, sc.nextInt());

            ps.executeUpdate();

            System.out.println(
                    "Consumption record deleted."
            );

            con.close();

        } catch (Exception e) {

            System.out.println(e.getMessage());
        }
    }


    // =====================================
    // SUSTAINABILITY METRICS
    // =====================================

    static void metricMenu() {

        System.out.println("\n1. Add Metric");
        System.out.println("2. View Metrics");
        System.out.println("3. Update Metric");
        System.out.println("4. Delete Metric");

        System.out.print("Choice: ");

        int ch = sc.nextInt();

        switch (ch) {

            case 1:
                addMetric();
                break;

            case 2:
                viewMetrics();
                break;

            case 3:
                updateMetric();
                break;

            case 4:
                deleteMetric();
                break;
        }
    }


    static void addMetric() {

        try {

            Connection con =
                    DBConnection.getConnection();

            PreparedStatement ps =
                    con.prepareStatement(

                    "INSERT INTO sustainability_metrics"
                    + "(metric_name,value,unit)"
                    + " VALUES(?,?,?)"

                    );

            sc.nextLine();

            System.out.print("Metric Name: ");
            String name = sc.nextLine();

            System.out.print("Value: ");
            double value = sc.nextDouble();

            sc.nextLine();

            System.out.print("Unit: ");
            String unit = sc.nextLine();

            ps.setString(1, name);
            ps.setDouble(2, value);
            ps.setString(3, unit);

            ps.executeUpdate();

            System.out.println(
                    "Metric added successfully."
            );

            con.close();

        } catch (SQLException e) {

            System.out.println(e.getMessage());
        }
    }


    static void viewMetrics() {

        try {

            Connection con =
                    DBConnection.getConnection();

            Statement st =
                    con.createStatement();

            ResultSet rs =
                    st.executeQuery(
                    "SELECT * FROM sustainability_metrics"
                    );

            while (rs.next()) {

                System.out.println(

                        rs.getInt("id") + " | "
                        + rs.getString("metric_name")
                        + " | "
                        + rs.getDouble("value")
                        + " "
                        + rs.getString("unit")

                );
            }

            con.close();

        } catch (Exception e) {

            System.out.println(e.getMessage());
        }
    }


    static void updateMetric() {

        try {

            Connection con =
                    DBConnection.getConnection();

            PreparedStatement ps =
                    con.prepareStatement(

                    "UPDATE sustainability_metrics "
                    + "SET value=? WHERE id=?"

                    );

            System.out.print("Metric ID: ");
            int id = sc.nextInt();

            System.out.print("New Value: ");
            double value = sc.nextDouble();

            ps.setDouble(1, value);
            ps.setInt(2, id);

            ps.executeUpdate();

            System.out.println(
                    "Metric updated."
            );

            con.close();

        } catch (Exception e) {

            System.out.println(e.getMessage());
        }
    }


    static void deleteMetric() {

        try {

            Connection con =
                    DBConnection.getConnection();

            PreparedStatement ps =
                    con.prepareStatement(

                    "DELETE FROM sustainability_metrics "
                    + "WHERE id=?"

                    );

            System.out.print("Metric ID: ");

            ps.setInt(1, sc.nextInt());

            ps.executeUpdate();

            System.out.println(
                    "Metric deleted."
            );

            con.close();

        } catch (Exception e) {

            System.out.println(e.getMessage());
        }
    }
}