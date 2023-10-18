import java.util.InputMismatchException;
import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {

        //fill seats as free
        char[][] seats = new char [10][10];
        int row;
        int column;
        Scanner input = new Scanner(System.in);
        boolean exit = false;
        // all seats begin free
        for(int f = 0; f < 10; f++) {
            for(int c = 0; c < 10; c++) {
                seats[f][c]='L';
            }
        }

        //show welcome message and menu options
        while(!exit) {
            showWelcomeMessage();
            showMenuOptions();
            int option = validateInput(input);
            validateMenuOption(option);
            input.nextLine();
            switch (option){
                case 1 -> {
                    printSeats(seats);
                    reserveASeat(input, seats);
                }
                case 2 ->{
                    printSeats(seats);
                }
                case 3 ->{
                    exit = exitQuestion(input);
                }
            }
        }
    }
    // functions
    private static boolean exitQuestion(Scanner input) {
        System.out.println("Are you sure you want to exit? [1] yes, another number, no");
        int option = validateInput(input);
        return option == 1;
    }

    private static void reserveASeat(Scanner input, char[][] seats) {

        boolean reservationCompleted = false;
        while(!reservationCompleted){

            System.out.println("Select a row");
            int row = validateInput(input)-1;
            //TODO
            if(row >= 0 && row <= 9){
                System.out.println("Select a column");
                int col = validateInput(input)-1;
                if(col >= 0 && col <= 9){
                    if(seats[row][col] != 'X'){
                        seats[row][col] = 'X';
                        System.out.println("Reservation has registered successfully");
                        reservationCompleted = true;
                    }else{
                        System.out.println("Sorry the seat is not available select another seat\n");
                        reservationCompleted = true;
                    }
                }
            }else System.out.println("Error Message\n");
        }
    }
    private static void validateMenuOption(int option){
        try{
            if(option < 1 || option > getMenuOptions().length){
                throw new RuntimeException("Invalid option!");
            }
        }catch (RuntimeException r){
            System.out.println(r.getMessage());
        }
    }
    private static int validateInput(Scanner input) {
        try{
            return input.nextInt();
        }catch (InputMismatchException i){
            input.next();
            System.out.println("input value must be a number");
            return 0;
        }
    }

    private static void showMenuOptions() {
        String[] options = getMenuOptions();
        System.out.println("Menu");
        for(int i = 0; i < options.length; i++){
            System.out.println("["+(i+1)+"] " + options[i]);
        }
        System.out.print("> ");
    }
    private static String[] getMenuOptions() {
        String option1 = "Reserve a seat";
        String option2 = "Show Seats";
        String option3 = "Exit";
        return new String[] {option1, option2, option3};
    }

    private static void showWelcomeMessage() {
        String welcomeMessage = "\nWelcome to reservation system";
        System.out.println(welcomeMessage);
    }

    //Print seats
    static void printSeats(char[][] seats){
        String blankSpace = "  ";
        //print column numbers
        System.out.print("     ");
        for (int i =0; i < 10; i++){
            System.out.print("["+ (i+1) + "] ");
        }
        System.out.println("\n");
        for(int r = 0; r < seats.length; r++) {
            if(r+1 == 10){
                blankSpace = " ";
            }
            System.out.print("[" + (r+1) + "]" + blankSpace);
            for(int c = 0; c < seats[r].length; c++) {

                System.out.print("[" + seats[r][c] + "] ");
            }
            System.out.println();
        }
        System.out.println("Free seats are marked with an L ");
    }
}
