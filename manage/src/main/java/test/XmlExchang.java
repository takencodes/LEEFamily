package test;


import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.ByteArrayInputStream;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

    /**
     *   * 获取指定标签中的内容
     *   * @param xml 传入的xml字符串
     *   * @param label  指定的标签中的内容
     *  
     */
    public static String regex(String xml, String label) {
        String context = "";
        //正则表达式
        String rgex = "<"+ label +">";
        String rgex1 = "<" + label + ">(.*?)</" + label + ">";
        Pattern pattern = Pattern.compile(rgex);// 匹配的模式    
        Matcher m = pattern.matcher(xml);
        //匹配的有多个
        List<String> list = new ArrayList<>();
        System.out.println(m.find());
        while (m.find()) {
            int i = 1;
            list.add(m.group(i));
            i++;
        }
        //只要匹配的第一个
        if (list.size() > 0) {
            context = list.get(0);
        }
        return context;
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
        String str3 = "<soap:Envelope xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\">\n" +
                "   <soap:Body>\n" +
                "      <ns2:MultiVerEnterpriseServiceResponse xmlns:ns2=\"com.platform.webservice.service\">\n" +
                "         <return><![CDATA[\n" +
                "         <?xml version='1.0' encoding='UTF-8'?>\n" +
                "       <HEAD>\n" +
                "\t         <SERIALNUM>A1000152000120201812191133250001</SERIALNUM>\n" +
                "\t         <code>200</code>\n" +
                "\t         <message>请求成功！</message>\n" +
                "\t       </HEAD>\n" +
                "         <BODY>\n" +
                "\t          <RESULT>\n" +
                "\t\t         <QYJBXX>\n" +
                "\t\t         \t\t<PRIPID>25200000******************</PRIPID>\n" +
                "\t\t         \t\t<UNISCID>91520222************5</UNISCID>\n" +
                "\t\t         \t\t<ENTNAME>盘州市************</ENTNAME>\n" +
                "\t\t         \t\t<REGNO>520222************</REGNO>\n" +
                "\t\t         \t\t<ENTTYPE>4540</ENTTYPE>\n" +
                "\t\t         \t\t<ENTTYPE_CN>个人独资企业</ENTTYPE_CN>\n" +
                "\t\t         \t\t<INDUSTRYPHY>H</INDUSTRYPHY>\n" +
                "\t\t         \t\t<INDUSTRYCO>6210</INDUSTRYCO>\n" +
                "\t\t         \t\t<ESTDATE>2017-05-23 00:00:00</ESTDATE>\n" +
                "\t\t         \t\t<REGORG>520222</REGORG>\n" +
                "\t\t         \t\t<REGORG_CN>盘州市市场监督管理局</REGORG_CN>\n" +
                "\t\t         \t\t<OPSCOTYPE></OPSCOTYPE>\n" +
                "\t\t         \t\t<OPSCOTYPE_CN></OPSCOTYPE_CN>\n" +
                "\t\t         \t\t<OPSCOPE>法律、法规、国务院决定规定禁止的不得经营；法律、法规、国务院决定规定应当许可（审批）的，经审批机关批准后凭许可（审批）文件经营;法律、法规、国务院决定规定无需许可（审批）的，市场主体自主选择经营。（餐饮，服务）</OPSCOPE>\n" +
                "\t\t         \t\t<OPFROM>2017-05-23 00:00:00</OPFROM>\n" +
                "\t\t         \t\t<OPTO></OPTO>\n" +
                "\t\t         \t\t<REGSTATE>1</REGSTATE>\n" +
                "\t\t         \t\t<REGSTATE_CN>存续（在营、开业、在册）</REGSTATE_CN>\n" +
                "\t\t         \t\t<DOMDISTRICT>520***</DOMDISTRICT>\n" +
                "\t\t         \t\t<DOM>贵州省六盘水市盘州市柏果镇***</DOM>\n" +
                "\t\t         \t\t<REGCAP>15</REGCAP>\n" +
                "\t\t         \t\t<REGCAPCUR>156</REGCAPCUR>\n" +
                "\t\t         \t\t<REGCAPCUR_CN>人民币</REGCAPCUR_CN>\n" +
                "\t\t         \t\t<REGCAPUSD>0</REGCAPUSD>\n" +
                "\t\t         \t\t<RECCAP>0</RECCAP>\n" +
                "\t\t         \t\t<RECCAPUSD>0</RECCAPUSD>\n" +
                "\t\t         \t\t<COUNTRY></COUNTRY>\n" +
                "\t\t         \t\t<EMPNUM>4</EMPNUM>\n" +
                "\t\t         \t\t<TOWN>否</TOWN>\n" +
                "\t\t         \t\t<NAME>***</NAME>\n" +
                "\t\t         \t\t<REPORTTYPE>B1</REPORTTYPE>\n" +
                "\t\t         \t\t<APPRDATE>2017-05-23 00:00:00</APPRDATE>\n" +
                "\t\t         \t</QYJBXX>\n" +
                "\t\t         \t<QYFDDBR>\n" +
                "\t\t         \t\t<PERSONID>9252000***018******</PERSONID>\n" +
                "\t\t         \t\t<NAME>***</NAME>\n" +
                "\t\t         \t\t<SEX>1</SEX>\n" +
                "\t\t         \t\t<NATDATE>1987-09-14 00:00:00</NATDATE>\n" +
                "\t\t         \t\t<CERTYPE>10</CERTYPE>\n" +
                "\t\t         \t\t<CERNO>530381198*********</CERNO>\n" +
                "\t\t         \t\t<POSITION></POSITION>\n" +
                "\t\t         \t\t<POSITION_CN></POSITION_CN>\n" +
                "\t\t         \t\t<POSBRFORM></POSBRFORM>\n" +
                "\t\t         \t\t<OCCSTBEAPP>无业</OCCSTBEAPP>\n" +
                "\t\t         \t\t<LEREPSIGN>1</LEREPSIGN>\n" +
                "\t\t         \t\t<APPOUNIT></APPOUNIT>\n" +
                "\t\t         \t\t<TEL>137***83***</TEL>\n" +
                "\t\t         \t\t<COUNTRY></COUNTRY>\n" +
                "\t\t         \t\t<TELNUMBER>137***83***</TELNUMBER>\n" +
                "\t\t         \t\t<MOBTEL>137***83***</MOBTEL>\n" +
                "\t\t         \t\t<EMAIL></EMAIL>\n" +
                "\t\t         \t\t<HOUSEADD>云南省玉溪市江川县江城镇明星***号</HOUSEADD>\n" +
                "\t\t         \t\t<ARRCHDATE></ARRCHDATE>\n" +
                "\t\t         \t\t<REPCARFROM></REPCARFROM>\n" +
                "\t\t         \t\t<REPCARTO></REPCARTO>\n" +
                "\t\t         \t\t<POSTALCODE>553***</POSTALCODE>\n" +
                "\t\t         \t</QYFDDBR>\n" +
                "\t\t         \t<GDZRRXX>\n" +
                "\t\t         \t\t<INV>***</INV>\n" +
                "\t\t         \t\t<CERTYPE>10</CERTYPE>\n" +
                "\t\t         \t\t<CERTYPE_CN>中华人民共和国居民身份证</CERTYPE_CN>\n" +
                "\t\t         \t\t<CERNO>5303811988*********</CERNO>\n" +
                "\t\t         \t\t<LISUBCONAM>15</LISUBCONAM>\n" +
                "\t\t         \t\t<SUBCONAMUSD>0</SUBCONAMUSD>\n" +
                "\t\t         \t\t<SUBCONFORM>0</SUBCONFORM>\n" +
                "\t\t         \t\t<SUBCONPROP>100</SUBCONPROP>\n" +
                "\t\t         \t\t<CONDATE>2017-05-23 00:00:00</CONDATE>\n" +
                "\t\t         \t\t<LIACCONAM>0</LIACCONAM>\n" +
                "\t\t         \t\t<ACCONAMUSD>0</ACCONAMUSD>\n" +
                "\t\t         \t\t<DOM>云南省玉溪市江川县江城镇明星***号</DOM>\n" +
                "\t\t         \t\t<CURRENCY>156</CURRENCY>\n" +
                "\t\t         \t\t<CURRENCY_CN>人民币元</CURRENCY_CN>\n" +
                "\t\t         \t\t<COUNTRY></COUNTRY>\n" +
                "\t\t         \t\t<COUNTRY_CN></COUNTRY_CN>\n" +
                "\t\t         \t\t<EXEAFFSIGN></EXEAFFSIGN>\n" +
                "\t\t         \t\t<RESPFORM></RESPFORM>\n" +
                "\t\t         \t\t<RESPFORM_CN></RESPFORM_CN>\n" +
                "\t\t         \t\t<SCONFORM></SCONFORM>\n" +
                "\t\t         \t\t<SEX>1</SEX>\n" +
                "\t\t         \t\t<NATION>01</NATION>\n" +
                "\t\t         \t\t<NATDATE>1987-09-14 00:00:00</NATDATE>\n" +
                "\t\t         \t\t<LITDEG>70</LITDEG>\n" +
                "\t\t         \t\t<POLSTAND>13</POLSTAND>\n" +
                "\t\t         \t\t<OCCST>无业</OCCST>\n" +
                "\t\t         \t\t<POSTALCODE>5535XXX</POSTALCODE>\n" +
                "\t\t         \t\t<TEL>1370888XXX1370888***</TEL>\n" +
                "\t\t         \t</GDZRRXX>\n" +
                "\t\t         \t<GDGSXX>\n" +
                "\t\t         \t\t无\n" +
                "\t\t         \t</GDGSXX>\n" +
                "\t\t         \t<FZJGXX>\n" +
                "\t\t         \t\t<PPRIPID>252000000***48***7</PPRIPID>\n" +
                "\t\t         \t\t<BRNAME>安顺公交出租汽车有限公司X分公司</BRNAME>\n" +
                "\t\t         \t\t<REGNO>52250129006***</REGNO>\n" +
                "\t\t         \t\t<REGORG>520***</REGORG>\n" +
                "\t\t         \t\t<REGORG_CN>安顺市西秀区市场监督管理局</REGORG_CN>\n" +
                "\t\t         \t\t<REGIDATE>2006-04-24 16:29:44</REGIDATE>\n" +
                "\t\t         \t\t</FZJGXX>\n" +
                "\t\t         \t</RESULT>\n" +
                "\t\t    </BODY>]]></return>\n" +
                "      </ns2: MultiVerEnterpriseServiceResponse>\n" +
                "   </soap:Body>\n" +
                "</soap:Envelope>\n";
//        String context = regex(str2,"BODY");
//        System.out.println("context = "+context);
        String rgex = "<BODY>";
        String rgex1 = "</BODY>";
        String[] str = str3.split(rgex);
        String[] st = str[1].split(rgex1);
        System.out.println(st[0]);

        SAXReader saxReader = new SAXReader();
        Document document;
        try {
            document = saxReader.read(new ByteArrayInputStream(st[0].getBytes()));
            Map map = Dom2Map(document);
            System.out.println("map>>> " + map);
        } catch (DocumentException e) {
            e.printStackTrace();
        }

    }
}
