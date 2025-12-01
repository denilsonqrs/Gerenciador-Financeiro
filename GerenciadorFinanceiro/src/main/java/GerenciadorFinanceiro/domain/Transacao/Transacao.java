package GerenciadorFinanceiro.domain.Transacao;

import java.math.BigDecimal;

public class Transacao {
    private String name;
    private BigDecimal value;
    private int parcel;
    private String description;

    public Transacao(String name, BigDecimal value, String description, int parcel){
        this.name = name;
        this.value = value;
        this.description = description;
        this.parcel = parcel;
    }
    public Transacao(String name, BigDecimal value, String description){
        this.name = name;
        this.value = value;
        this.description = description;
    }
    public Transacao(String name, BigDecimal value, int parcel){
        this.name = name;
        this.value = value;
        this.parcel = parcel;
    }
    public Transacao(String name, BigDecimal value){
        this.name = name;
        this.value = value;
    }
    public BigDecimal getValue(){
        return value;
    }

}

