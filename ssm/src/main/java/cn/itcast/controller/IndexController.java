package cn.itcast.controller;

import cn.itcast.service.PlusService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.net.URL;


@Controller
@RequestMapping(value = "/index",method = RequestMethod.GET)
public class IndexController {

    @RequestMapping(value = "/loginpage",method = RequestMethod.GET)
    public String index(){
        return "login2";
    }

    @RequestMapping(value = "/fileuploadpage",method = RequestMethod.GET)
    public String fileuploadpage(){
        return "fileupload";
    }

    /***
     * 用户登陆
     * <p>注解配置，只允许POST提交到该方法
     * @param username
     * @param password
     * @return
     */
    @RequestMapping(value="login",method= RequestMethod.POST)
    public ModelAndView login(String username, String password){
        //验证传递过来的参数是否正确，否则返回到登陆页面。
        if(this.checkParams(new String[]{username,password})){
            //指定要返回的页面为succ.jsp
            ModelAndView mav = new ModelAndView("fileupload");
            //将参数返回给页面
            mav.addObject("username",username);
            mav.addObject("password", password);
            return mav;
        }
        return new ModelAndView("error");
    }
    @RequestMapping(value = {"/plus"},method = RequestMethod.GET)
    @ResponseBody
    public String plus(){

        URL url= Thread.currentThread().getContextClassLoader().getResource("bean.xml");
        ApplicationContext ac=new FileSystemXmlApplicationContext(url.getPath());
        PlusService plusService=ac.getBean("plusService",PlusService.class);
        return String.valueOf(plusService.plus(1,2,3,4,5,6,7,8,9,10));
    }

    @RequestMapping(value = "/fileupload",method = RequestMethod.POST)
    @ResponseBody
    public String fileupload(@RequestParam(required=false) MultipartFile upload, HttpServletRequest request) throws Exception{
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
    /***
     * 验证参数是否为空
     * @param params
     * @return
     */
    private boolean checkParams(String[] params){
        for(String param:params){
            if(param==""||param==null||param.isEmpty()){
                return false;
            }
        }
        return true;
    }
}
