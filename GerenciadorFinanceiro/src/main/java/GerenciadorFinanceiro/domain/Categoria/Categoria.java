package GerenciadorFinanceiro.domain.Categoria;

import GerenciadorFinanceiro.domain.Transacao.Transacao;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class Categoria {
    private String name;
    private String description;
    private List<Transacao> transacoes;

    public Categoria(String name) {
        this.name = name;
    }

    public Categoria(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public void adicionarTransacao(String name, BigDecimal value, LocalTime time, LocalDate date, String description, int parcel) {
        Transacao transacao = new Transacao(name, value, time, date, description, parcel);
        addTransacao(transacao);
    }

    public void adicionarTransacao(String name, BigDecimal value, LocalTime time, LocalDate date, String description) {
        Transacao transacao = new Transacao(name, value, time, date, description);
        addTransacao(transacao);
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

    public String getName() {
        return name;
    }
}
