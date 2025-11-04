import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import javax.swing.*;

class Scheduler {
    private String[][] timetable;
    private static final String[] days = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday"};
    private static final String[] times = {"8:30-9:30", "9:30-10:30", "10:30-11:00", "11:00-12:00", "12:00-1:00", "1:00-2:00", "2:00-3:00", "3:00-4:00"};

    public Scheduler() {
        timetable = new String[days.length][times.length];
        for (int i = 0; i < days.length; i++) {
            Arrays.fill(timetable[i], "Free");
        }
        setSpecificSlots();
    }

    private void setSpecificSlots() {
        for (int i = 0; i < days.length; i++) {
            timetable[i][2] = "Break";
            timetable[i][5] = "Lunch";
        }
    }

    public boolean addClass(String subject, int dayIndex, int timeIndex) {
        if (timeIndex == 2 || timeIndex == 5 || !timetable[dayIndex][timeIndex].equals("Free")) {
            return false;
        }
        timetable[dayIndex][timeIndex] = subject;
        return true;
    }

    public boolean editClass(String subject, int dayIndex, int timeIndex) {
        if (timeIndex == 2 || timeIndex == 5) {
            return false;
        }
        timetable[dayIndex][timeIndex] = subject;
        return true;
    }

    public String[][] getTimetable() {
        return timetable;
    }

    public String[] getDays() {
        return days;
    }

    public String[] getTimes() {
        return times;
    }
}

public class ClassSchedGUI extends JFrame {
    private Scheduler scheduler;
    private JTextField subjectField;
    private JComboBox<Integer> dayComboBox;
    private JComboBox<Integer> timeComboBox;
    private JTextArea timetableArea;

    public ClassSchedGUI() {
        scheduler = new Scheduler();
        setupUI();
    }

    private void setupUI() {
        setTitle("Class Scheduler");
        setSize(600, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new FlowLayout());

        subjectField = new JTextField(15);
        inputPanel.add(new JLabel("Subject:"));
        inputPanel.add(subjectField);

        dayComboBox = new JComboBox<>(new Integer[]{0, 1, 2, 3, 4});
        inputPanel.add(new JLabel("Day (0-4):"));
        inputPanel.add(dayComboBox);

        timeComboBox = new JComboBox<>(new Integer[]{0, 1, 3, 4, 6, 7});
        inputPanel.add(new JLabel("Time Slot (0-7):"));
        inputPanel.add(timeComboBox);

        JButton addButton = new JButton("Add Class");
        addButton.addActionListener(new AddClassListener());
        inputPanel.add(addButton);

        JButton editButton = new JButton("Edit Class");
        editButton.addActionListener(new EditClassListener());
        inputPanel.add(editButton);

        JButton printButton = new JButton("Print Timetable");
        printButton.addActionListener(new PrintTimetableListener());
        inputPanel.add(printButton);

        add(inputPanel, BorderLayout.NORTH);

        timetableArea = new JTextArea();
        timetableArea.setEditable(false);
        add(new JScrollPane(timetableArea), BorderLayout.CENTER);
    }

    private class AddClassListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String subject = subjectField.getText();
            int dayIndex = dayComboBox.getSelectedIndex();
            int timeIndex = timeComboBox.getSelectedIndex();
            boolean success = scheduler.addClass(subject, dayIndex, timeIndex);
            if (success) {
                JOptionPane.showMessageDialog(ClassSchedGUI.this, "Class added successfully.");
                subjectField.setText("");
            } else {
                JOptionPane.showMessageDialog(ClassSchedGUI.this, "Failed to add class. Check time slot.");
            }
        }
    }

    private class EditClassListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String subject = subjectField.getText();
            int dayIndex = dayComboBox.getSelectedIndex();
            int timeIndex = timeComboBox.getSelectedIndex();
            boolean success = scheduler.editClass(subject, dayIndex, timeIndex);
            if (success) {
                JOptionPane.showMessageDialog(ClassSchedGUI.this, "Class edited successfully.");
                subjectField.setText("");
            } else {
                JOptionPane.showMessageDialog(ClassSchedGUI.this, "Failed to edit class. Check time slot.");
            }
        }
    }

    private class PrintTimetableListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            StringBuilder sb = new StringBuilder();
            String[][] timetable = scheduler.getTimetable();
            String[] days = scheduler.getDays();
            String[] times = scheduler.getTimes();

            sb.append(String.join(" | ", times)).append("\n");
            sb.append("-------------------------------------------------------\n");
            for (int i = 0; i < days.length; i++) {
                sb.append(days[i]).append(" | ");
                for (int j = 0; j < times.length; j++) {
                    sb.append(timetable[i][j]).append(" | ");
                }
                sb.append("\n");
            }
            timetableArea.setText(sb.toString());
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ClassSchedGUI gui = new ClassSchedGUI();
            gui.setVisible(true);
        });
    }
}
