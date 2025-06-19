import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        System.out.print("Enter the name:");
        String name=sc.nextLine();
        int id= nameAndId.getOrCreateId(name);
        System.out.println("ID:"+id);
        toDo.displayTasks(id);
        while(true) {
            System.out.print("1.Enter the task,2.Update the task,3.Delete the task,4.Exit: ");
            int choice = sc.nextInt();
            switch (choice) {
                case 1:
                    toDo.addTask(id);
                    break;
                case 2:
                    toDo.CompleteTask(id);
                    break;
                case 3:
                    toDo.deleteTask(id);
                    break;
                case 4:
                    System.out.println("Exiting...");
                    System.exit(0);
            }
            toDo.displayTasks(id);
        }
    }
}