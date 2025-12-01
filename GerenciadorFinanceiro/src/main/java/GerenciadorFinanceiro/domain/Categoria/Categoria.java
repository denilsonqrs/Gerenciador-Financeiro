package GerenciadorFinanceiro.domain.Categoria;

import GerenciadorFinanceiro.domain.Transacao.Transacao;

import java.math.BigDecimal;
import java.util.List;

public class Categoria {
    private String name;
    private String description;
    private List<Transacao> transacoes;

    public Categoria(String name){
        this.name = name;
    }
    public Categoria(String name, String description){
        this.name = name;
        this.description = description;
    }
    public void adicionarTransacao(String name, BigDecimal value, String description, int parcel){
        Transacao transacao = new Transacao(name, value, description, parcel);
        transacoes.add(transacao);
    }
    public void adicionarTransacao(String name, BigDecimal value, String description){
        Transacao transacao = new Transacao(name, value, description);
        transacoes.add(transacao);
    }
    public void adicionarTransacao(String name, BigDecimal value, int parcel){
        Transacao transacao = new Transacao(name, value, parcel);
        transacoes.add(transacao);
    }
    public void adicionarTransacao(String name, BigDecimal value){
        Transacao transacao = new Transacao(name, value);
        transacoes.add(transacao);
    }
    public String getName(){
        return name;
    }
}


