import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class WorkersBase {
    private static List<Manager> listManagers = new ArrayList<>();
    private static List<Employee> listEmployee = new ArrayList<>();
    private static List<String> listFailedData = new ArrayList<>();

    public static List<Manager> getListManagers() { return listManagers; }
    public static List<Employee> getListEmployee() { return listEmployee; }
    public static List<String> getListFailedData() { return listFailedData; }

    public static void addManager(Manager manager){ listManagers.add(manager); }
    public static void addEmployee(Employee employee){ listEmployee.add(employee); }
    public static void addFailedData(String failed){ listFailedData.add(failed); }

    public static Manager findManagerById(int id){
        for (Manager manager: listManagers){
            if (manager.getId() == id)
                return manager;
        }
        return null;
    }

    public static void sortDataFromFiles(List<String> workersData){
        for (String workerData : workersData){
            String[] data = workerData.split("\\s*,\\s*");
            String position = data[0].trim();

            if (position.startsWith("Employee")){
                if (Employee.isCorrectEmployee(workerData)){
                    WorkersBase.addEmployee(Employee.stringToEmployee(workerData));
                } else addFailedData(workerData);
            } else if (position.startsWith("Manager")) {
                if (Manager.isCorrectManager(workerData)){
                    WorkersBase.addManager(Manager.stringToManager(workerData));
                } else addFailedData(workerData);
            } else addFailedData(workerData);
        }
    }

    public static void sortEmployeeByName(){
        WorkersBase.listEmployee.sort(Comparator.comparing(Employee::getName));
    }

    public static void sortEmployeeByNameReversed(){
        WorkersBase.listEmployee.sort(Comparator.comparing(Employee::getName).reversed());
    }

    public static void sortEmployeeBySalary(){
        WorkersBase.listEmployee.sort(Comparator.comparing(Employee::getSalary));
    }

    public static void sortEmployeeBySalaryReversed(){
        WorkersBase.listEmployee.sort(Comparator.comparing(Employee::getSalary).reversed());
    }
}
