package study.stepup.lab4.loader;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Component
@Scope("prototype")
public class DataType {
    //Логин Фамилия Имя Отчество дата_входа тип_приложения
    private String login;
    private String surname;
    private String name;
    private String patr;
    private Timestamp accesDate;
    private String application;
    @Override
    public String toString() {
        return "DataType{" +
                "login='" + login + '\'' +
                ", surname='" + surname + '\'' +
                ", name='" + name + '\'' +
                ", patr='" + patr + '\'' +
                ", accesDate=" + accesDate +
                ", application='" + application + '\'' +
                '}';
    }
}
