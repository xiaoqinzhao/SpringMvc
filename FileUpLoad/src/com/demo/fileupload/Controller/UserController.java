package com.demo.fileupload.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import sun.awt.SunHints;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.Enumeration;

@Controller
public class UserController {
    @RequestMapping(value = "/index",method = RequestMethod.GET)
    public String index(){
        return "index.jsp";
    }

    @RequestMapping(value = "/fileupload",method = RequestMethod.POST)
    @ResponseBody
    public String fileupload(@RequestParam(required=false)  MultipartFile upload,HttpServletRequest request) throws Exception{
        System.out.println("spring文件上传");
        String path=request.getSession().getServletContext().getRealPath("/uploads/");
        File file=new File(path);
        if(!file.exists()){
            file.mkdirs();
        }

        String filename=upload.getOriginalFilename();
        upload.transferTo(new File(path,filename));

        return "success";
    }
}
