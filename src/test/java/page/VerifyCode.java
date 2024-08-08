package page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.$;

public class VerifyCode {
    private final SelenideElement inpulVerefyCode = $("[data-test-id='code'] input");
    private final SelenideElement bottonCode = $("[data-test-id=\"action-verify\"]");
    private final SelenideElement error = $("[data-test-id=\"error-notification\"]");

    public void verifyCode(String code){
        inpulVerefyCode.setValue(code);
        bottonCode.click();
    }

    public String getError(){
        return error.$("[class=\"notification__content\"]").getText();
    }

    public void visibleError(){
        error.shouldBe(Condition.visible, Duration.ofSeconds(4));
    }

    public Condition visiblePage(){
        inpulVerefyCode.shouldBe(Condition.visible,Duration.ofSeconds(5));
        return null;
    }
}
