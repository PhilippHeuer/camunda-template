package application;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.camunda.bpm.engine.ProcessEngine;
import org.camunda.bpm.spring.boot.starter.annotation.EnableProcessApplication;
import org.camunda.bpm.spring.boot.starter.event.PostDeployEvent;
import org.camunda.bpm.spring.boot.starter.event.PreUndeployEvent;
import org.camunda.bpm.spring.boot.starter.property.CamundaBpmProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.lang.invoke.MethodHandles;

/**
 * Process Application Core
 * 
 */
@SpringBootApplication
@EnableScheduling
@EnableProcessApplication("camundaTemplate")
public class CamundaProcessApplication implements CommandLineRunner {

	/**
	 * Holds the logger instance
	 */
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	/**
	 * Is the process instance stopped?
	 */
	boolean processApplicationReady = false;

	/**
	 * BPMN Properties
	 */
	@Autowired
	private CamundaBpmProperties camundaBpmProperties;
	
	/**
	 * Process Engine
	 */
	@Autowired
	private ProcessEngine processEngine;
	
	/**
	 * App EntryPoint
	 * 
	 * @param args
	 * @throws Exception
	 */
	public static void main(final String... args) throws Exception {
		SpringApplication.run(MethodHandles.lookup().lookupClass(), args);
	}
	
	/**
	 * Run: write camunda configuration to log
	 */
	@Override
	public void run(String... strings) throws Exception {
		logger.info("CommandLineRunner#run() - {}", ToStringBuilder.reflectionToString(camundaBpmProperties.getApplication(), ToStringStyle.SHORT_PREFIX_STYLE));
	}

	/**
	 * Event: DeploymentSuccess
	 * 
	 * @param event
	 */
	@EventListener
	public void onPostDeploy(PostDeployEvent event) {
		processApplicationReady = true;
	}
	
	/**
	 * Event: BeginUndeployment
	 * 
	 * @param event
	 */
	@EventListener
	public void onPreUndeploy(PreUndeployEvent event) {
		processApplicationReady = false;
	}

}
