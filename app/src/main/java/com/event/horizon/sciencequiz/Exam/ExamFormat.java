package com.event.horizon.sciencequiz.Exam;

/**
 * Created by Horizon on 12/29/2017.
 */

public class ExamFormat
{


   private String question;
  private   String answer;
  private   String option1;
   private String option2;
  private   String option3;
   private String option4;
  private   String search;

    public ExamFormat(String question,String answer,String option1,String option2
    ,String option3,String option4,String search) {
        super();
        this.question=question;
        this.answer=answer;
        this.option1=option1;
        this.option2=option2;
        this.option3=option3;
        this.option4=option4;
        this.search=search;
    }

    public String getQuestion() {
        return question;
    }

    public String getAnswer() {
        return answer;
    }

    public String getOption1() {
        return option1;
    }

    public String getOption2() {
        return option2;
    }

    public String getOption3() {
        return option3;
    }

    public String getOption4() {
        return option4;
    }

    public String getSearch() {
        return search;
    }
}
