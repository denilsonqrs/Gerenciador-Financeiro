package GerenciadorFinanceiro;

import GerenciadorFinanceiro.domain.Categoria.Categoria;
import GerenciadorFinanceiro.domain.Categoria.TransactionType;
import GerenciadorFinanceiro.domain.Transacao.Transacao;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class GerenciadorFinanceiro {
    HashMap<String, Categoria> categorias;
    List<Transacao> transacoes;
    public GerenciadorFinanceiro(){
        this.categorias = new HashMap<>();
        initBaseCategories();

    }
    private void initBaseCategories() {
        if (this.categorias.isEmpty()){
            this.categorias.put("FOOD", new Categoria("Food", "EXPENSE"));
            this.categorias.put("ENTERTAINMENT", new Categoria("Entertainment", "EXPENSE"));
            this.categorias.put("SALARY", new Categoria("Salary", "INCOME"));
            this.categorias.put("INVESTIMENTS", new Categoria("Investments", "INCOME"));
        }
    }

    public void registerTransaction(String name, BigDecimal value, String dateStr, String timeStr, String categoryName, int installments){

        DateTimeFormatter df = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        DateTimeFormatter tf = DateTimeFormatter.ofPattern("HH:mm");
        LocalDate data = LocalDate.parse(dateStr, df);
        LocalTime hora = LocalTime.parse(timeStr, tf);
        LocalDateTime dateTime = LocalDateTime.of(data, hora);
        if(!categorias.containsKey(categoryName)){
            //joga a exceção la pro main de categoria nao existente
        }
        Transacao transacao = new Transacao(name, value, dateTime, categorias.get(categoryName));
        int left = 0;
        int right = transacoes.size();
        while (left < right) {
            int mid = (left + right) / 2;
            if (transacao.getDateTime().isBefore(transacoes.get(mid).getDateTime())) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        transacoes.add(left, transacao);
    }

    public void registerTransaction(String name, BigDecimal value, String dateStr, String timeStr, String categoryName){

        DateTimeFormatter df = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        DateTimeFormatter tf = DateTimeFormatter.ofPattern("HH:mm");
        LocalDate data = LocalDate.parse(dateStr, df);
        LocalTime hora = LocalTime.parse(timeStr, tf);
        LocalDateTime dateTime = LocalDateTime.of(data, hora);
        if(!categorias.containsKey(categoryName)){
            //joga a exceção la pro main de categoria nao existente
        }
        Transacao transacao = new Transacao(name, value, dateTime, categorias.get(categoryName));
        int left = 0;
        int right = transacoes.size();
        while (left < right) {
            int mid = (left + right) / 2;
            if (transacao.getDateTime().isBefore(transacoes.get(mid).getDateTime())) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        transacoes.add(left, transacao);
    }
    public void addCategory(String categoryName, String typeName){
        if(categorias.containsKey(categoryName)){
            return;
        }
        Categoria category = new Categoria(categoryName, typeName);
        categorias.put(categoryName, category);
    }
    public List<Categoria> getCategories(){
        return  new ArrayList<Categoria>(categorias.values());
    }
}
