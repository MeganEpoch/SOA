<?xml version="1.0" encoding="UTF-8" standalone="no"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                xmlns:ns="http://jw.nju.edu.cn/schema"
                exclude-result-prefixes="ns"
                version="1.0">
    <xsl:output method="xml" version="1.0" encoding="UTF-8" indent="yes"/>
    <xsl:variable name="outFile" select="'文档3.xml'" />
    <!-- <xsl:mode on-no-match="shallow-copy"/> -->

    <xsl:template match="/">
        <课程成绩列表>
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


    

</xsl:stylesheet>