package study.stepup.lab4.checks;

import org.springframework.stereotype.Component;
import study.stepup.lab4.loader.DataType;

import java.util.ArrayList;
import java.util.List;

@Component
public class CheckApplicationType implements Checks{
    public List<DataType> start(List<DataType> dataTypeList) {

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
