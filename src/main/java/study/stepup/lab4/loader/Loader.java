package study.stepup.lab4.loader;

import java.util.List;

public interface Loader {
    List<DataType> loadData(String path);
}
