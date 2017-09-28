package com.tamll.learn.controller;

import com.tamll.learn.constant.CommonConstant;
import com.tamll.learn.entiy.Recive;
import com.tamll.learn.entiy.User;
import com.tamll.learn.exception.NotWriteException;
import com.tamll.learn.service.ReciveService;
import com.tamll.learn.service.UserService;
import com.tamll.learn.utils.CookieUtils;
import com.tamll.learn.utils.SendJMail;
import com.tamll.learn.utils.VerifyCode;
import com.tamll.learn.utils.WebUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

/**
 * user控制层
 */
@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private ReciveService reciveService;

    /**
     * 测试方法一
     * @param map 保存用户
     * @return 转向测试页面
     */
    @RequestMapping(value = "/index")
    public String test(ModelMap map){
        User user = userService.getUserByName("user");
        System.out.println(user.getUser_Address()+"sssss");
        System.out.println(user.getUser_Name()+"xxxxx");
        map.put("user",user);
        return "test/test";
    }

    /**
     * 打开登录界面
     * @return 转向到登录页面
     */
    @RequestMapping(value = "/login",method = RequestMethod.GET)
    public String login(){
        return "/login.jsp";
    }

    /**
     * 执行用户登录验证
     * @param userName 用户名
     * @param userPassword 用户密码
     * @param request 请求参数 如果验证为通过，向页面发送错误信息
     * @param response 响应参数 验证通过后，为用户名添加Cookie
     * @return 登录成功转向到首页，登录失败转回登录页面
     */
    @RequestMapping(value = "/doLogin")
    public String dologin(@RequestParam("username") String userName,
                        @RequestParam("password") String userPassword,
                        HttpServletRequest request,
                        HttpServletResponse response) throws UnsupportedEncodingException {
        if (WebUtils.isNull(userName)){
            request.setAttribute("errMsg", "用户名不能为空");
            return "forward:/login.jsp";
        }
        if (WebUtils.isNull(userPassword)){
            request.setAttribute("errMsg", "密码不能为空");
            return "forward:/login.jsp";
        }
        if (userService.getUserByName(userName) == null){
            request.setAttribute("errMsg", "用户名不存在");
            return "forward:/login.jsp";
        }
        if(userPassword.equals(userService.getUserByName(userName).getUser_Password())){
            if ("true".equals(request.getParameter("remname"))){
                CookieUtils.setCookie(request,response,"remname",userName,
                        3600*24*30,true);
            }else {//删除Cookie
                CookieUtils.deleteCookie(request,response,"remname");
            }if ("true".equals(request.getParameter("autologin"))){
                CookieUtils.setCookie(request,response,"autologin",
                        userName+":"+userPassword,3600*24*30,true);
            }else {
                CookieUtils.deleteCookie(request,response,"autologin");
            }
            Subject subject = SecurityUtils.getSubject();
            UsernamePasswordToken token = new UsernamePasswordToken(userName,userPassword);
            subject.login(token);
            request.getSession().setAttribute(CommonConstant.USER_CONTEXT,
                    userService.getUserByName(userName));
            return "redirect:"+request.getContextPath()+"/index.jsp";
        }else {
            request.setAttribute("errMsg", "用户名或密码错误");
            return "forward:/login.jsp";
        }
    }

    /**
     *登录注销
     * @param session 从session中取出用户
     * @return 转向主页
     */
    @RequestMapping("/doLogout")
    public String Logout(HttpSession session){
        session.removeAttribute(CommonConstant.USER_CONTEXT);
        return "forward:/index.jsp";
    }

    /**
     * 打开注册界面
     * @return 转向到注册页面
     */
    @RequestMapping(value = "/regist",method = RequestMethod.GET)
    public String indexPage(){
        return "add/regist";
    }

    /**
     * 验证用户名是否存在
     * @param request 请求参数 获取前台参数,用户名username
     * @param response 响应参数 如果数据库中没有和username名字相同的用户，向前台写一个true，否则false
     */
    @RequestMapping(value = "/checkusername")
    public void checkUserName(HttpServletRequest request,
                              HttpServletResponse response){
        if (request.getParameter("username")!=null){
            String username = request.getParameter("username");
            if (userService.getUserByName(username)==null){
                try {
                    response.getWriter().write("true");
                } catch (IOException e) {
                    e.printStackTrace();
                    NotWriteException.handleException(e,request);
                }
            }else {
                try {
                    response.getWriter().write("false");
                } catch (IOException e) {
                    e.printStackTrace();
                    NotWriteException.handleException(e,request);
                }
            }
        }else {
            try {
                response.getWriter().write("false");
            } catch (IOException e) {
                e.printStackTrace();
                NotWriteException.handleException(e,request);
            }
        }
    }

    /**
     * 检查邮箱是否已经被注册
     * @param request 请求参数 获取前台参数,邮箱eamil,用户ID userId
     * @param response 响应参数 如果数据库中没有和eamil相同的邮箱地址或者eamil和通过
     *                 userid查出来的user对象的eamil相同(即：修改个人信息时,不修改邮箱),
     *                 向前台写一个true，否则为false
     */
    @RequestMapping(value = "/checkeamil")
    public void checkUserEmail(HttpServletRequest request,
                               HttpServletResponse response){
        if (request.getParameter("email")!=null){
            String email = request.getParameter("email");
            if (request.getParameter("userId")!=null){
                Integer userid = Integer.parseInt(request.getParameter("userId"));
                User user = userService.getUserById(userid);
                if (email.equals(user.getUser_Email())){
                    try {
                        response.getWriter().write("true");
                    } catch (IOException e) {
                        e.printStackTrace();
                        NotWriteException.handleException(e,request);
                    }
                }
            }
            if (userService.getUserByEmail(email)==null){
                try {
                    response.getWriter().write("true");
                } catch (IOException e) {
                    e.printStackTrace();
                    NotWriteException.handleException(e,request);
                }
            }else {
                try {
                    response.getWriter().write("false");
                } catch (IOException e) {
                    e.printStackTrace();
                    NotWriteException.handleException(e,request);
                }
            }
        }else {
            try {
                response.getWriter().write("false");
            } catch (IOException e) {
                e.printStackTrace();
                NotWriteException.handleException(e,request);
            }
        }
    }

    /**
     * 生成验证码
     * @param request 请求参数 获取session域,把验证码内容存入域中
     * @param response 响应参数 获取一个字节输出流
     */
    @RequestMapping(value = "/valiImage")
    public void valiImage(HttpServletRequest request,
                          HttpServletResponse response){
        //生成验证码图片
        VerifyCode vc = new VerifyCode();
        try {
            vc.drawImage(response.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
            NotWriteException.handleException(e,request);
        }
        request.getSession().setAttribute("checkCode",vc.getCode());
    }

    /**
     * 验证用户输入的验证码是否正确
     * @param request 请求参数 获取session中存入的验证码内容，获取前台验证码参数valistr
     * @param response 响应参数 如果valistr的值和session域中的checkCode相同，写出一个true,
     *                 否则为false
     */
    @RequestMapping(value = "/checkValiImage",method = RequestMethod.GET)
    public void checkValiImage(HttpServletRequest request,
                               HttpServletResponse response){
        if(request.getParameter("valistr")!=null){
            String valistr = request.getParameter("valistr");
            String vc = (String) request.getSession().getAttribute("checkCode");
            if (valistr.equalsIgnoreCase(vc)){
                try {
                    response.getWriter().write("true");
                } catch (IOException e) {
                    e.printStackTrace();
                    NotWriteException.handleException(e,request);
                }
            }else {
                try {
                    response.getWriter().write("false");
                } catch (IOException e) {
                    e.printStackTrace();
                    NotWriteException.handleException(e,request);
                }
            }
        }else {
            try {
                response.getWriter().write("false");
            } catch (IOException e) {
                e.printStackTrace();
                NotWriteException.handleException(e,request);
            }
        }
    }

    /**
     * 用户注册事件
     * @param userName 用户名
     * @param userSex 用户性别
     * @param userPassword 用户密码
     * @param userEmail 用户邮箱
     * @param userAddress 用户地址
     * @param request 请求参数 如果验证失败，向前台返回错误信息
     * @return 注册成功转向首页
     */
    @RequestMapping(value = "/insert")
    public String insertUser(@RequestParam("username") String userName,
                           @RequestParam("usersex") String userSex,
                           @RequestParam("password") String userPassword,
                           @RequestParam("email") String userEmail,
                           @RequestParam("userAddress") String userAddress,
                           HttpServletRequest request){
        //后台校验
        if (WebUtils.isNull(userName) || WebUtils.isNull(userPassword) ||
                WebUtils.isNull(userSex) || WebUtils.isNull(userAddress) ||
                WebUtils.isNull(userEmail)){
            request.setAttribute("errMsg","输入信息不能为空");
            return "add/regist";
        }
        if (!userName.matches("^[^@#]{3,16}$")){
            request.setAttribute("errMsg","用户名不符合规则");
            return "add/regist";
        }
        if (userService.getUserByName(userName)!=null){
            request.setAttribute("errMsg","用户名已存在");
            return "add/regist";
        }
        if (!userPassword.matches("^[0-9A-Za-z_]\\w{7,15}$")){
            request.setAttribute("errMsg","密码不符合规则");
            return "add/regist";
        }
        if (!userEmail.matches("^[a-z0-9]\\w+@[a-z0-9]{2,3}(\\.[a-z]{2,3}){1,2}$")){
            request.setAttribute("errMsg","邮箱不符合规则");
            return "add/regist";
        }
        if (userService.getUserByEmail(userEmail) != null){
            request.setAttribute("errMsg","邮箱已被注册");
            return "add/regist";
        }
        //验证成功，注册用户
        userService.insertUser(userName,userSex,userPassword,userAddress,userEmail);
        //向用户邮箱发送激活邮件
        SendJMail.sendMail(userEmail,
                "请点击去<a href='http://localhost:8080/activecode/?email="
                        +userEmail+"'>激活帐号</a>");
        return "forward:/time1.jsp";
    }

    /**
     * 查询用户详细信息
     * @param userId 用户ID
     * @param request 请求参数 如果通过userId查询出的user对象存在,将该对象存入request域中，
     *                否则向前台页面返回错误信息
     * @return 查询成功转向个人详情页
     */
    @RequestMapping(value = "/userinfo/{userId}")
    public String userInfo(@PathVariable Integer userId, HttpServletRequest request){
        if (userService.getUserById(userId) != null){
            User user = userService.getUserById(userId);
            if (user.getState() == 0){
                request.setAttribute("errMsg","您的账号还未激活,请及时激活");
            }
            request.setAttribute("user", user);
            return "/userinfo";
        }else {
            request.setAttribute("errMsg","暂无用户信息");
            return "/userinfo";
        }
    }

    /**
     * 打开修改密码页面
     * @return 转到密码修改页面
     */
    @RequestMapping(value = "/changepwd")
    public String changePasswordPage(){
        return "update/changepwd";
    }

    /**
     * 修改密码
     * @param userName 用户名
     * @param oldpassword 旧密码
     * @param newpassword1 新密码
     * @param newpassword2 确认密码
     * @param request 请求参数 验证失败，向前台返回错误信息
     * @return 验证成功，修改密码并返回登录页面，验证失败返回修改页面
     */
    @RequestMapping(value = "/changepassword")
    public String changePassword(@RequestParam("userName") String userName,
                                 @RequestParam("oldpassword") String oldpassword,
                                 @RequestParam("newpassword1") String newpassword1,
                                 @RequestParam("newpassword2") String newpassword2,
                                 HttpServletRequest request){
        if (WebUtils.isNull(userName) || WebUtils.isNull(oldpassword) ||
                WebUtils.isNull(newpassword1) || WebUtils.isNull(newpassword2)){
            request.setAttribute("errMsg","输入信息不能为空");
            return "update/changepwd";
        }
        if (!newpassword1.equals(newpassword2)){
            request.setAttribute("errMsg","两次输入的密码不一致");
            return "update/changepwd";
        }
        if (!newpassword1.matches("^[0-9A-Za-z_]\\w{7,15}$")){
            request.setAttribute("errMsg","密码不符合规则");
            return "update/changepwd";
        }
        User user = userService.getUserByName(userName);
        if (!oldpassword.equals(user.getUser_Password())){
            request.setAttribute("errMsg","密码不正确");
            return "update/changepwd";
        }
        userService.userChangePwd(user.getUser_Id(),newpassword1);
        return "forward:/login.jsp";
    }

    /**
     * 打开用户所有的收货信息页面
     * @param userId 用户ID
     * @param request 请求参数 通过userId查询数据库,获取一个收货信息对象列表,如果列表为空,
     *                返回信息:你还没有收货地址。获取列表后遍历获取收货信息对象,如果有对象的
     *                详细地址属性为空,返回信息:您的详细地址为空,请尽快完善。并将列表存入request域中
     * @return 转向收货信息详情页面
     */
    @RequestMapping(value = "/reciveinfo/{userId}")
    public String reciveInfoPage(@PathVariable("userId") Integer userId,
                                 HttpServletRequest request){
        if (reciveService.getAllRecives(userId).size() > 0)
        {
            String err = null;
            List<Recive> recives = reciveService.getAllRecives(userId);
            for (Recive re : recives){
                if (WebUtils.isNull(re.getUser_Recive_Detail_Address())){
                    err = "您的详细地址为空,请尽快完善";
                }
            }
            request.setAttribute("recives",recives);
            request.setAttribute("errMsg",err);
            return "/reciveinfo";
        }else {
            request.setAttribute("errMsg","您还没有收货地址");
            return "/reciveinfo";
        }
    }

    /**
     * 检查用户的收货信息个数
     * @param request 请求参数
     * @param response 响应参数,如果个数大于5,就像页面输出false,否则输出true
     */
    @RequestMapping(value = "/checkrecive")
    public void checkReciveList(HttpServletRequest request,
                                HttpServletResponse response){
        if (request.getParameter("userId") != null){
            Integer userId = Integer.parseInt(request.getParameter("userId"));
            if (reciveService.getAllRecives(userId).size() < 5){
                try {
                    response.getWriter().write("true");
                } catch (IOException e) {
                    e.printStackTrace();
                    NotWriteException.handleException(e,request);
                }
            }else {
                try {
                    response.getWriter().write("false");
                } catch (IOException e) {
                    e.printStackTrace();
                    NotWriteException.handleException(e,request);
                }
            }
        }else {
            try {
                response.getWriter().write("false");
            } catch (IOException e) {
                e.printStackTrace();
                NotWriteException.handleException(e,request);
            }
        }
    }

    /**
     * 打开新增收货信息页面
     * @return 请求参数
     */
    @RequestMapping(value = "/addrecive")
    public String addRecivePage(){
        return "add/addrecive";
    }

    /**
     * 新增收货人信息
     * @param user_recive_name 收货人姓名
     * @param user_recive_phone 收货人手机号
     * @param user_recive_address 收货地址
     * @param userId 用户ID
     * @param user_Recive_Detail_Address 具体收货地址
     * @param request 请求参数 验证失败,向页面返回错误信息
     * @return 验证失败，返回添加页面，验证成功，添加收货信息，转向个人信息页面
     */
    @RequestMapping(value = "/addreciveinfo")
    public String addReciveInfo(@RequestParam("userId") Integer userId,
                                @RequestParam("user_recive_name") String user_recive_name,
                                @RequestParam("user_recive_phone") String user_recive_phone,
                                @RequestParam("user_recive_address") String user_recive_address,
                                @RequestParam("user_Recive_Detail_Address") String user_Recive_Detail_Address,
                                HttpServletRequest request){
        if (WebUtils.isNull(user_recive_address) || WebUtils.isNull(user_recive_phone) ||
                WebUtils.isNull(user_recive_name)){
            request.setAttribute("errMsg","输入信息不能为空");
            return "add/addrecive";
        }
        if (!user_recive_phone.matches("^((13[0-9])|(15[^4])|(18[0-9])|(17[0-8])|(147,145))\\d{8}$")){
            request.setAttribute("errMsg","手机号格式不正确");
            return "add/addrecive";
        }
        if (userService.getUserById(userId) == null){
            request.setAttribute("errMsg","用户不存在");
            return "add/addrecive";
        }
        reciveService.insertRecive(userId,user_recive_name,user_recive_phone,
                user_recive_address,user_Recive_Detail_Address);
        return "redirect:/reciveinfo/"+userId;
    }

    /**
     * 打开修改收货信息页面
     * @param reciveId 收货信息ID
     * @param request 请求参数 如果通过reciveId查询出的收货信息对象为空,返回错误信息
     *                        否则将查询出的收货信息对象存入到request域中
     * @return 若不为空，转向修改页面，若为空，返回本页面
     */
    @RequestMapping(value = "updaterecive/{reciveId}")
    public String updateRecivePage(@PathVariable Integer reciveId,
                                   HttpServletRequest request){
        if (reciveService.getReciveById(reciveId)==null){
            request.setAttribute("errMsg","这个地址不存在");
            return "/reciveinfo";
        }else {
            request.setAttribute("recive",reciveService.getReciveById(reciveId));
            return "update/updaterecive";
        }
    }

    /**
     * 修改收货信息
     * @param userId 用户ID
     * @param reciveId 收货信息ID
     * @param user_recive_name 收货人姓名
     * @param user_recive_phone 收货人手机号
     * @param user_recive_address 收货地址
     * @param user_Recive_Detail_Address 详细地址
     * @param request 请求参数 验证失败返回错误信息
     * @return 验证不通过，返回详情页，验证通过，修改信息，返回详情页
     */
    @RequestMapping(value = "updatereciveinfo/{userId}")
    public String updateRecive(@PathVariable Integer userId,
                               @RequestParam("reciveId") Integer reciveId,
                               @RequestParam("user_recive_name") String user_recive_name,
                               @RequestParam("user_recive_phone") String user_recive_phone,
                               @RequestParam("user_recive_address") String user_recive_address,
                               @RequestParam("user_Recive_Detail_Address") String user_Recive_Detail_Address,
                               HttpServletRequest request){
        if (WebUtils.isNull(user_recive_name) || WebUtils.isNull(user_recive_phone) ||
                WebUtils.isNull(user_recive_address)){
            request.setAttribute("errMsg","输入信息不能为空");
            return "update/updaterecive";
        }
        if (!user_recive_phone.matches("^((13[0-9])|(15[^4])|(18[0-9])|(17[0-8])|(147,145))\\d{8}$")){
            request.setAttribute("errMsg","手机号格式不正确");
            return "update/updaterecive";
        }
        reciveService.updateRecive(reciveId,user_recive_name,user_recive_phone,
                user_recive_address,user_Recive_Detail_Address);
        return "redirect:/reciveinfo/"+userId;
    }

    /**
     * 根据收货信息编号删除收货信息
     * @param reciveId 收货信息ID
     * @param userId 用户ID
     * @param request 请求参数 如果通过reciveId查询的的收货信息对象不存在,返回错误信息
     * @return 验证不通过，返回本页面，验证通过，删除信息系后返回本页面
     */
    @RequestMapping(value = "deleterecive/{userId}/{reciveId}")
    public String deleteRecive(@PathVariable Integer userId,
                               @PathVariable Integer reciveId,
                               HttpServletRequest request){
        if (reciveService.getReciveById(reciveId)==null){
            request.setAttribute("errMsg","该地址信息不存在");
            return "reciveinfo";
        }else {
            reciveService.deleteRecive(reciveId);
            return "redirect:/reciveinfo/"+userId;
        }
    }

    /**
     * 打开个人信息更新页面
     * @param userId 用户ID
     * @param request 请求参数 如果通过userId查询出的用户对象不存在,否则将该对象存入request域中
     * @return 验证失败返回首页，验证成功转向个人信息更新页面
     */
    @RequestMapping(value = "/updateuser/{userId}")
    public String updateUserPage(@PathVariable Integer userId,HttpServletRequest request){
        if (userService.getUserById(userId)==null){
            request.setAttribute("errMsg","用户不存在");
            return "/index";
        }
        request.setAttribute("user",userService.getUserById(userId));
        return "update/updateuser";
    }

    /**
     * 更新个人信息
     * @param userId 用户ID
     * @param userSex 用户性别
     * @param address 用户地址
     * @param email 邮箱地址
     * @param request 请求参数 如果验证失败,返回错误信息
     * @return 验证失败返回更新页面，验证成功更新信息，转向个人信息页面
     */
    @RequestMapping(value = "/updateuserinfo/{userId}")
    public String updateUserInfo(@PathVariable Integer userId,
                                 @RequestParam("usersex") String userSex,
                                 @RequestParam("address") String address,
                                 @RequestParam("email") String email,
                                 HttpServletRequest request){
        if ( WebUtils.isNull(address) || WebUtils.isNull(userSex) ||
                WebUtils.isNull(email)){
            request.setAttribute("errMsg","输入信息不能为空");
            return "update/updateuser";
        }
        if (!email.matches("^[a-z0-9]\\w+@[a-z0-9]{2,3}(\\.[a-z]{2,3}){1,2}$")){
            request.setAttribute("errMsg","邮箱不符合规则");
            return "update/updateuser";
        }
        User user = userService.getUserById(userId);
        if (user.getState() == 0){
            //向用户邮箱发送激活邮件
            SendJMail.sendMail(email,
                    "请点击去<a href='http://localhost:8080/activecode/?email="
                            +email+"'>激活帐号</a>");
        }
        userService.updateUser(user.getUser_Id(),userSex,address,email);
        return "redirect:/userinfo/"+user.getUser_Id();
    }

    /**
     * 用户账号邮箱激活
     * @param email 用户邮箱地址
     * @param request 请求参数,发生错误时输出错误信息
     * @param response 响应参数 如果激活失败,写出激活失败信息
     * @return 激活成功转向激活成功页面
     */
    @RequestMapping(value = "/activecode")
    public String activeCode(@RequestParam("email") String email,
                             HttpServletRequest request,
                             HttpServletResponse response){
        if (WebUtils.isNull(email)){
            try {
                response.getWriter().write("邮箱不能为空");
            } catch (IOException e) {
                e.printStackTrace();
                NotWriteException.handleException(e,request);
            }
            return "test/test";
        }else {
            int rowCount  =  userService.activeCodeByEmail(email);
            if(rowCount>0){
                //激活成功！
                return "activecodesuccess";
            }else{
                try {
                    response.getWriter().write("激活失败，请重新激活");
                } catch (IOException e) {
                    e.printStackTrace();
                    NotWriteException.handleException(e,request);
                }
                return "test/test";
            }
        }
    }

    @RequestMapping(value = "/management/userlist")
    public String userList(HttpServletRequest request){
        request.setAttribute("users",userService.getFullUserList());
        return "back/user/userlist";
    }

}
