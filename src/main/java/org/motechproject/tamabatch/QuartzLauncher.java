package org.motechproject.tamabatch;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameter;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.launch.support.SimpleJobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.HashMap;
import java.util.Random;

public class QuartzLauncher {

    Job viewIndexerJob;
    private SimpleJobLauncher simpleJobLauncher;

    public QuartzLauncher(Job viewIndexerJob, SimpleJobLauncher simpleJobLauncher) {
        this.viewIndexerJob = viewIndexerJob;
        this.simpleJobLauncher = simpleJobLauncher;
    }

    public void run() {
        System.out.println(this.getClass().getName() + " " + " RUNNING ");
        final HashMap<String, JobParameter> parameters = new HashMap<String, JobParameter>();
        parameters.put("soms", new JobParameter("" + new Random().nextLong()));
        JobParameters jobParameters = new JobParameters(parameters);
        try {
            simpleJobLauncher.run(viewIndexerJob, jobParameters);
        } catch (Exception e) {
            e.printStackTrace();  //TODO : e ignored
        }


    }
}
