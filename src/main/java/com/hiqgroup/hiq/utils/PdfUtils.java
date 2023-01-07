package com.hiqgroup.hiq.utils;

import com.aspose.words.Document;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Element;
import com.itextpdf.text.Image;
import com.itextpdf.text.pdf.*;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.UUID;

/**
 * 文件转PDF
 * <p>
 * Aspose下载地址：https://repository.aspose.com/repo/com/aspose/
 */
public class PdfUtils {

    /**
     * word 转为 pdf 输出
     *
     * @param inPath  word文件
     * @param outPath pdf 输出文件目录
     */
    public static String word2pdf(String inPath, String outPath) {
        // 验证License
        if (!isWordLicense()) {
            return null;
        }
        FileOutputStream os = null;
        try {
            String path = outPath.substring(0, outPath.lastIndexOf("/"));
            File file = new File(path);
            // 创建文件夹
            if (!file.exists()) {
                file.mkdirs();
            }
            // 新建一个空白pdf文档
            file = new File(outPath);
            os = new FileOutputStream(file);
            // Address是将要被转化的word文档
            Document doc = new Document(inPath);
            // 全面支持DOC, DOCX, OOXML, RTF HTML, OpenDocument, PDF,
            doc.save(os, com.aspose.words.SaveFormat.PDF);
            os.close();
        } catch (Exception e) {
            if (os != null) {
                try {
                    os.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
            e.printStackTrace();
        }
        return outPath;
    }

    /**
     * 验证 Aspose.word 组件是否授权
     * 无授权的文件有水印和试用标记
     */
    public static boolean isWordLicense() {
        boolean result = false;
        try {
            // 避免文件遗漏
            String licensexml = "<License>\n" +
                    "<Data>\n" +
                    "<Products>\n" +
                    "<Product>Aspose.Total for Java</Product>\n" +
                    "<Product>Aspose.Words for Java</Product>\n" +
                    "</Products>\n" +
                    "<EditionType>Enterprise</EditionType>\n" +
                    "<SubscriptionExpiry>20991231</SubscriptionExpiry>\n" +
                    "<LicenseExpiry>20991231</LicenseExpiry>\n" +
                    "<SerialNumber>8bfe198c-7f0c-4ef8-8ff0-acc3237bf0d7</SerialNumber>\n" +
                    "</Data>\n" +
                    "<Signature>sNLLKGMUdF0r8O1kKilWAGdgfs2BvJb/2Xp8p5iuDVfZXmhppo+d0Ran1P9TKdjV4ABwAgKXxJ3jcQTqE/2IRfqwnPf8itN8aFZlV3TJPYeD3yWE7IT55Gz6EijUpC7aKeoohTb4w2fpox58wWoF3SNp6sK6jDfiAUGEHYJ9pjU=</Signature>\n" +
                    "</License>";
            InputStream inputStream = new ByteArrayInputStream(licensexml.getBytes());
            com.aspose.words.License license = new com.aspose.words.License();
            license.setLicense(inputStream);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 给pdf文件添加图片水印
     * @param InPdfFile   要加水印的原pdf文件路径
     * @param outPdfFile  加了水印后要输出的路径
     * @param markImagePath  水印图片路径
     * @throws Exception
     */
    public static void addPdfImgMark(String InPdfFile, String outPdfFile, String markImagePath) throws Exception {
        PdfReader reader = new PdfReader(InPdfFile, "PDF".getBytes());
        PdfStamper stamp = new PdfStamper(reader, new FileOutputStream(new File(outPdfFile)));
        PdfContentByte under;
        PdfGState gs1 = new PdfGState();
        gs1.setFillOpacity(0.07f);// 透明度设置
        Image img = Image.getInstance(markImagePath);// 插入图片水印
        int pageSize = reader.getNumberOfPages();// 原pdf文件的总页数
        for (int i = 1; i <= pageSize; i++) {
            img.setAbsolutePosition(50, 150); // 坐标
            // img.setRotation(-20);// 旋转 弧度
            img.setRotationDegrees(45);// 旋转 角度
            // img.scaleAbsolute(200,100);//自定义大小
            img.scalePercent(70,65);
            under = stamp.getUnderContent(i);// 水印在之前文本下
            // under = stamp.getOverContent(i);//水印在之前文本上
            under.setGState(gs1);// 图片水印 透明度
            under.addImage(img);// 图片水印
            // 页脚
            // ColumnText.showTextAligned(under, Element.ALIGN_LEFT, new Phrase("页脚文字"),
            //       10, 20, 0);
        }
        stamp.close();// 关闭
        reader.close();
    }

    /**
     * Description: 给pdf文件添加文字水印 <br>
     * @param InPdfFile
     *            要加水印的原pdf文件路径
     * @param outPdfFile
     *            加了水印后要输出的路径
     * @param textMark
     *            水印文字
     * @param textWidth
     *            文字横坐标
     * @param textHeight
     *            文字纵坐标
     * @throws Exception
     */
    public static void addPdfTextMark(String InPdfFile, String outPdfFile, String textMark, int textWidth, int textHeight) throws Exception {
        PdfReader reader = new PdfReader(InPdfFile, "PDF".getBytes());
        PdfStamper stamp = new PdfStamper(reader, new FileOutputStream(new File(outPdfFile)));
        try {
            PdfContentByte under = null;
            BaseFont font = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
            // BaseFont font = BaseFont.createFont("C:/WINDOWS/Fonts/SIMSUN.TTC,1", "Identity-H", true);// 使用系统字体
            int pageSize = reader.getNumberOfPages();// 原pdf文件的总页数
            for (int i = 1; i <= pageSize; i++) {
                under = stamp.getUnderContent(i);// 水印在之前文本下
                // under = stamp.getOverContent(i);//水印在之前文本上
                under.beginText();
                under.setColorFill(BaseColor.GRAY);// 文字水印 颜色
                under.setFontAndSize(font, 38);// 文字水印 字体及字号
                under.setTextMatrix(textWidth, textHeight);// 文字水印 起始位置
                under.showTextAligned(Element.ALIGN_CENTER, textMark, textWidth, textHeight, 45);
                under.endText();
            }
            under.closePath();
        }finally {
            stamp.close();
            reader.close();
            File file = new File(InPdfFile);
            if (file.exists()) {
                System.gc();//启动jvm垃圾回收
                file.delete();
            }
        }
    }

    /**
     * OutputStream 转 InputStream
     */
    public static ByteArrayInputStream parse(OutputStream out) {
        ByteArrayOutputStream baos = (ByteArrayOutputStream) out;
        ByteArrayInputStream swapStream = new ByteArrayInputStream(baos.toByteArray());
        return swapStream;
    }

    /**
     * InputStream 转 File
     */
    public static File inputStreamToFile(InputStream ins, String name) throws Exception {
        File file = new File(System.getProperty("java.io.tmpdir") + File.separator + name);
        if (file.exists()) {
            return file;
        }
        OutputStream os = new FileOutputStream(file);
        int bytesRead;
        int len = 8192;
        byte[] buffer = new byte[len];
        while ((bytesRead = ins.read(buffer, 0, len)) != -1) {
            os.write(buffer, 0, bytesRead);
        }
        os.close();
        ins.close();
        return file;
    }

    /**
     * 根据网络地址获取 File 对象
     */
    public static File getFile(String url) throws Exception {
        String suffix = url.substring(url.lastIndexOf("."));
        HttpURLConnection httpUrl = (HttpURLConnection) new URL(url).openConnection();
        httpUrl.connect();
        return PdfUtils.inputStreamToFile(httpUrl.getInputStream(), UUID.randomUUID().toString() + suffix);
    }

}
