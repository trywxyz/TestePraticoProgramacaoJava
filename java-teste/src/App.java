import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class App {

    public static void main(String[] args) {
        
        List<Funcionario> funcionarios = new ArrayList<>();
        funcionarios.add(new Funcionario("Maria", LocalDate.of(2000, 10, 18), new BigDecimal("2009.44"), "Operador"));
        funcionarios.add(new Funcionario("João", LocalDate.of(1990, 05, 12), new BigDecimal("2284.38"), "Operador"));
        funcionarios.add(new Funcionario("Caio", LocalDate.of(1961, 05, 02), new BigDecimal("9836.14"), "Coordenador"));
        funcionarios.add(new Funcionario("Miguel", LocalDate.of(1968, 10, 14), new BigDecimal("19119.88"), "Diretor"));
        funcionarios.add(new Funcionario("Alice", LocalDate.of(1995, 01, 05), new BigDecimal("2234.68"), "Recepcionista"));
        funcionarios.add(new Funcionario("Heitor", LocalDate.of(1999, 11, 19), new BigDecimal("1582.72"), "Operador"));
        funcionarios.add(new Funcionario("Arthur", LocalDate.of(1993, 03, 31), new BigDecimal("4071.84"), "Contador"));
        funcionarios.add(new Funcionario("Laura", LocalDate.of(1994, 07, 8), new BigDecimal("3017.45"), "Gerente"));
        funcionarios.add(new Funcionario("Heloisa", LocalDate.of(2003, 05, 24), new BigDecimal("1606.85"), "Eletricista"));
        funcionarios.add(new Funcionario("Helena", LocalDate.of(1996, 9, 02), new BigDecimal("2799.93"), "Gerente"));

        
        funcionarios.removeIf(f -> f.getNome().equals("João"));

        
        System.out.println("\nFuncionários:");
        funcionarios.forEach(f -> {
            System.out.println(String.format("%s - %s - %s - %s",
                    f.getNome(),
                    f.getDataNascimento().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")),
                    f.getSalario().toPlainString(),
                    f.getFuncao()));
        });

        
        funcionarios.forEach(f -> f.setSalario(f.getSalario().multiply(new BigDecimal("1.10"))));

        
        Map<Object, List<Funcionario>> funcionariosPorFuncao = funcionarios.stream()
                .collect(Collectors.groupingBy(Funcionario::getFuncao));

        
        System.out.println(" \n Funcionários agrupados por função:");
        funcionariosPorFuncao.forEach((funcao, funcionariosAgrupados) -> {
            System.out.println(String.format("Função: %s", funcao));
            funcionariosAgrupados.forEach(f -> {
                System.out.println(String.format("\t%s - %s", f.getNome(), f.getFuncao()));
            });
        });

        
        System.out.println(" \n Funcionários com aniversário em outubro ou dezembro:");
        funcionarios.stream()
                .filter(f -> f.getDataNascimento().getMonthValue() == 10 || f.getDataNascimento().getMonthValue() == 12)
                .forEach(f -> {
                    System.out.println(String.format("%s - %s", f.getNome(), f.getDataNascimento().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))));
                });

        // 3.9 - Imprimir funcionário com maior idade
        Funcionario funcionarioMaisVelho = funcionarios.stream()
                .min(Comparator.comparing(Funcionario::getDataNascimento))
                .get();
        System.out.println("\n Funcionário mais velho:");
        System.out.println(String.format("%s - %s", funcionarioMaisVelho.getNome(), funcionarioMaisVelho.getDataNascimento().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))));


        // 3.10 - Imprimir funcionários por ordem alfabética
        System.out.println(" \n Funcionários por ordem alfabética:");
        funcionarios.stream()
                .sorted(Comparator.comparing(Funcionario::getNome))
                .forEach(f -> {
                    System.out.println(f.getNome());
                });

        // 3.11 - Imprimir total dos salários
        BigDecimal totalSalarios = funcionarios.stream()
                .map(Funcionario::getSalario)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        System.out.println("\nTotal dos salários:");
        System.out.println(String.format("R$ %.2f", totalSalarios));

        

        // 3.12 - Imprimir quantos salários mínimos ganha cada funcionário
        BigDecimal salarioMinimo = new BigDecimal("1212.00");
        System.out.println("\n Salários mínimos ganhos por funcionário:");
        funcionarios.forEach(f -> {
            System.out.println(String.format("%s - %s", f.getNome(), f.getSalario().divide(salarioMinimo, 2, BigDecimal.ROUND_HALF_UP)));
        });
    }
}

