package com.batch.springbatch.config;

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
		return jobBuilderFactory.get("dataBaseJob").start(chunkStep()).build();
	}

	// 2、创建一个step*/
	@Bean
	public Step chunkStep() {
		return stepBuilderFactory.get("chunkStep")
				.<MainDo,MainDo>chunk(10) // 每chunkSize次提交一次
				.reader(inMemoryMainReader) // 读取数据库，并把库表中每列数据映射到工程中的User bean中
				.processor(mainProccProcessor)
				.writer(outputViewItemWriter)
				.allowStartIfComplete(true)
				.build();
	}
	
}
