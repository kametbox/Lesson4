package study.stepup.lab4.loader;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import study.stepup.lab4.data.DataType;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@RequiredArgsConstructor
@Component
public class LoaderFromFiles implements Loader{
    public List<DataType> loadData(String path) throws FileNotFoundException {
        File folder;
        folder = new File(path);
        if (!folder.isDirectory()) throw new IllegalArgumentException("Programm argumets incorrect. Please insert a folder path");

        File[] files = folder.listFiles();

        List<DataType> dataFromFiles = new ArrayList<>();

        for (File file : files) {
            try (Scanner scan = new Scanner(file)) {
                while (scan.hasNext()) {
                    String dataLine = scan.nextLine();
                    //в задании указан конкретный формат с пробелами. Сделал парсинг именно так.
                    String[] words = dataLine.split("\\s+");
                    DataType dataType = new DataType();
                    //проверяем что строка состоит из 7 слов разделенных пробелами. Если нет, то это не наш формат
                    //Если есть другие ошибки, то они будут реальными ошибками - пусть ломается. надо разбираться
                    if (words.length == 7) {
                        dataType.setFileName(file.getName());
                        dataType.setLogin(words[0]);
                        dataType.setSurname(words[1]);
                        dataType.setName(words[2]);
                        dataType.setPatr(words[3]);
                        dataType.setAccesDate(words[4] + " " + words[5]);
                        dataType.setApplication(words[6]);

                        dataFromFiles.add(dataType);
                    }

                }
            } catch (Exception exception) {
                throw exception;
            }
        }
        return dataFromFiles;
    }
}
