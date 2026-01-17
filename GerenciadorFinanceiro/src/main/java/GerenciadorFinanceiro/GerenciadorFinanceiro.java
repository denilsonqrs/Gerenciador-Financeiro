package GerenciadorFinanceiro;

import GerenciadorFinanceiro.domain.Categoria.Categoria;
import GerenciadorFinanceiro.domain.Categoria.TransactionType;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class GerenciadorFinanceiro {
    List<Categoria> categorias;
    public GerenciadorFinanceiro(){
        this.categorias = new ArrayList<>();
        initBaseCategories();

    }
    private void initBaseCategories() {
        if (this.categorias.isEmpty()){
            this.categorias.add(new Categoria("Food", "EXPENSE"));
            this.categorias.add(new Categoria("Entertainment", "EXPENSE"));
            this.categorias.add(new Categoria("Salary", "INCOME"));
            this.categorias.add(new Categoria("Investments", "INCOME"));
        }
    }
    public boolean categoryExist(String categoryName){
        for (Categoria categoria : categorias){
            if(categoria.getName().equals(categoryName)){
                return true;
            }
        }
        return false;

    }


    public void registerTransaction(String name, BigDecimal value, LocalTime time, LocalDate date, String categoryName, int installments){
        for(Categoria category: categorias){
            if(category.getName().equals(categoryName.toUpperCase())){
                category.adicionarTransacao(name, value, time, date, installments);
                return;
            }
        }
    }

    public void registerTransaction(String name, BigDecimal value, LocalDate date, LocalTime time, String categoryName){
        for(Categoria category: categorias){
            if(category.getName().equals(categoryName.toUpperCase())){
                category.adicionarTransacao(name, value, time, date);
                return;
            }
        }

    }
    public void addCategory(String categoryName, String typeName){
        for (Categoria category:categorias){
            if (category.getName().equals(categoryName.toUpperCase())){
                return;
            }
        }
        Categoria category = new Categoria(categoryName, typeName);
        categorias.add(category);
    }
    public List<Categoria> getCategories(){
        return List.copyOf(this.categorias);
    }
}
