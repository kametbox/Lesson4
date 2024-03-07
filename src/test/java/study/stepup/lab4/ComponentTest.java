package study.stepup.lab4;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;
import study.stepup.lab4.data.DataType;
import study.stepup.lab4.model.Logins;
import study.stepup.lab4.model.Users;


public class ComponentTest{
    private Model model;
    private Users user1;
    private Users user2;
    private Logins logins1;
    private Logins logins2;
    private Logins logins3;
    private Logins logins4;


    @BeforeEach
    public void initData() {
        DataType dataType1 = new DataType("Новый текстовый документ.txt","kamet","кудрявцев","артем","Александрович","2024-03-01 23:13:01","mobile");
        DataType dataType2 = new DataType("Новый текстовый документ.txt","kamet","кудрявцев","артем","Александрович","2024-02-10 11:13:03","web");
        DataType dataType3 = new DataType("Новый текстовый документ2.txt","test11","Лобов", "Андрей", "Анатольевич", "2024-02-01 07:30:05", "mobile");
        DataType dataType4 = new DataType("Новый текстовый документ2.txt","irka123","Айвазян", "ирина", "Батьковна", "2024-02-09 09:37:41", "e-mail");
        DataType dataType5 = new DataType("Новый текстовый документ3.txt","irka123","Айвазян", "ирина", "батьковна", "2024-01-09 10:35:51", "mobile");
    }

    //компонент проверки данных - исправляет ФИО так, чтобы каждый его компонент начинался с большой буквы
    @Test
    @DisplayName("ФИО")
    public void DataChangeFioTest(){
        //внесем изменения в модель
        new DataChangeFio().change(model);

        //посмотрим визуально
        System.out.println(model);

        //выполним проверку
        for (Users user: model.data){
            for(String word : user.getFio().split(" ")){
                Assertions.assertTrue(Character.isUpperCase(word.charAt(0)));
            }
        }
    }

    //компонента проверяет, что тип приложения соответствует одному из: “web”, “mobile”. Если там записано что-либо иное, то оно преобразуется к виду “other:”+значение.
    @Test
    @DisplayName("Тип приложения")
    public void DataTypeRequiredTest() {
        //внесем изменения в модель
        new DataTypeRequired().change(model);

        //посмотрим визуально
        System.out.println(model);

        //выполним проверку
        for (Users user: model.data){
            for(Logins login : user.getLogins()){
                String app = login.getApplication();
                if (!app.matches("web|mobile")) Assertions.assertTrue(app.startsWith("other:"));
            }
        }
    }

    //компонента проверки даты проверяет её наличие. Если дата не задана, то человек не вносится в базу, а сведения о имени файла и значении человека заносятся в отдельный лог.
    @Test
    @DisplayName("Время и дата")
    public void DataCheckDateTest(){
        //внесем изменения в модель с помощью заглушек
        new DataCheckDateStub().change(model);

        //посмотрим визуально
        System.out.println(model);

        //проверим, что записи с пустой датой отсутствуют в нашей модели
        for (Users user: model.data){
            for(Logins login : user.getLogins()){
                Assertions.assertTrue(login.getDate() != null);
            }
        }
    }

    //проверим, что компонента читает записи из файлов
    @Test()
    @DisplayName("Чтение данных")
    public void DataReaderTest(){
        //пример записи в файлах - admin;администратор ку;11-02-2023 23:05:01;web
        String path = "C:/Users/79586/Documents/JAVA_COURSES/2 этап обучения java/Task4/log/read";
        Model model = new DataReader().get(path);
        Assertions.assertTrue(model.data.stream().count()>0);
    }

    @Test
    @DisplayName("Запись в БД")
    public void DataSaveTest(){
        //Определим контекст
        ApplicationContext ctx = SpringApplication.run(DataSave2.class);
        UsersRepo usersRepo = ctx.getBean(UsersRepo.class);
        //Почистим таблицу
        usersRepo.deleteAll();
        //Запишим данные в таблицу
        for (Users user : model.data) {
            usersRepo.save(user);
        }
        //Поищем новые записи
        usersRepo.findAll();

        Assertions.assertTrue(usersRepo.count()>0);
    }
}
