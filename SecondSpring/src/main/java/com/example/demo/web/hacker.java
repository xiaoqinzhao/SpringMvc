package com.example.demo.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class hacker {
    @RequestMapping(value = "/hacker")
    public String index(){
//        return "<!DOCTYPE html> \n" +
//                "<html lang=\"en\">\n" +
//                " <head> \n" +
//                " <meta charset=\"UTF-8\">\n" +
//                "  <title>hacker</title> \n" +
//                "  <style> *{margin: 0;padding: 0;} canvas{display: block;} </style>\n" +
//                "   </head>\n" +
//                "    <body>\n" +
//                "     <canvas id=\"c\"></canvas> \n" +
//                "\t <script> document.addEventListener(\"DOMContentLoaded\",function(){\n" +
//                "\t\t  var oC=document.getElementById(\"c\");\n" +
//                "\t\t   var gt=oC.getContext(\"2d\"); \n" +
//                "\t\t   oC.width=window.innerWidth; oC.height=window.innerHeight; var font=16; var txt=\"0123456789амнлсыиймлぅしさぉかそそぬさ\"; txt=txt.split(\"\");\n" +
//                "\t\t   var col=oC.width/font; var arr=[]; \n" +
//                "\t\t   for(var i=0;i<col;i++){ arr[i]=1; } \n" +
//                "\t\t   function show(){ gt.fillStyle=\"rgba(0,0,0,0.05)\";\n" +
//                "\t\t    gt.fillRect(0,0,oC.width,oC.height); \n" +
//                "\t\t\tgt.fillStyle=\"green\"; gt.font=font+\"px arial\"; \n" +
//                "\t\t\tfor(var i=0;i<arr.length;i++){ \n" +
//                "\t\t\t var oT=txt[Math.floor(Math.random()*txt.length)];\n" +
//                "\t\t\t gt.fillText(oT, i*font, arr[i]*font);\n" +
//                "\t\t\t if(arr[i]*font>oC.height || Math.random() > 0.95)\n" +
//                "\t\t\t  arr[i] = 0;\n" +
//                "\t\t\t  arr[i]++; } }\n" +
//                "\t\t\t   setInterval(show,30); },false) </script> \n" +
//                "               </body>\n" +
//                "                </html>\n" +
//                "\n";
        return "hacker";
    }
}

