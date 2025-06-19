import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;
public class toDo {
    static String url="jdbc:mysql://localhost:3306/zaidhDB1";
    static String user="root";
    static String pass="mysql14";
    public static void displayTasks(int id)
    {
        try {
            Connection conn = DriverManager.getConnection(url, user, pass);
            Statement stmt = conn.createStatement();
            ResultSet rs=stmt.executeQuery("SELECT * FROM tasks WHERE user="+id+"");
            if(rs.next()==false)
            {
                System.out.println("No tasks found");
                return;
            }
            System.out.printf("| No. | id |%-20s | %-10s|%n","Tasks","Progress");
            int i=1;
            do{
                System.out.printf("| %d |  %d  |%-20s | %-10s|%n",i++,rs.getInt("taskId"),rs.getString("task"),rs.getInt("progress"));
            }while(rs.next());
            conn.close();
        }catch(Exception e){System.out.println(e);}
    }
    public static void addTask(int id)
    {
      try{
        Scanner sc=new Scanner(System.in);
        Connection conn = DriverManager.getConnection(url, user, pass);
        Statement stmt = conn.createStatement();
        System.out.print("Enter the task:");
        String task=sc.nextLine();
        System.out.print("Enter the progress(0/1):");
        int progress=sc.nextInt();
        stmt.executeUpdate("INSERT INTO tasks (`user`, `task`, `progress`)VALUES("+id+",'"+task+"',"+progress+")");
      } catch (Exception e) {System.out.println(e);}
      System.out.println("Task added successfully");
    }

    public static void CompleteTask(int id)
    {
        try{
            Scanner sc=new Scanner(System.in);
            Connection conn = DriverManager.getConnection(url, user, pass);
            Statement stmt = conn.createStatement();
            displayTasks(id);
            System.out.print("Enter the taskId:");
            int taskId=sc.nextInt();
            stmt.executeUpdate("UPDATE tasks SET `progress` = '1' WHERE (taskId = "+taskId+")");
        } catch (Exception e) {System.out.println(e);}
        System.out.println("Task completed successfully");
    }
    public static void deleteTask(int id)
    {
        try{
            Scanner sc=new Scanner(System.in);
            Connection conn = DriverManager.getConnection(url, user, pass);
            Statement stmt = conn.createStatement();
            displayTasks(id);
            System.out.print("Enter the taskId:");
            int taskId=sc.nextInt();
            stmt.executeUpdate("DELETE FROM tasks WHERE (taskId = "+taskId+")");
        } catch (Exception e) {System.out.println(e);}
        System.out.println("Task Deleted successfully");
    }
}
