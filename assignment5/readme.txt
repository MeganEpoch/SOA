                        Assignment5
��Ա�����ҵ���ǲ��õķ�����ͨ��xslt����ת����ɸѡ����������ѧ���ɼ�����װ��soap��Ϣ���ء�
����˼·��ͨ�� void doGet(HttpServletRequest request, HttpServletResponse response) �ӿڻ�ȡ��Ӧ�����������Servlet��ʵ��
        ���Բ���ScoreServlet��ͨ��ע��@WebSeveletʵ�֡���request�л�ò���sid�󣬵���ScoreService�еķ���������˼·Ϊtransfor
        -merFactory.newTemplates()�ģ�壬��ͨ��ģ��Transformer���ٴ�new StreamSource()����xmlԴ�����ͨ��transformer
        .transform(source, result)�������ؽ���������߼���ScoreServlet��,�����query.xsl�ж���Soap��Ϣ�ĸ�ʽ�淶�Լ���ѯ����ʱ
        �ı�׼���أ��ٽ����д��response���ؼ��ɡ�

�ű�������srcĿ¼�£���ͼ��picturesĿ¼�¡�