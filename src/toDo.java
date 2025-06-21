import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
public class toDo {
    static String url="jdbc:mysql://localhost:3306/zaidhDB1";
    static String user="root";
    static String pass="mysql14";
    public static String displayTasks(int id,int i)
    {
        String s="";
        try {
            Connection conn = DriverManager.getConnection(url, user, pass);
            Statement stmt = conn.createStatement();
            ResultSet rs=stmt.executeQuery("SELECT * FROM tasks WHERE user="+id+"");
            if(rs.next()==false)
                return "No tasks found";
            int j=0;
            do {
                s =rs.getString("task");
                if(j==i)
                   return s;
                j++;
            } while(rs.next());
            conn.close();     
        }catch(Exception e){System.out.println(e);}
        return "Error in displaying tasks";
    }
    public static void addTask(String task,int id)
    {
      try{
        Connection conn = DriverManager.getConnection(url, user, pass);
        Statement stmt = conn.createStatement();

        stmt.executeUpdate("INSERT INTO tasks (`user`, `task`, `progress`)VALUES("+id+",'"+task+"',"+0+")");
      } catch (Exception e) {System.out.println(e);}
      System.out.println("Task added successfully");
    }

   /* public static void CompleteTask(int id)
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
    }*/
    public static void deleteTask(int id,String task)
    {
        try{
            Connection conn = DriverManager.getConnection(url, user, pass);
            Statement stmt = conn.createStatement();
            stmt.executeUpdate("DELETE FROM tasks WHERE (user = "+id+" AND task = '"+task+"')");
        } catch (Exception e) {System.out.println(e);}
        System.out.println("Task Deleted successfully");
    }
}
