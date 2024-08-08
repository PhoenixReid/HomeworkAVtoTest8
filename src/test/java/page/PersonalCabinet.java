package page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.$;

public class PersonalCabinet {
    private final SelenideElement cabinet = $("[data-test-id=\"dashboard\"]");

    public String openPersonalCabinet(){
        return cabinet.shouldBe(Condition.visible, Duration.ofSeconds(3)).getText();
    }
}
