abstract class Worker {
    private String position;
    private int id;
    private String name;
    private double salary;

    public Worker(String position, int id, String name, double salary) {
        this.position = position;
        this.id = id;
        this.name = name;
        this.salary = salary;
    }

    public String getPosition() {
        return this.position;
    }

    public int getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public double getSalary() {
        return this.salary;
    }

    public static boolean isInt(String str) {
        try {
            int num = Integer.parseInt(str);
            return num > 0;
        } catch (NumberFormatException var2) {
            return false;
        }
    }

    public static boolean isDouble(String str) {
        try {
            double num = Double.parseDouble(str);
            return num > (double)0.0F;
        } catch (NumberFormatException var3) {
            return false;
        }
    }

    public static boolean isText(String str) {
        return str.matches("[A-Za-z\\s]+") && !str.isEmpty();
    }

    public static WorkerFields stringToWorker(String str) {
        String[] workerData = str.split("\\s*,\\s*");
        String position = workerData[0];
        int id = Integer.parseInt(workerData[1]);
        String name = workerData[2];
        double salary = Double.parseDouble(workerData[3]);
        String field = workerData[4];
        return new WorkerFields(position, id, name, salary, field);
    }

    public static boolean isCorrectWorkerFields(String str) {
        String[] workerData = str.split("\\s*,\\s*");
        return isInt(workerData[1]) && workerData[2] != null && isText(workerData[2]) ? isDouble(workerData[3]) : false;
    }

    public String toString() {
        String var10000 = this.getPosition();
        return var10000 + "," + this.getId() + "," + this.getName() + "," + this.getSalary();
    }

    public static class WorkerFields {
        protected String position;
        protected int id;
        protected String name;
        protected double salary;
        protected String field;

        protected WorkerFields(String position, int id, String name, double salary, String field) {
            this.position = position;
            this.id = id;
            this.name = name;
            this.salary = salary;
            this.field = field;
        }
    }
}
