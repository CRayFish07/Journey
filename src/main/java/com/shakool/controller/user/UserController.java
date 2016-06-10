package com.shakool.controller.user;

import com.shakool.pojo.User;
import com.shakool.service.UserService;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by geekgao on 16-3-29.
 * 用户相关控制器
 */
@Controller
@RequestMapping("/user")
public class UserController {
    @Resource
    private UserService userService;

    @RequestMapping(value = "/login" , method = RequestMethod.POST)
    public @ResponseBody String login(@RequestParam(required = false,name = "phone") String phone,
                                      @RequestParam(required = false,name = "username") String username,
                                      @RequestParam(required = false,name = "passwd") String passwd) {
        if (passwd == null || passwd.equals("")) {
            return "{\"errcode\":\"2\",\"msg\":\"参数错误\"}";
        }

        boolean isTrueUser = false;
        if (phone != null) {
            isTrueUser = userService.verifyWithPhone(phone,passwd);
        } else if (username != null) {
            isTrueUser = userService.verifyWithUserName(username,passwd);
        }

        User user = null;
        if (isTrueUser) {
            if (phone != null) {
                user = userService.getDecalredInfosWithPhone(phone);
            } else if (username != null) {
                user = userService.getDecalredInfosWithUserName(username);
            }

            /**
             * 某些值可能为null，那么就不会被返回到前台，这里处理来一下，如果为null改值为空
             */
            if (user.getUsername() == null) {
                user.setUsername("");
            }
            if (user.getBirthday() == null) {
                user.setBirthday("");
            }
            if (user.getPhone() == null) {
                user.setPhone("");
            }
            if (user.getEmail() == null) {
                user.setEmail("");
            }
            if (user.getImage() == null) {
                user.setImage("");
            }



            JSONObject resultJson = new JSONObject();
            resultJson.put("errcode","0");
            resultJson.put("msg","正确");
            JSONObject userJson = new JSONObject(user);
            resultJson.put("user",userJson);

            return resultJson.toString();
        } else {
            return "{\"errcode\":\"1\",\"msg\":\"用户名或密码错误\"}";
        }
    }

    @RequestMapping(value = "/register" , method = RequestMethod.POST)
    public @ResponseBody String register(HttpSession session,
                                         @RequestParam(required = false,name = "phone") String phone,
                                         @RequestParam(required = false,name = "passwd") String passwd,
                                         @RequestParam(required = false,name = "nickname") String nickname,
                                         @RequestParam(required = false,name = "captcha") String captcha) {
        if (captcha == null || captcha.equals("") || phone == null || phone.equals("") || passwd == null || passwd.equals("") || nickname == null || nickname.equals("")) {
            return "{\"errcode\":\"3\",\"msg\":\"参数错误\"}";
        }
        if (session.getAttribute("captcha") == null || !session.getAttribute("captcha").equals(captcha)) {
            return "{\"errcode\":\"2\",\"msg\":\"验证码错误\"}";
        }

        if (userService.phoneExist(phone)) {
            return "{\"errcode\":\"1\",\"msg\":\"手机号已被注册\"}";
        }

        User newUser = new User();
        newUser.setPhone(phone);
        newUser.setPassword(passwd);
        newUser.setNickname(nickname);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        newUser.setRegisttime(dateFormat.format(new Date()));
        userService.insert(newUser);
        session.invalidate();

        return "{\"errcode\":\"0\",\"msg\":\"正常\"}";
    }

    @RequestMapping(value = "/following", method = RequestMethod.GET)
    public @ResponseBody String following(@RequestParam(required = false,name = "userid") String userId, @RequestParam(required = false,name = "followingid") String followingId) {
        if (userId == null || followingId == null || userId.equals("") || followingId.equals("")) {
            return "{\"errcode\":\"1\",\"msg\":\"参数错误\"}";
        }

        //两个用户存在
        if (userService.userIdExist(Integer.parseInt(userId)) && userService.userIdExist(Integer.parseInt(followingId))) {
            userService.following(Integer.parseInt(userId),Integer.parseInt(followingId));
        } else {
            return "{\"errcode\":\"2\",\"msg\":\"用户id错误\"}";
        }

        return "{\"errcode\":\"0\",\"msg\":\"关注成功\"}";
    }

    @RequestMapping(value = "/deletefollowing", method = RequestMethod.GET)
    public @ResponseBody String deleteFollowing(@RequestParam(required = false,name = "userid") String userId, @RequestParam(required = false,name = "followingid") String followingId) {
        if (userId == null || followingId == null || userId.equals("") || followingId.equals("")) {
            return "{\"errcode\":\"1\",\"msg\":\"参数错误\"}";
        }

        //两个用户存在
        if (userService.userIdExist(Integer.parseInt(userId)) && userService.userIdExist(Integer.parseInt(followingId))) {
            userService.deleteFollowing(Integer.parseInt(userId),Integer.parseInt(followingId));
        } else {
            return "{\"errcode\":\"2\",\"msg\":\"用户id错误\"}";
        }

        return "{\"errcode\":\"0\",\"msg\":\"删除关注成功\"}";
    }

    @RequestMapping(value = "/getfollowings", method = RequestMethod.GET)
    public @ResponseBody String getfollowings(@RequestParam(required = false,name = "userid") String userId) {
        if (userId == null || userId.equals("")) {
            return "{\"errcode\":\"1\",\"msg\":\"参数错误\"}";
        }

        //两个用户存在
        if (userService.userIdExist(Integer.parseInt(userId))) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("errcode","0");
            List<User> users = new LinkedList<User>();
            for (String uid:userService.getFollowings(Integer.parseInt(userId)).split(",")) {
                User user = userService.getDecalredInfosWithUserId(uid);
                if (user == null) {
                    continue;
                }
                /**
                 * 某些值可能为null，那么就不会被返回到前台，这里处理来一下，如果为null改值为空
                 */
                if (user.getUsername() == null) {
                    user.setUsername("");
                }
                if (user.getBirthday() == null) {
                    user.setBirthday("");
                }
                if (user.getPhone() == null) {
                    user.setPhone("");
                }
                if (user.getEmail() == null) {
                    user.setEmail("");
                }
                if (user.getImage() == null) {
                    user.setImage("");
                }
                users.add(user);
            }

            if (users.size() == 0) {
                jsonObject.put("errcode","3");
                jsonObject.put("msg","没有关注别人");
            } else {
                jsonObject.put("followings",users);
            }
            return jsonObject.toString();
        } else {
            return "{\"errcode\":\"2\",\"msg\":\"用户id错误\"}";
        }
    }

    @RequestMapping(value = "/getfollowers", method = RequestMethod.GET)
    public @ResponseBody String getfollowers(@RequestParam(required = false,name = "userid") String userId) {
        if (userId == null || userId.equals("")) {
            return "{\"errcode\":\"1\",\"msg\":\"参数错误\"}";
        }

        //两个用户存在
        if (userService.userIdExist(Integer.parseInt(userId))) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("errcode","0");
            List<User> users = new LinkedList<User>();
            for (String uid:userService.getFollowers(Integer.parseInt(userId)).split(",")) {
                User user = userService.getDecalredInfosWithUserId(uid);
                if (user == null) {
                    continue;
                }
                /**
                 * 某些值可能为null，那么就不会被返回到前台，这里处理来一下，如果为null改值为空
                 */
                if (user.getUsername() == null) {
                    user.setUsername("");
                }
                if (user.getBirthday() == null) {
                    user.setBirthday("");
                }
                if (user.getPhone() == null) {
                    user.setPhone("");
                }
                if (user.getEmail() == null) {
                    user.setEmail("");
                }
                if (user.getImage() == null) {
                    user.setImage("");
                }
                users.add(user);
            }

            if (users.size() == 0) {
                jsonObject.put("errcode","3");
                jsonObject.put("msg","没有粉丝");
            } else {
                jsonObject.put("followers",users);
            }
            return jsonObject.toString();
        } else {
            return "{\"errcode\":\"2\",\"msg\":\"用户id错误\"}";
        }
    }
}
