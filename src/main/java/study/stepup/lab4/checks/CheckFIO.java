package study.stepup.lab4.checks;

import org.springframework.stereotype.Component;
import study.stepup.lab4.loader.DataType;

import java.util.ArrayList;
import java.util.List;

@Component
public class CheckFIO implements Checks{
    public List<DataType> start(List<DataType> dataTypeList){

        List<DataType> dataTypeList2 = new ArrayList<>();
        for(DataType dataType : dataTypeList){
            dataType.setSurname(dataType.getSurname().substring(0,1).toUpperCase()+dataType.getSurname().substring(1));
            dataType.setName(dataType.getName().substring(0,1).toUpperCase()+dataType.getName().substring(1));
            dataType.setPatr(dataType.getPatr().substring(0,1).toUpperCase()+dataType.getPatr().substring(1));
            dataTypeList2.add(dataType);
        }
        return dataTypeList2;
    }
}
