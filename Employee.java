import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

public class Employee {
    private String id;
    private String name;
    private String jobTitle;
    private double salary;
    private LocalDate dateOfEmployment;

    private List<Paycheck> paychecks = new ArrayList<>();
    // Constructor
    public Employee(String id, String name, String jobTitle, double salary, LocalDate dateOfEmployment) {
        this.id = id;
        this.name = name;
        this.jobTitle = jobTitle;
        this.salary = salary;
        this.dateOfEmployment = dateOfEmployment;
    }

    public void createPaycheck(LocalDate payday){ // Notice that the method restricts the form in which paychecks are added
        paychecks.add(new Paycheck(payday, salary));
    }
    public String getId(){return id;}

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public String getJobTitle() {return jobTitle;}

    public void removePaycheck(Paycheck paycheck){
        paychecks.remove(paycheck);
    }
    public Iterator<Paycheck> iteratorPaychecks(){
        return paychecks.iterator(); // the Collection provides a method that returns an Iterator of its type
    }
    public double getYearsOfService(){
        return Period.between(dateOfEmployment,LocalDate.now()).getYears();
    }
    @Override
    public String toString() {
        return "Employee{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", jobTitle='" + jobTitle + '\'' +
                ", salary=" + salary +
                ", dateOfEmployment=" + dateOfEmployment +
                '}';
    }
}
