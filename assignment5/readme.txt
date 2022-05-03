                        Assignment5
针对本次作业我们采用的方法是通过xslt进行转换，筛选符合条件的学生成绩并封装成soap消息返回。
具体思路：通过 void doGet(HttpServletRequest request, HttpServletResponse response) 接口获取相应的请求参数，Servlet的实现
        可以参照ScoreServlet，通过注解@WebSevelet实现。从request中获得参数sid后，调用ScoreService中的方法，方法思路为transfor
        -merFactory.newTemplates()搭建模板，再通过模板搭建Transformer，再从new StreamSource()设置xml源，最后通过transformer
        .transform(source, result)方法返回结果（具体逻辑见ScoreServlet）,最后在query.xsl中定义Soap消息的格式规范以及查询出错时
        的标准返回，再将结果写入response返回即可。

脚本代码在src目录下，截图在pictures目录下。