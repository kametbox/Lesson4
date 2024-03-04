package study.stepup.lab4.service;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import study.stepup.lab4.loader.*;

@Service
@RequiredArgsConstructor
public class ProcService {
    LoadingData loadingData;
    public void mainStart(String path) {
        System.out.println("------------------------------------");
        Loader loader = new LoaderFromFiles();
        loadingData = loader.loadData(path);
        System.out.println("------------------------------------");
        System.out.println("Вывод из мейна");
        System.out.println("------------------------------------");
        System.out.println(loadingData);
        loadingData.start();

        System.out.println("done");



    }
}
