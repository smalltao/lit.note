package com.controller;

import org.apache.commons.lang.StringEscapeUtils;

/**
 * Created by litaoos2862 on 2017/7/17.
 */
public class TmpTest {

    public static void main(String[] args) {

        StringBuilder sb = new StringBuilder("");

        sb.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
        sb.append("<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:v2=\"http://api.microsofttranslator.com/V2\">");
        sb.append("<soapenv:Header/>");
        sb.append("<soapenv:Body>");
        sb.append("<v2:Translate>");
        sb.append("<v2:appId>"+ "" +"</v2:appId>");
        sb.append("<v2:text>"+ StringEscapeUtils.escapeXml("") +"</v2:text>");
        sb.append("<v2:from>"+ "" +"</v2:from>");
        sb.append("<v2:to>"+ "" +"</v2:to>");
        sb.append("<v2:needQc>"+ "" +"</v2:needQc>");
        sb.append("<v2:contentType>text/plain</v2:contentType>");
        sb.append("<v2:category>general</v2:category>");
        sb.append("<v2:suv>"+ "" +"</v2:suv>");
        sb.append("<v2:uuid>"+ "" +"</v2:uuid>");
        sb.append("</v2:Translate>");
        sb.append("</soapenv:Body>");
        sb.append("</soapenv:Envelope>");

        System.out.printf(sb.toString());
    }
}
