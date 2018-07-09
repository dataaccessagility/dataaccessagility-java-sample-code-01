import dataaccessobjects.ConnectionProvider;
import dataaccessobjects.EmployeeData;
import models.Employee;

import java.math.BigDecimal;
import java.util.Date;

public class DataAccessAgilityJavaSampleCode {
    public static void main(String[] args) {

        try {
            ConnectionProvider.setConnectionString("", "", "");

            Class.forName("");

            Employee employee = new Employee();

            employee.setId(1);
            employee.setName("John Smith");
            employee.setHiringDate(new Date());
            employee.setBaseSalary(new BigDecimal(1100.23));
            employee.setDivisionId(1);

            EmployeeData.addEmployee(employee);

            employee = EmployeeData.getEmployeeById(employee.getId());

            //
            //Print employee
            //
            System.out.println(employee.getName());
        }
        catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
}
