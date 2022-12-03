package com.driver;

import org.springframework.stereotype.Repository;

import java.sql.ClientInfoStatus;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class StudentRepository {

    Map<String,Student> studentMap = new HashMap<>();
    Map<String,Teacher> teacherMap = new HashMap<>();

    Map<String, List<String>> pair = new HashMap<>();

    public void addStudent(Student student){
        studentMap.put(student.getName(),student);
    }

    public void addTeacher(Teacher teacher){
        teacherMap.put(teacher.getName(),teacher);
    }
    public void addStudentTeacherPair(String student ,String teacher){
        List<String> list = new ArrayList<>();
        if(pair.containsKey(teacher)){
            list = pair.get(teacher);
            list.add(student);
            pair.put(teacher,list);
        }
        else {
            list.add(student);
            pair.put(teacher,list);
        }
    }
    public Student getStudentByName(String name){
            return studentMap.get(name);
    }
    public Teacher getTeacherByName(String name){
            return teacherMap.get(name);
    }
    public List<String> getStudentsByTeacherName(String teacher){
        List<String> list =null;
       if(pair.containsKey(teacher)){
           list = pair.get(teacher);}

           return list;


    }
    public List<String> getAllStudents(){
        List<String> list = new ArrayList<>();
        for (Student i : studentMap.values()){
            list.add(i.getName());
        }
        return list;
    }
    public void deleteTeacherByName(String name){
        List<String> listOfStudent = new ArrayList<>();
       if(pair.containsKey(name)){
           listOfStudent = pair.get(name);
           pair.remove(name);
       }

       for(String i : listOfStudent){
           studentMap.remove(i);
       }
        teacherMap.remove(name);
    }
    public void  deleteAllTeachers(){
        for (String i : teacherMap.keySet()){
            deleteTeacherByName(i);
        }
    }
}
