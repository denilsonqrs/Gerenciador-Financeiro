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

    public void registerTransaction(String name, BigDecimal value, LocalDateTime dateTime, String categoryName, int installments){
        if(!categorias.containsKey(categoryName)){
            throw new IllegalArgumentException("Categoria Inexistente");
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
            throw new IllegalArgumentException("Categoria já existe.");
        }
        Categoria category = new Categoria(categoryName, typeName);
        categorias.put(categoryName, category);
    }

    public List<Transacao> getTransactionsBetween(LocalDate startDate, LocalDate finalDate){
        List<Transacao> betweenTransactions = new ArrayList<>();
        if(finalDate.isBefore(startDate)){
            throw new IllegalArgumentException("Data Final antes da data Inicial");
        }
        for(Transacao transacao:transacoes){
            if ((transacao.getDate().isAfter(startDate) || transacao.getDate().isEqual(startDate)) &&
                    (transacao.getDate().isBefore(finalDate) || transacao.getDate().isEqual(finalDate))){
                betweenTransactions.add(transacao);
            }
        }
        return betweenTransactions;
    }

    public BigDecimal getTotalValueBetween(LocalDate startDate, LocalDate finalDate){
        BigDecimal total = new BigDecimal(0.0);

        for(Transacao transacao:getTransactionsBetween(startDate, finalDate)){
                total = total.add(transacao.getValue());
        }
        return total;
    }
    public List<Categoria> getCategories(){
        return  new ArrayList<Categoria>(categorias.values());
    }
}

