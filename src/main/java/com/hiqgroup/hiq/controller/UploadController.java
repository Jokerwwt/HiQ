package com.hiqgroup.hiq.controller;

import com.alibaba.fastjson.JSONObject;
import com.hiqgroup.hiq.entity.HqtOrderattach;
import com.hiqgroup.hiq.service.HqtOrderattachService;
import com.hiqgroup.hiq.utils.*;
import io.jsonwebtoken.Claims;
import io.swagger.annotations.Api;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.FileCopyUtils;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Api(tags = "文件上传相关接口")
@RestController
@RequestMapping("upload")
public class UploadController {
    @Resource
    private JwtConfig jwtConfig;
    @Autowired
    FilepathUtil filepathUtil;
    @Resource
    private HqtOrderattachService hqtOrderattachService;

    /**
     * @param request
     * @param response
     * @param filePath
     */
    @RequestMapping("getFile")
    public void getFile(HttpServletRequest request, HttpServletResponse response, String filePath) {
        try {
            File file = new File(filepathUtil.getZyUploadPath(), filePath);
            String fileName = file.getName();
            String fileType = fileName.substring(fileName.lastIndexOf(".") + 1);
            // 设置contenttype，即告诉客户端所发送的数据属于什么类型
            response.setHeader("Content-type",fileType);
            // 设置编码
            String hehe = new String(fileName.getBytes("utf-8"), "iso-8859-1");
            // 设置扩展头，当Content-Type 的类型为要下载的类型时 , 这个信息头会告诉浏览器这个文件的名字和类型。
            response.setHeader("Content-Disposition", "attachment;filename=" + hehe);
            FileUtil.download(file.getPath(), response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * @param request
     * @param response
     * @param filePath
     */
    @RequestMapping("getTemplateFile")
    public void getTemplateFile(HttpServletRequest request, HttpServletResponse response, String filePath) {
        try {
            File file = new File(filepathUtil.getTemplatePath(), filePath);
            String fileName = file.getName();
            String fileType = fileName.substring(fileName.lastIndexOf(".") + 1);
            // 设置contenttype，即告诉客户端所发送的数据属于什么类型
            response.setHeader("Content-type",fileType);
            // 设置编码
            String hehe = new String(fileName.getBytes("utf-8"), "iso-8859-1");
            // 设置扩展头，当Content-Type 的类型为要下载的类型时 , 这个信息头会告诉浏览器这个文件的名字和类型。
            response.setHeader("Content-Disposition", "attachment;filename=" + hehe);
            FileUtil.download(file.getPath(), response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * @param request
     * @param response
     * @param fileId
     */
    @RequestMapping("deleteFile")
    public Result deleteFile(HttpServletRequest request, HttpServletResponse response, String fileId) {
        try {
            HqtOrderattach hqtOrderattach = this.hqtOrderattachService.queryById(fileId);
            String filePath = hqtOrderattach.getFilepath().split("=")[1];
            File file = new File(filepathUtil.getZyUploadPath(), filePath);
            if (file.isFile() && file.exists()) {
                file.delete();
            }
            this.hqtOrderattachService.deleteById(fileId);
            return ResultUtil.success();
        } catch (Exception e) {
            return ResultUtil.error(ResultEnum.SYSTEM_ERROR.getCode(),"删除文件出错！"+e.getMessage());
        }
    }


    private String getError(String message) {
        JSONObject obj = new JSONObject();
        obj.put("error", 1);
        obj.put("message", message);
        return obj.toJSONString();
    }

    /**
     * 上传文件
     *
     * @return 单条数据
     */
    @RequestMapping("uploadFiles")
    public void uploadFiles(HttpServletRequest request, HttpServletResponse response, @RequestParam("file") MultipartFile file) throws IOException {
//        int serverPort = request.getServerPort();//8080
//        String contextPath = request.getContextPath();//项目名
        //String url = scheme + "://" + serverName + ":" + serverPort + contextPath;//http://127.0.0.1:8080/test
        String requstUrl = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort();//http://127.0.0.1:8080
        String referer = request.getHeader("referer");
        Pattern p = Pattern.compile("([a-z]*:(//[^/?#]+/[^/?#]*/)?)", Pattern.CASE_INSENSITIVE);
        Matcher mathcer = p.matcher(referer);
        if (mathcer.find()) {
            String htmlheader = mathcer.group();// 请求来源
            response.setContentType("application/json; charset=UTF-8");
            // 处理返回token-----------------------------------------------------
            String headToken = request.getParameter(jwtConfig.getHeader());
            if (headToken != null && !headToken.trim().equals("")) {
                Claims claims = jwtConfig.getTokenClaim(headToken);
                Map map = new HashMap<>();
                map.put("id", claims.getId());
                map.put("userid", claims.getAudience());
                map.put("username", claims.getSubject());
                map.put("usercompany", (claims.get("usercompany") == null) ? "" : claims.get("usercompany").toString());
                map.put("usercompanytype", (claims.get("usercompanytype") == null)? "": claims.get("usercompanytype").toString());
                map.put("userdept", (claims.get("userdept") == null) ? "" : claims.get("userdept").toString());
                String token = jwtConfig.createToken(map);
                if (StringUtils.isNotBlank(token)) {
                    /*刷新token*/
                    response.setCharacterEncoding("UTF-8");
                    response.setHeader("Access-Control-Expose-Headers", "token,userid,username,usercompany,usercompanytype,userdept");
                    response.addHeader("token", token);
                    response.addHeader("userid", map.get("id").toString());
                    response.addHeader("username", URLEncoder.encode(map.get("username").toString(), "utf8"));
                    response.addHeader("usercompany", map.get("usercompany").toString());
                    response.addHeader("usercompanytype", map.get("usercompanytype").toString());
                    response.addHeader("userdept", map.get("userdept").toString());
                }
            }
            //--------------------------------------------------------------------------------
            PrintWriter out = response.getWriter();
            //定义允许上传的文件扩展名
            HashMap<String, String> extMap = new HashMap<String, String>();
            extMap.put("image", "gif,jpg,jpeg,png,bmp");
            extMap.put("flash", "swf,flv");
            extMap.put("media", "swf,flv,mp3,wav,wma,wmv,mid,avi,mpg,asf,rm,rmvb,mp4");
            extMap.put("file", "doc,docx,xls,xlsx,ppt,htm,html,txt,zip,rar,gz,bz2,pdf");
            //最大文件大小
            long maxSize = 15728640;
            response.setContentType("text/html; charset=UTF-8");
            if (!ServletFileUpload.isMultipartContent(request)) {
                out.println(getError("请选择文件。"));
                return;
            }
            File uploadDir = new File(filepathUtil.getZyUploadPath());
            if (!uploadDir.exists()) uploadDir.mkdirs();
            //检查目录写权限
            if (!uploadDir.canWrite()) {
                out.println(getError("上传目录没有写权限。"));
                return;
            }
            String fileFullname = file.getOriginalFilename();
            fileFullname = fileFullname.replace('&', 'a');
            fileFullname = fileFullname.replace(',', 'b');
            fileFullname = fileFullname.replace('，', 'c');
            //检查扩展名
            String fileExt = fileFullname.substring(fileFullname.lastIndexOf(".") + 1).toLowerCase();
            String dirName = request.getParameter("dir");
            if (dirName == null) {
                dirName = "file";
                for (Map.Entry<String, String> entry : extMap.entrySet()) {
                    if (entry.getValue().contains(fileExt)) {
                        dirName = entry.getKey();
                        break;
                    }
                }
            }
            if (!extMap.containsKey(dirName)) {
                out.println(getError("目录名不正确。"));
                return;
            }
            if (!Arrays.<String>asList(extMap.get(dirName).split(",")).contains(fileExt)) {
                out.println(getError("上传文件扩展名是不允许的扩展名。\n只允许" + extMap.get(dirName) + "格式。"));
                return;
            }
            //文件保存目录路径
            String savePath = uploadDir.getPath() + "/" + dirName + "/";
            File saveDirFile = new File(savePath);
            if (!saveDirFile.exists()) {
                saveDirFile.mkdirs();
            }
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
            String ymd = sdf.format(new Date());
            savePath += ymd + "/";
            File dirFile = new File(savePath);
            if (!dirFile.exists()) {
                dirFile.mkdirs();
            }
            //此处是直接采用Spring的上传
            //检查文件大小
            if (file.getSize() > maxSize) {
                out.println(getError("上传文件大小超过限制。"));
                return;
            }

            String newFileName = UUID.randomUUID() + "." + fileExt;
            File uploadFile = new File(savePath + newFileName);
            try {
                FileCopyUtils.copy(file.getBytes(), uploadFile);
                //保存至数据库----------------------------
                String urlString = "/upload/getFile?filePath=" + dirName + "/" + ymd + "/" + newFileName;
                String orderid = request.getParameter("orderid");
                String filetype = request.getParameter("filetype");
                HqtOrderattach hqtOrderattach = new HqtOrderattach();
                hqtOrderattach.setId(UUID.randomUUID().toString());
                hqtOrderattach.setOrderid(orderid);
                hqtOrderattach.setFilename(fileFullname);
                hqtOrderattach.setFilepath(urlString);
                hqtOrderattach.setFiletype(filetype);
                this.hqtOrderattachService.insert(hqtOrderattach);
                //--------------------------------------
                urlString = requstUrl + urlString;
                JSONObject obj = new JSONObject();
                obj.put("error", 0);
                obj.put("url", urlString);
                obj.put("id", hqtOrderattach.getId());
                obj.put("filename", fileFullname);
                out.println(obj.toJSONString());
            } catch (IOException e) {
                e.printStackTrace();
                out.println(getError("上传文件失败。"));
                return;
            }
        }
    }

    @PostMapping("uploadFile")
    public String uploadFile(HttpServletRequest request, HttpServletResponse response, @RequestParam("file") MultipartFile file) {
        // 上传根路径
        File path = null;
        try {
            path = new File(ResourceUtils.getURL("classpath:").getPath());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        File uploadDir = new File(path.getAbsolutePath(), "attached/");
        if (!uploadDir.exists()) uploadDir.mkdirs();
        //检查目录写权限
        if (!uploadDir.canWrite()) {
            return "上传目录没有写权限。";
        }
        HashMap<String, String> extMap = new HashMap<String, String>();
        extMap.put("image", "gif,jpg,jpeg,png,bmp");
        extMap.put("flash", "swf,flv");
        extMap.put("media", "swf,flv,mp3,wav,wma,wmv,mid,avi,mpg,asf,rm,rmvb,mp4");
        extMap.put("file", "doc,docx,xls,xlsx,ppt,htm,html,txt,zip,rar,gz,bz2,pdf");
        String dirName = "file";
        if (!extMap.containsKey(dirName)) {
            return "目录名不正确。";
        }
        //文件保存目录路径
        String savePath = uploadDir.getPath() + "/" + dirName + "/";
        File saveDirFile = new File(savePath);
        if (!saveDirFile.exists()) {
            saveDirFile.mkdirs();
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        String ymd = sdf.format(new Date());
        //文件名 后缀名
        String filename = file.getOriginalFilename();
        String suffix = filename.substring(filename.lastIndexOf("."));
        savePath += ymd + "/";
        File dirFile = new File(savePath, filename);
        if (!dirFile.exists()) {
            dirFile.mkdirs();
        }
        try {
            file.transferTo(dirFile);
            // 返回路径
            String requstUrl = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort();
            String urlString = requstUrl + "/zyUpload/getFile?filePath=" + ymd + "/" + filename;
            return urlString;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "文件上传失败！";
    }
}
