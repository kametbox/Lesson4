package study.stepup.lab4.service;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import study.stepup.lab4.checks.CheckAccesDate;
import study.stepup.lab4.checks.CheckFIO;
import study.stepup.lab4.inserter.Inserter;
import study.stepup.lab4.loader.*;

import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProcService {
    private final Inserter insertingData;
    private final Loader loaderFromFiles;
    private final CheckFIO checkFIO;
    private final CheckAccesDate checkAccesDate;
    public void mainStart(String path) throws IOException {
        List<DataType> dataTypeList = loaderFromFiles.loadData(path);
        dataTypeList = checkFIO.start(dataTypeList);
        dataTypeList = checkAccesDate.start(dataTypeList);


        insertingData.start(dataTypeList);
    }
}
