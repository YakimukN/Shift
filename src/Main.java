

public class Main {
    public static void main(String[] args) {
        MyFile myFile = new MyFile();
        WorkersBase.sortDataFromFiles(myFile.getDataFromAllFiles());

        Statistic.fullFiles();
    }
}
