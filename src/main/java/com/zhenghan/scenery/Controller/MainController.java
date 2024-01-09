package com.zhenghan.scenery.Controller;

import com.alibaba.fastjson2.JSON;
import com.zhenghan.scenery.Labels;
import com.zhenghan.scenery.Main;
import com.zhenghan.scenery.Pictrues;
import com.zhenghan.scenery.Pojo.SceneryPojo;
import com.zhenghan.scenery.Pojo.UserPojo;
import com.zhenghan.scenery.Service.*;
import com.zhenghan.scenery.issupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.net.http.HttpRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@RestController
@CrossOrigin
public class MainController {
    @Autowired
    SceneryServiceImpl sceneryService;
    @Autowired
    UserServiceImpl userService;
    @Autowired
    PictruesServiceImpl pictruesService;
    @Autowired
    ScenerySupportServiceImpl scenerySupportService;
    @Autowired
    SceneryLabelServiceImpl sceneryLabelService;
    @Autowired
    RecallServiceImpl recallService;
    @RequestMapping(value = "/main",method = RequestMethod.GET)
    public String maininformation(HttpServletRequest req,
                                  @RequestParam("userid") String userid,
                                  @DateTimeFormat(pattern = "yyyy-MM-dd’T’HH:mm:ss.SSS’Z")String time){
        Long max=sceneryService.maxid();
        List<Object> list = new ArrayList<>();
        List<Object> resultList=new ArrayList<>();
        for(int i=1;i<=5;i++) {
            Random rand = new Random();
            int id = rand.nextInt(Math.toIntExact(max)) + 1;
            String sceneryid=Integer.toString(id);
            SceneryPojo list1 = sceneryService.findSceneryById(sceneryid);
            String uploader = list1.getUserid();
            UserPojo list2 = userService.findUserById(uploader);
            Pictrues list3 = new Pictrues(pictruesService.findPictruesById(sceneryid));
            Labels list4 = new Labels(sceneryLabelService.findlabel(sceneryid));
            issupport is = scenerySupportService.issupport(sceneryid, userid);
            list.clear();
            list.add(list1);
            list.add(list2);
            list.add(list3);
            list.add(list4);
            list.add(is);
            Main result=new Main(list);
            resultList.add(result);
        }
        return JSON.toJSONString(resultList);
    }
}
