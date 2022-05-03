<?xml version="1.0" encoding="UTF8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:jw="http://jw.nju.edu.cn/schema">
    <xsl:output method="xml" indent="yes"/>
    <xsl:param name="sid"/>
    <xsl:param name="cid"/>
    <xsl:param name="type"/>

    <xsl:template match="/">
        <env:Envelope
                xmlns:env="http://www.w3.org/2003/05/soap-envelope"
                xmlns:my="http://www.example.com/">
            <env:Header>
                <t:transaction xmlns:t="http://thirdparty.example.org/transaction"
                               env:encodingStyle="http://example.com/encoding" env:mustUnderstand="true">5
                </t:transaction>
                <s:Security xmlns:s="http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-secext-1.0.xsd"
                            env:mustUnderstand="true">
                    <s:UsernameToken>
                        <s:Username>UserName</s:Username>
                        <s:Password>Password</s:Password>
                    </s:UsernameToken>
                </s:Security>
            </env:Header>
            <env:body>
                <xsl:choose>
                    <xsl:when test="count(//jw:课程成绩[@成绩性质=$type and @课程编号=$cid]/jw:成绩/jw:学号[text()=$sid]) = 0">
                        <env:Fault>
                            <env:Code>
                                <env:Value>env:Receiver</env:Value>
                                <env:Subcode>
                                    <env:Value>my:bad argument</env:Value>
                                </env:Subcode>
                            </env:Code>
                            <env:Reason>
                                <env:Text xml:lang="en">no such sid found in score list:<xsl:value-of select="$sid"/>,
                                    or no such kind of score
                                </env:Text>
                                <env:Text xml:lang="zh">成绩系统中没有 <xsl:value-of select="$sid"/> 同学的 <xsl:value-of select="$cid"/> 课程的 <xsl:value-of select="$type"/>
                                </env:Text>
                            </env:Reason>
                            <env:Detail>
                                <my:adivce>请确认输入的学号、课程正确。</my:adivce>
                            </env:Detail>
                        </env:Fault>
                    </xsl:when>
                    <xsl:otherwise>
                        <jw:课程成绩列表>
                            <xsl:apply-templates
                                    select="//jw:课程成绩[@成绩性质=$type and @课程编号=$cid]/jw:成绩/jw:学号[text()=$sid]"/>
                        </jw:课程成绩列表>
                    </xsl:otherwise>
                </xsl:choose>
            </env:body>
        </env:Envelope>
    </xsl:template>

    <xsl:template match="//jw:课程成绩[@成绩性质=$type and @课程编号=$cid]/jw:成绩/jw:学号[text()=$sid]">
        <jw:课程成绩>
            <xsl:attribute name="成绩性质">
                <xsl:value-of select="../../@成绩性质"/>
            </xsl:attribute>
            <xsl:attribute name="课程编号">
                <xsl:value-of select="../../@课程编号"/>
            </xsl:attribute>
            <jw:成绩>
                <jw:学号>
                    <xsl:value-of select="$sid"/>
                </jw:学号>
                <jw:得分>
                    <xsl:value-of select="../jw:得分"/>
                </jw:得分>
            </jw:成绩>
        </jw:课程成绩>
    </xsl:template>

</xsl:stylesheet>
