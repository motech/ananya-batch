package org.motechproject.ananyabatch.backup.jobs;

import org.motechproject.ananyabatch.utils.AntTask;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.Properties;

@Component
public class StartAllServices implements Tasklet, InitializingBean {

    private Properties batchProperties;

    @Autowired
    public StartAllServices(@Qualifier("batchProperties") Properties batchProperties) {
        this.batchProperties = batchProperties;
    }


    @Override
    public void afterPropertiesSet() throws Exception {
    }

    @Override
    public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {
        String buildFilePath = batchProperties.getProperty("deploy.build.file");
        String buildFile = buildFilePath + File.separator + "build.xml";
        String environment = batchProperties.getProperty("environment");
        new AntTask(buildFile, buildFilePath, environment).run("start.services");
        return RepeatStatus.FINISHED;

    }
}
