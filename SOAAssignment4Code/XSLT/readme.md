# SOA Assignment4 XSLT

1. 新建xsl文件transform.xsl，在XML2.xml中引入：

   ```xml
   <?xml-stylesheet type="text/xsl" href="transform.xsl"?>
   ```

2. 在xsl文件中建立stylesheet根节点，其中确定要引入的命名空间。

   ```xml
   <xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                   xmlns:ns="http://jw.nju.edu.cn/schema"
                   exclude-result-prefixes="ns"
                   version="1.0">
   ```

3. 在template中，先匹配根节点，生成scorelist.xsd的root标签`<课程成绩列表>`，然后遍历每一个匹配到的`/ns:学生列表/ns:学生/ns:课程成绩列表/ns:课程成绩`节点，生成符合scorelist.xsd的元素`<课程成绩>`，并为其生成两个属性“成绩性质”和“课程编号”，以及两个子元素“学号”与“得分”。

   ```xml
       <xsl:template match="/">
           <课程成绩列表 xmlns="http://jw.nju.edu.cn/schema">
               <xsl:for-each select="/ns:学生列表/ns:学生/ns:课程成绩列表/ns:课程成绩">
                   <xsl:sort select="@课程编号">
                   </xsl:sort>
                   <xsl:sort select="@成绩性质" order="descending">
                   </xsl:sort>
                   <xsl:sort select="ns:成绩/ns:得分" order="descending">
                   </xsl:sort>
                   <xsl:element name="课程成绩">
                       <xsl:attribute name="成绩性质">
                           <xsl:value-of select="@成绩性质"/>
                       </xsl:attribute>
                       <xsl:attribute name="课程编号">
                           <xsl:value-of select="@课程编号"/>
                       </xsl:attribute>         
                       <成绩>
                           <xsl:element name="学号">
                               <xsl:value-of select="ns:成绩/ns:学号"/>
                           </xsl:element>
                           <xsl:element name="得分">
                               <xsl:value-of select="ns:成绩/ns:得分"/>
                           </xsl:element>
                       </成绩> 
                   </xsl:element>
                   
               </xsl:for-each>
           </课程成绩列表>
       </xsl:template>
   ```

4. 在java代码中，使用transformer来将xml通过xsl文件转换成新的xml文件XML3.xml

   ```java
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
   ```

5. 对XML3.xml进行验证，看是否符合ScoreList.xsd

   ```java
       public static void main(String[] args) throws SAXException, IOException {
           SchemaFactory schemaFactory = SchemaFactory.newInstance("http://www.w3.org/2001/XMLSchema");
           File schemaFile = new File("XSLT/ScoreList.xsd");
           Schema schema = schemaFactory.newSchema(schemaFile);
           Validator validator = schema.newValidator();
           Source source = new StreamSource("XSLT/XML3.xml");
           validator.validate(source);
       }
   ```

   