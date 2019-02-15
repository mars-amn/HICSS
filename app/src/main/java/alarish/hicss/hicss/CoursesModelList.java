package alarish.hicss.hicss;

public class CoursesModelList {
    public String cou_name;
    public String dr_name;
    public String book_name;
    public int full_mark;
    public int stu_mark;
    public String stu_Percentage;
    public String stu_grade;

    public CoursesModelList(String cou_name, String dr_name, String book_name, int full_mark,int stu_mark,String stu_Percentage,String stu_grade) {
        this.cou_name = cou_name;
        this.dr_name = dr_name;
        this.book_name = book_name;
        this.full_mark = full_mark;
        this.stu_mark = stu_mark;
        this.stu_Percentage = stu_Percentage;
        this.stu_grade = stu_grade;


    }



    public String getCou_name() {
        return cou_name;
    }

    public String getDr_namr() {
        return dr_name;
    }
    public String getBook_name() {
        return book_name;
    }
    public int getFull_mark() {
        return full_mark;
    }
    public int getStu_mark (){
        return stu_mark;
    }

    public String getStu_Percentage () {
        return stu_Percentage;
    }


    public String getStu_grade() {
        return stu_grade;
    }


}

