package com.hiqgroup.hiq.utils;

import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.xwpf.usermodel.XWPFDocument;

import javax.servlet.http.HttpServletResponse;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

public class FileUtil {
    public static void download(String filepath, HttpServletResponse res) throws IOException {
        // 发送给客户端的数据
        OutputStream outputStream = res.getOutputStream();
        // 读取filename
//        File path = new File(ResourceUtils.getURL("classpath:").getPath());
        String fileExt = filepath.substring(filepath.lastIndexOf(".") + 1).toLowerCase();
        if (fileExt.equals("docx")){
            XWPFDocument document = null;
            FileInputStream fis = null;
            fis = new FileInputStream(filepath);
            try {
                document = new XWPFDocument(OPCPackage.open(fis));
                document.write(outputStream);
            } catch (InvalidFormatException e) {
                e.printStackTrace();
            }
        } else if (fileExt.equals("doc")) {
            FileInputStream fis = null;
            fis = new FileInputStream(filepath);
            POIFSFileSystem pfs = new POIFSFileSystem(fis);
            try {
                HWPFDocument hwpfDocument = new HWPFDocument(pfs);
                hwpfDocument.write(outputStream);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (fileExt.equals("pdf")) {
            FileInputStream fileInputStream = null;
            BufferedInputStream  bis = null;
            OutputStream os = null;
            try {
                fileInputStream = new FileInputStream(filepath);
                byte[] buff = new byte[1024];
                bis = new BufferedInputStream(fileInputStream);
                os = res.getOutputStream();
                int i = bis.read(buff);
                while (i != -1) {
                    os.write(buff, 0, buff.length);
                    i = bis.read(buff);
                }
                os.flush();
                os.close();
//                return SimpleResult.ok("导出成功",os);
            } catch ( IOException e ) {
                e.printStackTrace();
//                return SimpleResult.fail("导出失败",null);
            } finally {
                if (bis != null) {
                    try {
                        bis.close();
                        fileInputStream.close();
                    } catch (IOException e) {
                        e.printStackTrace();
//                        return SimpleResult.fail("导出失败",null);
                    }
                }
            }
        } else {
            FileInputStream fileInputStream = null;
            BufferedInputStream  bis = null;
            OutputStream os = null;
            try {
                fileInputStream = new FileInputStream(filepath);
                byte[] buff = new byte[1024];
                bis = new BufferedInputStream(fileInputStream);
                os = res.getOutputStream();
                int i = bis.read(buff);
                while (i != -1) {
                    os.write(buff, 0, buff.length);
                    i = bis.read(buff);
                }
                os.flush();
                os.close();
//                return SimpleResult.ok("导出成功",os);
            } catch ( IOException e ) {
                e.printStackTrace();
//                return SimpleResult.fail("导出失败",null);
            } finally {
                if (bis != null) {
                    try {
                        bis.close();
                        fileInputStream.close();
                    } catch (IOException e) {
                        e.printStackTrace();
//                        return SimpleResult.fail("导出失败",null);
                    }
                }
            }
        }
    }
}
