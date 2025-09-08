

public class Employee extends Worker{
    private int managerId;

    public Employee(String position, int id, String name, Double salary, int managerId){
        super(position, id, name, salary);
        this.managerId = managerId;
    }

    public int getManagerId(){return managerId;}

    public static Employee stringToEmployee(String str){
        WorkerFields workerFields = stringToWorker(str);

        return new Employee(workerFields.position, workerFields.id, workerFields.name, workerFields.salary, Integer.parseInt(workerFields.field));
    }

    public static boolean isCorrectEmployee(String data){
        String[] employeeData = data.split("\\s*,\\s*");

        return isCorrectWorkerFields(data) && isInt(employeeData[4]);
    }

    @Override
    public String toString(){
        return super.toString() + "," + getManagerId();
    }
}
