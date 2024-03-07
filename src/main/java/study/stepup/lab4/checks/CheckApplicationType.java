package study.stepup.lab4.checks;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import study.stepup.lab4.data.DataType;

import java.util.ArrayList;
import java.util.List;

@Component
@Order(3)
public class CheckApplicationType implements Checks{
    @LogTransformation
    public List<DataType> start(List<DataType> dataTypeList) {

        List<DataType> dataTypeList2 = new ArrayList<>();
        for(DataType dataType : dataTypeList){
            DataType dataType2 = dataType.clone();
            if (!dataType.getApplication().equals("web") && !dataType.getApplication().equals("mobile")){
                dataType2.setApplication("other:"+dataType.getApplication());
            }
            dataTypeList2.add(dataType2);
        }
        return dataTypeList2;
    }
}
