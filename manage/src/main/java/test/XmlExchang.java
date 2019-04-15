package test;


import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.ByteArrayInputStream;
import java.util.*;

/**
 * @Author: lzz
 * @Description:
 * @Date: Created in 17:54 2019/1/23
 */
public class XmlExchang {
    public static Map Dom2Map(Element e) {
        Map map = new HashMap();
        List list = e.elements();
        if (list.size() > 0) {
            for (int i = 0; i < list.size(); i++) {
                Element iter = (Element) list.get(i);
                List mapList = new ArrayList();
                if (iter.elements().size() > 0) {
                    Map m = Dom2Map(iter);
                    if (map.get(iter.getName()) != null) {
                        Object obj = map.get(iter.getName());
                        if (!obj.getClass().getName().equals("java.util.ArrayList")) {
                            mapList = new ArrayList();
                            mapList.add(obj);
                            mapList.add(m);
                        }
                        if (obj.getClass().getName().equals("java.util.ArrayList")) {
                            mapList = (List) obj;
                            mapList.add(m);
                        }
                        map.put(iter.getName(), mapList);
                    } else
                        map.put(iter.getName(), m);
                } else {
                    if (map.get(iter.getName()) != null) {
                        Object obj = map.get(iter.getName());
                        if (!obj.getClass().getName().equals("java.util.ArrayList")) {
                            mapList = new ArrayList();
                            mapList.add(obj);
                            mapList.add(iter.getText());
                        }
                        if (obj.getClass().getName().equals("java.util.ArrayList")) {
                            mapList = (List) obj;
                            mapList.add(iter.getText());
                        }
                        map.put(iter.getName(), mapList);
                    } else
                        map.put(iter.getName(), iter.getText());//公共map resultCode=0
                }
            }
        } else
            map.put(e.getName(), e.getText());
        return map;
    }

    public static Map<String, Object> Dom2Map(Document doc) {
        Map<String, Object> map = new HashMap<>();
        if (doc == null) {
            return map;
        }
        Element root = doc.getRootElement();
        for (Iterator iterator = root.elementIterator(); iterator.hasNext(); ) {
            Element e = (Element) iterator.next();
            List list = e.elements();
            if (list.size() > 0) {
                map.put(e.getName(), Dom2Map(e));
            } else
                map.put(e.getName(), e.getText());
        }
        return map;
    }

    public static void main(String[] args) {
        String str2 = "<ROOT>" +
                "  <HEADER>" +
                "      <POOL_ID>2</POOL_ID>" +
                "      <CHANNEL_ID>11</CHANNEL_ID>" +
                "      <USERNAME>tom</USERNAME>" +
                "      <PASSWORD>sss</PASSWORD>" +
                "  </HEADER>" +
                "  <BODY>" +
                "      <BUSLIST>" +
                "          <PHONE_NO>7107300212</PHONE_NO>" +
                "          <TRACE_ID>97D2C7D26224A2DAE9A1CB501E60F395</TRACE_ID>" +
                "          <TENANT_ID>EUR</TENANT_ID>" +
                "          <LANG>zh_CN</LANG>" +
                "      </BUSLIST>" +
                "      <BUSLIST>" +
                "          <PHONE_NO>2222300212</PHONE_NO>" +
                "          <TRACE_ID>444424A2DAE9A1CB501E60F395</TRACE_ID>" +
                "          <TENANT_ID>USA</TENANT_ID>" +
                "          <LANG>zh_CN</LANG>" +
                "      </BUSLIST>" +
                "  </BODY>" +
                "</ROOT>";
        SAXReader saxReader = new SAXReader();
        Document document;
        try {
            document = saxReader.read(new ByteArrayInputStream(str2.getBytes()));
            Map map = Dom2Map(document);
            System.out.println("map>>> " + map);
        } catch (DocumentException e) {
            e.printStackTrace();
        }
    }
}
