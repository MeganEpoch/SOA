package edu.nju.soa.controller;

import edu.nju.soa.entity.Score;
import edu.nju.soa.enums.ScoreType;
import edu.nju.soa.service.ScoreService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.soap.*;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * score servlet
 */
@WebServlet("/score")
public class ScoreServlet extends HttpServlet{

    ScoreService service;
    private MessageFactory messageFactory;

    @Override
    public void init() throws ServletException {
        service = new ScoreService();
        try {
            messageFactory = MessageFactory.newInstance(SOAPConstants.SOAP_1_2_PROTOCOL);
        } catch (SOAPException e) {
            e.printStackTrace();
        }
    }

//    @Override
//    protected void doGet(HttpServletRequest request, HttpServletResponse response){
//        System.out.println(request.getRequestURL());
//        String sid =  request.getParameter("sid");
//        String result = service.getScoresByStudentId(sid);
//        response.setCharacterEncoding("UTF-8");
//        response.setHeader("content-type","text/xml;charset=UTF-8");
//        PrintWriter writer = null;
//        try {
//            writer = response.getWriter();
//            writer.print(result);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }

//    @Override
//    public void doPost(HttpServletRequest req, HttpServletResponse resp){
//        System.out.println(req.getRequestURL());
//        String sid =  req.getParameter("sid");
//        String type = req.getParameter("type");
//        String score = req.getParameter("score");
//        String result = service.updateScoresByStudentId(sid,type,score);
//        resp.setCharacterEncoding("UTF-8");
//        resp.setHeader("content-type","text/xml;charset=UTF-8");
//        PrintWriter writer = null;
//        try {
//            writer = resp.getWriter();
//            writer.print(result);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse resp) throws ServletException {
        MimeHeaders headers = new MimeHeaders();
        List<Score> scoreList = new ArrayList<Score>();
        try {
            InputStream inputStream = request.getInputStream();
            SOAPMessage message = messageFactory.createMessage(headers,inputStream);
            SOAPBody soapBody = message.getSOAPBody();
            Iterator iterator = soapBody.getChildElements();
            SOAPElement element = (SOAPElement) iterator.next();
            Iterator dataIterator = element.getChildElements();
            while (dataIterator.hasNext()) {
                SOAPElement soapElement = (SOAPElement) dataIterator.next();
                String courseType = soapElement.getAttribute("jw:成绩性质");
                String cid = soapElement.getAttribute("jw:课程编号");
                SOAPElement scoreElement = (SOAPElement) soapElement.getChildElements().next();
                String sid = scoreElement.getFirstChild().getTextContent();
                String score = scoreElement.getLastChild().getTextContent();
                Score editScore = new Score(sid,score,cid, ScoreType.valueOf(courseType));
                scoreList.add(editScore);
            }
            PrintWriter writer = null;
            resp.setCharacterEncoding("UTF-8");
            resp.setHeader("content-type","text/xml;charset=UTF-8");
            try {
                writer = resp.getWriter();
            } catch (IOException e) {
                e.printStackTrace();
            }
            for (Score s: scoreList) {
                String msg = service.updateScoresByStudentId(s.getStudentId(),s.getCourseId(),s.getType().toString(),s.getScore());
                assert writer != null;
                writer.print(msg);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SOAPException e) {
            e.printStackTrace();
        }
    }
}
