package study.stepup.lab4.inserter;

import jakarta.transaction.Transactional;
import lombok.*;
import org.springframework.stereotype.Component;
import study.stepup.lab4.loader.DataType;
import study.stepup.lab4.model.Logins;
import study.stepup.lab4.model.Users;
import study.stepup.lab4.repository.LoginsRepository;
import study.stepup.lab4.repository.UsersRepository;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
@Data
@Getter
@Transactional
public class InsertingData implements Inserter {

    private final UsersRepository usersRepository;
    private final LoginsRepository loginsRepository;

    public void start(List<DataType> dataFromFiles) {
        for (DataType dataType : dataFromFiles) {
            Optional<Users> user = usersRepository.findByUsername(dataType.getLogin());
            Users currentUser = user.orElseGet(() -> {
                        String fio = dataType.getSurname() + " " + dataType.getName() + " " + dataType.getPatr();
                        return usersRepository.save(new Users(null, dataType.getLogin(), fio));
                    }
            );

            Timestamp timestamp;
            try {
                timestamp = Timestamp.valueOf(dataType.getAccesDate());
            } catch (IllegalArgumentException e){
                continue;
            }

            Logins logins = new Logins(null, timestamp, currentUser, dataType.getApplication());
            loginsRepository.save(logins);
        }
    }
}
