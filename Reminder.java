import java.sql.*;
import java.util.*; 

/* 
	Application to add, remove, update and show reminder
	Usage: java Reminder <list|add|update|delete>
	Database: MS Access
	Created by: Kanjana Rashimi Rose
	Date: 11/09/2018
*/
 
 
class Reminder
{  

/* List method will list all reminders */
public static void list()
{
	
	 try
    {
		Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
        Connection conn=DriverManager.getConnection("jdbc:ucanaccess://ReminderDB.mdb");
        Statement stment = conn.createStatement();
        String qry = "SELECT * FROM Remind";

        ResultSet rs = stment.executeQuery(qry);
		// Print the list objects in tabular format.
		System.out.println("----------------------------------------------------------------------------------");
		System.out.printf("%10s | %40s | %20s", "ID", "Reminder", "When");
		System.out.println();
		System.out.println("----------------------------------------------------------------------------------");
		while(rs.next())
        {
            String ID = rs.getString("ID");
			String Reminder    = rs.getString("Reminder") ;
            String Date = rs.getString("When");
			System.out.format("%10s | %40s | %20s", ID,  Reminder, Date );
			System.out.println();
        }
		System.out.println("----------------------------------------------------------------------------------");
	}
    catch(Exception err)
    {
        System.out.println(err);
    }



	
}
/* Add method will Add reminder to Database */
public static int add(String reminder, String when)
{
	int row = 0;
	 try
    {
		Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
        Connection conn=DriverManager.getConnection("jdbc:ucanaccess://ReminderDB.mdb");
        
		String sql = "INSERT INTO Remind (Reminder, When) VALUES (?, ?)";
             
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
		    preparedStatement.setString(1, reminder);
            preparedStatement.setString(2, when);
            row = preparedStatement.executeUpdate();
			conn.commit();
			if (row > 0) {
                System.out.println("A Reminder has been inserted successfully.");
            }
		
    }
    catch(Exception err)
    {
        System.out.println(err);
    }



	return row;
	
}

/* Update method will Update reminder to Database */

public static int update(int ID, String reminder, String when)
{
	int row = 0;
	 try
    {
		
		Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
        Connection conn=DriverManager.getConnection("jdbc:ucanaccess://ReminderDB.mdb");
        Statement stment = conn.createStatement();
        String qry = "UPDATE Remind SET Reminder = \"" +  reminder + "\" , When = \""+ when + "\" WHERE ID = "+ID;

        row = stment.executeUpdate(qry);
		if(row > 0)
		{
			System.out.println("A Reminder has been updated successfully.");
        }
		
    }
    catch(Exception err)
    {
        System.out.println(err);
    }



	return row;
	
}
/* Delete method will delete reminder from Database */

public static int delete(int ID)
{
	int row = 0;
	 try
    {
		
		Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
        Connection conn=DriverManager.getConnection("jdbc:ucanaccess://ReminderDB.mdb");
        Statement stment = conn.createStatement();
        String qry = "DELETE FROM Remind WHERE ID = " + ID;
        row = stment.executeUpdate(qry);
		if(row > 0)
		{
			System.out.println("A Reminder has been deleted successfully.");
        }
		
    }
    catch(Exception err)
    {
        System.out.println(err);
    }



	return row;
	
}


public static void main(String ar[])
{  
 
	if(ar.length >= 1)
	{
		if(ar[0].equals("list"))
		{
			System.out.println("list");
			list();
		}
		else if(ar[0].equals("add"))
		{
				
				Scanner scanner = new Scanner(System.in);
				System.out.println("Enter Reminder: ");
				String reminder = scanner.nextLine();
				System.out.println("When(Format: yyyy-mm-dd hh:mm:ss): ");
				String when = scanner.nextLine();
				
				System.out.println("Added " + add(reminder,when) + " Reminder");
		}
		else if(ar[0].equals("update"))
		{
				list();
				
				Scanner scanner = new Scanner(System.in);
				System.out.println("Enter Reminder ID number to update: ");
				int ID = Integer.parseInt(scanner.nextLine());
				System.out.println("Enter New Reminder: ");
				String reminder = scanner.nextLine();
				System.out.println("When(Format: yyyy-mm-dd hh:mm:ss): ");
				String when = scanner.nextLine();
				update(ID,reminder,when);
		}
		else if(ar[0].equals("delete"))
		{
				list();
				
				Scanner scanner = new Scanner(System.in);
				System.out.println("Enter Reminder ID number to delete: ");
				int ID = Integer.parseInt(scanner.nextLine());
				delete(ID);
		}
		
	}
	else
	{
		
		System.out.println("Usage: java Reminder <list|add|update|delete>");
	}
}

}  