package GerenciadorFinanceiro.domain.Categoria;

public enum TransactionType {
    INCOME,
    EXPENSE;

    public static TransactionType fromString(String text){
        for(TransactionType type : TransactionType.values()){
            if(type.name().equalsIgnoreCase(text)){
                return type;

            }
        }
        throw new IllegalArgumentException("Tipo de transação incorreta, insira EXPENSE ou INCOME.");
    }
}

