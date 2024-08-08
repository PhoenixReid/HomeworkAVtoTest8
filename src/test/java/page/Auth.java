package page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import data.DataHelper;
import groovy.lang.GString;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.$;

public class Auth {
    private final SelenideElement inputLogin = $("[data-test-id=login] input");
    private final SelenideElement inputPassword = $("[data-test-id=password] input");
    private final SelenideElement  bullonlogin = $("[data-test-id=\"action-login\"]");
    private final SelenideElement error = $("[data-test-id=\"error-notification\"]");


    public VerifyCode verifyLogin(DataHelper.AuthInfo getAuthInfo){
        inputLogin.setValue(getAuthInfo.getLogin());
        inputPassword.setValue(getAuthInfo.getPassword());
        bullonlogin.click();

        return new VerifyCode();
    }

    public String getError(){
        return error.$("[class=\"notification__content\"]").getText();
    }
}
