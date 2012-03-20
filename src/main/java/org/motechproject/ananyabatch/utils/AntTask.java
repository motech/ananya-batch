package org.motechproject.ananyabatch.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class AntTask {
    private String buildFile;
    private String buildFilePath;

    public AntTask(String buildFile, String buildFilePath) {
        this.buildFile = buildFile;
        this.buildFilePath = buildFilePath;
    }

    public void run(String targetName) throws IOException, InterruptedException {
        String cmd = "sudo ant -f " + buildFile + " " + targetName + " -lib " + buildFilePath;
        Runtime runtime = Runtime.getRuntime();
        Process process = null;
        process = runtime.exec(cmd);
        process.waitFor();

        BufferedReader buf = new BufferedReader(new InputStreamReader(process.getInputStream()));
        String line = "";
        while ((line = buf.readLine()) != null) {
            System.out.println(line);
        }
    }
}
