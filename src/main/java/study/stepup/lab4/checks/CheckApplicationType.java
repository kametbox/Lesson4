package study.stepup.lab4.checks;

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
public class CheckApplicationType {
    public List<DataType> start(List<DataType> dataTypeList) throws IOException {

        List<DataType> dataTypeList2 = new ArrayList<>();
        for(DataType dataType : dataTypeList){
            if (!dataType.getApplication().equals("web") && !dataType.getApplication().equals("mobile")){
                dataType.setApplication("other:"+dataType.getApplication());
            }
            dataTypeList2.add(dataType);
        }
        return dataTypeList2;
    }
}
