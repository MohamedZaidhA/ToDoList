import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class nameAndId {
    static String url="jdbc:mysql://localhost:3306/zaidhDB1";
    static String user="root";
    static String pass="mysql14";
    public static void displayAll() {
        try {
            Connection conn = DriverManager.getConnection(url, user, pass);
            Statement stmt = conn.createStatement();
            ResultSet rs=stmt.executeQuery("SELECT * FROM id_table");
            System.out.println("Connection Established");
            System.out.println("ID | NAME");
            while(rs.next())
                System.out.println(rs.getInt("id")+" "+rs.getString("name"));
            conn.close();
        }catch(Exception e){System.out.println(e);}
    }
    public static int getOrCreateId(String name)
    {
        try {
            Connection conn = DriverManager.getConnection(url, user, pass);
            Statement stmt = conn.createStatement();
            ResultSet rs=stmt.executeQuery("SELECT id FROM id_table WHERE name='"+name+"'");
            if(rs.next()) {
                int id= rs.getInt(1);
                conn.close();
                return id;
            }
            else{
                System.out.println("NO ID found ,Creating new ID....");
                stmt = conn.createStatement();
                stmt.executeUpdate("INSERT INTO id_table(`name`) VALUES ('"+name+"')");
                conn.close();
                return getOrCreateId(name);
            }
        }catch(Exception e){System.out.println(e);}
        return 0;
    }
}
