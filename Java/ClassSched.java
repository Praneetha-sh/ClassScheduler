import java.util.*;

// Class to manage the timetable
class Scheduler {
    private String[][] timetable;
    private static final String[] days = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday"};
    private static final String[] times = {"8:30-9:30", "9:30-10:30", "10:30-11:00", "11:00-12:00", "12:00-1:00", "1:00-2:00", "2:00-3:00", "3:00-4:00","4:00-5:00"};

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
        if (timeIndex == 2 || timeIndex == 5) {
            System.out.println("This time slot is reserved for Break or Lunch. Please choose another time.");
            return false;
        }
        if (!timetable[dayIndex][timeIndex].equals("Free")) {
            System.out.println("This time slot is already taken. Please choose another time.");
            return false;
        }
        timetable[dayIndex][timeIndex] = subject;
        System.out.println(subject + " has been scheduled on " + days[dayIndex] + " at " + times[timeIndex] + ".");
        return true;
    }

    public boolean editClass(String newSubject, int dayIndex, int timeIndex) {
        if (timeIndex == 2 || timeIndex == 5) {
            System.out.println("This time slot is reserved for Break or Lunch. Please choose another time.");
            return false;
        }
        if (timetable[dayIndex][timeIndex].equals("Free")) {
            System.out.println("This time slot is free. There is no class to edit.");
            return false;
        }
        timetable[dayIndex][timeIndex] = newSubject;
        System.out.println("Class has been updated to " + newSubject + " on " + days[dayIndex] + " at " + times[timeIndex] + ".");
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

public class ClassSched {
    private Scheduler scheduler;

    public ClassSched() {
        scheduler = new Scheduler();
    }

    public void start() {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("\nWhat would you like to do?");
            System.out.println("1. Add a class");
            System.out.println("2. Edit a class");
            System.out.println("3. Print Time Table");
            System.out.println("4. Exit");

            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    addClass(sc);
                    break;
                case 2:
                    editClass(sc);
                    break;
                case 3:
                    printTable();
                    break;
                case 4:
                    System.out.println("Exiting program.");
                    sc.close();
                    System.exit(0);
                default:
                    System.out.println("Please enter a valid choice.");
            }
        }
    }

    private void addClass(Scanner sc) {
        try {
            System.out.println("Enter subject:");
            String subject = sc.nextLine();
            System.out.println("Choose day (0 for Monday, 1 for Tuesday, ... , 4 for Friday):");
            int dayIndex = sc.nextInt();
            System.out.println("Choose time slot:");
            String[] times = scheduler.getTimes();
            for (int i = 0; i < times.length; i++) {
                System.out.println(i + ": " + times[i]);
            }
            int timeIndex = sc.nextInt();
            sc.nextLine();
            scheduler.addClass(subject, dayIndex, timeIndex);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Invalid index. Please ensure you enter a valid day (0-4) and time slot (0-7).");
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter a number.");
            sc.next();
        }
    }

    private void editClass(Scanner sc) {
        try {
            System.out.println("Enter new subject:");
            String newSubject = sc.nextLine();
            System.out.println("Choose day (0 for Monday, 1 for Tuesday, ... , 4 for Friday):");
            int dayIndex = sc.nextInt();
            System.out.println("Choose time slot:");
            String[] times = scheduler.getTimes();
            for (int i = 0; i < times.length; i++) {
                System.out.println(i + ": " + times[i]);
            }
            int timeIndex = sc.nextInt();
            sc.nextLine();
            scheduler.editClass(newSubject, dayIndex, timeIndex);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Invalid index. Please ensure you enter a valid day (0-4) and time slot (0-7).");
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter a number.");
            sc.next();
        }
    }

    private void printTable() {
        String[][] timetable = scheduler.getTimetable();
        String[] days = scheduler.getDays();
        String[] times = scheduler.getTimes();

        System.out.printf("%-10s", " ");
        for (String time : times) {
            System.out.printf("| %-15s", time);
        }
        System.out.println();
        System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------");

        for (int i = 0; i < days.length; i++) {
            System.out.printf("%-10s", days[i]);
            for (int j = 0; j < times.length; j++) {
                System.out.printf("| %-15s", timetable[i][j]);
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        ClassSched classScheduler = new ClassSched();
        classScheduler.start();
    }
}
