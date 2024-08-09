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

    public void getError(String expected) {
        error.$("[class=\"notification__content\"]").shouldBe(Condition.text(expected));

    }
    public void visiblePage(){
        inpulVerefyCode.shouldBe(Condition.visible,Duration.ofSeconds(5));

    }
}
