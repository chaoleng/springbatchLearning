package com.batch.springbatch.writer;

import java.util.List;
 
import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

import com.batch.springbatch.model.MainDo;
 
@Component("OutputViewItemWriter")
public class OutputViewItemWriter implements ItemWriter<MainDo> {
	
	@Override
    public void write(List<? extends MainDo> items) throws Exception {
        System.out.println("writer chunk size is :" + items.size());
        for (MainDo item : items) {
            System.out.println("writer data is:" + item.getPerson());
        }
    }
}
 

