package edu.nju.soa;

import edu.nju.soa.entity.Score;
import org.xml.sax.helpers.AttributesImpl;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.sax.SAXTransformerFactory;
import javax.xml.transform.sax.TransformerHandler;
import javax.xml.transform.stream.StreamResult;
import java.io.FileOutputStream;
import java.util.List;

public class ScoreXMLGenerator {
    public static void createXML(List<Score> scores) {
        try {
            SAXTransformerFactory factory = (SAXTransformerFactory) SAXTransformerFactory.newInstance();
            TransformerHandler handler = factory.newTransformerHandler();
            Transformer info = handler.getTransformer();
            info.setOutputProperty(OutputKeys.INDENT, "yes");
            info.setOutputProperty(OutputKeys.ENCODING, "utf-8");
            info.setOutputProperty(OutputKeys.VERSION, "1.0");
            StreamResult result = new StreamResult(new FileOutputStream("XML4.xml"));
            handler.setResult(result);
            handler.startDocument();
            AttributesImpl impl = new AttributesImpl();
            impl.clear();
            handler.startElement("", "", "课程成绩列表", impl);
            for (Score score : scores) {
                impl.clear(); //清空属性
                impl.addAttribute("", "", "成绩性质", "", score.getType().toString());
                impl.addAttribute("", "", "课程编号", "", score.getCourseId());
                handler.startElement("", "", "课程成绩", impl);
                handler.startElement("", "", "成绩", impl);
                handler.startElement("", "", "学号", impl);
                handler.characters(score.getStudentId().toCharArray(), 0, score.getStudentId().length()); //为title元素添加文本
                handler.endElement("", "", "学号");
                handler.startElement("", "", "得分", impl);
                handler.characters(score.getScore().toCharArray(), 0, score.getScore().length()); //为title元素添加文本
                handler.endElement("", "", "得分");
                handler.endElement("", "", "成绩");
                handler.endElement("", "", "课程成绩");
            }
            handler.endElement("", "", "课程成绩列表");
            handler.endDocument();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
