package GerenciadorFinanceiro.domain.Transacao;

import GerenciadorFinanceiro.domain.Categoria.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class Transacao {
    private String name;
    private BigDecimal value;
    private int installments;
    private LocalDateTime dateTime;
    private Categoria category;

    public Transacao(String name, BigDecimal value, LocalDateTime dateTime, Categoria category, int installments) {
        this.name = name;
        this.value = value;
        this.installments = installments;
        this.dateTime = dateTime;
        this.category = category;
    }

    public Transacao(String name, BigDecimal value, LocalDateTime dateTime, Categoria category) {
        this.name = name;
        this.value = value;
        this.dateTime = dateTime;
        this.category = category;
    }

    public BigDecimal getValue() {
        return value;
    }

    public int getMonth() {
        return getDateTime().getMonthValue();
    }
    public LocalDate getDate(){
        return dateTime.toLocalDate();
    }
    public LocalDateTime getDateTime() {
        return dateTime;
    }

    @Override
    public String toString() {
        return "Nome: " + name + "\n" + "Valor: R$" + value + "\n" + "Data e Hora: " + dateTime.toLocalDate() + " " + dateTime.toLocalTime();
    }
}
