//package com.batch.springbatch.config;
//
//import org.springframework.batch.core.Job;
//import org.springframework.batch.core.Step;
//import org.springframework.batch.core.StepContribution;
//import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
//import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
//import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
//import org.springframework.batch.core.job.flow.JobExecutionDecider;
//import org.springframework.batch.core.scope.context.ChunkContext;
//import org.springframework.batch.core.step.tasklet.Tasklet;
//import org.springframework.batch.repeat.RepeatStatus;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//import com.batch.springbatch.decider.MyDecider;
//
//@Configuration
//@EnableBatchProcessing
//public class DeciderDemo {
//
//	@Autowired
//	private JobBuilderFactory jobBuilderFactory;
//	
//	@Autowired
//	private StepBuilderFactory stepBuilderFactory;
//	
//	@Autowired
//	private MyDecider myDecider;
//	
//	@Bean
//	public Step DeciderDemoStep1() {
//		return stepBuilderFactory.get("splitDemoStep1")
//				.tasklet(new Tasklet() {
//					
//					@Override
//					public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
//						System.out.println("DeciderDemoStep1");
//						return RepeatStatus.FINISHED;
//					}
//				}).build();
//	}
//	
//	@Bean
//	public Step DeciderDemoStep2() {
//		return stepBuilderFactory.get("splitDemoStep2")
//				.tasklet(new Tasklet() {
//					
//					@Override
//					public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
//						System.out.println("even");
//						return RepeatStatus.FINISHED;
//					}
//				}).build();
//	}
//	
//	@Bean
//	public Step DeciderDemoStep3() {
//		return stepBuilderFactory.get("splitDemoStep3")
//				.tasklet(new Tasklet() {
//					
//					@Override
//					public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
//						System.out.println("odd");
//						return RepeatStatus.FINISHED;
//					}
//				}).build();
//	}
//	
//	
//	// create Job
//	@Bean
//	public Job deciderDemoJob() {
//		return jobBuilderFactory.get("deciderDemoJob")
//				.start(DeciderDemoStep1())
//				.next(myDecider)
//				.from(myDecider).on("even").to(DeciderDemoStep2())  //如果返回even执行step2
//				.from(myDecider).on("odd").to(DeciderDemoStep3()) //如果返回even执行step3
//				.from(DeciderDemoStep3()).on("*").to(myDecider) //无论返回什么返回到决策器
//				.end()
//				.build();
//	}
//	
//}
