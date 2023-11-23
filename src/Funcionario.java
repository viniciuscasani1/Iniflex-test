import java.math.BigDecimal;
import java.time.LocalDate;

public class Funcionario extends Pessoa {
    BigDecimal salario;
    String funcao;

    Funcionario(String nome, LocalDate dataNascimento, BigDecimal salario, String funcao) {
        super(nome, dataNascimento);
        this.salario = salario;
        this.funcao = funcao;
    }

    @Override
    public String toString() {
        return super.toString() +
                ", Salário: " + String.format("%,.2f", salario) +
                ", Função: " + funcao;
    }
}