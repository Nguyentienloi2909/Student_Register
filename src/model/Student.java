package model;

public class Student {
    private static int id = 10000000;
    private int studentID;
    private String fullName;
    private String address;
    private String phoneNumber;


    public int getStudentID() {
        return studentID;
    }

    public void setStudentID(int studentID) {
        this.studentID = studentID;
    }
    public void setStudentID() {
        this.studentID = id;
        id = id + 1;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Student(int studentID, String fullName, String address, String phoneNumber) {
        if (studentID == 0){
            setStudentID();
        }else{
            this.studentID = studentID;
        }
        this.fullName = fullName;
        this.address = address;
        this.phoneNumber = phoneNumber;
    }

    public Student(int studentID, String fullName) {
        this.studentID = studentID;
        this.fullName = fullName;
    }
    public Student() {}
    public String output(){
        return this.getStudentID()+"|"+ this.getFullName()+"|"+this.getAddress()+"|"+
                this.getPhoneNumber();
    }
    @Override
    public String toString() {
        return "Student{" +
                "studentID=" + studentID +
                ", fullName='" + fullName + '\'' +
                ", address='" + address + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                '}';
    }
}
