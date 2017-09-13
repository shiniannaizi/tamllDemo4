package com.tamll.learn.constant;

/**
 * 整个应用通用的常量
 */
public class CommonConstant {
    /**
     * 用户对象放到Session中的键名称
     */
    public static final String USER_CONTEXT = "USER_CONTEXT";

    /**
     * 将登录前的URL放到Session中的键名称
     */
    public static final String LOGIN_TO_URL = "toUrl";

    /**
     * 每页的记录数
     */
    public static final int PAGE_SIZE = 3;

    //订单支付---未支付
    public static final int UN_PAY = 0;

    //订单状态---未发货
    public static final int UN_DELIVERY = 1;

    //订单状态---未收货
    public static final int UN_RECIVE = 2;

    //订单状态--完成
    public static final int FINISH = 3;
}
