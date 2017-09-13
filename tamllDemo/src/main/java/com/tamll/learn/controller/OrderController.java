package com.tamll.learn.controller;

import com.tamll.learn.constant.CommonConstant;
import com.tamll.learn.entiy.*;
import com.tamll.learn.service.OrderItemService;
import com.tamll.learn.service.OrderService;
import com.tamll.learn.service.ProductService;
import com.tamll.learn.service.ReciveService;
import com.tamll.learn.utils.PaymentUtils;
import com.tamll.learn.utils.PropUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * 订单控制层
 */
@Controller
public class OrderController {

    @Autowired
    private ReciveService reciveService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private OrderItemService orderItemService;

    @Autowired
    private ProductService productService;

    /**
     * 添加订单
     * @param reciveId 收货信息ID
     * @param request 请求参数 从session域中获取购物车对象,取出其中的商品页面添加到订单中
     * @return 转向我的订单页面
     */
    @RequestMapping(value = "/addorder")
    public String addOrder(@RequestParam("recive") String reciveId,
                           HttpServletRequest request){
        Integer userReciveId = Integer.parseInt(reciveId);
        Recive recive = reciveService.getReciveById(userReciveId);
        String reciveInfo = recive.getUser_Recive_Name()+"-"+recive.getUser_Recive_Phone()+"-"
                +recive.getUser_Recive_Address()+"-"+recive.getUser_Recive_Detail_Address();
        Object cartObject = request.getSession().getAttribute("cart");
        if (cartObject==null){
            return "productlist";
        }
        Object objectUser = request.getSession().getAttribute(CommonConstant.USER_CONTEXT);
        User user = (User) objectUser;
        Order order = new Order();
        order.setOrder_Number(UUID.randomUUID().toString());
        double money = 0;
        int number = 0;
        Map<Product,Integer> cart = (Map<Product, Integer>) cartObject;
        for (Map.Entry<Product,Integer> entry:cart.entrySet()){
            orderItemService.insertOrderItem(entry.getKey().getProduct_Id(),user.getUser_Id(),
                    entry.getValue(),order);
            Product product = productService.getProductById(entry.getKey().getProduct_Id());
            product.setProduct_Stock(product.getProduct_Stock()-entry.getValue());
            productService.updateProductStock(product);
            money += entry.getKey().getProduct_Orignal_Price()*entry.getValue();
            number += entry.getValue();
        }
        orderService.insertOrder(order.getOrder_Number(),new Date(),money,
                number,CommonConstant.UN_PAY,reciveInfo);
        return "redirect:/myorderlist";
    }

    /**
     * 打开我的订单列表页
     * @param request 请求参数 从session域中获取登陆的user对象,发送用户的订单列表到前台
     * @return 如果用户未登录,转向用户登陆页面,否则,转向订单列表页
     */
    @RequestMapping(value = "/myorderlist")
    public String orderList(HttpServletRequest request){
        Object objectUser = request.getSession().getAttribute(CommonConstant.USER_CONTEXT);
        if (objectUser==null){
            return "../../login";
        }
        User user = (User) objectUser;
        List<Order> orders = orderService.getOrderListByUserId(user.getUser_Id());
        for (Order order:orders) {
            List<OrderItem> orderItems = orderItemService.getOrderItemListByOrderNumber(order.getOrder_Number());
            order.setOrderItems(orderItems);
        }
        request.setAttribute("orderlist",orders);
        return "order_list";
    }

    /**
     * 删除订单
     * @param orderNumber 订单编号
     * @param request 请求参数
     * @param response 响应参数 向页面返回错误信息
     * @return 返回我的订单页
     * @throws IOException 抛出读写异常
     */
    @RequestMapping(value = "/deleteorder/{orderNumber}")
    public String deleteOrder(@PathVariable String orderNumber,
                              HttpServletRequest request,
                              HttpServletResponse response) throws IOException {
        Order order = orderService.getOrderByNumber(orderNumber);
        if (order.getOrder_Status()!=0){
            response.getWriter().write("只有未支付的订单才能删除");
            return "order_list";
        }
        List<OrderItem> orderItems = orderItemService.getOrderItemListByOrderNumber(orderNumber);
        for (OrderItem orderItem:orderItems){
            System.out.println(orderItem.getProduct().getProduct_Name());
            System.out.println(orderItem.getProduct().getProduct_Stock());
            Product product = orderItem.getProduct();
            product.setProduct_Stock(product.getProduct_Stock()+orderItem.getOrderItem_Product_Number());
            productService.updateProductStock(product);
        }
        orderItemService.deleteOrderItemByOrderNumber(orderNumber);
        orderService.deleteOrderByNumber(orderNumber);
        return "redirect:/myorderlist";
    }

    /**
     * 打开订单支付页
     * @param orderNumber 订单编号
     * @param orderTotal 订单金额
     * @param request 请求参数 向前台传递参数
     * @return 转向支付页
     */
    @RequestMapping(value = "/paypage/{orderNumber}/{orderTotal}")
    public String payOrderPage(@PathVariable String orderNumber,
                           @PathVariable double orderTotal,
                           HttpServletRequest request){
        request.setAttribute("orderNumber",orderNumber);
        request.setAttribute("orderTotal",orderTotal);
        return "pay";
    }

    /**
     * 确认支付
     * @param orderNumber 订单编号
     * @param request 请求参数 向前台传递支付平台参数信息
     * @return 转向确认支付页
     */
    @RequestMapping(value = "/payorder")
    public String payOrder(@RequestParam("orderNumber") String orderNumber,
                           HttpServletRequest request){
        //1、准备请求需要的参数
        String p0_Cmd="Buy";//业务类型
        //商户编号
        String p1_MerId= PropUtils.getProperty("p1_MerId");
        //订单号
        String p2_Order = orderNumber;
        //支付金额,测试时使用0.01
        String p3_Amt="0.01";
        //String p3 = orderService.getOrderByNumber(orderNumber).getOrder_Total();
        String p4_Cur="CNY";//交易币种
        String p5_Pid="";//商品名称
        String p6_Pcat="";//商品种类
        String p7_Pdesc="";//商品描述
        //回调的Servlet:商户接收支付成功数据的地址
        String p8_Url=PropUtils.getProperty("responseURL");
        String p9_SAF = "";//送货地址
        String pa_MP = "";//商户的扩展信息
        String pd_FrpId=request.getParameter("pd_FrpId");
        String pr_NeedResponse="1";//应答机制
        //使用提供的工具和秘钥对以上参数进行加密
        String hmac= PaymentUtils.buildHmac(p0_Cmd, p1_MerId, p2_Order,
                p3_Amt, p4_Cur, p5_Pid, p6_Pcat, p7_Pdesc, p8_Url,
                p9_SAF, pa_MP, pd_FrpId, pr_NeedResponse,
                PropUtils.getProperty("keyValue"));
        //2、将以上参数保存到request作用域中
        request.setAttribute("pd_FrpId", pd_FrpId);
        request.setAttribute("p0_Cmd", p0_Cmd);
        request.setAttribute("p1_MerId", p1_MerId);
        request.setAttribute("p2_Order", p2_Order);
        request.setAttribute("p3_Amt", p3_Amt);
        request.setAttribute("p4_Cur", p4_Cur);
        request.setAttribute("p5_Pid", p5_Pid);
        request.setAttribute("p6_Pcat", p6_Pcat);
        request.setAttribute("p7_Pdesc", p7_Pdesc);
        request.setAttribute("p8_Url", p8_Url);
        request.setAttribute("p9_SAF", p9_SAF);
        request.setAttribute("pa_MP", pa_MP);
        request.setAttribute("pr_NeedResponse", pr_NeedResponse);
        request.setAttribute("hmac", hmac);
        return "confirm";
    }

    /**
     * 支付状态回执
     * @param request 请求参数 获取支付平台回执参数
     * @param response 响应参数 向前台返回错误信息
     * @return 转向我的订单页
     * @throws IOException 抛出读写异常
     */
    @RequestMapping(value = "/callback")
    public String callBack(HttpServletRequest request,
                           HttpServletResponse response) throws IOException {
        //1、接收参数
        String p1_MerId = request.getParameter("p1_MerId");
        String r0_Cmd = request.getParameter("r0_Cmd");
        String r1_Code =request.getParameter("r1_Code");
        String r2_TrxId = request.getParameter("r2_TrxId");
        String r3_Amt = request.getParameter("r3_Amt");
        String r4_Cur = request.getParameter("r4_Cur");
        String r5_Pid = request.getParameter("r5_Pid");
        String r6_Order = request.getParameter("r6_Order");
        String r7_Uid =request.getParameter("r7_Uid");
        String r8_MP = request.getParameter("r8_MP");
        //1表示重定向，2表示点对点通讯
        String r9_BType = request.getParameter("r9_BType");
        String rb_BankId = request.getParameter("rb_BankId");
        //银行流水号
        String ro_BankOrderId =request.getParameter("ro_BankOrderId");
        String rp_PayDate = request.getParameter("rp_PayDate");
        String rq_CardNo = request.getParameter("rq_CardNo");
        String ru_Trxtime =request.getParameter("ru_Trxtime");
        //签名数据
        String hmac = request.getParameter("hmac");
        //2校验数据是否被修改  true:未被修改   false:被修改
        boolean isNoUpdate = PaymentUtils.verifyCallback(
                hmac, p1_MerId, r0_Cmd, r1_Code, r2_TrxId,
                r3_Amt, r4_Cur, r5_Pid, r6_Order, r7_Uid,
                r8_MP, r9_BType, PropUtils.getProperty("keyValue"));
        //3、未修改的话
        if(isNoUpdate){//为被修改
            //判断重定向过来，还是点对点通讯过来的
            if("1".equals(r9_BType)){//重定向
                //测试时使用.....
                //修改订单的支付状态

                //查询当前的订单信息   课后思考：如何防止更新丢失
                orderService.getOrderByNumber(r6_Order);
                //.......
                response.getWriter().write("支付操作已受理，支付结果需要等待进一步通知的消息...");
            }else if("2".equals(r9_BType)&&"1".equals(r1_Code)){
                //修改订单的支付状态
                Order order = orderService.getOrderByNumber(r6_Order);
                order.setOrder_Status(CommonConstant.UN_DELIVERY);
                order.setOrder_Pay_Date(new Date());
                orderService.updateOrderByNumber(order);
                //查询当前的订单信息   课后思考：如何防止更新丢失
                orderService.getOrderByNumber(r6_Order);
                //响应的第三方支付平台success
                response.getWriter().write("success");
            }
        }else{//4被修改了
            System.out.println("数据被篡改了...");
            //将来可以转发到数据被篡改的页面
        }
        return "myorderlist";
    }
}
