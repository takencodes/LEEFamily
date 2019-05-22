package test;

import org.apache.soap.Constants;
import org.apache.soap.Fault;
import org.apache.soap.SOAPException;
import org.apache.soap.rpc.Call;
import org.apache.soap.rpc.Parameter;
import org.apache.soap.rpc.Response;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.io.SAXReader;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;
import java.util.Vector;

public class WebServiceInvokeTest {

    /**
     * soa
     *
     * @param user
     * @return
     */
    public static String getService(String user) {
        URL url = null;
        try {
            url = new URL(
                    "http://192.168.0.100:8080/ca3/services/caSynrochnized");
        } catch (MalformedURLException mue) {
            return mue.getMessage();
        }
        // This is the main SOAP object
        Call soapCall = new Call();
        // Use SOAP encoding
        soapCall.setEncodingStyleURI(Constants.NS_URI_SOAP_ENC);
        // This is the remote object we're asking for the price
        soapCall.setTargetObjectURI("urn:xmethods-caSynrochnized");
        // This is the name of the method on the above object
        soapCall.setMethodName("getUser");
        // We need to send the ISBN number as an input parameter to the method
        Vector soapParams = new Vector();

        // name, type, value, encoding style
        Parameter isbnParam = new Parameter("userName", String.class, user,
                null);
        soapParams.addElement(isbnParam);
        soapCall.setParams(soapParams);
        try {
            // Invoke the remote method on the object
            Response soapResponse = soapCall.invoke(url, "");
            // Check to see if there is an error, return "N/A"
            if (soapResponse.generatedFault()) {
                Fault fault = soapResponse.getFault();
                String f = fault.getFaultString();
                return f;
            } else {
                // read result
                Parameter soapResult = soapResponse.getReturnValue();
                // get a string from the result
                return soapResult.getValue().toString();
            }
        } catch (SOAPException se) {
            return se.getMessage();
        }
    }

    /**
     * httpUrlConnection
     */
    public static void main(String[] args) throws IOException, DocumentException {
        //1：创建服务地址
        URL url = new URL("http://www.webxml.com.cn/WebServices/WeatherWebService.asmx?wsdl");
        //2：打开到服务地址的一个连接
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        //3：设置连接参数
        //3.1设置发送方式：POST必须大写
        connection.setRequestMethod("POST");
        //3.2设置数据格式：Content-type
        connection.setRequestProperty("content-type", "text/xml;charset=utf-8");
        //3.3设置输入输出，新创建的connection默认是没有读写权限的，
        connection.setDoInput(true);
        connection.setDoOutput(true);

        //4：组织SOAP协议数据，发送给服务端
        String soapXML = getXML();
        OutputStream os = connection.getOutputStream();
        os.write(soapXML.getBytes());

        //5：接收服务端的响应
        int responseCode = connection.getResponseCode();
        if (200 == responseCode) {//表示服务端响应成功
            InputStream is = connection.getInputStream();
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader br = new BufferedReader(isr);

            StringBuilder sb = new StringBuilder();
            String temp = null;

            while (null != (temp = br.readLine())) {
                sb.append(temp);
            }

            System.out.println(sb.toString());
            SAXReader saxReader = new SAXReader();
            Document document = saxReader.read(new ByteArrayInputStream(sb.toString().getBytes()));
            Map map = XmlExchang.Dom2Map(document);
            System.out.println(map);
            is.close();
            isr.close();
            br.close();
        }

        os.close();
    }

    /**
     *
     * @param
     * @return
     */
    public static String getXML() {
        String soapXML = "<?xml version=\"1.0\" encoding=\"utf-8\"?>"
                + "<soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\">"
                + "<soap:Body>"
                + "<getSupportDataSet xmlns=\"http://WebXml.com.cn/\">"
//                + "<byProvinceName>天津</byProvinceName>"
                + "</getSupportDataSet>"
                + "</soap:Body>"
                + "</soap:Envelope>";
        return soapXML;
    }

}
