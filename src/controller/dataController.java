package controller;

import model.Student;
import model.StudentRegister;
import model.Subject;

import java.lang.reflect.Array;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class dataController {
    private controllerUnility controller = new controllerUnility();
    private Scanner sc = new Scanner(System.in);
    private String subjectFileName = "D:/.vscode/LAP_TRINH_JAVA/student_Register/src/SUBJECTS.DAT";
    private String studentFileName = "D:/.vscode/LAP_TRINH_JAVA/student_Register/src/STUDENTS.DAT";
    private String studentRegisterFile= "D:/.vscode/LAP_TRINH_JAVA/student_Register/src/STUDENT_REGISTER.DAT";

//    case 1:
    public void addSubjectNewToFile(){
        String subjectName, subjectType;
        int totalLesson, soluongSubject;
        String[] listSubjectType = {"General", "Specializations base", "compulsory majors", "specialization options"};

        System.out.print("Số lượng subject: ");
        soluongSubject = sc.nextInt();
        sc.nextLine();
        for (int i =0; i< soluongSubject; i++){
            System.out.print("subjectName: ");
            subjectName = sc.nextLine();
            System.out.print("totalLesson: ");
            totalLesson = sc.nextInt();
            sc.nextLine();

            System.out.println("subjectType");
            int chonSubjectType;
            do{
                System.out.println("\t1. General");
                System.out.println("\t2. Specializations base");
                System.out.println("\t3. compulsory majors");
                System.out.println("\t4. specialization options");
                System.out.print("\nmoi chon: ");
                chonSubjectType = sc.nextInt();
                sc.nextLine();
            }while(chonSubjectType < 1 && chonSubjectType > 4);
            subjectType = listSubjectType[chonSubjectType-1];
            Subject subject =new Subject(0, subjectName, totalLesson, subjectType);
            controller.duplicateSubjectID(subject,subjectFileName);
            controller.writeSubjectToFile(subject, subjectFileName);
        }
    }

//    case 2
    public void printSubjectFromFile(){
        ArrayList<Subject> listSubject = controller.readSubjectFromFile(subjectFileName);
        for (var a : listSubject){
            System.out.println(a);
        }
    }

//    case 3
    public void addStudentNewToFile(){
        String fullName, address, phoneNumber;
        int soLuongStudent;
        System.out.print("Nhap so lượng Student: ");
        soLuongStudent = sc.nextInt();
        sc.nextLine();
        for (int i =0; i< soLuongStudent; i++){
            System.out.print("\nfullName: ");
            fullName = sc.nextLine();
            System.out.print("address: ");
            address = sc.nextLine();
            while(true) {
                System.out.print("phoneNumber(###-###-####): ");
                phoneNumber = sc.nextLine();
                boolean checkPhoneNumber = controller.formatPhoneNumber(phoneNumber);
                if(checkPhoneNumber){
                    break;
                }else{
                    System.out.println("phoneNumber không đúng định dạng!");
                }
            }
            Student student = new Student(0, fullName, address, phoneNumber);
            controller.duplicateStudentID(student, studentFileName);
            controller.writeStudentToFile(student, studentFileName);
        }
    }

//    case 4
    public void printStudentFromFile(){
        ArrayList<Student> listStudent = controller.readStudentFromFile(studentFileName);
        for(var a : listStudent){
            System.out.println(a);
        }
    }

//    case 5
    public void createSavePrintStudentRegister(){
        int studentID, subjectID;
        String time = "";
        ArrayList<Student> listStudent = controller.readStudentFromFile(studentFileName);
        for(var a : listStudent){
            System.out.println(a);
        }
        System.out.print("studentID: ");
        studentID = sc.nextInt();

        System.out.println();
        ArrayList<Subject> listSubject = controller.readSubjectFromFile(subjectFileName);
        for (var a : listSubject){
            System.out.println(a);
        }
        System.out.print("subjectID: ");
        subjectID = sc.nextInt();
        time = controller.thePresentTime();
        Boolean check =controller.checkRegisteredSubject(studentRegisterFile,studentID, subjectID);
        if (check){
            System.out.println("subject đã đăng ký!");
        }else{
            int count = controller.countSubject(studentRegisterFile, studentID);
            if(count < 10){
                Student student = controller.student(studentID, studentFileName);
                Subject subject = controller.subject(subjectID, subjectFileName);
                StudentRegister studentRegister = new StudentRegister(student, subject, time);
                controller.writeSutdentRegisterToFile(studentRegister, studentRegisterFile);
                ArrayList<StudentRegister> lists = controller.readStudentRegisterToFile(studentRegisterFile);
                for(var a : lists){
                    System.out.println(a);
                }
            }else{
                System.out.println("đã đăng ký tối đa subject");
            }

        }

    }

    public void menu(){
        int luaChon = 0;
        do{
            System.out.println("___________________CHỨC NĂNG_____________");
            System.out.println("1. add Subject new to file SUBJECT.DAT");
            System.out.println("2. print the list Subject already in the file");
            System.out.println("3. add Student new to file STUDENT.DAT");
            System.out.println("4. print the list Student already in the file");
            System.out.println("5. create information studentRegister, save to file, and print list");
            System.out.println("6. sort list studentRegistes in the file");
            System.out.println("7. make a list of classes according to the registration list");
            System.out.println("0. exit");
            System.out.print("\nxin mời bạn chọn: ");
            luaChon = sc.nextInt();
            sc.nextLine();
            switch (luaChon){
                case 1:
                    addSubjectNewToFile();
                    break;
                case 2:
                    printSubjectFromFile();
                    break;
                case 3:
                    addStudentNewToFile();
                    break;
                case 4:
                    printStudentFromFile();
                    break;
                case 5:
                    createSavePrintStudentRegister();
                    break;
                case 6:
                    break;
                case 7:
                    break;
                case 0:
                    break;
            }
        }while (luaChon !=0);
    }
}
