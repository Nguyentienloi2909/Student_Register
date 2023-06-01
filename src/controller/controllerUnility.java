package controller;
import model.Student;
import model.StudentRegister;
import model.Subject;
import java.io.*;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class controllerUnility {
    private FileWriter fileWriter;
    private BufferedReader bufferedReader;
    private InputStreamReader inputStreamReader;
    private FileInputStream fileInputStream;

//    mở - đóng file
    public void openFileToRead(String fileName){
        try {
            fileInputStream = new FileInputStream(fileName);
            inputStreamReader = new InputStreamReader(fileInputStream);
            bufferedReader = new BufferedReader(inputStreamReader);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void closeFile(String fileName){
        try{
            bufferedReader.close();
            inputStreamReader.close();
            fileInputStream.close();
        }catch (Exception e){
            e.printStackTrace();
        }

    }

//    SUBJECT
    public void writeSubjectToFile(Subject subject,String nameFileSubject){
        try {
            fileWriter = new FileWriter(nameFileSubject, true);
            fileWriter.write(subject.output()+"\n");
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Subject> readSubjectFromFile(String nameFileSubject){
        ArrayList<Subject> listSubject = new ArrayList<>();
        openFileToRead(nameFileSubject);
        try {
            String line = bufferedReader.readLine();
            while(line!= null){
                Subject subject = creatToSubject(line);
                listSubject.add(subject);
                line = bufferedReader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        closeFile(nameFileSubject);
        return listSubject;
    }

    private Subject creatToSubject(String line) {
        String[] datas = line.split("\\|");
        Subject subject = new Subject(Integer.valueOf(datas[0]), datas[1], Integer.valueOf(datas[2]), datas[3]);
        return subject;
    }

    public void duplicateSubjectID(Subject subject, String nameFileSubject){
        ArrayList<Subject> listSubject = readSubjectFromFile(nameFileSubject);
        if(listSubject.size() != 0){
            int subjectIDTamp = listSubject.get(listSubject.size()-1).getSubjectID() + 1;
            subject.setSubjectID(subjectIDTamp);
        }
    }

    public Subject subject(int subjectID, String nameFileSubject){
        ArrayList<Subject> listSubject = readSubjectFromFile(nameFileSubject);
        for (var a : listSubject){
            if(a.getSubjectID() == subjectID){
                return a;
            }
        }
        return null;
    }

//    STUDENT
    public void writeStudentToFile(Student student, String nameFileStudent){
        try {
            fileWriter = new FileWriter(nameFileStudent, true);
            fileWriter.write(student.output()+"\n");
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public Boolean formatPhoneNumber(String phoneNumber){
        String regex = "^\\d{3}[-]\\d{3}[-]\\d{4}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(phoneNumber);
        return matcher.matches();
    }

    public ArrayList<Student> readStudentFromFile(String nameFileStudent){
        ArrayList<Student> lists = new ArrayList<>();
        openFileToRead(nameFileStudent);
        try{
            String line = bufferedReader.readLine();
            while(line != null){
                Student student = creatToStudent(line);
                lists.add(student);
                line = bufferedReader.readLine();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        closeFile(nameFileStudent);
        return lists;
    }

    private Student creatToStudent(String line) {
        String[] datas = line.split("\\|");
        Student student = new Student(Integer.valueOf(datas[0]), datas[1], datas[2], datas[3]);
        return student;
    }

    public void duplicateStudentID(Student student, String nameFileStudent){
        ArrayList<Student> listStudent = readStudentFromFile(nameFileStudent);
        if(listStudent.size() != 0){
            int studentIDTamp = listStudent.get(listStudent.size()-1).getStudentID() + 1;
            student.setStudentID(studentIDTamp);
        }
    }

    public Student student(int studentID, String nameFileStudent){
        ArrayList<Student> listStudent = readStudentFromFile(nameFileStudent);
        for (var a : listStudent){
            if(a.getStudentID() == studentID){
                return a;
            }
        }
        return null;
    }

//  Studen_Register
    public void writeSutdentRegisterToFile(StudentRegister studentRegister, String nameFileSutdentRegister){
        try{
            fileWriter = new FileWriter(nameFileSutdentRegister, true);
            fileWriter.write(studentRegister.output()+"\n");
            fileWriter.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public ArrayList<StudentRegister> readStudentRegisterToFile(String nameFileSutdentRegister){
        ArrayList<StudentRegister> lists = new ArrayList<>();
        openFileToRead(nameFileSutdentRegister);
        try{
            String line = bufferedReader.readLine();
            while(line != null){
                StudentRegister studentRegister = creatToStudentRegister(line);
                lists.add(studentRegister);
                line = bufferedReader.readLine();
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        closeFile(nameFileSutdentRegister);
        return lists;
    }

    private StudentRegister creatToStudentRegister(String line) {
        String[] datas = line.split("\\|");
        StudentRegister studentRegister = new StudentRegister(new Student(Integer.valueOf(datas[0]), datas[1]),
                new Subject(Integer.valueOf(datas[2])),datas[3]);
        return studentRegister;
    }

    public Boolean checkRegisteredSubject(String nameFileSutdentRegister, int studenID, int subjectID){
        ArrayList<StudentRegister> lists = readStudentRegisterToFile(nameFileSutdentRegister);
        for (var a : lists){
            if(a.getStudent().getStudentID() == studenID && a.getSubject().getSubjectID() == subjectID){
                return true; // thông tin đã tồn tại
            }
        }
        return false;
    }

    public String thePresentTime(){
        String registrationTime = java.time.LocalDate.now().toString();
        return registrationTime;
    }

    public int countSubject(String nameFileSutdentRegister, int studentID){
        int count = 0;
        ArrayList<StudentRegister> lists = readStudentRegisterToFile(nameFileSutdentRegister);
        for (var a : lists){
            if(a.getStudent().getStudentID() == studentID){
                count++;
            }
        }
        return count;
    }
}



