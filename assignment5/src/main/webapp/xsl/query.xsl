<?xml version="1.0" encoding="utf-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:jw="http://jw.nju.edu.cn/schema" >
    <xsl:output method="xml" indent="yes"/>
    <xsl:param name="sid"/>

    <xsl:template match="/">
        <env:Envelope
                xmlns:env="http://www.w3.org/2003/05/soap-envelope"
                xmlns:my="http://www.example.com/">
            <env:Header>
                <t:transaction xmlns:t="http://thirdparty.example.org/transaction" env:encodingStyle="http://example.com/encoding" env:mustUnderstand="true">5</t:transaction>
                <s:Security xmlns:s="http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-secext-1.0.xsd" env:mustUnderstand="true">
                    <s:UsernameToken>
                        <s:Username>UserName</s:Username>
                        <s:Password>Password</s:Password>
                    </s:UsernameToken>
                </s:Security>
            </env:Header>
            <env:body>
                <xsl:choose>
                    <xsl:when test="count(//jw:学号[text()=$sid]) = 0">
                        <env:Fault>
                            <env:Code>
                                <env:Value>env:Receiver</env:Value>
                                <env:Subcode>
                                    <env:Value>my:bad argument</env:Value>
                                </env:Subcode>
                            </env:Code>
                            <env:Reason>
                                <env:Text xml:lang="en">no such sid found in score list:<xsl:value-of select="$sid"/></env:Text>
                                <env:Text xml:lang="zh">成绩系统中无此学号：<xsl:value-of select="$sid"/></env:Text>
                            </env:Reason>
                            <env:Detail>
                                <my:adivce>请确认输入的学号为9位，且合法(仅包含数字)。</my:adivce>
                            </env:Detail>
                        </env:Fault>
                    </xsl:when>
                    <xsl:otherwise>
                        <jw:课程成绩列表>
                            <xsl:apply-templates select="//jw:学号[text()=$sid]"/>
                        </jw:课程成绩列表>
                    </xsl:otherwise>
                </xsl:choose>
            </env:body>
        </env:Envelope>
    </xsl:template>

    <xsl:template match="//jw:学号[text()=$sid]">
        <jw:课程成绩>
            <xsl:attribute name="成绩性质">
                <xsl:value-of select="../../@成绩性质"/>
            </xsl:attribute>
            <xsl:attribute name="课程编号">
                <xsl:value-of select="../../@课程编号" />
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
