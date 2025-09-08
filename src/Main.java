

public class Main {
    public static void main(String[] args) {
        MyFile myFile = new MyFile();
        WorkersBase.sortDataFromFiles(myFile.getDataFromAllFiles());


        boolean sort = false;
        String sortType = "";
//        Statistic.fullFiles();
        for (String arg : args) {
            if (arg.startsWith("--")){
                String[] str = arg.substring(2).split("=", 2);
                if (str[0].equals("sort")){
                    if (str[1].equals("name")){
                        sort = true;
                        sortType = "name";
                    } else if (str[1].equals("salary")) {
                        sort = true;
                    } else System.out.println("Неверный тип сортировки");
                } else if (str[0].equals("order") && sort){
                    if (str[1].equals("asc")){
                        if (sortType.equals("name")){
                            WorkersBase.sortEmployeeByName();
                        } else WorkersBase.sortEmployeeBySalary();
                    } else if (str[1].equals("desc")) {
                        if (sortType.equals("name")){
                            WorkersBase.sortEmployeeByNameReversed();
                        } else WorkersBase.sortEmployeeBySalaryReversed();
                    } else System.out.println("Неверно указан порядок сортировки");
                } else if (str[0].equals("stat")){
                    System.out.println("Данный параметр находится на стадии доработки :)");
                } else if (str[0].equals("output")){
                    System.out.println("Данный параметр находится на стадии доработки :)");
                }else if (str[0].equals("path")){
                    System.out.println("Данный параметр находится на стадии доработки :)");
                } else System.out.println("Нет таких параметров");
            }

        }
    }
}
