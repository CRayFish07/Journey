package com.shakool.common;

import com.shakool.common.web.Constants;
import org.apache.commons.io.FilenameUtils;
import org.json.JSONObject;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.UUID;

/**
 * Created by nibnait on 2016/5/7.
 */
public class UploadUtils {


    public static JSONObject uploadFile(MultipartFile uploadfile, String model, HttpServletRequest request) {
        String ext = FilenameUtils.getExtension(uploadfile.getOriginalFilename());//扩展名
//        String datepath = FormatDateUtils.dateToString1(new Date());
        String filename = UUID.randomUUID().toString()+"."+ext;
        String bathpath = "upload/"+ model;//在图片服务器中的相对路径（也是保存在数据库中的 相对路径）
        String bathpath2 = "upload/"+ model;//返回给浏览器 url中的相对路径
        String webRoot = request.getServletContext().getRealPath("/");
        String filepath = webRoot+bathpath;
        String path = bathpath2+"/"+ filename;//在图片服务器中的路径（也是保存在数据库中的 相对路径）
        String url = Constants.WEB_ROOT + bathpath2+"/" +filename;

        File file = new File(filepath);//在服务器中 new一个文件夹
        if(!file.exists()){
            file.mkdirs();
        }

        OutputStream out = null;
        try {
            byte[] bytes = uploadfile.getBytes();
            out = new FileOutputStream(new File(filepath,filename));
            out.write(bytes);
        } catch (IOException e) {
            //返回二个路径
            JSONObject jo1 = new JSONObject();
            jo1.put("encode","1");
            jo1.put("msg","出现异常");
            jo1.put("url", "NULL");
            jo1.put("path","NULL");
            e.printStackTrace();
            return jo1;
        } finally {
            if (out!=null){
                try {
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        //返回二个路径
        JSONObject jo = new JSONObject();
        jo.put("encode","0");
        jo.put("msg","上传成功");
        jo.put("url", url);
        jo.put("path",path);

        return jo;
    }
}
