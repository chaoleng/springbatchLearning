package com.batch.springbatch.config;

import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.job.flow.FlowExecutionStatus;
import org.springframework.batch.core.job.flow.JobExecutionDecider;

import io.micrometer.core.instrument.distribution.CountAtBucket;

public class myDecider implements JobExecutionDecider {

	private int count;
	
	@Override
	public FlowExecutionStatus decide(JobExecution jobExecution, StepExecution stepExecution) {
		count ++;
		if (count%2 ==0) {
			return new FlowExecutionStatus("even");
		} else {
			return new FlowExecutionStatus("odd");
		}
	}

}
