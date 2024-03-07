package study.stepup.lab4;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import study.stepup.lab4.checks.*;
import study.stepup.lab4.inserter.Inserter;
import study.stepup.lab4.loader.Loader;
import study.stepup.lab4.repository.LoginsRepository;
import study.stepup.lab4.repository.UsersRepository;
import study.stepup.lab4.service.ProcService;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(args={"C:\\Java_InnoTech\\Lab4\\src\\main\\resources\\files"})
public class Lab4ApplicationTests {
    UsersRepository usersRepository;
    LoginsRepository loginsRepository;
    Inserter testInsertingData;
    Loader testLoaderFromFiles;
    List<Checks> testChecksList;
    ProcService procService;


    @Autowired
    public Lab4ApplicationTests(UsersRepository usersRepository, LoginsRepository loginsRepository, Inserter insertingData, Loader loaderFromFiles, List<Checks> checksList, ProcService procService) {
        this.testInsertingData = insertingData;
        this.testLoaderFromFiles = loaderFromFiles;
        this.testChecksList = checksList;
        this.procService = procService;
        this.usersRepository = usersRepository;
        this.loginsRepository = loginsRepository;
    }

    //ищем конкретные бины
    @Test
    public void contextLoads() {

        assertThat(testInsertingData).isNotNull();
        assertThat(testLoaderFromFiles).isNotNull();
        assertThat(testChecksList).isNotNull();
        assertThat(procService).isNotNull();
        assertThat(usersRepository).isNotNull();
        assertThat(loginsRepository).isNotNull();

    }
}