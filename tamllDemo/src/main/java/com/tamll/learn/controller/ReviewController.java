package com.tamll.learn.controller;

import com.tamll.learn.constant.CommonConstant;
import com.tamll.learn.entiy.User;
import com.tamll.learn.service.ReviewService;
import com.tamll.learn.utils.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
public class ReviewController {

    @Autowired
    private ReviewService reviewService;

    @RequestMapping(value = "/prodreview/{prodId}")
    public String productReviewPage(@PathVariable Long prodId,
                                    HttpServletRequest request,
                                    Model model){
        model.addAttribute("productId",prodId);
        model.addAttribute("reviews",reviewService.getReviewsByProductId(prodId));
        return "productreview";
    }

    @RequestMapping(value = "/addprodreview/{prodId}")
    public String addProductReview(@PathVariable Long prodId,
                                   @RequestParam("addreview") String addreview,
                                   HttpServletRequest request){
        if (WebUtils.isNull(addreview)){
            request.setAttribute("errMsg","评价不能为空");
            return "redirect:/prodreview/"+prodId;
        }
        User user = (User) request.getSession().getAttribute(CommonConstant.USER_CONTEXT);
        reviewService.insertReview(addreview.trim(),user.getUser_Id(),prodId);
        return "redirect:/prodreview/"+prodId;
    }

    @RequestMapping(value = "/myprodreview")
    public String myProductReview(HttpServletRequest request){
        User user = (User) request.getSession().getAttribute(CommonConstant.USER_CONTEXT);
        if (user==null){
            return "redirect:/login";
        }
        request.setAttribute("myreviews",reviewService.getReviewsByUserId(user.getUser_Id()));
        return "myreview";
    }

    @RequestMapping(value = "/updatereview/{prodId}")
    public String updateMyReviewPage(@PathVariable Long prodId,HttpServletRequest request){
        User user = (User) request.getSession().getAttribute(CommonConstant.USER_CONTEXT);
        request.setAttribute("review",reviewService.getReviewInfo(user.getUser_Id(),prodId));
        return "update/updatereview";
    }

    @RequestMapping(value = "/updatereviewinfo/{prodId}")
    public String updateMyReviewInfo(@PathVariable Long prodId,
                                     @RequestParam("myreview") String myreview,
                                     HttpServletRequest request){
        User user = (User) request.getSession().getAttribute(CommonConstant.USER_CONTEXT);
        reviewService.updateReviewInfo(myreview,user.getUser_Id(),prodId);
        return "redirect:/myprodreview";
    }

    @RequestMapping(value = "/deletereviewinfo/{prodId}")
    public String deleteMyReview(@PathVariable Long prodId,
                                 HttpServletRequest request){
        User user = (User) request.getSession().getAttribute(CommonConstant.USER_CONTEXT);
        reviewService.deleteReview(user.getUser_Id(),prodId);
        return "redirect:/myprodreview";
    }
}
