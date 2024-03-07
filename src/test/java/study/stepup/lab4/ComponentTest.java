package study.stepup.lab4;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;
import study.stepup.lab4.checks.CheckAccesDate;
import study.stepup.lab4.checks.CheckApplicationType;
import study.stepup.lab4.checks.CheckFIO;
import study.stepup.lab4.data.DataType;
import study.stepup.lab4.loader.LoaderFromFiles;
import study.stepup.lab4.model.Logins;
import study.stepup.lab4.model.Users;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;


public class ComponentTest{
    private List<DataType> dataTypeList = new ArrayList<>();
    private List<DataType> chekedTypeList;


    @BeforeEach
    public void initData() {
        DataType dataType1 = new DataType("Новый текстовый документ.txt","kamet","кудрявцев","артем","Александрович","2024-03-01 23:13:01","mobile");
        DataType dataType2 = new DataType("Новый текстовый документ.txt","kamet","кудрявцев","артем","Александрович","2024-02-10 11:13:03","web");
        DataType dataType3 = new DataType("Новый текстовый документ2.txt","test11","Лобов", "Андрей", "Анатольевич", "2024-02-01 07:30:05", "mobile");
        DataType dataType4 = new DataType("Новый текстовый документ2.txt","irka123","Айвазян", "ирина", "Батьковна", "2024-02-09 09:37:41", "e-mail");
        DataType dataType5 = new DataType("Новый текстовый документ3.txt","irka123","Айвазян", "ирина", "батьковна", "2024-01-09 10:35:51", "mobile");
        DataType dataType6 = new DataType("Новый текстовый документ3.txt","irka123","Айвазян", "ирина", "батьковна", "2024-01-37 10:35:51", "web");
        DataType dataType7 = new DataType("Новый текстовый документ3.txt","irka123","Айвазян", "ирина", "батьковна", "", "mobile");

        dataTypeList.add(dataType1);
        dataTypeList.add(dataType2);
        dataTypeList.add(dataType3);
        dataTypeList.add(dataType4);
        dataTypeList.add(dataType5);
        dataTypeList.add(dataType6);
        dataTypeList.add(dataType7);
    }

    @Test
    public void CheckFIOTest(){
        chekedTypeList = new CheckFIO().start(dataTypeList);

        for (DataType dataType: chekedTypeList){
            Assertions.assertTrue(Character.isUpperCase(dataType.getSurname().charAt(0)));
            Assertions.assertTrue(Character.isUpperCase(dataType.getName().charAt(0)));
            Assertions.assertTrue(Character.isUpperCase(dataType.getPatr().charAt(0)));
        }
    }

    @Test
    public void CheckApplicationTypeTest() {
        chekedTypeList = new CheckApplicationType().start(dataTypeList);

        for (DataType dataType: chekedTypeList){
            if (!dataType.getApplication().matches("web|mobile")) Assertions.assertTrue(dataType.getApplication().startsWith("other:"));
        }
    }

    @Test
    public void CheckAccesDateTest() throws IOException {
        chekedTypeList = new CheckAccesDate().start(dataTypeList);

        for (DataType dataType: chekedTypeList){
            Assertions.assertTrue(dataType.getAccesDate() != null);
            Timestamp.valueOf(dataType.getAccesDate());
        }
    }

    @Test
    public void LoaderFromFilesTest() throws FileNotFoundException {
        String path = System.getProperty("user.dir")+"\\src\\test\\java\\study\\stepup\\lab4\\files";
        chekedTypeList = new LoaderFromFiles().loadData(path);
        Assertions.assertEquals(chekedTypeList.size(), 12);
    }

}
