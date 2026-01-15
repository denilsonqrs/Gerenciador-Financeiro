import GerenciadorFinanceiro.GerenciadorFinanceiro;
import GerenciadorFinanceiro.domain.Categoria.Categoria;
import GerenciadorFinanceiro.domain.Transacao.Transacao;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.LocalDate;
import java.util.GregorianCalendar;
import java.util.List;
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
            case "B":
                showTransactions(manager, scanner);
                break;
            case "C":
                createCategory(manager, scanner);
                break;
            case "D":
                showCategories(manager, scanner);
            case "S":
                quit();
                break;
            default:
                System.out.println("Invalid Option");
        }

    }


    private static void addTransaction(GerenciadorFinanceiro manager, Scanner scanner) {
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
        System.out.println("Enter the transaction category: ");
        String categoryName = scanner.nextLine();
        System.out.println("This transaction is intallment?(y/n): ");
        String choice = scanner.nextLine().toLowerCase();
        if (choice.equals("s")){
            System.out.println("How many installments?: ");
            int installments = Integer.parseInt(scanner.nextLine());
            manager.registerTransaction(name, value, date, time, categoryName, installments);
        }else{
            manager.registerTransaction(name, value, date, time, categoryName);
        }
    }
    private static void showTransactions(GerenciadorFinanceiro manager, Scanner scanner){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        System.out.println("Enter the start date you want to show(Day/Month/Year): ");
        String startDateText = scanner.nextLine();
        LocalDate startDate = LocalDate.parse(startDateText, formatter);
        System.out.println("Enter the final date you want to show(Day/Month/Year): ");
        String finalDateText = scanner.nextLine();
        LocalDate finalDate = LocalDate.parse(finalDateText, formatter);
        for (Categoria category:manager.getCategories()){
            for (Transacao transaction:category.getTransacoes()){
                LocalDate transactionDate = transaction.getDate();
                if((startDate.isAfter(transactionDate) || startDate.isEqual(transactionDate)) && (finalDate.isBefore(transactionDate) || finalDate.isEqual(transactionDate))){
                    System.out.println(transaction.toString());
                }
            }
        }

    }

    private static void createCategory(GerenciadorFinanceiro manager, Scanner scanner){
        System.out.println("Enter a category name: ");
        String categoryName = scanner.nextLine();
        System.out.println("Enter a transaction type(INCOME/EXPENSE): ");
        String typeName = scanner.nextLine();
        System.out.println(manager.addCategory(categoryName, typeName));
    }

    private static void showCategories(GerenciadorFinanceiro manager, Scanner scanner){
        for (Categoria category: manager.getCategories()){
            System.out.println(category.toString());
        }
    }
    private static void quit(){
        System.out.println("Thanks, bye!");
        System.exit(0);
    }

}