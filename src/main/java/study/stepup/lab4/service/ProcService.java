package study.stepup.lab4.service;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import study.stepup.lab4.checks.Checks;
import study.stepup.lab4.data.DataType;
import study.stepup.lab4.inserter.Inserter;
import study.stepup.lab4.loader.*;
import study.stepup.lab4.utils.Utils;

import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProcService {
    private final Inserter insertingData;
    private final Loader loaderFromFiles;
    private final List<Checks> checksList;

    public void mainStart(String path) throws IOException {
        List<DataType> dataTypeList = loaderFromFiles.loadData(path);

        for(Checks checks : checksList){
            //dataTypeList = checks.start(dataTypeList);
            Checks checks2 = Utils.log(checks);
            dataTypeList = checks2.start(dataTypeList);
        }

        insertingData.start(dataTypeList);
    }
}
