package com;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.time.Duration;
import java.time.Instant;

public class App {
	public static void main(String[] args) {

		Instant start = Instant.now();


		String[] springConfig  = 
			{	
				"spring/batch/jobs/job-hello-world.xml" 
			};
		
		ApplicationContext context = 
				new ClassPathXmlApplicationContext(springConfig);
		
		JobLauncher jobLauncher = (JobLauncher) context.getBean("jobLauncher");
		Job job = (Job) context.getBean("helloWorldJob");

		try {

			JobExecution execution = jobLauncher.run(job, new JobParameters());
			System.out.println("Exit Status : " + execution.getStatus());
			System.out.println("Create Time : " + execution.getCreateTime());
			System.out.println("End Time    : " + execution.getEndTime());

		} catch (Exception e) {
			e.printStackTrace();
		}

		System.out.println("Done" + Duration.between(start, Instant.now()));
		System.out.println("Done" + Duration.between(start, Instant.now()).toMillis());


	}
}
