package GerenciadorFinanceiro;

import GerenciadorFinanceiro.domain.Categoria.Categoria;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GerenciadorFinanceiro {
    List<Categoria> categorias;
    public GerenciadorFinanceiro(){
        this.categorias = new ArrayList<>();

    }
    public boolean categoryExist(String categoryName){
        for (Categoria categoria : categorias){
            if(categoria.getName().equals(categoryName)){
                return true;
            }
        }
        return false;

    }
    public List<Categoria> getCategories(){
        return List.copyOf(this.categorias);
    }
}
