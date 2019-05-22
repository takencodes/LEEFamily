package test;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.net.ftp.FTPClient;

import java.io.*;

public class FileTest {

    public static void main(String[] args){
        boolean isOk;
        String path = "D:/";
        String name = "test.txt";
        String local = "D:/test/test.txt";
        String remote = "/home/shadowsocks_r_qr.png";
        String ip = "176.122.155.8";
        int port = 27344;
        String user = "root";
        String password = "pVNraL7rF5gZ";
        //从服务器抓取文件到本地
        FTPClient ftpClient = new FTPClient();
        try {
            ftpClient.connect(ip, port);
            ftpClient.login(user, password);
            isOk = ftpClient.retrieveFile(remote, new FileOutputStream(FilenameUtils.normalize(local)));
            if(!isOk){
                System.out.println("get file failed!");
            }
            System.out.println(isOk);
        } catch (IOException e) {
            e.printStackTrace();
        }
        //获取本地文件

        String fileFullName = path+name;
        FilenameUtils.normalize(fileFullName);
        System.out.println("get formatted path = "+FilenameUtils.normalize(fileFullName));
        File file = new File(FilenameUtils.normalize(fileFullName));
        if(!file.isFile()){
            System.out.println("file is not exist.");
        }
        BufferedReader bufferedReader = null;
        try {
           bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(file),"GBK"));
           String line = null;
            while ((line = bufferedReader.readLine()) != null){
                String[] str = line.split("\\,");
               System.out.println("bufferedReader = "+str[0]+"....."+str[1]);
           }
        } catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }


    }
}
