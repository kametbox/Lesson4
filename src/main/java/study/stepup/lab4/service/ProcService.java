package study.stepup.lab4.service;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import study.stepup.lab4.checks.Checks;
import study.stepup.lab4.inserter.Inserter;
import study.stepup.lab4.loader.*;

import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProcService {
    private final Inserter insertingData;
    private final Loader loaderFromFiles;
    private final List<Checks> checksList;
//    private final CheckFIO checkFIO;
//    private final CheckAccesDate checkAccesDate;
//    private final CheckApplicationType checkApplicationType;

    public void mainStart(String path) throws IOException {
        List<DataType> dataTypeList = loaderFromFiles.loadData(path);
//        dataTypeList = checkFIO.start(dataTypeList);
//        dataTypeList = checkAccesDate.start(dataTypeList);
//        dataTypeList = checkApplicationType.start(dataTypeList);
        for(Checks checks : checksList){
            dataTypeList = checks.start(dataTypeList);
        }





        insertingData.start(dataTypeList);
    }
}
