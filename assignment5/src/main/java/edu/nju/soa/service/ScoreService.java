package edu.nju.soa.service;

import javax.xml.transform.*;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.StringWriter;

/**
 * service 层
 */
public class ScoreService {

    /**
     * 负责从xml中读取符合xsl模板的信息并返回
     * @param sid
     * @return
     */
    public String getScoresByStudentId(String sid) {
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        try {
            Templates templates = transformerFactory.newTemplates(new StreamSource(this.getClass().getResourceAsStream("/../../xsl/query.xsl")));
            Transformer transformer = templates.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
            Source source = new StreamSource(this.getClass().getResourceAsStream("/../../xsl/scorelist.xml"));
            StringWriter message = new StringWriter();
            Result result = new StreamResult(message);
            transformer.setParameter("sid",sid);
            transformer.transform(source, result);
            return message.toString();
        }catch (TransformerException e) {
            System.out.println("An error occurred while applying the xml file.");
            e.printStackTrace();
        }
        return "";
    }
}
