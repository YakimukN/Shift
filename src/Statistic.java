import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class Statistic {
    private static Map<String, Integer> setDepartmentNames = new HashMap();
    private static Map<Employee, Boolean> notUsedEmployees = new HashMap<>();

    public static void fullFiles(){
        for (Manager manager: WorkersBase.getListManagers()){
            setDepartmentNames.put(manager.getDepartmentName().trim(), manager.getId());
        }
        for (Employee employee: WorkersBase.getListEmployee()){
            notUsedEmployees.put(employee, false);
        }
        for (Map.Entry<String, Integer> data: setDepartmentNames.entrySet()){
            fullDepartmentFile(data.getValue(), data.getKey());
        }

        fullErrorFile();
    }

    public static void showStatisticInConsole(){
        List< String> statistic = getStatisticInfo();
        System.out.println("department, min, max, mid");
        for (String stat: statistic){
            System.out.println(stat);
        }
    }

    public static void fullStatisticFile(String filePath) {
        List< String> statistic = getStatisticInfo();

        try (FileWriter fileWriter = new FileWriter(filePath)){
            fileWriter.write("department, min, max, mid\n");
            for (String stat: statistic){
                fileWriter.write(stat + "\n");
            }
        } catch (IOException e) {
            System.out.println("Что-то пошло не так, мы работаем над ошибкой\n" + e);
        }
    }

    private static List<String> getStatisticInfo(){
        List<String> statistic = new ArrayList<>();

        for (Manager manager: WorkersBase.getListManagers()){
            setDepartmentNames.put(manager.getDepartmentName().trim(), manager.getId());
        }

        for (Map.Entry<String, Integer> data: setDepartmentNames.entrySet()){
            List<Employee> temp = new ArrayList<>();
            for (Employee employee: WorkersBase.getListEmployee()){
                if (employee.getManagerId() == data.getValue()){
                    temp.add(employee);
                }
            }
            double max = 0;
            double min = Double.MAX_VALUE;
            double mid = 0;
            if (!temp.isEmpty()){
                for (Employee employee: temp){
                    double employeeSalary = employee.getSalary();
                    mid += employeeSalary;
                    if (employeeSalary > max){
                        max = employeeSalary;
                    }
                    if (employeeSalary < min){
                        min = employeeSalary;
                    }
                }
                mid = mid / temp.size();
            } else max = min = mid = 0;

            statistic.add(data.getKey() + "," + max + "," + min + "," + mid);
        }

        return statistic;
    }

    public static void fullDepartmentFile(int managerId, String fileName){
        try (FileWriter fileWriter = createFile(fileName, "sb")){
            fileWriter.write(WorkersBase.findManagerById(managerId).toString() + "\n");
            for (Employee employee: WorkersBase.getListEmployee()){
                if (employee.getManagerId() == managerId){
                    notUsedEmployees.put(employee, true);
                    fileWriter.write(employee + "\n");
                }
            }
        } catch (IOException e) {
            System.out.println("Что-то пошло не так, мы работаем над ошибкой\n" + e);
        }
    }

    public static FileWriter createFile(String fileName, String extension) throws IOException {
        return new FileWriter("output\\" + fileName + "." + extension);
    }

    private static void fullErrorFile(){
        try (FileWriter fileWriter = createFile("error", "log")){
            for (Map.Entry<Employee, Boolean> employee: notUsedEmployees.entrySet()){
                if (!employee.getValue()){
                    Employee emp = employee.getKey();
                    WorkersBase.addFailedData(emp.toString());
                }
            }
            for (String failedData: WorkersBase.getListFailedData()){
                fileWriter.write(failedData + "\n");
            }
        } catch (IOException e) {
            System.out.println("Что-то пошло не так, мы работаем над ошибкой\n" + e);
        }
    }
}
