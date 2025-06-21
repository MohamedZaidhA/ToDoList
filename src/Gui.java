import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Gui extends JFrame{
   Gui()
   {
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setTitle("To-Do List Application");
    this.setLayout(new GridBagLayout()); // Use GridBagLayout for center alignment

    GridBagConstraints gbc = new GridBagConstraints();
    gbc.gridx = 0;
    gbc.anchor = GridBagConstraints.CENTER;
    gbc.insets = new Insets(5, 0, 10, 0); // vertical spacing

    int i=0;
    String task;
    JCheckBox task1;
    JButton addTaskButton = new JButton("Add Task");
    JLabel title= new JLabel("To-Do List Application");
    title.setFont(title.getFont().deriveFont(30f));
    gbc.gridy = i;
    this.add(title, gbc);
    i++;
    addTaskButton.setFont(addTaskButton.getFont().deriveFont(20f));
    do{
        task = toDo.displayTasks(nameAndId.getOrCreateId("zaidh"), i-1);
        if(task.equals("No tasks found") || task.equals("Error in displaying tasks"))
            break;
        final String currentTask = task; // Make a final copy for lambda
        task1 = new JCheckBox(currentTask);
        task1.setFont(task1.getFont().deriveFont(20f));

        // Create a delete button for each task
        JButton deleteButton = new JButton("Delete");
        deleteButton.setFont(deleteButton.getFont().deriveFont(14f));

        // Panel to hold checkbox and delete button side by side
        javax.swing.JPanel taskPanel = new javax.swing.JPanel();
        taskPanel.setOpaque(false);
        taskPanel.add(task1);
        taskPanel.add(deleteButton);

        gbc.gridy = i;
        this.add(taskPanel, gbc);

        // Add action listener for delete
        int taskIndex = i - 1; // final for lambda
        deleteButton.addActionListener(e -> {
            toDo.deleteTask(nameAndId.getOrCreateId("zaidh"), currentTask); // Use your existing deleteTask method
            javax.swing.JOptionPane.showMessageDialog(this, "Task deleted!");
            this.dispose();
            new Gui(); // Refresh GUI
        });

        i++;
    }while(true);
    gbc.gridy = i;
    this.add(addTaskButton, gbc);
    addTaskButton.addActionListener(e -> {
        String newTask = javax.swing.JOptionPane.showInputDialog("Enter new task:");
        if (newTask != null && !newTask.trim().isEmpty()) {
            toDo.addTask(newTask, nameAndId.getOrCreateId("zaidh"));
            javax.swing.JOptionPane.showMessageDialog(this, "Task added successfully!");
            this.dispose();
            new Gui(); // Refresh the GUI to show the new task
        } else {
            javax.swing.JOptionPane.showMessageDialog(this, "Task cannot be empty!");
        }
    });
    this.setSize(400,400);
    this.setVisible(true);
   }
    public static void main(String[] args) {
         new Gui();
    }
}