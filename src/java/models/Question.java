package models;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author bayan
 */
public class Question {
    private int id;
    private String title;
    private String opt1;
     private String opt2;
      private String opt3;
       private String opt4;
        private int correct;
        private int curId = 1;
        
        public Question(String title, String opt1,String opt2,
                  String opt3, String opt4, int correct){
        this.correct = correct;
        this.title=title;
        this.opt1 = opt1;
        this.opt2= opt2;
            this.opt3= opt3;
            this.opt4= opt4;
            this.id = curId;
            curId++;
        }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getOpt1() {
        return opt1;
    }

    public void setOpt1(String opt1) {
        this.opt1 = opt1;
    }

    public String getOpt2() {
        return opt2;
    }

    public void setOpt2(String opt2) {
        this.opt2 = opt2;
    }

    public String getOpt3() {
        return opt3;
    }

    public void setOpt3(String opt3) {
        this.opt3 = opt3;
    }

    public String getOpt4() {
        return opt4;
    }

    public void setOpt4(String opt4) {
        this.opt4 = opt4;
    }

    public int getCorrect() {
        return correct;
    }

    public void setCorrect(int correct) {
        this.correct = correct;
    }
        
        
}
