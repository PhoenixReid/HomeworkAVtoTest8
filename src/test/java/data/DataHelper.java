package data;

import com.github.javafaker.Faker;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Value;

import java.util.Locale;

public class DataHelper {
    private static final Faker FAKER = new Faker(new Locale("ru"));

    private DataHelper(){}

    public static AuthInfo getAuthInfo(){
        return new AuthInfo("vasya", "qwerty123");
    }

    public static String getRandemLogin(){
        return FAKER.name().username();

    }

    public static String getRandemPassword(){
        return FAKER.internet().password();

    }

    public static AuthInfo getRandomAuthInfo(){
        return new AuthInfo(getRandemLogin(),getRandemPassword());
    }

    public static VerificationCode randomCode(){
        return new VerificationCode(FAKER.numerify("######"));

    }

    @Value
    public static class AuthInfo{
        String login;
        String password;
    }
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class VerificationCode{
        String code;
    }
}
