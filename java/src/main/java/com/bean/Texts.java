package com.bean;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;

/**
 * <p>Title:      xml to bean的例子. </p>
 * <p>Description TODO </p>
 * <p>Company:    https://www.sogou.com/ </p>
 *
 * @Author <a href="litaoos2862@sogou-inc.com"/>李涛</a>
 * @CreateDate 2017/6/27 12:28
 */
@XmlRootElement(name = "Texts")
public class Texts {

    private ArrayList<String> text;

    public ArrayList<String> getText() {
        return text;
    }

    @XmlElement(name = "string", namespace = "http://schemas.microsoft.com/2003/10/Serialization/Arrays")
    public void setText(ArrayList<String> text) {
        this.text = text;
    }

}
