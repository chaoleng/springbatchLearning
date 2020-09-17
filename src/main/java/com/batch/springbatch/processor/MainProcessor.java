package com.batch.springbatch.processor;


import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

import com.batch.springbatch.model.MainDo;
import com.batch.springbatch.model.Student;
 
@Component
public class MainProcessor implements ItemProcessor<MainDo, MainDo> {
 
    @Override
    public MainDo process(MainDo item) throws Exception {
    	
    	Student student = new Student();
    	student.setClassId("008");
    	student.setName("li");
    	
    	item.setStudent(student);
    	
    	System.out.println("this is a processor"+item.getStudent());
		return item;
        
    }
}
 
