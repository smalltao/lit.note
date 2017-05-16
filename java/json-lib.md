# json-libʹ������

1. json toBean ʱȥ�����ַ��� �� null �ֶ�

        ʹ����ķ�ı��ʽ
        JsonConfig jsonConfig = new JsonConfig();
        PropertyFilter filter = (object, fieldName, fieldValue) -> {
            if(fieldValue instanceof List){
                List<Object> list = (List<Object>) fieldValue;
                if (list.size()==0) {
                    return true;
                }
            }
            if ("createTime"==fieldName || "createTime".equals(fieldName)) {
                fieldValue = null;
            }
            if ("id"==fieldName || "id".equals(fieldName)) {
                fieldValue = null;
            }
            return null == fieldValue || "".equals(fieldValue);
        };
        jsonConfig.setJsonPropertyFilter(filter);
        JSONArray.fromObject(result, jsonConfig).toString();
        
        //��ͨ��ʽ
        JsonConfig jsonConfig = new JsonConfig();
        PropertyFilter filter = new PropertyFilter() {
                public boolean apply(Object object, String fieldName, Object fieldValue) {
                if(fieldValue instanceof List){
                    List<Object> list = (List<Object>) fieldValue;
                    if (list.size()==0) {
                        return true;
                    }
                }
                return null == fieldValue || "".equals(fieldValue);
                }
        };
        
2. �������ڸ�ʽ��


    public class JsonDateValueProcessor implements JsonValueProcessor {
    
         /**
         * datePattern
         */
        private String datePattern = "yyyy-MM-dd";
    
        /**
         * JsonDateValueProcessor
         */
        public JsonDateValueProcessor() {
            super();
        }
    
        /**
         * @param format
         */
        public JsonDateValueProcessor(String format) {
            this.datePattern = format;
        }
    
        /**
         * @param value
         * @param jsonConfig
         * @return Object
         */
        public Object processArrayValue(Object value, JsonConfig jsonConfig) {
            return process(value);
        }
    
        /**
         * @param key
         * @param value
         * @param jsonConfig
         * @return Object
         */
        public Object processObjectValue(String key, Object value,
                JsonConfig jsonConfig) {
            return process(value);
        }
    
        /**
         * process
         * @param value
         * @return
         */
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
    
        /**
         * @return the datePattern
         */
        public String getDatePattern() {
            return datePattern;
        }
    
        /**
         * @param pDatePattern the datePattern to set
         */
        public void setDatePattern(String pDatePattern) {
            datePattern = pDatePattern;
        }
    }
