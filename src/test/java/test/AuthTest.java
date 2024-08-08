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
        new VerifyCode().visiblePage();
        String code =  getVerificationCode();
        new VerifyCode().verifyCode(code);
        Assertions.assertEquals( "  Личный кабинет",new PersonalCabinet().openPersonalCabinet() );
    }

    @Test
    void negativeAuthTest(){
        var user = getRandomAuthInfo();
        var auth =  loginPage.verifyLogin(user);
        new VerifyCode().visibleError();;
        String actual = new Auth().getError();
        Assertions.assertEquals("Ошибка! Неверно указан логин или пароль", actual);

    }

    @Test
    void negativeVerificationCode(){
        var user = getAuthInfo();
        var auth =  loginPage.verifyLogin(user);

        var code = randomCode();
        new VerifyCode().verifyCode(code.getCode());
        new VerifyCode().visibleError();;
        String actual = new VerifyCode().getError();
        Assertions.assertEquals( "Ошибка! Неверно указан код! Попробуйте ещё раз." , actual);

    }

    

}
