import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class MyFile {

    private List<String> dataFromAllFiles;

    public MyFile(){
        dataFromAllFiles = readDataFromAllFiles();
    }

    public List<String> getDataFromAllFiles() {
        return dataFromAllFiles;
    }

    private List<String> getDataFromFile(String fileName){
        Path path = Paths.get(fileName);

        try{
            List<String> allLines = Files.readAllLines(path, StandardCharsets.UTF_8);
            List<String> fullLines = new ArrayList<>();
            for (String line : allLines){
                if (!line.isEmpty()){
                    fullLines.add(line);
                }
            }
            return fullLines;

        } catch (IOException e) {
            System.out.println("С файлом что-то не так, возможно, Вы указали неверное имя файла");
            return new ArrayList<>();
        }
    }

    private List<String> getAllSBFiles(){
        Path projectDir = Paths.get(System.getProperty("user.dir"));
        List<String> fileNames = new ArrayList<>();

        try{
            DirectoryStream<Path> stream = Files.newDirectoryStream(projectDir, "*.sb");
            for(Path file : stream){
                fileNames.add(file.getFileName().toString());
            }
        } catch (IOException e) {
            System.out.println("Произошла ошибка при чтении рабочей директории :(");
        }
        return fileNames;
    }

    private List<String> readDataFromAllFiles(){
        List<String> fileNames = getAllSBFiles();
        List<String> dataLines = new ArrayList<>();
        for (String fileName : fileNames){
            List<String> dataFromFile = getDataFromFile(fileName);
            dataLines.addAll(dataFromFile);
        }
        return dataLines;
    }
}
