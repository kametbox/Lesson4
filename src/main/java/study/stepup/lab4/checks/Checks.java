package study.stepup.lab4.checks;

import study.stepup.lab4.data.DataType;

import java.io.IOException;
import java.util.List;

public interface Checks {
    List<DataType> start(List<DataType> dataTypeList) throws IOException;
}
