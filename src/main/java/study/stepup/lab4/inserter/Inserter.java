package study.stepup.lab4.inserter;

import study.stepup.lab4.loader.DataType;

import java.util.List;

public interface Inserter {
    void start(List<DataType> dataFromFiles);
}
