package com.chatOnline.controller;

import com.chatOnline.common.MailService;
import com.chatOnline.common.Result;
import com.chatOnline.domain.User;
import com.chatOnline.domain.dto.UserSearch;
import com.chatOnline.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Slf4j
@RestController
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private MailService mailService;

    /**
     * 登录
     * @param user
     * @param session
     * @return
     */
    @PostMapping("/loginPost")
    public Result login(@RequestBody Map user, HttpSession session){
        String email = user.get("email").toString();
        String password = user.get("password").toString();
        log.info("email={},password={}",email,password);
        User userOne = userService.getOne(email);
        if(userOne==null)
            return Result.Error(0,"该邮箱还未注册");
        if(!password.equals(userOne.getPassword()))
            return Result.Error(100,"密码错误!");
        //id作为标识符
        session.setAttribute("user",userOne);

        return Result.succeed(userOne);
    }

    /**
     * 注册
     * @param map
     * @return
     */
    @PostMapping("/userPost")
    public Result postUser(@RequestBody Map map){

        String email = map.get("email").toString();
        String password = map.get("password").toString();
        String nickname = map.get("nickname").toString();

        log.info("email={},password={},nickname={}",email,password,nickname);

        User user = userService.getOne(email);
        if(user!=null)
            return Result.Error(100,"邮箱已经注册!");
        if (password.length() <=3)
            return Result.Error(100,"密码长度不能小于3");
        User newuser = new User();
        newuser.setEmail(email);
        newuser.setPassword(password);
        newuser.setNickname(nickname);
        userService.save(newuser);
        return Result.succeed(newuser);
    }

    /**
     *获取我的信息
     */
    @PostMapping("/get_my_info")
    public Result getInfo(@RequestBody Map map){
        String id = (String) map.get("id");
        User user = userService.getById(id);
        if (user == null)
            return Result.Error(0,"");
        return Result.succeed(user);
    }

    /**
     * 更新我的信息
     */
    @PostMapping("/update_my_info")
    public Result updateInfo(@RequestBody Map map){
        String id = (String) map.get("id");
        String nickname = (String) map.get("nickname");
        String sex = (String) map.get("sex");
        String birthday = (String) map.get("birthday");
        String phone = (String) map.get("phone");
        String signature = (String) map.get("signature");

        User user = userService.getById(id);
        if (user == null)
            return Result.Error(0,"");

        user.setNickname(nickname);
        user.setSex(sex);
        user.setBirthday(birthday);
        user.setPhone(phone);
        user.setSignature(signature);

        userService.updateById(user);
        return Result.succeed("更新成功");
    }

    /**
     * 修改头像
     * @param map
     * @return
     */
    @PostMapping("/change_profile")
    public Result changeProfile(@RequestBody Map map){
        String id = (String) map.get("id");
        String url = (String) map.get("url");

        User user = userService.getById(id);
        if (user == null)
            return Result.Error(0,"");

        user.setUrl(url);
        userService.updateById(user);

        return Result.succeed(user);
    }

    /**
     * 修改密码
     * @param map
     * @return
     */
    @PostMapping("/change_password")
    public Result changePw(@RequestBody Map map){
        String id = (String) map.get("id");
        String old_pw = (String) map.get("old_pw");
        String new_pw = (String) map.get("new_pw");

        User user = userService.getById(id);
        if (user == null)
            return Result.Error(0,"");

        if(!user.getPassword().equals(old_pw))
            return Result.Error(100,"旧密码错误！");

        if(new_pw.length()<3)
            return Result.Error(100,"新密码位数不能小于3位！");

        user.setPassword(new_pw);
        userService.updateById(user);

        return Result.succeed("修改成功!");
    }

    /**
     * 查询当前在线人数 前端给了在线人数的id???
     * 通过id查询单个用户
     * @param map
     * @return
     */
    @PostMapping("/get_online")
    public Result getOnline(@RequestBody Map map){
        String ids = (String) map.get("ids");
        String[] iDs = ids.split(",");

        List<UserSearch> users = new ArrayList<>();
        for(String id:iDs){
            UserSearch user = userService.getOnline(id);
            users.add(user);
        }
        return Result.succeed(users);
    }

    /**
     * 忘记密码
     * @param map
     * @return
     */
    @PostMapping("/get_password")
    public Result get_password(@RequestBody Map map){
        String email = (String) map.get("email");
        Map<String, Object> response = new HashMap<>();
        User user = userService.getOne(email);
        if(user==null)
            return Result.Error(100,"该邮箱未注册！");

        try {
            mailService.sendMail(email,"邮箱找回密码","您的密码是："+user.getPassword());
        }catch (Exception e){
            e.printStackTrace();
            return Result.Error(100,"发送邮件失败！可能是因为该邮箱不是一个合法邮箱！");
        }
        response.put("status", 200);
        return Result.succeed("找回成功!");
    }


}
