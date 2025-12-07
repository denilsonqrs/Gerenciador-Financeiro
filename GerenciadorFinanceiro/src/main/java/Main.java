import GerenciadorFinanceiro.GerenciadorFinanceiro;
import GerenciadorFinanceiro.domain.Categoria.Categoria;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.LocalDate;
import java.util.GregorianCalendar;
import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        GerenciadorFinanceiro manager = new GerenciadorFinanceiro();
        Scanner scanner = new Scanner(System.in);
        while (true){
            String choice = menu(scanner);
            command(manager, choice, scanner);
        }
    }
    private static String menu(Scanner scanner) {
        System.out.println(
                "\n---\nMENU\n" +
                        "(A)Add a new transaction\n" +
                        "(M)List a Month Transactions\n" +
                        "(D)Create a category\n" +
                        "(S)Sair\n" +
                        "\n" +
                        "Opção> ");
        return scanner.nextLine().toUpperCase();
    }
    private static void command(GerenciadorFinanceiro manager, String choice, Scanner scanner){
        switch(choice){
            case "A":
                addTransaction(manager, scanner);
                break;
            case "L":
                //listTransaction(manager, scanner);
                break;
            case "C":
                //createCategory(manager, scanner);
                break;
            case "S":
                quit();
                break;
            default:
                System.out.println("Invalid Option");
        }

    }
    private static void quit(){
        System.out.println("Thanks, bye!");
        System.exit(0);
    }
    private static void addTransaction(GerenciadorFinanceiro manager, Scanner scanner){
        System.out.println("Enter the transaction name: ");
        String name = scanner.nextLine();
        System.out.println("Enter the transaction value: ");
        String valueText = scanner.nextLine();
        BigDecimal value = new BigDecimal(valueText);
        System.out.println("Enter the transaction date(day/month/year): ");
        String dateText = scanner.nextLine();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate date = LocalDate.parse(dateText, formatter);
        System.out.println("Enter the transaction time(hour:minutes): ");
        String timeText = scanner.nextLine();
        LocalTime time = LocalTime.parse(timeText);
        String categoryName;
        categoryloop:
        while(true) {
            System.out.println("Enter the category of this transaction: ");
            categoryName = scanner.nextLine();
            if (!manager.categoryExist(categoryName)) {
                System.out.println("Category not found.");
                while (true) {
                    System.out.println("C. Create a new Category.\nS. Show all categories.\nT. Try Again.\nQ. Quit.\n\nChoice: ");
                    String choice = scanner.nextLine().toUpperCase();
                    switch (choice) {
                        case "C":
                            addCategory(manager, scanner);
                            continue categoryloop;
                        case "S":
                            for (Categoria category : manager.getCategories()) {
                                System.out.println(category.getName());
                            }
                            break;
                        case "T":
                            continue categoryloop;
                        case "Q":
                            return;
                        default:
                            System.out.println("Invalid Option, Enter Again.");
                    }
                }
            }else{
                break;
            }

        }

        String description = "";
        boolean isRunning = true;
        while (isRunning) {
            System.out.println("Want add Description?(y/n): ");
            String choice = scanner.nextLine().toUpperCase();
            switch (choice) {
                case "Y":
                    System.out.println("Enter a description: ");
                    description = scanner.nextLine();
                    isRunning = false;
                    break;
                case "N":
                    isRunning = false;
                    break;
                default:
                    System.out.println("Invalid Option, enter again.");
            }
        }
        int installments = 0;
        isRunning = true;
        while(isRunning){
            System.out.println("Is this transaction paid in installments?(y/n): ");
            String choice = scanner.nextLine().toUpperCase();
            switch (choice) {
                case "Y":
                    System.out.println("Enter a number of installments?(y/n): ");
                    installments = scanner.nextInt();
                    isRunning = false;
                    break;
                case "N":
                    isRunning = false;
                    break;
                default:
                    System.out.println("Invalid Option, enter again.");
            }
        }
        if (!description.isEmpty() && installments > 0){
            manager.registerTransaction(name, value, date, time, categoryName, description, installments);
        }else if(!description.isEmpty()){
            manager.registerTransaction(name, value, date, time, categoryName, description);
        }else if (installments > 0) {
            manager.registerTransaction(name, value, date, time, categoryName, installments);
        }else{
            manager.registerTransaction(name, value, date, time, categoryName);
        }

    }
    public static void addCategory(GerenciadorFinanceiro manager, Scanner scanner){}

}