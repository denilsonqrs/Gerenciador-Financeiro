import GerenciadorFinanceiro.GerenciadorFinanceiro;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.LocalDate;
import java.util.GregorianCalendar;
import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        GerenciadorFinanceiro gf = new GerenciadorFinanceiro();
        Scanner scanner = new Scanner(System.in);
        while (true){
            String choice = menu(scanner);

        }
    }
    private static String menu(Scanner scanner) {
        System.out.println(
                "\n---\nMENU\n" +
                        "(A)Adicionar filme\n" +
                        "(M)Mostrar todos\n" +
                        "(D)Detalhar filme\n" +
                        "(S)Sair\n" +
                        "\n" +
                        "Opção> ");
        return scanner.next().toUpperCase();
    }
    private static void command(GerenciadorFinanceiro gf, String choice, Scanner scanner){
        switch(choice){
            case "A":
                addTransaction(gf, scanner);
                break;
            case "L":
                //listTransaction(gf, scanner);
                break;
            case "Q":
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
    private static void addTransaction(GerenciadorFinanceiro gf, Scanner scanner){
        System.out.println("Enter the transaction name: ");
        String name = scanner.next();
        System.out.println("Enter the transaction value: ");
        String valueText = scanner.next();
        BigDecimal value = new BigDecimal(valueText);
        System.out.println("Enter the transaction date(day/month/year): ");
        String dateText = scanner.next();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate date = LocalDate.parse(dateText, formatter);
        System.out.println("Enter the transaction time(hour:minutes): ");
        String timeText = scanner.next();
        LocalTime time = LocalTime.parse(timeText);
        System.out.println("Enter the category of this transaction: ");
        String categoryName = scanner.next();
        if(!gf.categoryExist(categoryName)){
            System.out.println("Category not found, create a new category to add this transaction: ");
            return;
        }
    }

}