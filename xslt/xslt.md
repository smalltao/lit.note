# xslt 语法
> 按指定分割分分割字符传

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
