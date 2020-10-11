import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        int choice;
        System.out.println("Press 1 to use console, 2 for GUI, other to quit");
        choice = scanner.nextInt();

        switch (choice){
            case 1:ConsoleDomino.run();break;
//            case 2:GUIDomino.run();break;
            default:break;
        }
    }
}
