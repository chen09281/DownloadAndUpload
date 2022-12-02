package com.chen.controller;

import cn.hutool.core.io.FileUtil;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;

@RestController
@RequestMapping("/file")
@Slf4j
public class DownLoadController {
    @Value("${file.upload.dir}")
    private String uploadFilePath;

    /**
     * 测试方法
     * @return
     */
    @GetMapping(value = {"","/","/index"})
    public String index(){
        return "upload"+uploadFilePath;
    }

    /**
     * 上传文件
     * @param file
     * @return
     * @throws JSONException
     */
    @ResponseBody
    @CrossOrigin(origins = "*")
    @PostMapping("/uploadFile")
    public String fileUpload(@RequestParam("file")MultipartFile file) throws JSONException {
        JSONObject result = new JSONObject();
        if (file.isEmpty()){
            result.put("error","空文件!");
            return result.toString();
        }
        // 文件名
        String fileName = file.getOriginalFilename();
        String suffixName = fileName.substring(fileName.lastIndexOf("."));
        log.info("上传文件名称为:{},后缀名为:{}!",fileName,suffixName);
        File fileTempObj = new File(uploadFilePath + File.separator + fileName);
        // 检测目录是否存在
        if (fileTempObj.exists()){
            result.put("error","文件已存在");
            return result.toString();
        }
        try {
            FileUtil.writeBytes(file.getBytes(),fileTempObj);
        } catch (Exception e){
            log.error("发生错误:{}",e);
            result.put("eorror",e.getMessage());
            return result.toString();
        }
        result.put("success","文件上传成功");
        return result.toString();
    }

    @ResponseBody
    @PostMapping("/uploadFiles")
    public String fileUploads(@RequestParam("files") MultipartFile files[]) throws JSONException{
        JSONObject result = new JSONObject();

        for (int i=0;i<files.length;i++){
            String filename = files[i].getOriginalFilename();
            File dest = new File(uploadFilePath + File.separator + filename);
            if (!dest.getParentFile().exists()){
                dest.getParentFile().mkdirs();
            }
            try {
                files[i].transferTo(dest);
            } catch (Exception e) {
                log.error("发生错误:{}",e);
                result.put("error",e.getMessage());
                return result.toString();
            }
        }
        result.put("success","文件上传成功!");
        return result.toString();
    }

    /**
     * 下载到服务器方法
     * @param response
     * @param fileName
     * @return
     * @throws JSONException
     * @throws IOException
     */
    @ResponseBody
    @GetMapping("/downloadFile")
    public String fileDownload(HttpServletResponse response, @RequestParam("fileName") String fileName) throws JSONException, IOException {
        JSONObject result = new JSONObject();

        File file = new File(uploadFilePath + '/' + fileName);
        if (!file.exists()) {
            result.put("error", "下载文件不存在!");
            return result.toString();
        }

        response.reset();
        response.setContentType("application/octet-stream");
        response.setCharacterEncoding("utf-8");
        response.setContentLength((int) file.length());
        response.setHeader("Content-Disposition", "attachment;filename=" + fileName);

        byte[] readBytes = FileUtil.readBytes(file);
        OutputStream os = response.getOutputStream();
        os.write(readBytes);
        result.put("success", "下载成功!");
        return result.toString();
    }

    /**
     * 删除文件
     * @param resp
     * @param fileName
     * @return
     * @throws JSONException
     */
    @ResponseBody
    @PostMapping("/delete")
    public String deleteFile(HttpServletResponse resp,@RequestParam("fileName") String fileName) throws JSONException{
        JSONObject result = new JSONObject();

        File file = new File(uploadFilePath + File.separator + fileName);
        // 判断文件不为null或文件目录存在
        if (file == null || !file.exists()){
            result.put("success","文件不存在!");
            return result.toString();
        }
        try{
            if (file.isFile()) file.delete();
            else {
                // 文件夹，需要先删除文件夹下面所有的文件，然后删除
                for (File temp:file.listFiles()){
                    temp.delete();
                }
                file.delete();
            }
        } catch (Exception e){
            log.error("发生错误:{}",e);
            result.put("error",e.getMessage());
            return result.toString();
        }
        result.put("success","删除成功");
        return result.toString();
    }

    @ResponseBody
    @GetMapping("/test")
    public String test() throws JSONException{
        JSONObject result = new JSONObject();
        File file = new File(uploadFilePath+File.separator);
        File[] fs = file.listFiles();
        for (File f:fs){
            if (f.isDirectory()){
                log.error("文件夹是空的");
                result.put("error",f.toString());
                return result.toString();
            }
            for (int i = 0; i<fs.length; i++) {
                String removeStr = "d:\\test\\";
                result.put(String.valueOf(i),f.toString().replace(removeStr,""));
            }
        }
        return result.toString();
    }
}
