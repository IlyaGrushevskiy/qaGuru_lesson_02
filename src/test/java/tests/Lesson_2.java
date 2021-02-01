package tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.*;

public class Lesson_2 {

    SelenideElement inputFirstName = $x("//input[@id='firstName']");
    SelenideElement inputLastName = $x("//input[@id='lastName']");
    SelenideElement inputUserEmail = $x("//input[@id='userEmail']");
    SelenideElement inputUserNumber = $x("//input[@id='userNumber']");

    SelenideElement textareaCurrentAddress = $x("//textarea[@id='currentAddress']");

    SelenideElement buttonSubmit = $x("//button[@id='submit']");

    String state, city;
    String firstName = "Ivan";
    String lastName = "Ivanov";
    String userEmail = "IvanovEmail@gmail.com";
    String gender = "Male";
    String userNumber = "9997778888";
    String dateOfBirth = "6 Jul 1988";
    String subjects = "English";
    String hobbies = "Sports";
    String currentAddress = "Moscow, Arbat";

    void selectGender(String gender) {
        $x("//input[@id='gender-radio-1']/following-sibling::label[text()='"+gender+"']").click();
    }

    void selectAutoCompleteInput(String text) {
        $x("//input[@id='subjectsInput']").setValue(text);
        $x("//input[@id='subjectsInput']").pressEnter();
    }

    void setCheckBox(String text) {
        $x("//input[@id='hobbies-checkbox-1']/following-sibling::label[text()='"+text+"']").click();
    }

    void reactSelect() {
        $x("//div[@id='state']").scrollTo().click();
        $x("//div[contains(@id,'react-select-3')]").click();
        state = $x("//div[@id='state']//div[contains(@class,'singleValue')]").getText();
        $x("//div[@id='city']").click();
        $x("//div[contains(@id,'react-select-4')]").click();
        city = $x("//div[@id='city']//div[contains(@class,'singleValue')]").getText();
    }

    void inputDate(String date) {
        String[] dateArray = date.split(" ");
        $x("//input[@id='dateOfBirthInput']").click();
        $x("//select[contains(@class,'year')]/option[text()='" + dateArray[2] + "']").click();
        $x("//select[contains(@class,'month')]/option[contains(text(),'" + dateArray[1] + "')]").click();
        $x("//div[contains(@class,'datepicker') and text()='"+dateArray[0]+"']").click();
    }

    void checkingValues(String tableRow, String checkingValue) {
        if (tableRow.toLowerCase().contains("date")) {
            String[] dateArray = checkingValue.split(" ");
            for (String s : dateArray) {
                String value = $x("//td[text()='" + tableRow + "']/following-sibling::td").getText();
                Assertions.assertTrue(value.contains(s), "ОШИБКА: " + value + " не содержит " + s + " !");
            }
        } else {
            String value = $x("//td[text()='" + tableRow + "']/following-sibling::td").getText();
            Assertions.assertEquals(value, checkingValue, "ОШИБКА: " + value + " не равно " + checkingValue + " !");
        }
    }

    @BeforeAll
    static void setup() {
        Configuration.startMaximized = true;
    }

    @Test
    public void practiceForm() {

        open("https://demoqa.com/automation-practice-form");

        inputFirstName.setValue(firstName);
        inputLastName.setValue(lastName);
        inputUserEmail.setValue(userEmail);
        selectGender(gender);
        inputUserNumber.setValue(userNumber);
        inputDate(dateOfBirth);
        selectAutoCompleteInput(subjects);
        setCheckBox(hobbies);
        textareaCurrentAddress.setValue(currentAddress);
        reactSelect();

        buttonSubmit.hover().click();

        checkingValues("Student Name", firstName+" "+lastName);
        checkingValues("Student Email", userEmail);
        checkingValues("Gender", gender);
        checkingValues("Mobile", userNumber);
        checkingValues("Date of Birth", dateOfBirth);
        checkingValues("Subjects", subjects);
        checkingValues("Hobbies", hobbies);
        checkingValues("Address", currentAddress);
        checkingValues("State and City", state + " " + city);
    }
}
