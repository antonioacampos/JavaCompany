import java.time.LocalDate;
import java.util.Optional;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Company company = new Company();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n=== Menu ===");
            System.out.println("1. Contratar funcionário");
            System.out.println("2. Demitir funcionário");
            System.out.println("3. Listar todos os funcionários");
            System.out.println("4. Listar funcionários por cargo");
            System.out.println("5. Pagar salário a um funcionário");
            System.out.println("6. Aumentar salário de um funcionário");
            System.out.println("7. Média salarial por cargo");
            System.out.println("8. Média salarial em um período");
            System.out.println("9. Sair");
            System.out.print("Escolha uma opção: ");
            int option = scanner.nextInt();
            scanner.nextLine(); // Consumir a quebra de linha

            switch (option) {
                case 1:
                    System.out.print("Digite o id do funcionário: ");
                    String id = scanner.nextLine();
                    System.out.print("Digite o nome do funcionário: ");
                    String name = scanner.nextLine();
                    System.out.print("Digite o cargo do funcionário: ");
                    String jobTitle = scanner.nextLine();
                    System.out.print("Digite o salário do funcionário: ");
                    double salary = scanner.nextDouble();
                    try{
                        company.hire(id, name, jobTitle, salary);
                    } catch (Exception e){
                        System.err.println(e);
                    }
                    break;
                case 2:
                    System.out.print("Digite o id do funcionário a ser demitido: ");
                    String idToFire = scanner.nextLine();
                    try{
                        company.fire(idToFire);
                    } catch (Exception e){
                        System.err.println(e);
                    }

                    break;
                case 3:
                    System.out.println("Lista de todos os funcionários:");
                    company.getEmployees().forEach(System.out::println);
                    break;
                case 4:
                    System.out.print("Digite o cargo para listar os funcionários: ");
                    String jobTitleToList = scanner.nextLine();
                    System.out.println("Lista de funcionários com cargo '" + jobTitleToList + "':");
                    try{
                        company.getEmployees(jobTitleToList).forEach(System.out::println);
                    } catch (Exception e){
                        System.err.println(e);
                    }
                    break;
                case 5:
                    System.out.print("Digite o id do funcionário para pagar o salário: ");
                    String idPay = scanner.nextLine();
                    try{
                        company.pay(idPay);
                    } catch (Exception e){
                        System.err.println(e);
                    }
                    break;
                case 6:
                    System.out.print("Digite o id do funcionário para aumentar o salário: ");
                    String idToIncreaseSalary = scanner.nextLine();
                    System.out.print("Digite o novo salário: ");
                    double newSalary = scanner.nextDouble();
                    scanner.nextLine(); // Consumir a quebra de linha
                    try{
                        company.increaseSalary(idToIncreaseSalary, newSalary);
                    } catch (Exception e){
                        System.err.println(e);
                    }
                    break;
                case 7:
                    System.out.print("Digite o cargo para calcular a média salarial: ");
                    String jobTitleForAverage = scanner.nextLine();
                    try{
                        Optional<Double> avgSal = Optional.of(company.averageSalary(jobTitleForAverage));
                        System.out.println("Média salarial para o cargo '" + jobTitleForAverage + "': " +
                                avgSal);
                    } catch (Exception e){
                        System.err.println(e);
                    }
                    break;
                case 8:
                    System.out.print("Digite a data inicial (formato AAAA-MM-DD): ");
                    LocalDate startDate = LocalDate.parse(scanner.nextLine());
                    System.out.print("Digite a data final (formato AAAA-MM-DD): ");
                    LocalDate endDate = LocalDate.parse(scanner.nextLine());

                    try{
                        Optional<Double> avgSal = Optional.of(company.averageSalary(startDate, endDate));
                        System.out.println("Média salarial no período de " + startDate + " a " + endDate + ": " +
                                avgSal);
                    } catch (Exception e){
                        System.err.println(e);
                    }
                    break;
                case 9:
                    System.out.println("Saindo...");
                    return;
                default:
                    System.out.println("Opção inválida.");
            }
        }
    }
}
