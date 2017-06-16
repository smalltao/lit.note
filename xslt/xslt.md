# xslt 语法
## 按指定分割分分割字符传

```
<xsl:template match="text()" name="split">
    <xsl:param name="pText" select="."/>
    <xsl:param name="separator" />
    <xsl:if test="string-length($pText)">
        <xsl:if test="not($pText=.)">
            <a href="javascript:void(0);">
                <xsl:attribute name="href">https://wap.sogou.com/web/searchList.jsp?keyword=<xsl:value-of select="URLEncoder.encode(substring-before(concat($pText, $separator), $separator),'UTF-8')"/></xsl:attribute>
                <xsl:value-of select="substring-before(concat($pText, $separator), $separator)"/>
            </a>
        </xsl:if>
        &#160;
        <xsl:call-template name="split">
            <xsl:with-param name="pText" select="substring-after($pText, $separator)"/>
            <xsl:with-param name="separator" select="$separator"/>
        </xsl:call-template>
    </xsl:if>
</xsl:template>
```

## 截断
```
<xsl:call-template name="sub">
        <xsl:with-param name="str" select="util:subStringByByte(java:getMedianame(newsutil:getInstance(),/DOCUMENT/item/display/url),15)"/>
</xsl:call-template>
<xsl:template name="sub">
    <xsl:param name="str"/>
    <xsl:param name="length" select="16"/>
    <xsl:choose>
        <xsl:when test="string-length($str) &lt;= $length"><xsl:value-of select="$str"/></xsl:when>
        <xsl:otherwise><xsl:value-of select="substring($str,1,$length)"/>..</xsl:otherwise>
    </xsl:choose>
</xsl:template>
```
```
<xsl:call-template name="cutString" >
    <xsl:with-param name="str" select="util:filterUnpaired(//display/abstract)" />
</xsl:call-template>
<xsl:template name="cutString">
    <xsl:param name="str"/>
    <xsl:param name="maxlen" select="40"/>
    <xsl:choose>
        <xsl:when test="string-length($str) &lt;= $maxlen">
            <xsl:value-of select="$str" />
        </xsl:when>
        <xsl:otherwise>
            <xsl:value-of select="concat(substring($str,0,$maxlen),'...')" />
        </xsl:otherwise>
    </xsl:choose>
</xsl:template>
```

## 引入xslt工具
```
xmlns:java="http://xml.apache.org/xslt/java"
xmlns:encoder="xalan://java.net.URLEncoder"
xmlns:vrutil="xalan://com.sohu.sogou.wap.web.util.VRUtil"
xmlns:newsutil="xalan://com.sohu.sogou.wap.web.vr.news.NewsUrlInfo"
xmlns:util="xalan://com.sohu.sogou.wap.web.searchhub.QueryResultItem"
```