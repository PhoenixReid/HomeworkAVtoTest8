package test;

import org.junit.jupiter.api.*;
import page.Auth;
import page.PersonalCabinet;
import page.VerifyCode;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static data.DataHelper.*;
import static data.SQLHelper.*;

// java -jar ./artifacts/app-deadline.jar -P:jdbc.url=jdbc:mysql://localhost:3306/app -P:jdbc.user=app -P:jdbc.password=pass
public class AuthTest {
    Auth loginPage;

    @AfterEach
    void cleanCode(){
        cleanAUthCodes();
    }
    @AfterAll
    static void cleanDataBase(){
        cleanDatabase();
    }

    @BeforeEach
    void setUp(){
        loginPage = open("http://localhost:9999/", Auth.class);
//        open("http://localhost:9999/");
    }

    @Test
    void  positivAuthTest() {
        var user = getAuthInfo();
        var auth = loginPage.verifyLogin(user);
        auth.visiblePage();
        String code =  getVerificationCode();
        auth.verifyCode(code);
        new PersonalCabinet().openPersonalCabinet("  Личный кабинет");
    }

    @Test
    void negativeAuthTest(){
        var user = getRandomAuthInfo();
        var auth =  loginPage.verifyLogin(user);
       auth.getError("Ошибка! Неверно указан логин или пароль");

    }

    @Test
    void negativeVerificationCode(){
        var user = getAuthInfo();
        var auth =  loginPage.verifyLogin(user);
        var code = randomCode();
        auth.verifyCode(code.getCode());
       auth.getError("Ошибка! Неверно указан код! Попробуйте ещё раз.");

    }

    

}
