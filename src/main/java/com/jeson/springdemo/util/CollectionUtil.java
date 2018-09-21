package com.jeson.springdemo.util;

import java.util.Map;

public class CollectionUtil {

    private CollectionUtil() {
    }

    public static String toString(Map<String, String[]> parameterMap) {
        if (parameterMap == null) {
            return "null";
        }
        StringBuilder sb = new StringBuilder("{");
        boolean isFirstKey = true;
        for (Map.Entry<String, String[]> entry : parameterMap.entrySet()) {
            if (isFirstKey) {
                isFirstKey = false;
            } else {
                sb.append(",");
            }
            String key = entry.getKey();
            String[] values = entry.getValue();
            sb.append("\"");
            sb.append(key);
            sb.append("\": ");
            if (values == null) {
                sb.append("[]");
                continue;
            }
            sb.append("[");
            boolean isFirstValue = true;
            for (String value : values) {
                if (isFirstValue) {
                    isFirstValue = false;
                } else {
                    sb.append(",");
                }
                sb.append("\"");
                sb.append(value);
                sb.append("\"");
            }
            sb.append("]");
        }
        sb.append("}");
        return sb.toString();
    }

}
