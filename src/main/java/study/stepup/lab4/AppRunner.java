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
        for (String s : ar) {
            path = s;
        }
        //if(path.isEmpty()){path= "C:\\Java_InnoTech\\Lab4\\src\\main\\resources\\files";}
        procService.mainStart(path);
    }
}
