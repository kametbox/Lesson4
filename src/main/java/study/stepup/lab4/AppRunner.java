package study.stepup.lab4;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import study.stepup.lab4.service.ProcService;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class AppRunner implements ApplicationRunner {

    private final ProcService procService;

    @Override
    public void run(ApplicationArguments args) throws IOException {
        String[] ar = args.getSourceArgs();
        String path = "";
        for (int i = 0; i < ar.length; i++) {
            path = ar[i];
        }
        System.out.println("Путь из аргументов запуска приложения: " + path);
        procService.mainStart(path);
    }
}
