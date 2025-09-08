abstract class Worker {
    private String position;
    private int id;
    private String name;
    private double salary;

    public Worker(String position, int id, String name, double salary){
        this.position = position;
        this.id = id;
        this.name = name;
        this.salary = salary;
    }

    public String getPosition() {
        return position;
    }
    public int getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public double getSalary() {
        return salary;
    }

    public static boolean isInt(String str){
        try{
            int num = Integer.parseInt(str);
            return num > 0;
        } catch (NumberFormatException  e){
            return false;
        }
    }
    public static boolean isDouble(String str){
        try{
            double num = Double.parseDouble(str);
            return num > 0;
        } catch (NumberFormatException  e){
            return false;
        }
    }
    public static boolean isText(String str){
        return str.matches("[A-Za-z\\s]+") && !str.isEmpty();
    }

    public static WorkerFields stringToWorker(String str){
        String[] workerData = str.split("\\s*,\\s*");

        String position = workerData[0];
        int id = Integer.parseInt(workerData[1]);
        String name = workerData[2];
        double salary = Double.parseDouble(workerData[3]);
        String field = workerData[4];

        return new WorkerFields(position, id, name, salary, field);
    }

    public static boolean isCorrectWorkerFields(String str){
        String[] workerData = str.split("\\s*,\\s*");

        if (isInt(workerData[1])){
            if (workerData[2] != null && isText(workerData[2])){
                return isDouble(workerData[3]);
            }
        }
        return false;
    }

    @Override
    public String toString() {
        return getPosition() + "," + getId() + "," + getName() + "," + getSalary();
    }

    public static class WorkerFields{
        protected String position;
        protected int id;
        protected String name;
        protected double salary;
        protected String field;

        protected WorkerFields(String position, int id, String name, double salary, String field){
            this.position = position;
            this.id = id;
            this.name = name;
            this.salary = salary;
            this.field = field;
        }
    }
}
