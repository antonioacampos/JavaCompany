import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

public class Company {
    private List<Employee> employees = new ArrayList<>();

    //public Company(List<Employee> employees) {
       // this.employees = employees;
    //}

    public void hire (String id, String name, String jobTitle, double salary) throws Exception {
        if(id == null) throw new NullPointerException("id value must not be null!");
        if(name == null) throw new NullPointerException("name value must not be null!");
        if(jobTitle == null) throw new NullPointerException("jobTitle value must not be null!");

        if (employees.stream().anyMatch(employee -> employee.getId().equals(id)))
            throw new IllegalArgumentException("employee with the same id already exists!");
        employees.add(new Employee(id, name, jobTitle, salary, LocalDate.now()));
    }

    public void fire(String id) throws Exception{
        if(id == null) throw new NullPointerException("id value must not be null!");
        boolean removed = employees.removeIf(employee -> employee.getId().equals(id));
        if (!removed) throw new IllegalArgumentException("employee with this id does not exist!");
        }

    public List<Employee> getEmployees() {
        return employees;
    }

    public List<Employee> getEmployees(String jobTitle) throws Exception {
        if(jobTitle == null) throw new NullPointerException("jobTitle value must not be null!");

        return employees.stream()
                .filter(employee -> employee.getJobTitle().equals(jobTitle)).toList();
    }

    public void pay(String id) throws Exception{
        if(id == null) throw new NullPointerException("id value must not be null!");
        employees.stream()
                .filter(employee -> employee.getId().equals(id)).findFirst()
                .ifPresent(employee -> {employee.createPaycheck(LocalDate.now());
                });
    }

    public void increaseSalary(String id, double salary) throws Exception{
        if(id == null) throw new NullPointerException("id value must not be null!");
      /*  employees.stream()
                .filter(employee -> employee.getId().equals(id))
                .findFirst().ifPresent(employee -> {employee.setSalary(salary);});*/
        Optional<Employee> employeeToPay = employees.stream()
                .filter(employee -> employee.getId().equals(id))
                .findFirst();

        if (employeeToPay.isPresent()) {
            employeeToPay.get().createPaycheck(LocalDate.now());
        } else {
            throw new IllegalArgumentException("Employee with the given ID does not exist!");
        }
    }

    public double averageSalary(String jobTitle) throws Exception{
        if(jobTitle == null) throw new NullPointerException("jobTitle value must not be null!");
        return employees.stream()
                .filter(employee -> employee.getJobTitle().equals(jobTitle))
                .mapToDouble(Employee::getSalary)
                .average()
                .orElse(0.0);
    }

    public double averageSalary(LocalDate inicio, LocalDate fim) throws Exception {
        if (inicio == null || fim == null) throw new NullPointerException("dates must not be null!");
        double avgSal = 0;
        double numSal = 0;
        Iterator<Employee> iterator = employees.iterator();
        while (iterator.hasNext()) {
            Employee employee = iterator.next();
            Iterator<Paycheck> paycheckIterator = employee.iteratorPaychecks();
            while (paycheckIterator.hasNext()) {
                Paycheck paycheck = paycheckIterator.next();
                if (paycheck.getDate().isAfter(inicio) && paycheck.getDate().isBefore(fim)) {
                    avgSal += paycheck.getSalary();
                    numSal++;
                }
            }
        }
        if (numSal==0) throw new ArithmeticException("no salary between these dates");
        return avgSal / numSal;
    }
}
