package tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.*;

public class Lesson_2 {

    void selectGender(String gender) {
        $x("//input[@id='gender-radio-1']/following-sibling::label[text()='"+gender+"']").click();
    }

    @BeforeAll
    static void setup() {
        Configuration.startMaximized = true;
    }

    @Test
    public void practiceForm() {

        SelenideElement inputFirstName = $x("//input[@id='firstName']");
        SelenideElement inputLastName = $x("//input[@id='lastName']");
        SelenideElement inputUserEmail = $x("//input[@id='userEmail']");

        String firstName = "Ivan";
        String lastName = "Ivanov";
        String userEmail = "IvanovEmail@gmail.com";
        String gender = "Male";

        open("https://demoqa.com/automation-practice-form");

        inputFirstName.setValue(firstName);
        inputLastName.setValue(lastName);
        inputUserEmail.setValue(userEmail);
        selectGender(gender);
    }
}
