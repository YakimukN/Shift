

public class Main {
    public static void main(String[] args) {
        MyFile myFile = new MyFile();
        WorkersBase.sortDataFromFiles(myFile.getDataFromAllFiles());

        boolean sort = false;
        boolean stat = false;
        boolean outputInFile = false;
        String sortType = "";

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
                } else if (str[0].equals("order")){
                    if (sort){
                        if (str[1].equals("asc")){
                            if (sortType.equals("name")){
                                WorkersBase.sortEmployeeByName();
                            } else WorkersBase.sortEmployeeBySalary();
                        } else if (str[1].equals("desc")) {
                            if (sortType.equals("name")){
                                WorkersBase.sortEmployeeByNameReversed();
                            } else WorkersBase.sortEmployeeBySalaryReversed();
                        } else {
                            sort = false;
                            System.out.println("Неверно указан порядок сортировки");
                        }
                    } else System.out.println("Нельзя указать порядок сортировки, без указания сортировки :(");
                } else if (str[0].equals("stat")){
                    if (str.length > 1){
                        System.out.println("Параметр stat не принимает аргументов");
                    } else {
                        stat = true;
                    }
                } if (str[0].equals("output")){
                    if (stat && str.length > 1){
                        if (str[1].equals("console")){
                            Statistic.showStatisticInConsole();
                            stat = false;
                        } else if (str[1].equals("file")) {
                            outputInFile = true;
                        }
                    } else System.out.println("Неизвестный параметр");
                } else if (str[0].equals("path")){
                    if (outputInFile && str.length > 1){
                        Statistic.fullStatisticFile(str[1]);
                        outputInFile = false;
                    }
                } else if (stat && str[0].equals("stat")) {
                    Statistic.showStatisticInConsole();
                    stat = false;
                }
            } else if (arg.startsWith("-")){
                String[] str = arg.substring(1).split("=", 2);
                if (str[0].equals("s")){
                    if (str[1].equals("name")){
                        sort = true;
                        sortType = "name";
                    } else if (str[1].equals("salary")) {
                        sort = true;
                    } else System.out.println("Неверный тип сортировки");
                } else if (str[0].equals("o")){
                    if (stat && str.length > 1){
                        if (str[1].equals("console")){
                            Statistic.showStatisticInConsole();
                            stat = false;
                        } else if (str[1].equals("file")) {
                            outputInFile = true;
                        }
                    } else System.out.println("Неизвестный параметр");
                } else if (str[0].equals("path")){
                    if (outputInFile && str.length > 1){
                        Statistic.fullStatisticFile(str[1]);
                        outputInFile = false;
                    }
                }
            }
        }
        Statistic.fullFiles();
    }
}
