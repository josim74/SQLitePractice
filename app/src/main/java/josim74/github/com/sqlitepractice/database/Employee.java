package josim74.github.com.sqlitepractice.database;

/**
 * Created by JosimUddin on 29/04/2018.
 */

public class Employee {
    private int empId;
    private String name;
    private String designation;

    public Employee(int empId, String name, String designation) {
        this.empId = empId;
        this.name = name;
        this.designation = designation;
    }

    public Employee(String name, String designation) {
        this.name = name;
        this.designation = designation;
    }

    public int getEmpId() {
        return empId;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }
}
