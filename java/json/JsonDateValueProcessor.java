/**
 * 
 */
package com.sogou.reventon.util;

import net.sf.json.JsonConfig;
import net.sf.json.processors.JsonValueProcessor;
import net.sf.json.util.PropertyFilter;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * @author litaoos2862
 * @date 2017.06.09
 * @description json 工具类
 */
public class JsonDateValueProcessor implements JsonValueProcessor {

    private String datePattern = "yyyy-MM-dd";

    public JsonDateValueProcessor() {
        super();
    }

    public JsonDateValueProcessor(String format) {
        this.datePattern = format;
    }

    public Object processArrayValue(Object value, JsonConfig jsonConfig) {
        return process(value);
    }

    public Object processObjectValue(String key, Object value,
            JsonConfig jsonConfig) {
        return process(value);
    }

    private Object process(Object value) {
        try {
            if (value instanceof Date) {
                SimpleDateFormat sdf = new SimpleDateFormat(datePattern,
                        Locale.UK);
                return sdf.format((Date) value);
            }
            return value == null ? "" : value.toString();
        } catch (Exception e) {
            return "";
        }

    }

    public String getDatePattern() {
        return datePattern;
    }

    public void setDatePattern(String pDatePattern) {
        datePattern = pDatePattern;
    }

    public static JsonConfig getJsonConfig(String... args) {
        JsonConfig jsonConfig = new JsonConfig();
        PropertyFilter filter = (object, fieldName, fieldValue) -> {
            if(fieldValue instanceof List){
                List<Object> list = (List<Object>) fieldValue;
                if (list.size()==0) {
                    return true;
                }
            }

            for (String arg : args) {
                if (arg == fieldName || arg.equals(fieldName)) {
                    fieldValue = null;
                }
            }

            return null == fieldValue || "".equals(fieldValue);
        };
        jsonConfig.setJsonPropertyFilter(filter);
        return jsonConfig;
    }


}
