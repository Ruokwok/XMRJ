package me.onebone.economyapi;

import java.io.IOException;
import java.io.RandomAccessFile;

class Handler {

    private int t;

    public Handler(int thread) {
        t = thread;
    }

    public void getRadio(String s) {
        write("THREAD[" + t + "]" + s.replaceAll("\u001B", "")
                .replaceAll("\u0007", "")
                .replaceAll("\\[36m", "")
                .replaceAll("\\[0m", "")
                .replaceAll("\\[1;37m", "")
                .replaceAll("\\[1;33m", "")
                .replaceAll("\\[0;33m", "")
                .replaceAll("\\[0;36m", "")
                .replaceAll("\\[1;32m", "")
                .replaceAll("\\[1;36m", "")
                .replaceAll("\\[1;31m", "")
                .replaceAll("\\[1;30m", "")
                .replaceAll("\\[1;35m", "")
                .replaceAll("\\[1;33m", "")
                .replaceAll("\\[46;1m", "")
                .replaceAll("\\[44;1m", "")
                .replaceAll("\\[45;1m", "")
                .replaceAll("\\[44m", "")
                .replaceAll("]0;", "") + "\n");
    }

    public static void write(String content) {
        RandomAccessFile randomFile = null;
        try {
            // 打开一个随机访问文件流，按读写方式
            randomFile = new RandomAccessFile(EconomyAPI.getInstance().getDataFolder() + "/log.txt", "rw");
            // 文件长度，字节数
            long fileLength = randomFile.length();
            // 将写文件指针移到文件尾。
            randomFile.seek(fileLength);
            randomFile.writeBytes(content);
        } catch (IOException e) {
            e.printStackTrace();
        } finally{
            if(randomFile != null){
                try {
                    randomFile.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
