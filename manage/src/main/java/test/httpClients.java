package test;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

public class httpClients {

    public static String postXml(String url, String xmlFile) {
        CloseableHttpClient client = null;
        CloseableHttpResponse resp = null;
        try {
            HttpPost httpPost = new HttpPost(url);
            httpPost.setHeader("Content-Type", "text/xml;charset=UTF-8");
            client = HttpClients.createDefault();
            StringEntity entityParams = new StringEntity(xmlFile, "utf-8");
            httpPost.setEntity(entityParams);
            client = HttpClients.createDefault();
            resp = client.execute(httpPost);
            String resultMsg = EntityUtils.toString(resp.getEntity(), "utf-8");
            return resultMsg;

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (client != null) {
                    client.close();
                }
                if (resp != null) {
                    client.close();
                }
            } catch (IOException e) {
                e.getMessage();
            }

        }
        return null;
    }
}
