package model;

public class Subject {
    private static int id = 10000000;
    private int subjectID;
    private String subjectName;
    private int totalLesson;
    private String subjectType;



    public void setSubjectID() {
        this.subjectID = id;
        id = id+1;
    }

    public int getSubjectID() {
        return subjectID;
    }

    public void setSubjectID(int subjectID) {
        this.subjectID = subjectID;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public int getTotalLesson() {
        return totalLesson;
    }

    public void setTotalLesson(int totalLesson) {
        this.totalLesson = totalLesson;
    }

    public String getSubjectType() {
        return subjectType;
    }

    public void setSubjectType(String subjectType) {
        this.subjectType = subjectType;
    }


    public Subject(int subjectID, String subjectName, int totalLesson, String subjectType) {
        if (subjectID == 0){
            setSubjectID();
        }else{
            this.subjectID = subjectID;
        }
        this.subjectName = subjectName;
        this.totalLesson = totalLesson;
        this.subjectType = subjectType;
    }
    public Subject() {}
    public Subject(int subjectID) {
        this.subjectID = subjectID;
    }

    public String output(){
        return this.getSubjectID() + "|" + this.getSubjectName() + "|" + this.getTotalLesson()+"|"+
                this.getSubjectType();
    }

    @Override
    public String toString() {
        return "Subject{" +
                "subjectID=" + subjectID +
                ", subjectName='" + subjectName + '\'' +
                ", totalLesson=" + totalLesson +
                ", subjectType='" + subjectType + '\'' +
                '}';
    }
}
