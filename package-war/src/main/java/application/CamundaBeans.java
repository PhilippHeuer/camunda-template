package application;

import org.camunda.bpm.ProcessEngineService;
import org.camunda.bpm.engine.ProcessEngine;
import org.springframework.beans.factory.annotation.Autowire;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

/**
 * Wire camunda components
 */
@Component
public class CamundaBeans {

    @Bean(autowire = Autowire.BY_TYPE)
    public ProcessEngine getProcessEngine() {
        ApplicationContext context = new ClassPathXmlApplicationContext("camunda-beans.xml");
        ProcessEngineService processEngineServices = (ProcessEngineService) context.getBean("processEngineService");
        return processEngineServices.getDefaultProcessEngine();
    }

}
