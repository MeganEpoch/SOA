import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.File;
import java.io.IOException;

public class Main{
    public static void main(String[] args) {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        try{
            TransformerFactory tFac = TransformerFactory.newInstance();
            Source xslSource = new StreamSource("XSLT/transform.xsl");
            Transformer t = tFac.newTransformer(xslSource);
            t.setOutputProperty(OutputKeys.INDENT, "yes");
            t.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
            File xmlFile = new File("XSLT/XML2.xml");
            File htmlFile = new File("XSLT/XML3.xml");
            Source source = new StreamSource(xmlFile);
            Result result = new StreamResult(htmlFile);
            System.out.println(result.toString());
            t.transform(source, result);
        } catch (TransformerException e) {
            e.printStackTrace();
        }
    }
}