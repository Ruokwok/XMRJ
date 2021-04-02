package me.onebone.economyapi;

import java.io.*;

public class Task {
    private String shell;
    private String charset = "UTF-8";
    private Handler handler;
    private String pid;

    private Process process;
    final private BufferedWriter[] bw = {null};
    final private DataOutputStream[] out = {null};

    public Task() {}

    public Task(Handler telescope) {
        this();
        this.handler = telescope;
    }

    public void setShell(String shell) {
        this.shell = shell;
    }

    public void run() {
        try {
            process = Runtime.getRuntime().exec(shell);
            bw[0] = new BufferedWriter(new OutputStreamWriter(process.getOutputStream()));
            read();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void read() {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(process.getInputStream(), charset));
            String cmdout = new String();
            while ((cmdout = br.readLine()) != null) {
                handler.getRadio(cmdout);
            }
        } catch (IOException e) { }
    }

    public void input(String string) throws IOException {
        bw[0].write(string);
        bw[0].newLine();
        bw[0].flush();
    }

    public void close() {
        process.destroy();
    }

    public String getCharset() {
        return charset;
    }

    public void setCharset(String charset) {
        this.charset = charset;
    }

    public void setHandler(Handler handler) {
        this.handler = handler;
    }

    public String getPID() {
        return pid;
    }
}
