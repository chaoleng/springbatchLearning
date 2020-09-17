//package com.batch.springbatch.config;
//
//import org.springframework.batch.core.Job;
//import org.springframework.batch.core.Step;
//import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
//import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
//import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
//import org.springframework.batch.core.launch.JobLauncher;
//import org.springframework.batch.core.repository.JobRepository;
//import org.springframework.batch.core.step.builder.JobStepBuilder;
//import org.springframework.batch.core.step.builder.StepBuilder;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.transaction.PlatformTransactionManager;
//
//
//@Configuration
//@EnableBatchProcessing
//public class NestedDemo {
//	
//
//	@Autowired
//	private JobBuilderFactory jobBuilderFactory;
//	
//	@Autowired
//	private StepBuilderFactory stepBuilderFactory;
//	
//	@Autowired
//	private Job childJobOne;
//	
//	@Autowired
//	private Job childJobTwo;
//	
//	@Autowired
//	private JobLauncher launcher;
//	
//	@Bean
//	public Job parentJob(JobRepository jobRepository,PlatformTransactionManager transactionManager) {
//		return jobBuilderFactory.get("parentJob")
//				.start(childjob1(jobRepository,transactionManager))
//				.next(childjob2(jobRepository,transactionManager))
//				.build();
//	}
//	
//	
//	// job -> step
//	private Step childjob2(JobRepository jobRepository,PlatformTransactionManager transactionManager) {
//		return new JobStepBuilder(new StepBuilder("childjob2"))
//				.job(childJobTwo)
//				.launcher(launcher)
//				.repository(jobRepository)
//				.transactionManager(transactionManager)
//				.build();
//	}
//
//	private Step childjob1(JobRepository jobRepository,PlatformTransactionManager transactionManager) {
//		return new JobStepBuilder(new StepBuilder("childjob1"))
//				.job(childJobOne)
//				.launcher(launcher)
//				.repository(jobRepository)
//				.transactionManager(transactionManager)
//				.build();
//	}
//
//}
