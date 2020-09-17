package com.batch.springbatch.config;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.batch.springbatch.model.MainDo;
import com.batch.springbatch.model.Student;
import com.batch.springbatch.reader.InMemoryMainReader;

@Configuration
@EnableBatchProcessing
public class JobDemo {

	@Autowired
	private StepBuilderFactory stepBuilderFactory;

	@Autowired
	private JobBuilderFactory jobBuilderFactory;

	@Autowired
	private DataSource dataSource;

	@Autowired
	private SqlSessionFactory sqlSessionFactory;
	
	@Autowired
	@Qualifier("OutputViewItemWriter")
	private ItemWriter<? super MainDo> outputViewItemWriter;
	
	@Autowired
    private ItemProcessor<MainDo, MainDo> mainProccProcessor;

	/* 1、创建一个Job作业 */
	@Bean
	public Job databaseJob2() {
		return jobBuilderFactory.get("dataBaseJob").start(chunkStep()).build();
	}

	// 2、创建一个step*/
	@Bean
	public Step chunkStep() {
		return stepBuilderFactory.get("chunkStep")
				.<MainDo,MainDo>chunk(10) // 每chunkSize次提交一次
				.reader(databaseItemReader()) // 读取数据库，并把库表中每列数据映射到工程中的User bean中
				.processor(mainProccProcessor)
				.writer(outputViewItemWriter)
				.allowStartIfComplete(true)
				.build();
	}

	/* 3、读数据库配置 */
//    @Bean
//    @StepScope
//    public ItemReader<Person> databaseItemReader(){
//        MyBatisPagingItemReader<Person> reader = new MyBatisPagingItemReader<Person>();
//      
//        Map<String, Object> parameterValues = new HashMap<String, Object>();
//        parameterValues.put("person_id", "1");
//        
//        reader.setQueryId("com.batch.springbatch.model.PersonMapper.selectById");
//        reader.setPageSize(1);
//        reader.setParameterValues(parameterValues);
//        reader.setSqlSessionFactory(sqlSessionFactory);
//        return reader;              //设置数据源
//    }
	
	@Bean
	public InMemoryMainReader databaseItemReader() {
		return new InMemoryMainReader();

	}
	
}
