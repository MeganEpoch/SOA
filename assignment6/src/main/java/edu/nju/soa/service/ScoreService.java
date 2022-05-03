package edu.nju.soa.service;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.soap.Node;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.*;
import java.net.URISyntaxException;
import java.util.Objects;

/**
 * service 层
 */
public class ScoreService {

    /**
     * 负责从xml中读取符合xsl模板的信息并返回
     * @param sid
     * @return
     */
//    public String getScoresByStudentId(String sid) {
//        TransformerFactory transformerFactory = TransformerFactory.newInstance();
//        try {
//            Templates templates = transformerFactory.newTemplates(new StreamSource(this.getClass().getResourceAsStream("/../../xsl/query.xsl")));
//            Transformer transformer = templates.newTransformer();
//            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
//            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
//            Source source = new StreamSource(this.getClass().getResourceAsStream("/../../xsl/scorelist.xml"));
//            StringWriter message = new StringWriter();
//            Result result = new StreamResult(message);
//            transformer.setParameter("sid",sid);
//            transformer.transform(source, result);
//            return message.toString();
//        }catch (TransformerException e) {
//            System.out.println("An error occurred while applying the xml file.");
//            e.printStackTrace();
//        }
//        return "";
//    }

    public String updateScoresByStudentId(String sid,String cid,String type,String score) {
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        try{
            Source xslSource = new StreamSource(this.getClass().getResourceAsStream("/../../xsl/transform.xsl"));
            Transformer t = transformerFactory.newTransformer(xslSource);
            t.setOutputProperty(OutputKeys.INDENT, "yes");
            t.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
            File htmlFile = new File(Objects.requireNonNull(this.getClass().getResource("/../../xsl/scorelist.xml")).toURI().getPath());
            Source source = new StreamSource(this.getClass().getResourceAsStream("/../../xsl/scorelist.xml"));
            Result result = new StreamResult(htmlFile);
            t.setParameter("sid",sid);
            t.setParameter("cid",cid);
            t.setParameter("type",type);
            t.setParameter("score",score);
            t.transform(source, result);
        } catch (TransformerException | URISyntaxException e) {
            e.printStackTrace();
        }
        try {
            Templates templates = transformerFactory.newTemplates(new StreamSource(this.getClass().getResourceAsStream("/../../xsl/query.xsl")));
            Transformer transformer = templates.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
            File htmlFile = new File(Objects.requireNonNull(this.getClass().getResource("/../../xsl/scorelist.xml")).toURI().getPath());
            Source source = new StreamSource(htmlFile);
            StringWriter message = new StringWriter();
            Result result = new StreamResult(message);
            transformer.setParameter("sid",sid);
            transformer.setParameter("cid",cid);
            transformer.setParameter("type",type);
            transformer.transform(source, result);
            return message.toString();
        }catch (TransformerException | URISyntaxException e) {
            System.out.println("An error occurred while applying the xml file.");
            e.printStackTrace();
        }
        return "";
    }
}
