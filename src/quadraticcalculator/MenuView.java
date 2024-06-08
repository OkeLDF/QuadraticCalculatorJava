import java.util.Scanner;

public abstract class MenuView {
    @SuppressWarnings("resource")
    public static String input(String message){
        Scanner scan = new Scanner(System.in);
        System.out.print(message);
        String buf = scan.nextLine();
        return buf.strip();
    }

    @SuppressWarnings("resource")
    public static int inputInt(String message){
        Scanner scan = new Scanner(System.in);
        System.out.print(message);
        int buf = scan.nextInt();
        return buf;
    }

    @SuppressWarnings("resource")
    public static double inputDouble(String message){
        Scanner scan = new Scanner(System.in);
        System.out.print(message);
        double buf = scan.nextDouble();
        return buf;
    }

    public static int requestIntByOptions(String[] options){
        for(String option : options){
            System.out.println(option);
        }
        return inputInt("\nSelecione uma opção: ");
    }

    public static void clearScreen(){
        System.out.print("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\033[2J");
    }
}
