package com.batch.springbatch.reader;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.springframework.batch.item.ItemReader;

import com.batch.springbatch.model.MainDo;
import com.batch.springbatch.model.Person;
import com.batch.springbatch.model.Student;

public class InMemoryMainReader implements ItemReader<MainDo> {
	 
    private int nextStudentIndex;
    private List<MainDo> studentData;
 
    public InMemoryMainReader() {
        initialize();
    }
 
    private void initialize() {
        Student student = new Student();
        student.setClassId("tony.tester@gmail.com");
        student.setName("Tony Tester");
 
        Person person = new Person();
        person.setLastName("Nick Newbie");
        person.setFirstName("starter");
        
        MainDo mainDo = new MainDo();
        mainDo.setPerson(person);
        mainDo.setStudent(student);
 
//        studentData.add(mainDo);
        studentData = Collections.unmodifiableList(Arrays.asList(mainDo));
        nextStudentIndex = 0;
    }
 
    @Override
    public MainDo read() throws Exception {
        MainDo nextStudent = null;
 
        if (nextStudentIndex < studentData.size()) {
            nextStudent = studentData.get(nextStudentIndex);
            nextStudentIndex++;
        }
 
        return nextStudent;
    }
}
