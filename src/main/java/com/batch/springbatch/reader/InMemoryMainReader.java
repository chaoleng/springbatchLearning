package com.batch.springbatch.reader;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.annotation.BeforeStep;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.ItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.batch.springbatch.model.MainDo;
import com.batch.springbatch.model.MainDoObject;
import com.batch.springbatch.model.Person;
import com.batch.springbatch.model.Student;

@Component
public class InMemoryMainReader implements ItemReader<MainDo> {
	 
    private int nextStudentIndex;
    private List<MainDo> studentData;
//    private Object someObject;
    
    @Autowired
	private MainDoObject mainDoObject;
    
    public InMemoryMainReader() {
        initialize();
    }
 
    private void initialize() {
    	
    	
//    	System.out.println(persons);
//        Student student = new Student();
//        
//        student.setClassId("tony.tester@gmail.com");
//        student.setName("Tony Tester");
// 
        Person person = new Person();
//        person.setLastName("Nick Newbie");
//        person.setFirstName("starter");
        
        MainDo mainDo = new MainDo();
        mainDo.setPerson(person);
//        mainDo.setStudent(student);
        
        if (mainDoObject != null) {
        	System.out.println(mainDoObject.getPersons());
		}
 
//        studentData.add(mainDo);
        studentData = Collections.unmodifiableList(Arrays.asList(mainDo));
        nextStudentIndex = 0;
       
       
        
//        System.out.println(someObject);
        
//      
        
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
