package study.stepup.lab4.loader;

import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@RequiredArgsConstructor
@Component
public class LoaderFromFiles implements Loader{
    public List<DataType> loadData(String path){
        File folder;
        folder = new File(path);
        if (!folder.isDirectory()) throw new IllegalArgumentException("Programm argumets incorrect. Please insert a folder path");

        File[] files = folder.listFiles();

        List<DataType> dataFromFiles = new ArrayList<>();
        for (File file : files) {
            System.out.println("name= "+ file.getName());
            try {
                Scanner scan = new Scanner(file);
                while (scan.hasNext()) {
                    String dataLine = scan.nextLine();
                    String[] words = dataLine.split("\\s+");
                    DataType dataType = new DataType();
                    if (words.length == 7) {
                        try {
                            dataType.setLogin(words[0]);
                            dataType.setSurname(words[1]);
                            dataType.setName(words[2]);
                            dataType.setPatr(words[3]);
                            dataType.setAccesDate(Timestamp.valueOf(words[4]+" "+words[5]));
                            dataType.setApplication(words[6]);

                            dataFromFiles.add(dataType);

                        } catch (Exception ex) {
                            continue;
                        }
                    }

                }
            } catch (FileNotFoundException fileNotFoundException) {
                System.out.println(fileNotFoundException.fillInStackTrace());
            }
        }
        System.out.println("dataFromFiles= "+dataFromFiles);
        return dataFromFiles;
    }
}
