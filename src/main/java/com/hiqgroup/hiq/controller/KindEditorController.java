package com.hiqgroup.hiq.controller;

import com.alibaba.fastjson.JSONObject;
import com.hiqgroup.hiq.utils.*;
import io.jsonwebtoken.Claims;
import io.swagger.annotations.Api;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Api(tags = "富文本相关接口")
@RestController
@RequestMapping("kindEditor")
public class KindEditorController {
    @Resource
    private JwtConfig jwtConfig;
    @Autowired
    FilepathUtil filepathUtil;
    /**
     *
     * @param request
     * @param response
     * @param docPath
     */
    @RequestMapping("getPicture")
    public void getPicture(HttpServletRequest request, HttpServletResponse response, String docPath) {
        FileInputStream in = null;
        ServletOutputStream outputStream = null;
        try {
            File file = new File(filepathUtil.getKindEditorPath(), docPath);
            String fileName = file.getName();
            String fileType = fileName.substring(fileName.lastIndexOf(".") + 1);
            in = new FileInputStream(file);
            outputStream = response.getOutputStream();
            IOUtils.copyLarge(in, outputStream);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            IOUtils.closeQuietly(in);
            IOUtils.closeQuietly(outputStream);
        }
    }

    private String getError(String message) {
        JSONObject obj = new JSONObject();
        obj.put("error", 1);
        obj.put("message", message);
        return obj.toJSONString();
    }

//    /**
//     * 通过主键查询单条数据
//     *
//     * @param dir 存放目录
//     * @return 单条数据
//     */
//    @RequestMapping(value="uploadFiles", method = RequestMethod.POST)
//    public String uploadFiles(@RequestParam String callBackPath, HttpServletRequest request, HttpServletResponse response, String dir) throws Exception {
////        int serverPort = request.getServerPort();//8080
////        String contextPath = request.getContextPath();//项目名
//        //String url = scheme + "://" + serverName + ":" + serverPort + contextPath;//http://127.0.0.1:8080/test
//        String requstUrl = request.getScheme() + "://" + request.getServerName()+":"+request.getServerPort();//http://127.0.0.1:8080
//        String referer = request.getHeader("referer");
//        Pattern p = Pattern.compile("([a-z]*:(//[^/?#]+/[^/?#]*/)?)", Pattern.CASE_INSENSITIVE);
//        Matcher mathcer = p.matcher(referer);
//        String url = "";
//        if (mathcer.find()) {
//            MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
//            Map<String, MultipartFile> fileMap = multipartRequest.getFileMap();
//            //定义允许上传的文件扩展名
//            HashMap<String, String> extMap = new HashMap<String, String>();
//            extMap.put("image", "gif,jpg,jpeg,png,bmp");
//            extMap.put("flash", "swf,flv");
//            extMap.put("media", "swf,flv,mp3,wav,wma,wmv,mid,avi,mpg,asf,rm,rmvb,mp4");
//            extMap.put("file", "doc,docx,xls,xlsx,ppt,htm,html,txt,zip,rar,gz,bz2,pdf");
//            //最大文件大小
//            long maxSize = 1000000;
////            response.setContentType("text/html; charset=UTF-8");
//            if (!ServletFileUpload.isMultipartContent(request)) {
//                url = "redirect:" + callBackPath + "?error=1&message="+"请选择文件!";
//            }
//            File path = new File(ResourceUtils.getURL("classpath:").getPath());
//            File uploadDir = new File(path.getAbsolutePath(), "attached/");
//            if (!uploadDir.exists()) uploadDir.mkdirs();
//            //检查目录写权限
//            if (!uploadDir.canWrite()) {
//                url = "redirect:" + callBackPath + "?error=1&message="+"上传目录没有写权限!";
//            }
//            String dirName = request.getParameter("dir");
//            if (dirName == null) {
//                dirName = "image";
//            }
//            if (!extMap.containsKey(dirName)) {
//                url = "redirect:" + callBackPath + "?error=1&message="+"目录名不正确!";
//            }
//            //文件保存目录路径
//            String savePath = uploadDir.getPath() + "/" + dirName + "/";
//            File saveDirFile = new File(savePath);
//            if (!saveDirFile.exists()) {
//                saveDirFile.mkdirs();
//            }
//            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
//            String ymd = sdf.format(new Date());
//            savePath += ymd + "/";
//            File dirFile = new File(savePath);
//            if (!dirFile.exists()) {
//                dirFile.mkdirs();
//            }
//            FileItemFactory factory = new DiskFileItemFactory();
//            ServletFileUpload upload = new ServletFileUpload(factory);
//            //此处是直接采用Spring的上传
//            for (Map.Entry<String, MultipartFile> entity : fileMap.entrySet()) {
//                MultipartFile mf = entity.getValue();
//                String fileFullname = mf.getOriginalFilename();
//                fileFullname = fileFullname.replace('&', 'a');
//                fileFullname = fileFullname.replace(',', 'b');
//                fileFullname = fileFullname.replace('，', 'c');
//                //检查文件大小
//                if (mf.getSize() > maxSize) {
//                    url = "redirect:" + callBackPath + "?error=1&message="+"上传文件大小超过限制!";
//                }
//                //检查扩展名
//                String fileExt = fileFullname.substring(fileFullname.lastIndexOf(".") + 1).toLowerCase();
//                if (!Arrays.<String>asList(extMap.get(dirName).split(",")).contains(fileExt)) {
//                    url = "redirect:" + callBackPath + "?error=1&message="+"上传文件扩展名是不允许的扩展名。\n只允许" + extMap.get(dirName) + "格式。";
//                }
//                String newFileName = UUID.randomUUID() + "." + fileExt;
//                File uploadFile = null;
//                if (extMap.get("file").contains(fileExt)) {
//                    uploadFile = new File(savePath + fileFullname);
//                } else {
//                    uploadFile = new File(savePath + newFileName);
//                }
//                try {
//                    FileCopyUtils.copy(mf.getBytes(), uploadFile);
//                    String urlString = requstUrl + "/kindEditor/getPicture?docPath=" + ymd + "/" + newFileName;
//                    url = "redirect:" + callBackPath + "?error=0&url="+urlString;
//                } catch (IOException e) {
//                    e.printStackTrace();
//                    url = "redirect:" + callBackPath + "?error=1&message="+"上传文件失败!";
//                }
//            }
//        }
//        return url;
//    }

    /**
     * 通过主键查询单条数据
     *
     * @param dir 存放目录
     * @return 单条数据
     */
    @RequestMapping("uploadFiles")
    public void uploadFiles(HttpServletRequest request, HttpServletResponse response, String dir) throws Exception {
//        int serverPort = request.getServerPort();//8080
//        String contextPath = request.getContextPath();//项目名
        //String url = scheme + "://" + serverName + ":" + serverPort + contextPath;//http://127.0.0.1:8080/test
        String requstUrl = request.getScheme() + "://" + request.getServerName()+":"+request.getServerPort();//http://127.0.0.1:8080
        String referer = request.getHeader("referer");
        Pattern p = Pattern.compile("([a-z]*:(//[^/?#]+/[^/?#]*/)?)", Pattern.CASE_INSENSITIVE);
        Matcher mathcer = p.matcher(referer);
        if (mathcer.find()) {
            String htmlheader = mathcer.group();// 请求来源
            response.setContentType("application/json; charset=UTF-8");
            // 处理返回token-----------------------------------------------------
            String headToken = request.getParameter(jwtConfig.getHeader());
            if(headToken != null && !headToken.trim().equals("")){
                Claims claims = jwtConfig.getTokenClaim(headToken);
                Map map = new HashMap<>();
                map.put("id", claims.getId());
                map.put("userid", claims.getAudience());
                map.put("username", claims.getSubject());
                map.put("usercompany", (claims.get("usercompany") == null)? "": claims.get("usercompany").toString());
                map.put("usercompanytype", (claims.get("usercompanytype") == null)? "": claims.get("usercompanytype").toString());
                map.put("userdept", (claims.get("userdept") == null)? "": claims.get("userdept").toString());
                String token = jwtConfig.createToken(map);
                if (StringUtils.isNotBlank(token)) {
                    /*刷新token*/
                    response.setCharacterEncoding("UTF-8");
                    response.setHeader( "Access-Control-Expose-Headers" , "token,userid,username,usercompany,usercompanytype,userdept" );
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
            MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
            Map<String, MultipartFile> fileMap = multipartRequest.getFileMap();
            //定义允许上传的文件扩展名
            HashMap<String, String> extMap = new HashMap<String, String>();
            extMap.put("image", "gif,jpg,jpeg,png,bmp");
            extMap.put("flash", "swf,flv");
            extMap.put("media", "swf,flv,mp3,wav,wma,wmv,mid,avi,mpg,asf,rm,rmvb,mp4");
            extMap.put("file", "doc,docx,xls,xlsx,ppt,htm,html,txt,zip,rar,gz,bz2,pdf");
            //最大文件大小
            long maxSize = 1000000;
            response.setContentType("text/html; charset=UTF-8");
            if (!ServletFileUpload.isMultipartContent(request)) {
                out.println(getError("请选择文件。"));
                return;
            }
            File uploadDir = new File(filepathUtil.getKindEditorPath());
            if (!uploadDir.exists()) uploadDir.mkdirs();
            //检查目录写权限
            if (!uploadDir.canWrite()) {
                out.println(getError("上传目录没有写权限。"));
                return;
            }
            String dirName = request.getParameter("dir");
            if (dirName == null) {
                dirName = "image";
            }
            if (!extMap.containsKey(dirName)) {
                out.println(getError("目录名不正确。"));
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
            FileItemFactory factory = new DiskFileItemFactory();
            //此处是直接采用Spring的上传
            for (Map.Entry<String, MultipartFile> entity : fileMap.entrySet()) {
                MultipartFile mf = entity.getValue();
                String fileFullname = mf.getOriginalFilename();
                fileFullname = fileFullname.replace('&', 'a');
                fileFullname = fileFullname.replace(',', 'b');
                fileFullname = fileFullname.replace('，', 'c');
                //检查文件大小
                if (mf.getSize() > maxSize) {
                    out.println(getError("上传文件大小超过限制。"));
                    return;
                }
                //检查扩展名
                String fileExt = fileFullname.substring(fileFullname.lastIndexOf(".") + 1).toLowerCase();
                if (!Arrays.<String>asList(extMap.get(dirName).split(",")).contains(fileExt)) {
                    out.println(getError("上传文件扩展名是不允许的扩展名。\n只允许" + extMap.get(dirName) + "格式。"));
                    return;
                }
                String newFileName = UUID.randomUUID() + "." + fileExt;
                File uploadFile = null;
                if (extMap.get("file").contains(fileExt)) {
                    uploadFile = new File(savePath + fileFullname);
                } else {
                    uploadFile = new File(savePath + newFileName);
                }
                try {
                    FileCopyUtils.copy(mf.getBytes(), uploadFile);
                    String urlString = requstUrl + "/kindEditor/getPicture?docPath=" + dirName + "/" + ymd + "/" + newFileName;
                    JSONObject obj = new JSONObject();
                    obj.put("error", 0);
                    obj.put("url", urlString);
                    out.println(obj.toJSONString());
                } catch (IOException e) {
                    e.printStackTrace();
                    out.println(getError("上传文件失败。"));
                    return;
                }
            }
        }
    }

    @RequestMapping("/fileManager")
    public void fileManager(HttpServletRequest request, HttpServletResponse response) throws Exception {
        response.setContentType("application/json; charset=UTF-8");
        PrintWriter out = response.getWriter();
        // 根目录路径，可以指定绝对路径，比如 /var/www/attached/

        String rootPath = filepathUtil.getKindEditorPath();
        // 根目录URL，可以指定绝对路径，比如 http://www.yoursite.com/attached/
        String requstUrl = request.getScheme() + "://" + request.getServerName()+":"+request.getServerPort();
        String rootUrl = requstUrl + "/kindEditor/getPicture?docPath=";
        // 图片扩展名
        String[] fileTypes = new String[] { "gif", "jpg", "jpeg", "png", "bmp" };
        String dirName = request.getParameter("dir");
        if (dirName != null) {
            if (!Arrays.<String>asList(new String[] { "image", "flash", "media", "file" }).contains(dirName)) {
                out.println(getError("无效的文件夹名称。"));
                return;
            }
            rootPath += "/" + dirName + "/";
            rootUrl += dirName + "/";
            File saveDirFile = new File(rootPath);
            if (!saveDirFile.exists()) {
                saveDirFile.mkdirs();
            }
        }
        // 根据path参数，设置各路径和URL
        String path = request.getParameter("path") != null ? request.getParameter("path") : "";
        String currentPath = rootPath + path;
        String currentUrl = rootUrl + path;
        String currentDirPath = path;
        String moveupDirPath = "";
        if (!"".equals(path)) {
            String str = currentDirPath.substring(0, currentDirPath.length() - 1);
            moveupDirPath = str.lastIndexOf("/") >= 0 ? str.substring(0, str.lastIndexOf("/") + 1) : "";
        }

        // 排序形式，name or size or type
        String order = request.getParameter("order") != null ? request.getParameter("order").toLowerCase() : "name";

        // 不允许使用..移动到上一级目录
        if (path.indexOf("..") >= 0) {
            out.println(getError("不允许使用..移动到上一级目录。"));
            return;
        }
        // 最后一个字符不是/
        if (!"".equals(path) && !path.endsWith("/")) {
            out.println(getError("无效参数"));
            return;
        }
        // 目录不存在或不是目录
        File currentPathFile = new File(currentPath);
        if (!currentPathFile.isDirectory()) {
            out.println(getError("文件夹不存在。"));
            return;
        }
        // 遍历目录取的文件信息
        List<Hashtable> fileList = new ArrayList<Hashtable>();
        if (currentPathFile.listFiles() != null) {
            for (File file : currentPathFile.listFiles()) {
                Hashtable<String, Object> hash = new Hashtable<String, Object>();
                String fileName = file.getName();
                if (file.isDirectory()) {
                    hash.put("is_dir", true);
                    hash.put("has_file", (file.listFiles() != null));
                    hash.put("filesize", 0L);
                    hash.put("is_photo", false);
                    hash.put("filetype", "");
                } else if (file.isFile()) {
                    String fileExt = fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();
                    hash.put("is_dir", false);
                    hash.put("has_file", false);
                    hash.put("filesize", file.length());
                    hash.put("is_photo", Arrays.<String>asList(fileTypes).contains(fileExt));
                    hash.put("filetype", fileExt);
                }
                hash.put("filename", fileName);
                hash.put("datetime", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(file.lastModified()));
                fileList.add(hash);
            }
        }

        if ("size".equals(order)) {
            Collections.sort(fileList, new SizeComparator());
        } else if ("type".equals(order)) {
            Collections.sort(fileList, new TypeComparator());
        } else {
            Collections.sort(fileList, new NameComparator());
        }
        JSONObject result = new JSONObject();
        result.put("moveup_dir_path", moveupDirPath);
        result.put("current_dir_path", currentDirPath);
        result.put("current_url", currentUrl);
        result.put("total_count", fileList.size());
        result.put("file_list", fileList);
        out.println(result.toJSONString());
    }
}
