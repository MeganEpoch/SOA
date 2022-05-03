import org.xml.sax.SAXException;

import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.File;
import java.io.IOException;

public class ValidateXML {
    public static void main(String[] args) throws SAXException, IOException {
        SchemaFactory schemaFactory = SchemaFactory.newInstance("http://www.w3.org/2001/XMLSchema");
        File schemaFile = new File("XSLT/ScoreList.xsd");
        Schema schema = schemaFactory.newSchema(schemaFile);
        Validator validator = schema.newValidator();
        Source source = new StreamSource("XSLT/XML3.xml");
        validator.validate(source);
    }
}
