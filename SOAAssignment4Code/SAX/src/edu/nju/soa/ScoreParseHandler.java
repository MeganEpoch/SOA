package edu.nju.soa;

import edu.nju.soa.entity.Score;
import edu.nju.soa.enums.ScoreType;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ScoreParseHandler extends DefaultHandler {
    Score tmpScore;
    String tmpValue;
    String tmpStudentId;
    String tmpInnerScore;
    String tmpCourseId;
    ScoreType tmpScoreType;
    List<Score> scoreList = new ArrayList<>();

    public void parseDocument(String filename) {
        SAXParserFactory factory = SAXParserFactory.newInstance();
        try {
            SAXParser parser = factory.newSAXParser();
            parser.parse(filename, this);
            ScoreXMLGenerator.createXML(scoreList);
        } catch (ParserConfigurationException e) {
            System.out.println("ParserConfig error");
        } catch (SAXException e) {
            System.out.println("SAXException : xml not well formed");
        } catch (IOException e) {
            System.out.println("IO error");
        }
    }

    @Override
    public void startElement(String uri, String localName,
                             String qName, Attributes attributes) throws SAXException {
        if (qName.equals("课程成绩")) {
            tmpCourseId = attributes.getValue("课程编号");
            tmpScoreType = ScoreType.valueOf(attributes.getValue("成绩性质"));
        }
    }

    @Override
    public void endElement(String s, String s1, String element) throws SAXException {
        switch (element) {
            case "课程成绩":
                tmpScore = new Score(tmpStudentId, tmpInnerScore, tmpCourseId, tmpScoreType);
                if (!tmpScore.isQualified()) {
                    scoreList.add(tmpScore);
                }
                break;
            case "学号":
                tmpStudentId = tmpValue;
                break;
            case "得分":
                tmpInnerScore = tmpValue;
                break;
        }

    }

    @Override
    public void characters(char[] ac, int i, int j) throws SAXException {
        tmpValue = new String(ac, i, j);
    }
}
