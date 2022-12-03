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
        if(studentMap.containsKey(name)){
            return studentMap.get(name);
        }
        return null;
    }
    public Teacher getTeacherByName(String name){
        if(teacherMap.containsKey(name)){
            return teacherMap.get(name);
        }
        return null;
    }
    public List<String> getStudentsByTeacherName(String teacher){
        List<String> list = new ArrayList<>();
       if(pair.containsKey(teacher)){
           list = pair.get(teacher);
           return list;
       }
       return null;
    }
    public List<String> getAllStudents(){
        List<String> list = new ArrayList<>();
        for (String i : studentMap.keySet()){
            list.add(i);
        }
        return list;
    }
    public void deleteTeacherByName(String name){
        List<String> listOfTecher = new ArrayList<>();
       if(pair.containsKey(name)){
           listOfTecher = pair.get(name);
       }
       for(String i : listOfTecher){
           teacherMap.remove(i);
       }
    }
    public void  deleteAllTeachers(){
        for (String i : teacherMap.keySet()){
            deleteTeacherByName(i);
        }
    }
}
