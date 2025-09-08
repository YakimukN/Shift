import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

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
        fullStatisticFile();
    }

    private static void fullStatisticFile() {
        try (FileWriter fileWriter = new FileWriter("output\\statistic.txt")){
            fileWriter.write("department, min, max, mid\n");
            for (Map.Entry<String, Integer> data: setDepartmentNames.entrySet()){
                int count = 0;
                double min = Double.MAX_VALUE, max = 0;
                for (Employee employee: WorkersBase.getListEmployee()){
                    if (employee.getManagerId() == data.getValue()){
                        count++;
                        if (employee.getSalary() > max){
                            max = employee.getSalary();
                        }
                        if (employee.getSalary() < min){
                            min = employee.getSalary();
                        }
                    }
                }
                if (count == 0){
                    min = 0;
                }
                fileWriter.write(data.getKey() + "," + min + "," + max + "," + String.format(Locale.US, "%.2f", (min + max) / 2) + "\n");
            }
        } catch (IOException e) {
            System.out.println("Что-то пошло не так, мы работаем над ошибкой\n" + e);
        }
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
