package com.wirtec.rparayno.examify.GameActivity;

/**
 * Created by Miguel on 05/04/2018.
 */

public class Questions {

    private String answer, choice_1, choice_2, choice_3, choice_4, qNumber, qText;

    public Questions(String answer, String choice_1, String choice_2, String choice_3, String choice_4, String qNumber, String qText) {
        this.answer = answer;
        this.choice_1 = choice_1;
        this.choice_2 = choice_2;
        this.choice_3 = choice_3;
        this.choice_4 = choice_4;
        this.qNumber = qNumber;
        this.qText = qText;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getChoice_1() {
        return choice_1;
    }

    public void setChoice_1(String choice_1) {
        this.choice_1 = choice_1;
    }

    public String getChoice_2() {
        return choice_2;
    }

    public void setChoice_2(String choice_2) {
        this.choice_2 = choice_2;
    }

    public String getChoice_3() {
        return choice_3;
    }

    public void setChoice_3(String choice_3) {
        this.choice_3 = choice_3;
    }

    public String getChoice_4() {
        return choice_4;
    }

    public void setChoice_4(String choice_4) {
        this.choice_4 = choice_4;
    }

    public String getqNumber() {
        return qNumber;
    }

    public void setqNumber(String qNumber) {
        this.qNumber = qNumber;
    }

    public String getqText() {
        return qText;
    }

    public void setqText(String qText) {
        this.qText = qText;
    }
}
