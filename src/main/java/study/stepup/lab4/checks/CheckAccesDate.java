package study.stepup.lab4.checks;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import study.stepup.lab4.data.DataType;

import java.io.BufferedWriter;
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
                SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
                String log = formatter.format(new java.util.Date()) + '\n';
                log+= dataType.getFileName() + '\n';
                log+= log + dataType.getAccesDate() + '\n';
                log+= log + dataType.getSurname()+ " "+dataType.getName()+ " " +dataType.getPatr() + '\n';
                log+= log + "\n";

                try (FileWriter fw = new FileWriter("TimestampError.log", true);
                     BufferedWriter bw = new BufferedWriter(fw)) {
                    bw.write(log);
                }

                continue;
            }
            dataTypeList2.add(dataType);
        }
        return dataTypeList2;
    }
}
