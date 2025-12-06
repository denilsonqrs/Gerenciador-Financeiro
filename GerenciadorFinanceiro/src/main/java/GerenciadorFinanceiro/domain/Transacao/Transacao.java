package GerenciadorFinanceiro.domain.Transacao;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class Transacao {
    private String name;
    private BigDecimal value;
    private int parcel;
    private String description;
    private LocalDate date;
    private LocalTime time;

    public Transacao(String name, BigDecimal value, LocalTime time, LocalDate date, String description, int parcel) {
        this.name = name;
        this.value = value;
        this.description = description;
        this.parcel = parcel;
        this.date = date;
        this.time = time;
    }

    public Transacao(String name, BigDecimal value, LocalTime time, LocalDate date, String description) {
        this.name = name;
        this.value = value;
        this.description = description;
        this.date = date;
        this.time = time;
    }

    public Transacao(String name, BigDecimal value, LocalTime time, LocalDate date, int parcel) {
        this.name = name;
        this.value = value;
        this.parcel = parcel;
        this.date = date;
        this.time = time;
    }

    public Transacao(String name, BigDecimal value, LocalTime time, LocalDate date) {
        this.name = name;
        this.value = value;
        this.date = date;
        this.time = time;
    }

    public BigDecimal getValue() {
        return value;
    }

    public int getMonth() {
        return date.getMonthValue();
    }

    public LocalDateTime getDateTime() {
        LocalDateTime dateTime = LocalDateTime.of(date, time);
        return dateTime;
    }

    @Override
    public String toString() {
        return "Nome: " + name + "\n" + "Valor: R$" + value + "\n" + "Data e Hora: " + date + " " + time;
    }
}

