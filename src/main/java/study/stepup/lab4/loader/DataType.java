package study.stepup.lab4.loader;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class DataType {
    //Логин Фамилия Имя Отчество дата_входа тип_приложения
    private String fileName;
    private String login;
    private String surname;
    private String name;
    private String patr;
    //private Timestamp accesDate;
    private String accesDate;
    private String application;
}
