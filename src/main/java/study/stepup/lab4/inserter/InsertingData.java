package study.stepup.lab4.inserter;

import lombok.*;
import org.springframework.stereotype.Service;
import study.stepup.lab4.loader.DataType;
import study.stepup.lab4.model.Logins;
import study.stepup.lab4.model.Users;
import study.stepup.lab4.repository.LoginsRepository;
import study.stepup.lab4.repository.UsersRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Data
@Getter
public class InsertingData implements Inserter {

    private final UsersRepository usersRepository;
    private final LoginsRepository loginsRepository;

    public void start(List<DataType> dataFromFiles) {
        for (DataType dataType : dataFromFiles) {
            System.out.println("find by= "+ dataType.getLogin());
            Optional<Users> user = usersRepository.findByUsername(dataType.getLogin());
            System.out.println("find user= "+ user.toString());
            Users currentUser = user.orElseGet(() -> {
                        System.out.println("Create new user ");
                        String fio = dataType.getSurname() + " " + dataType.getName() + " " + dataType.getPatr();
                        System.out.println("==== "+fio+dataType.getLogin());
                        return usersRepository.save(new Users(null, dataType.getLogin(), fio));
                    }
            );
            Logins logins = new Logins(null, dataType.getAccesDate(), currentUser, dataType.getApplication());
            loginsRepository.save(logins);
        }
    }

    @Override
    public String toString() {
        return "LoadingData{" +
                ", usersRepository=" + usersRepository +
                ", loginsRepository=" + loginsRepository +
                '}';
    }
}
