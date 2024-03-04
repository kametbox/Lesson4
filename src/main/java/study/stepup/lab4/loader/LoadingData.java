package study.stepup.lab4.loader;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import study.stepup.lab4.inserter.Inserter;
import study.stepup.lab4.model.Logins;
import study.stepup.lab4.model.Users;
import study.stepup.lab4.repository.LoginsRepository;
import study.stepup.lab4.repository.UsersRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LoadingData implements Inserter {
    @Getter
    private List<DataType> allData;
    private UsersRepository usersRepository;
    private LoginsRepository loginsRepository;

    public LoadingData(List<DataType> allData) {
        this.allData = allData;
    }

    public void start() {
        for (DataType dataType : allData) {
            Optional<Users> user = usersRepository.findByUsername(dataType.getLogin());
            Users currentUser = user.orElseGet(() -> {
                        System.out.println("Create new user");
                        String fio = dataType.getSurname() + " " + dataType.getName() + " " + dataType.getPatr();
                        return usersRepository.save(new Users(null, fio, dataType.getLogin()));
                    }
            );
            Logins logins = new Logins(null, dataType.getAccesDate(), currentUser, dataType.getApplication());
            loginsRepository.save(logins);
        }
    }

    @Override
    public String toString() {
        return "LoadingData{" +
                "allData=" + allData +
                ", usersRepository=" + usersRepository +
                ", loginsRepository=" + loginsRepository +
                '}';
    }
}
