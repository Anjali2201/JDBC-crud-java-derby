package Assignment1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Delivery {

    public static void main(String[] args) {
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        Scanner sc = new Scanner(System.in);
        try {
            //1. Register the driver class
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            System.out.println("Driver Registered");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try {
            //2.Establish a connection
            con = DriverManager.getConnection("jdbc:derby://localhost:1527/sample", "app", "app");
            System.out.println("Connection established.\n\n");

            //3. Create statmente
            stmt = con.createStatement();

            //introduction
            System.out.println("*********************************************************************");
            System.out.println("                   WELCOME TO FOODCOURT!\n            DELICIOUS DELICACIES AT YOUR FOODSTEP:-");
            System.out.println("*********************************************************************");
            System.out.println("\nARE YOU AN EXISTING USER?\n1.Yes\n2.No");
            int choice = sc.nextInt();
            if (choice == 1) {
                //customer credentials   
                System.out.println("ENTER YOUR CUSTOMER ID:");
                int id = sc.nextInt();
                String query = "select * from customerlist where customerid =" + id;
                rs = stmt.executeQuery(query);
                while (rs.next()) {
                    id = rs.getInt(1);
                    String name = rs.getString("NAME");
                    String address = rs.getString("ADDRESS");
                    String number = rs.getString("MOBILENO");
                    String city = rs.getString("CITY");
                    int pincode = rs.getInt("pincode");
                    //System.out.println("Please check your credentials:\nCustomer id : "+id + "\nName: "+name+"\nPhone Number: "+number+"\nAddress: "+address);
                    System.out.println("----------------------------------------------------------------------------------------");
                    System.out.println("CUSTOMER ID |  CUSTOMER NAME   |   PHONE NUMBER  |  ADDRESS                             |");
                    System.out.println("----------------------------------------------------------------------------------------");
                    System.out.println(id + "            " + name + "       " + number + "       " + address + "," + city + "," + pincode);
                    System.out.println("----------------------------------------------------------------------------------------");
                    System.out.println("\n                    HELLO " + name + "!!\n                 LETS BEGIN ORDERING YOUR FOOD......");

                }
            }
            /*else{
             System.out.println("Please enter your name:");
             String name = sc.next();
             System.out.println("Enter your Phone Number:");
             String number =sc.next();
             System.out.println("Enter your Address:");
             String address=sc.next();
         
             System.out.println("Enter your City:");
             String city =sc.next();
             System.out.println("Enter your Pincode:");
             int pincode=sc.nextInt();
         
             String query2 = "insert into customerlist values NAME='"+name+"'MOBILENO='"+number+"'ADDRESS='"+address+"'CITY='"+city+"'PINCODE="+pincode;
             int x;
             x = stmt.executeUpdate(query2);
             if (x>0){
             System.out.println("Acoount Created successfully..................");
             System.out.println("Please check your credentials:\nName: "+name+"\nPhone Number: "+number+"\nAddress: "+address+","+city+","+pincode);}
             else{
             System.out.println("Process Unsuccessfully..................");
             }
             }*/

            System.out.println("\nENTER YOUR CITY: ");
            String city = sc.next();
            String query3 = "select * from hotel where location ='" + city + "'";
            rs = stmt.executeQuery(query3);

            System.out.println("  *********************************************************************");
            System.out.println("                     HOTELS AND RESTAURENTS                         ");
            System.out.println("  *********************************************************************");
            System.out.println("       -------------------------------------------------------");
            System.out.println("               HOTEL ID      |     HOTEL NAME");
            System.out.println("       -------------------------------------------------------");
            while (rs.next()) {
                city = rs.getString(3);
                int hid = rs.getInt("hid");
                String hname = rs.getString("hname");
                int pincode = rs.getInt("pincode");
                // System.out.println("\nHotel id : "+hid + "\nHotel Name: "+hname+"\nlocation: "+city+" "+pincode+"\n");
                System.out.println("               " + hid + "           " + hname + "," + city + "," + pincode + "\n");
            }
            System.out.println("       -------------------------------------------------------");

            System.out.println("PLEASE ENTER THE HOTEL ID YOU WANT TO ORDER FROM𝐌:");
            int hotelid = sc.nextInt();
            String query4 = "select * from menu where hid =" + hotelid;
            rs = stmt.executeQuery(query4);
            System.out.println("             ********************************");
            System.out.println("                          MENU              ");
            System.out.println("             ********************************");
            System.out.println("       ------------------------------------------------------------------------");
            System.out.println("                 PRODUCT ID   |  PRICE  |  DISH NAME  ");
            //System.out.println("                     8           "+price+"    "+pname);
            System.out.println("       -------------------------------------------------------------------------");
            while (rs.next()) {
                int pid = rs.getInt("pid");
                String pname = rs.getString("pname");
                int price = rs.getInt("price");
         //System.out.println("\nProduct ID : "+pid + "\nDish Name: "+pname+"\nPrice: "+price+"\n");

                System.out.println("                     " + pid + "           " + price + "    " + pname);
            }
            System.out.println("\n       -------------------------------------------------------------------------");

            System.out.println("PLEASE ENTER THE PRODUCT ID YOU WANT TO ORDER:");
            int proid = sc.nextInt();
            System.out.println("ENTER QUANTITY:");
            int quantity = sc.nextInt();
            String query5 = "select * from menu where pid =" + proid;
            rs = stmt.executeQuery(query5);

            while (rs.next()) {

                int pid = rs.getInt("pid");
                String pname = rs.getString("pname");
                int price = rs.getInt("price");
                float totalcost = price * quantity;
                System.out.println("\nYou have choosen:\nDish Name: " + pname + "\nPrice: " + price + "\nQuantity:" + quantity);
                System.out.println("\n confirm your order?\n1.Yes\n2.No");
                int c = sc.nextInt();

                if (c == 1) {
                    System.out.println("         --------------------------------------------------      ");
                    System.out.println("          *******CONGRATULATIONS!!!!ORDER SUCCESSFULL******        ");
                    System.out.println("         ---------------------------------------------------      ");
                    System.out.println("\n                    ----BILL-----\n");
                    System.out.println("                    PRODUCT ID: " + pid + "\n                    DISH NAME: " + pname + "\n                    PRICE: " + price + "\n                    𝐐𝐮𝐚𝐧𝐭𝐢𝐭𝐲: " + quantity + "\n                    𝐓𝐨𝐭𝐚𝐥 𝐜𝐨𝐬𝐭: " + totalcost);

                    System.out.println("\n\n          *******THANK YOU FOR ORDERING!! ******        ");
                    System.out.println("*********************************************************************");

                } else {
                    System.out.println("       ORDER UNSUCCESSFULL        ");
                }

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        //6. Closing all resources      
        try {
            if (rs != null) {
                rs.close();
            }
            if (stmt != null) {
                stmt.close();
            }
            if (con != null) {
                con.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
