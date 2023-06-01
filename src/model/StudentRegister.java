package model;

import java.time.LocalDate;

public class StudentRegister {
    private Student student;
    private Subject subject;
    private String registrationTime;

    public StudentRegister(Student student, Subject subject, String registrationTime) {
        this.student = student;
        this.subject = subject;
        this.registrationTime = registrationTime;
    }

    public StudentRegister() {
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public String getTime() {
        return registrationTime;
    }

    public void setTime(String registrationTime) {
        this.registrationTime = registrationTime;
    }

    public String output(){
        return student.getStudentID() + "|"+ student.getFullName() +"|"+ subject.getSubjectID() +
                "|"+registrationTime;
    }

    @Override
    public String toString() {
        return "StudentRegister{" +
                "studentID=" + student.getStudentID() +
                ", fullName"+ student.getFullName() +
                ", subjectID=" + subject.getSubjectID() +
                ", registrationTime='" + registrationTime + '\'' +
                '}';
    }
}
