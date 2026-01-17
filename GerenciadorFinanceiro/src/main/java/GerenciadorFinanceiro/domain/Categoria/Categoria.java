package GerenciadorFinanceiro.domain.Categoria;

import GerenciadorFinanceiro.domain.Transacao.Transacao;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class Categoria {
    private String name;
    private TransactionType type;
    private List<Transacao> transacoes;

    public Categoria(String name, String typeName) {
        this.name = name.toUpperCase();
        this.type = TransactionType.valueOf(typeName.toUpperCase());
        this.transacoes = new ArrayList<>();
    }

    public void adicionarTransacao(String name, BigDecimal value, LocalTime time, LocalDate date, int parcel) {
        Transacao transacao = new Transacao(name, value, time, date, parcel);
        addTransacao(transacao);
    }

    public void adicionarTransacao(String name, BigDecimal value, LocalTime time, LocalDate date) {
        Transacao transacao = new Transacao(name, value, time, date);
        addTransacao(transacao);
    }

    private void addTransacao(Transacao transacao) {
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

    public List<Transacao> getTransacoes(int month) {
        List<Transacao> transacaoList = new ArrayList<>();
        for (Transacao transacao : transacoes) {
            if (transacao.getMonth() == month) {
                transacaoList.add(transacao);
            } else if (transacao.getMonth() > month) {
                break;
            }
        }
        return transacaoList;
    }

    public List<Transacao> getTransacoes() {
        return this.transacoes;
    }

    public String getName() {
        return name;
    }
    @Override
    public String toString(){
        return name+"("+type+")";
    }
}