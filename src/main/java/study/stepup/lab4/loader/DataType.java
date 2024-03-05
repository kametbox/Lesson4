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
    private String accesDate;
    private String application;

    public DataType clone(){

        DataType retDataType = new DataType();

        retDataType.setFileName(this.getFileName());
        retDataType.setLogin(this.getLogin());
        retDataType.setSurname(this.getSurname());
        retDataType.setName(this.getName());
        retDataType.setPatr(this.getPatr());
        retDataType.setAccesDate(this.getAccesDate());
        retDataType.setApplication(this.getApplication());

        return retDataType;
    }
}
