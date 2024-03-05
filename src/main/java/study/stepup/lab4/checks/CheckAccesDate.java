package study.stepup.lab4.checks;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import study.stepup.lab4.loader.DataType;

import java.io.FileWriter;
import java.io.IOException;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

@Component
@Order(1)
public class CheckAccesDate implements Checks{
    public List<DataType> start(List<DataType> dataTypeList) throws IOException {

        List<DataType> dataTypeList2 = new ArrayList<>();
        for(DataType dataType : dataTypeList){
            try{
                Timestamp.valueOf(dataType.getAccesDate());
            }catch (IllegalArgumentException e){
                //сведения о имени файла и значении человека заносятся в отдельный лог
                try (FileWriter writer = new FileWriter("TimestampError.log", true)) {
                    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");
                    Date date = new Date(System.currentTimeMillis());
                    AtomicReference<String> log = new AtomicReference<>(formatter.format(date) + '\n');
                    log.set(log + dataType.getFileName() + '\n');
                    log.set(log + dataType.getAccesDate() + '\n');
                    log.set(log + dataType.getSurname()+ " "+dataType.getName()+ " " +dataType.getPatr() + '\n');
                    log.set(log + "\n");
                    writer.write(log.get());
                    writer.flush();
                } catch (IOException ex) {
                    throw ex;
                }
                continue;
            }
            dataTypeList2.add(dataType);
        }
        return dataTypeList2;
    }
}
