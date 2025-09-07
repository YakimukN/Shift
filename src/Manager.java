public class Manager extends Worker {
    private String departmentName;

    public Manager(String position, int id, String name, Double salary, String departmentName) {
        super(position, id, name, salary);
        this.departmentName = departmentName;
    }

    public String getDepartmentName() {
        return this.departmentName;
    }

    public static Manager stringToManager(String str) {
        WorkerFields workerFields = stringToWorker(str);
        return new Manager(workerFields.position, workerFields.id, workerFields.name, workerFields.salary, workerFields.field);
    }

    public static boolean isCorrectManager(String data) {
        String[] managerData = data.split("\\s*,\\s*");
        return isCorrectWorkerFields(data) && isText(managerData[4]);
    }

    public String toString() {
        String var10000 = super.toString();
        return var10000 + "," + this.getDepartmentName();
    }
}
