package org.motechproject.ananyabatch.utils;

import java.io.*;

public class AntTask {
    private String buildFile;
    private String buildFilePath;
    private String environment;
    private String antFile;

    public AntTask(String buildFile, String buildFilePath, String environment, String antFile) {
        this.buildFile = buildFile;
        this.buildFilePath = buildFilePath;
        this.environment = environment;
        this.antFile = antFile;
    }

    public void run(String targetName) throws IOException, InterruptedException {
        String cmd = "sudo "+ antFile +" -f " + buildFile + " " + targetName + " -Denv=" + environment + " -lib " + buildFilePath;
        Runtime runtime = Runtime.getRuntime();
        Process process = null;
        process = runtime.exec(cmd);
        process.waitFor();

        BufferedReader buf = new BufferedReader(new InputStreamReader(process.getInputStream()));
        String line = "";
        File abcd = new File("/home/motech/abcd");
        abcd.createNewFile();
        BufferedWriter writer = new BufferedWriter(new FileWriter("/home/motech/abcd"));
        writer.write("aaaaaaaaaaaaaaaaaa");
        while ((line = buf.readLine()) != null) {
            writer.write(line);
        }
        writer.close();
    }
}
