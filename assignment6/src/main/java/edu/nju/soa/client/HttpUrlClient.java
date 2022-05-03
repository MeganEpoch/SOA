package edu.nju.soa.client;



import edu.nju.soa.entity.Score;
import edu.nju.soa.enums.ScoreType;

import javax.xml.soap.*;
import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * client for post test using {@link HttpURLConnection}
 */
public class HttpUrlClient {

    public static void main(String[] args) {
        HttpUrlClient client = new HttpUrlClient();
        List<Score> Scores = new ArrayList<Score>();
        Score Score = new Score("191250002","22","000201", ScoreType.总评成绩);
        Scores.add(Score);
        System.out.println(client.doPost(Scores));
//        Scores = new ArrayList<Score>();
//        Score = new Score(141250204,309717,"平时成绩",22);
//        Scores.add(Score);
//        System.out.println(client.doPost(Scores));
//        Scores = new ArrayList<Score>();
//        Score = new Score(141250205,309716,"平时成绩",22);
//        Scores.add(Score);
//        System.out.println(client.doPost(Scores));
    }

    private String doPost(List<Score> Scores) {
        try {
            URL url = new URL("http://localhost:8080/assignment5/score");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoOutput(true);
            connection.setDoInput(true);
            String msg = generatePostMsg(Scores);
            PrintWriter printWriter = new PrintWriter(connection.getOutputStream());
            printWriter.write(msg);
            printWriter.flush();
            BufferedInputStream bis = new BufferedInputStream(connection.getInputStream());
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            int len;
            byte[] arr = new byte[1024];
            while((len=bis.read(arr))!= -1){
                bos.write(arr,0,len);
                bos.flush();
            }
            bos.close();
            return bos.toString("utf-8");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

    private String generatePostMsg(List<Score> Scores) {
        try {
            MessageFactory messageFactory = MessageFactory.newInstance(SOAPConstants.SOAP_1_2_PROTOCOL);
            SOAPMessage message = messageFactory.createMessage();
            SOAPPart soapPart = message.getSOAPPart();
            SOAPEnvelope soapEnvelope = soapPart.getEnvelope();
            soapEnvelope.addNamespaceDeclaration("xsd","http://www.w3.org/2001/XMLSchema");
            SOAPBody soapBody = soapEnvelope.getBody();
            SOAPElement element = soapBody.addChildElement(soapEnvelope.createName("课程成绩列表","jw","http://jw.nju.edu.cn/schema"));
            for (Score entity: Scores) {
                SOAPElement courseElement = element.addChildElement(element.createQName("课程成绩","jw"));
                courseElement.addAttribute(element.createQName("成绩性质","jw"),
                        entity.getType().toString());
                courseElement.addAttribute(element.createQName("课程编号","jw"),
                        entity.getCourseId()+"");
                SOAPElement scoreElement = courseElement.addChildElement(courseElement.createQName("成绩","jw"));
                scoreElement.addChildElement(scoreElement.createQName("学号","jw"))
                        .addTextNode(entity.getStudentId()+"");
                scoreElement.addChildElement(scoreElement.createQName("得分","jw"))
                        .addTextNode(entity.getScore()+"");
            }
            message.saveChanges();
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            message.writeTo(stream);
            return new String(stream.toByteArray(), "utf-8");
        } catch (SOAPException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

}
