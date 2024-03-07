package study.stepup.lab4.checks;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import study.stepup.lab4.data.DataType;

import java.util.ArrayList;
import java.util.List;

@Component
@Order(2)
public class CheckFIO implements Checks{
    @LogTransformation
    public List<DataType> start(List<DataType> dataTypeList){

        List<DataType> dataTypeList2 = new ArrayList<>();
        for(DataType dataType : dataTypeList){

            DataType dataType2 = dataType.currentClone();

            dataType2.setSurname(dataType.getSurname().substring(0,1).toUpperCase()+dataType.getSurname().substring(1));
            dataType2.setName(dataType.getName().substring(0,1).toUpperCase()+dataType.getName().substring(1));
            dataType2.setPatr(dataType.getPatr().substring(0,1).toUpperCase()+dataType.getPatr().substring(1));

            dataTypeList2.add(dataType2);
        }
        return dataTypeList2;
    }
}
