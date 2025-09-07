import java.io.IOException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class MyFile {
    private List<String> dataFromAllFiles = this.readDataFromAllFiles();

    public List<String> getDataFromAllFiles() {
        return this.dataFromAllFiles;
    }

    private List<String> getDataFromFile(String fileName) {
        Path path = Paths.get(fileName);

        try {
            List<String> allLines = Files.readAllLines(path, StandardCharsets.UTF_8);
            List<String> fullLines = new ArrayList();

            for(String line : allLines) {
                if (!line.isEmpty()) {
                    fullLines.add(line);
                }
            }

            return fullLines;
        } catch (IOException var7) {
            System.out.println("С файлом что-то не так, возможно, Вы указали неверное имя файла");
            return new ArrayList();
        }
    }

    private List<String> getAllSBFiles() {
        Path projectDir = Paths.get(System.getProperty("user.dir"));
        List<String> fileNames = new ArrayList();

        try {
            for(Path file : Files.newDirectoryStream(projectDir, "*.sb")) {
                fileNames.add(file.getFileName().toString());
            }
        } catch (IOException var6) {
            System.out.println("Произошла ошибка при чтении рабочей директории :(");
        }

        return fileNames;
    }

    private List<String> readDataFromAllFiles() {
        List<String> fileNames = this.getAllSBFiles();
        List<String> dataLines = new ArrayList();

        for(String fileName : fileNames) {
            List<String> dataFromFile = this.getDataFromFile(fileName);
            dataLines.addAll(dataFromFile);
        }

        return dataLines;
    }
}
