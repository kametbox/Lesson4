package study.stepup.lab4.service;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import study.stepup.lab4.inserter.InsertingData;
import study.stepup.lab4.loader.*;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProcService {
    private final InsertingData insertingData;
    private final LoaderFromFiles loaderFromFiles;
    public void mainStart(String path) {
        List<DataType> dataTypeList = loaderFromFiles.loadData(path);
        insertingData.start(dataTypeList);
    }
}
