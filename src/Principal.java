import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class Principal {
    public static void main(String[] args) {
        List<Funcionario> funcionarios = populeteAndGetFuncionarioList();

        funcionarios.removeIf(funcionario -> funcionario.nome.equals("João"));

        printFuncionarios("Funcionários:", funcionarios);

        funcionarios.forEach(funcionario -> funcionario.salario = funcionario.salario.multiply(new BigDecimal("1.1")));

        groupByFuncao(funcionarios);

        printFuncinariosFromOctoberAndDecember(funcionarios);

        printOldestFuncionario(funcionarios);

        printFuncionariosInAlphabeticOrder(funcionarios);

        getAndPrintTotalSalarios(funcionarios);

        getAndPrintNumberOfMinimumSalary(funcionarios);
    }

    private static void getAndPrintNumberOfMinimumSalary(List<Funcionario> funcionarios) {
        System.out.println("\nSalários em termos de salário mínimo:");
        BigDecimal salarioMinimo = new BigDecimal("1212.00");
        funcionarios.forEach(funcionario -> {
            BigDecimal salariosMinimos = funcionario.salario.divide(salarioMinimo, 2, BigDecimal.ROUND_HALF_UP);
            System.out.println(funcionario.nome + ": " + String.format("%,.2f", salariosMinimos) + " salários mínimos");
        });
    }

    private static void getAndPrintTotalSalarios(List<Funcionario> funcionarios) {
        BigDecimal totalSalarios = funcionarios.stream()
                .map(funcionario -> funcionario.salario)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        System.out.println("\nTotal dos salários dos funcionários: " + String.format("%,.2f", totalSalarios));
    }

    private static void printFuncionariosInAlphabeticOrder(List<Funcionario> funcionarios) {
        System.out.println("\nFuncionários em ordem alfabética:");
        funcionarios.stream()
                .sorted(Comparator.comparing(funcionario -> funcionario.nome))
                .forEach(System.out::println);
    }

    private static void printOldestFuncionario(List<Funcionario> funcionarios) {
        Funcionario maisVelho = Collections.min(funcionarios, Comparator.comparing(funcionario -> funcionario.dataNascimento));
        System.out.println("\nFuncionário mais velho:");
        System.out.println("Nome: " + maisVelho.nome + ", Idade: " + maisVelho.calcularIdade());
    }

    private static void printFuncinariosFromOctoberAndDecember(List<Funcionario> funcionarios) {
        System.out.println("\nFuncionários com aniversário em outubro (10) e dezembro (12):");
        funcionarios.stream()
                .filter(funcionario -> funcionario.dataNascimento.getMonthValue() == 10 || funcionario.dataNascimento.getMonthValue() == 12)
                .forEach(System.out::println);
    }

    private static void groupByFuncao(List<Funcionario> funcionarios) {
        Map<String, List<Funcionario>> funcionariosPorFuncao = funcionarios.stream()
                .collect(Collectors.groupingBy(funcionario -> funcionario.funcao));

        System.out.println("\nFuncionários agrupados por função:");
        funcionariosPorFuncao.forEach((funcao, lista) -> printFuncionarios("Função: " + funcao, lista));
    }

    private static void printFuncionarios(String x, List<Funcionario> funcionarios) {
        System.out.println(x);
        funcionarios.forEach(System.out::println);
    }

    private static List<Funcionario> populeteAndGetFuncionarioList() {
        List<Funcionario> funcionarios = new ArrayList<>();

        funcionarios.add(new Funcionario("Maria", LocalDate.of(2000, 10, 18), new BigDecimal("2009.44"), "Operador"));
        funcionarios.add(new Funcionario("João", LocalDate.of(1990, 5, 12), new BigDecimal("2284.38"), "Operador"));
        funcionarios.add(new Funcionario("Caio", LocalDate.of(1961, 5, 2), new BigDecimal("9836.14"), "Coordenador"));
        funcionarios.add(new Funcionario("Miguel", LocalDate.of(1988, 10, 14), new BigDecimal("19119.88"), "Diretor"));
        funcionarios.add(new Funcionario("Alice", LocalDate.of(1995, 1, 5), new BigDecimal("2234.68"), "Recepcionista"));
        funcionarios.add(new Funcionario("Heitor", LocalDate.of(1999, 11, 19), new BigDecimal("1582.72"), "Operador"));
        funcionarios.add(new Funcionario("Arthur", LocalDate.of(1993, 3, 31), new BigDecimal("4071.84"), "Contador"));
        funcionarios.add(new Funcionario("Laura", LocalDate.of(1994, 7, 8), new BigDecimal("3017.45"), "Gerente"));
        funcionarios.add(new Funcionario("Heloísa", LocalDate.of(2003, 5, 24), new BigDecimal("1606.85"), "Eletricista"));
        funcionarios.add(new Funcionario("Helena", LocalDate.of(1996, 9, 2), new BigDecimal("2799.93"), "Gerente"));
        return funcionarios;
    }
}
