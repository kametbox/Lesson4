package study.stepup.lab4;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;
import study.stepup.lab4.repository.LoginsRepository;
import study.stepup.lab4.repository.UsersRepository;
import study.stepup.lab4.service.ProcService;
import java.io.IOException;

public class InsertingDataTest {
    @Test
    public void applicationTest() throws IOException {
        String path = System.getProperty("user.dir")+"\\src\\test\\java\\study\\stepup\\lab4\\files";

        //Определим контекст всего приложения в т.ч. для работы с БД
        ApplicationContext ctx = SpringApplication.run(Lab4Application.class, path);
        UsersRepository usersRepository = ctx.getBean(UsersRepository.class);
        LoginsRepository loginsRepository = ctx.getBean(LoginsRepository.class);

        //Очистим таблицы
        loginsRepository.deleteAll();
        usersRepository.deleteAll();

        Assertions.assertEquals(usersRepository.count(), 0);
        Assertions.assertEquals(loginsRepository.count(), 0);

        //Стартанем основную операцию еще раз, чтоб добавила записи в БД
        ctx.getBean("procService", ProcService.class).mainStart(path);

        Assertions.assertEquals(usersRepository.count(), 5);
        Assertions.assertEquals(loginsRepository.count(), 11);
    }
}
