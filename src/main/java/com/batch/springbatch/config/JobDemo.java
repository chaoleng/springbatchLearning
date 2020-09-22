package com.batch.springbatch.config;

import java.util.Arrays;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.annotation.BeforeStep;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.support.ListItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.batch.springbatch.model.MainDo;
import com.batch.springbatch.reader.InMemoryMainReader;

@Configuration
@EnableBatchProcessing
public class JobDemo {

	@Autowired
	private StepBuilderFactory stepBuilderFactory;

	@Autowired
	private JobBuilderFactory jobBuilderFactory;

	@Autowired
	@Qualifier("OutputViewItemWriter")
	private ItemWriter<? super MainDo> outputViewItemWriter;
	
	@Autowired
    private ItemProcessor<MainDo, MainDo> mainProccProcessor;
	
	@Autowired
	private InMemoryMainReader inMemoryMainReader;

	/* 1、创建一个Job作业 */
	@Bean
	public Job databaseJob2() {
		return jobBuilderFactory.get("databaseJob2")
				.incrementer(new RunIdIncrementer())
				.start(chunkStep1())
				.next(chunkStep2())
				.build();
	}

	// 2、创建一个step*/
	@Bean
	public Step chunkStep1() {
		return stepBuilderFactory.get("chunkStep")
				.<Integer, Integer>chunk(10) // 每chunkSize次提交一次
				.reader(itemReader()) // 读取数据库，并把库表中每列数据映射到工程中的User bean中
//				.processor(mainProccProcessor)
				.writer(itemWriter())
				.allowStartIfComplete(true)
				.build();
	}
	
	@Bean
	public ItemReader<Integer> itemReader() {
		return new ListItemReader<>(Arrays.asList(1, 2, 3, 4));
	}
	
	@Bean
    public ItemWriter<Integer> itemWriter() {
        return new ItemWriter<Integer>() {

            private StepExecution stepExecution;

            @Override
            public void write(List<? extends Integer> items) throws Exception {
                for (Integer item : items) {
                    System.out.println("item = " + item);
                }
                stepExecution.getJobExecution().getExecutionContext().put("data", "foo");
            }

            @BeforeStep
            public void saveStepExecution(StepExecution stepExecution) {
                this.stepExecution = stepExecution;
            }

        };
    }
	
	@Bean
	public Step chunkStep2() {
		return stepBuilderFactory.get("chunkStep")
				.<MainDo,MainDo>chunk(10) // 每chunkSize次提交一次
				.reader(inMemoryMainReader) // 读取数据库，并把库表中每列数据映射到工程中的User bean中
				.processor(mainProccProcessor)
				.writer(outputViewItemWriter)
				.allowStartIfComplete(true)
				.build();
	}
	
}
