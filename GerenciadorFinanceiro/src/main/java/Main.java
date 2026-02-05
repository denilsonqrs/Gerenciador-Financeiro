import GerenciadorFinanceiro.GerenciadorFinanceiro;
import GerenciadorFinanceiro.domain.Categoria.Categoria;
import GerenciadorFinanceiro.domain.Transacao.Transacao;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
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
                        "(B)List a Month Transactions\n" +
                        "(C)Create a category\n" +
                        "(D)Show all categories\n"+
                        "(Q)Sair\n" +
                        "\n" +
                        "Opção> ");
        return scanner.nextLine().toUpperCase().trim();
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
                break;
            case "Q":
                quit();
                break;
            default:
                System.out.println("Invalid Option");
        }

    }

    private static void addTransaction(GerenciadorFinanceiro manager, Scanner scanner) {
        try {
            System.out.println("Enter the transaction name: ");
            String name = scanner.nextLine();

            System.out.println("Enter the transaction value: ");
            String valueText = scanner.nextLine();
            BigDecimal value = new BigDecimal(valueText);

            System.out.println("Enter the transaction date(day/month/year): ");
            String dateStr = scanner.nextLine();

            System.out.println("Enter the transaction time(hour:minutes): ");
            String timeStr = scanner.nextLine();

            DateTimeFormatter df = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            DateTimeFormatter tf = DateTimeFormatter.ofPattern("HH:mm");
            LocalDate data = LocalDate.parse(dateStr, df);
            LocalTime hora = LocalTime.parse(timeStr, tf);
            LocalDateTime dateTime = LocalDateTime.of(data, hora);

            System.out.println("Enter the transaction category: ");
            String categoryName = scanner.nextLine();

            System.out.println("Enter a number of installments in this transaction: ");
            String intallmentsStr = scanner.nextLine();
            int installments = Integer.parseInt(intallmentsStr);
            manager.registerTransaction(name, value, dateTime, categoryName, installments);

        } catch (NumberFormatException e) {
            System.out.println("Invalid value! Please use numbers.");;
        } catch (DateTimeParseException e) {
            System.out.println("Invalid date or time input! Please enter the correct format.");
        } catch (IllegalArgumentException e){
            System.out.println(e.getMessage());
        }
    }
    private static void showTransactions(GerenciadorFinanceiro manager, Scanner scanner){
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            System.out.println("Enter the start date you want to show(Day/Month/Year): ");
            String startDateText = scanner.nextLine();
            LocalDate startDate = LocalDate.parse(startDateText, formatter);

            System.out.println("Enter the final date you want to show(Day/Month/Year): ");
            String finalDateText = scanner.nextLine();
            LocalDate finalDate = LocalDate.parse(finalDateText, formatter);

            if(manager.getTransactionsBetween(startDate, finalDate).isEmpty()){
                System.out.println("Você nao gastou nada nesse período.");
                return;
            }
           for(Transacao transacao: manager.getTransactionsBetween(startDate, finalDate)){
               System.out.println(transacao.toString());
           }
            System.out.println("Total: "+manager.getTotalValueBetween(startDate, finalDate));
        }catch (DateTimeParseException e) {
            System.out.println("Invalid date input! Plese enter the correct format(ex: 11/10/2006).");
        }catch (Exception e) {
            System.out.println("An unexpected error occurred.");
            e.printStackTrace();
        }

    }

    private static void createCategory(GerenciadorFinanceiro manager, Scanner scanner){
        try {
            System.out.println("Enter a category name: ");
            String categoryName = scanner.nextLine();

            System.out.println("Enter a transaction type(INCOME/EXPENSE): ");
            String typeName = scanner.nextLine();

            manager.addCategory(categoryName, typeName);

        }catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }catch (Exception e){
            System.out.println("An unexpected error occurred.");
        }
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