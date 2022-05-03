<?xml version="1.0" encoding="UTF8" standalone="no"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                xmlns:ns="http://jw.nju.edu.cn/schema"
                exclude-result-prefixes="ns"
                version="1.0">
    <xsl:output method="xml" version="1.0" encoding="UTF-8" indent="yes"/>
    <xsl:param name="sid"/>
    <xsl:param name="cid"/>
    <xsl:param name="type"/>
    <xsl:param name="score"/>

    <xsl:template match="node()|@*">
        <xsl:copy>
            <xsl:apply-templates select="node()|@*"/>
        </xsl:copy>
    </xsl:template>

    <xsl:template match="//ns:课程成绩[@成绩性质=$type and @课程编号=$cid]/ns:成绩/ns:得分[preceding-sibling::ns:学号/text()=$sid]/text()">
        <xsl:value-of select="$score"/>
    </xsl:template>
</xsl:stylesheet>