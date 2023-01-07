package com.hiqgroup.hiq.utils;

import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.converters.DateConverter;
import org.apache.commons.beanutils.converters.SqlTimestampConverter;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CommonUtil {
    private String header;

    public static void copyObjProperties(Object dest, Object orig) throws InvocationTargetException, IllegalAccessException {
        ConvertUtils.register(new DateConverter(null), java.util.Date.class);
        ConvertUtils.register(new SqlTimestampConverter(null), java.sql.Timestamp.class);
        BeanUtils.copyProperties(dest, orig, true);
    }

    /**
     * 获取对应字段的值
     *
     * @param f   字段
     * @param t   对象
     * @param <T>
     * @return
     */
    public static <T> String getFieldValue(String f, T t) {
        /*Field[] declaredFields = t.getClass().getDeclaredFields();
        for (Field field : declaredFields) {
            String name = field.getName();
            if (name.equals(f)) {
                String getField = "get" + name.substring(0, 1).toUpperCase() + name.substring(1);
                try {
                    return t.getClass().getMethod(getField).invoke(t).toString();
                } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
                    e.printStackTrace();
                    logger.error("根据字段名获取属性值失败" + ExceptionMessage.mess(e));
                }
            }
        }
        return null;*/
        try {
            BeanInfo beanInfo = Introspector.getBeanInfo(t.getClass());
            PropertyDescriptor[] descriptors = beanInfo.getPropertyDescriptors();
            for (PropertyDescriptor descriptor : descriptors) {
                if (descriptor.getName().equals(f)) {
                    return ((descriptor.getReadMethod().invoke(t) == null)? "" : descriptor.getReadMethod().invoke(t).toString());
                }
            }
        } catch (IntrospectionException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static final String regEx_script = "<script[^>]*?>[\\s\\S]*?<\\/script>"; // 定义script的正则表达式
    private static final String regEx_style = "<style[^>]*?>[\\s\\S]*?<\\/style>"; // 定义style的正则表达式
    private static final String regEx_html = "<[^>]+>"; // 定义HTML标签的正则表达式

    /**
     * @param htmlStr
     * @return 删除Html标签
     */
    public static String formatHTMLTag(String htmlStr) {
        Pattern p_script = Pattern.compile(regEx_script, Pattern.CASE_INSENSITIVE);
        Matcher m_script = p_script.matcher(htmlStr);
        htmlStr = m_script.replaceAll(""); // 过滤script标签

        Pattern p_style = Pattern.compile(regEx_style, Pattern.CASE_INSENSITIVE);
        Matcher m_style = p_style.matcher(htmlStr);
        htmlStr = m_style.replaceAll(""); // 过滤style标签

        htmlStr = htmlStr.replaceAll("<br\\/>", "\n");// 换行替换
        htmlStr = htmlStr.replaceAll("</p>", "\n");// 段落替换

        Pattern p_html = Pattern.compile(regEx_html, Pattern.CASE_INSENSITIVE);
        Matcher m_html = p_html.matcher(htmlStr);
        htmlStr = m_html.replaceAll(""); // 过滤html标签

        htmlStr = htmlStr.replaceAll("&nbsp;", "");
        htmlStr = htmlStr.replaceAll("&amp;", "&");

        Pattern p = Pattern.compile("(\r?\n(\\s*\r?\n)+)");//多个换行替换成一个
        Matcher m = p.matcher(htmlStr);
        htmlStr = m.replaceAll("\r\n");
        return htmlStr; // 返回文本字符串
    }

    public static List<String> getImgSrc(String htmlStr) {
        if( (htmlStr == null) || htmlStr.equals("")){
            return null;
        }

        String img = "";
        Pattern p_image;
        Matcher m_image;
        List<String> pics = new ArrayList<String>();

        String regEx_img = "<img.*src\\s*=\\s*(.*?)[^>]*?>";
        p_image = Pattern.compile(regEx_img, Pattern.CASE_INSENSITIVE);
        m_image = p_image.matcher(htmlStr);
        while (m_image.find()) {
            img = img + "," + m_image.group();
            // Matcher m =
            // Pattern.compile("src=\"?(.*?)(\"|>|\\s+)").matcher(img); //匹配src
            Matcher m = Pattern.compile("src\\s*=\\s*\"?(.*?)(\"|>|\\s+)").matcher(img);

            while (m.find()) {
                pics.add(m.group(1));
            }
        }
        return pics;
    }
}
