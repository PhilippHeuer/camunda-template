package org.camunda.bpm;

import org.camunda.bpm.application.PostDeploy;
import org.camunda.bpm.application.PreUndeploy;
import org.camunda.bpm.application.ProcessApplication;
import org.camunda.bpm.engine.spring.application.SpringServletProcessApplication;
import org.springframework.stereotype.Component;

@Component("camundaProcessApplication")
@ProcessApplication
public class Application extends SpringServletProcessApplication {

	@PostDeploy
	public void onDeployed() throws Exception {
		System.out.println("Deployed!");
	}

	@PreUndeploy
	public void onUndeployed() throws Exception {
		System.out.println("Undeployed!");
	}
}
