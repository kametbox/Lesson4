package study.stepup.lab4.repository;

import study.stepup.lab4.model.Logins;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoginsRepository extends JpaRepository<Logins, Long> {

}