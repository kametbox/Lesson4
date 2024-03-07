package study.stepup.lab4.loader;

import study.stepup.lab4.data.DataType;

import java.io.FileNotFoundException;
import java.util.List;

public interface Loader {
    List<DataType> loadData(String path) throws FileNotFoundException;
}
