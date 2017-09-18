package com.tamll.learn.controller;

import com.tamll.learn.constant.CommonConstant;
import com.tamll.learn.entiy.*;
import com.tamll.learn.service.*;
import com.tamll.learn.utils.IDUtils;
import com.tamll.learn.utils.UploadImageUtils;
import com.tamll.learn.utils.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 商品控制层
 */
@Controller
public class ProductController {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ProductService productService;

    @Autowired
    private PropertyValueService propertyValueService;

    @Autowired
    private PropertyService propertyService;

    @Autowired
    private ProdImageService prodImageService;

    @Autowired
    private UserService userService;
    /**
     * 打开商品添加页面
     * @param request 请求参数，向页面返回分类列表
     * @return 转向商品添加页面
     */
    @RequestMapping(value = "/backend/addProd")
    public String addProductPage(HttpServletRequest request){
        request.setAttribute("categorys",categoryService.getAllCategory());
        return "/add/addproduct";
    }

    /**
     * 将前台传过来的相应字符串转换为对应格式的日期类型
     * @param binder 前台数据参数
     */
    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }

    /**
     * 添加商品
     * @param product_Name 商品名称
     * @param product_Orign_Price 商品价格
     * @param product_Promote_Price 促销价
     * @param product_Stock 商品库存
     * @param product_Create_Date 生成日期
     * @param product_Category 商品分类
     * @param product_Subtitle 商品简介
     * @param request 请求参数
     * @return 添加成功返回商品更新页面
     */
    @RequestMapping(value = "/backend/addProdInfo")
    public String addProductInfo(@RequestParam("prod_name") String product_Name,
                                 @RequestParam("prod_ori_price") Double product_Orign_Price,
                                 @RequestParam("prod_pro_price") Double product_Promote_Price,
                                 @RequestParam("prod_stock") Integer product_Stock,
                                 @RequestParam("prod_create_date") Date product_Create_Date,
                                 @RequestParam("prod_category") String product_Category,
                                 @RequestParam("prod_subtitle") String product_Subtitle,
                                 MultipartHttpServletRequest request){
        long product_Id = IDUtils.getProductId();
        Integer product_Status = 0;
        Date product_Update_Date = new Date();
        Category category = categoryService.getCategoryById(Integer.parseInt(product_Category));
        List<MultipartFile> files = request.getFiles("prod_first_image");
        String product_First_Image = null;
        try {
            product_First_Image = UploadImageUtils.uploadImage(files);
        } catch (IOException e) {
            e.printStackTrace();
        }
        productService.insertProduct(product_Id,product_Name,product_Orign_Price,product_Promote_Price,
                product_Stock,product_Create_Date,product_Update_Date,product_Subtitle,product_First_Image,
                category,product_Status);
        return "redirect:/backend/updateprod/"+product_Id;
    }

    /**
     * 打开后台商品列表页面
     * @param request 请求参数，返回一个商品列表
     * @return 转向后台商品列表页面
     */
    @RequestMapping(value = "/backend/prodlist")
    public String backendProdList(HttpServletRequest request){
        Map<String,Object> map = new HashMap<String, Object>();
        map.put("product_Name",null);
        map.put("category",null);
        map.put("minPrice",null);
        map.put("maxPrice",null);
        request.setAttribute("productlist",productService.getAllProduct(map));
        return "/back/prod/prodlist";
    }

    /**
     * 打开商品信息修改页面
     * @param prodId 商品ID
     * @param request 请求参数，返回一个商品对象,分类列表,商品图片列表,以及属性+属性名的Map
     * @return 转向商品信息修改页面
     */
    @RequestMapping(value = "/backend/updateprod/{prodId}")
    public String updateProduct(@PathVariable Long prodId,
                                HttpServletRequest request){
        Product product = productService.getFullProductById(prodId);
        request.setAttribute("product",product);
        request.setAttribute("categorys",categoryService.getAllCategory());
        request.setAttribute("productImages",product.getProductImages());
        List<PropertyValue> propertyValues = product.getPropertyValues();
        Map<String,String> map = new HashMap<String, String>();
        for (PropertyValue propertyValue : propertyValues){
            String prvalue = propertyValue.getProperty_Value_Value();
            PropertyValue pv = propertyValueService.getFullPropertyValueById(
                    propertyValue.getProperty_Value_Id());
            String prname = pv.getProperty().getProperty_Name();
            map.put(prname,prvalue);
        }
        request.setAttribute("propertyMap",map);
        return "/back/prod/updateprod";
    }

    /**
     * 修改商品信息
     * @param prodId 商品ID
     * @param prodName 商品名称
     * @param prodOP 商品价格
     * @param prodPP 促销价
     * @param prodStock 商品库存
     * @param prodSubtitle 商品简介
     * @param prodCategory 商品分类
     * @param request 请求参数,获取前台图片参数
     * @return 转向商品修改页面
     */
    @RequestMapping(value = "/backend/updateproductinfo/{prodId}")
    public String updateProductInfo(@PathVariable Long prodId,
                                    @RequestParam("prod_name") String prodName,
                                    @RequestParam("prod_ori_price") Double prodOP,
                                    @RequestParam("prod_pro_price") Double prodPP,
                                    @RequestParam("prod_stock") Integer prodStock,
                                    @RequestParam("prod_subtitle") String prodSubtitle,
                                    @RequestParam("prod_category") String prodCategory,
                                    MultipartHttpServletRequest request){
        Category category = categoryService.getCategoryById(Integer.parseInt(prodCategory));
        Date updateDate = new Date();
        String imgurl = null;
        List<MultipartFile> files = request.getFiles("prod_first_image");
        if (WebUtils.isNull(files.get(0).getOriginalFilename())){
            productService.updateProductById(prodId,prodName,prodOP,prodPP,
                    prodStock,updateDate,prodSubtitle,0,category);
            return "redirect:/backend/updateprod/"+prodId;
        } else {
            try {
                imgurl = UploadImageUtils.uploadImage(files);
                productService.updateProductById(prodId,prodName,prodOP,prodPP,
                        prodStock,updateDate,prodSubtitle,imgurl,0,category);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return "redirect:/backend/updateprod/"+prodId;
        }
    }

    /**
     * 打开设置商品属性页面
     * @param prodId 商品ID
     * @param request 请求参数,返回一个属性列表以及商品ID
     * @return 转向商品属性设置页面
     */
    @RequestMapping(value = "/backend/addproperty/{prodId}")
    public String addPropertyPage(@PathVariable Long prodId,
                                  HttpServletRequest request){
        request.setAttribute("propertys",propertyService.getAllProperty());
        request.setAttribute("productId",prodId);
        return "back/prod/addproperty";
    }

    /**
     * 商品属性设置
     * @param prodId 商品ID
     * @param request 获取前台参数Map
     * @return 返回商品信息修改页面
     */
    @RequestMapping(value = "/backend/addpropertyinfo/{prodId}")
    public String addProperty(@PathVariable Long prodId,
                              HttpServletRequest request){
        Set<Map.Entry<String,String[]>> entrySet = request.getParameterMap().entrySet();
        for (Map.Entry<String,String[]> entry : entrySet){
            String key = entry.getKey();
            String[] value = entry.getValue();
            String s = value[0];
            if (!WebUtils.isNull(s)){
                propertyValueService.insert(prodId,key,s);
            }
        }
        return "redirect:/backend/updateprod/"+prodId;
    }

    /**
     * 打开商品图片上传页面
     * @param prodId 商品ID
     * @param request 请求参数 从session域中获取商品ID
     *                发送商品图片列表到前台
     * @return 转向图片添加页面
     */
    @RequestMapping(value = "/backend/addprodimage/{prodId}")
    public String addProductImagePage(@PathVariable Long prodId,HttpServletRequest request){
        request.getSession().setAttribute("prodId",prodId);
        request.setAttribute("prodImageList",prodImageService.getProductImageByProductId(prodId));
        return "back/prod/addprodimage";
    }

    /**
     * 商品图片上传
     * @param request 请求参数 转换为文件上传的servlet
     * @param response 响应参数 上传成功后返回true
     */
    @RequestMapping(value = "/backend/addprodimageinfo")
    public void addProductImage(HttpServletRequest request,
                                HttpServletResponse response){
        String imgurl = null;
        MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
        List<MultipartFile> files = multiRequest.getFiles("file");
        if (WebUtils.isNull(files.get(0).getOriginalFilename())){
            request.setAttribute("fileUrlList","还未上传任何图片");
        }else {
            try {
                imgurl = UploadImageUtils.uploadImage(files);
                request.setAttribute("fileUrlList",imgurl);
                response.getWriter().write("true");
                Long prodId = (Long) multiRequest.getSession().getAttribute("prodId");
                prodImageService.addProductImages(imgurl,prodId);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 删除商品图片
     * @param prodImgId 商品图片ID
     * @param prodId 商品ID
     * @param request 请求参数 删除成功后向页面返回信息
     * @return 转向商品图片添加页面
     */
    @RequestMapping(value = "/backend/deleteprodimg/{prodImgId}/{prodId}")
    public String deleteProductImage(@PathVariable Integer prodImgId,
                                     @PathVariable Long prodId,
                                     HttpServletRequest request){
        prodImageService.deleteProductImage(prodImgId);
        request.setAttribute("errMsg","图片已删除");
        return "redirect:/backend/addprodimage/"+prodId;
    }

    /**
     * 删除商品(删除商品的同时与商品关联的图片,属性也一起删除)
     * @param prodId 商品ID
     * @param request 请求参数
     * @return 转向商品列表页面
     */
    @RequestMapping(value = "/backend/deleteproduct/{prodId}")
    public String deleteProduct(@PathVariable Long prodId,
                                HttpServletRequest request){
        productService.deleteProductById(prodId);
        prodImageService.deleteProductImageByProductId(prodId);
        propertyValueService.deletePropertyValueByProductId(prodId);
        return "redirect:/backend/prodlist";
    }

    /**
     * 打开前台商品列表页面
     * @param request 请求参数，返回一个商品列表
     * @return 转向商品列表页面
     */
    @RequestMapping(value = "/productlist")
    public String productList(@RequestParam(value = "name",required = false) String name,
                              @RequestParam(value = "category",required = false) String category,
                              @RequestParam(value = "minprice",required = false) String minprice,
                              @RequestParam(value = "maxprice",required = false) String maxprice,
                              HttpServletRequest request){
        String productName = null;
        Integer categoryId = null;
        Double min = null;
        Double max = null;
        if (WebUtils.isNotNull(name)){
            productName = name.trim();
        }
        if (WebUtils.isNotNull(category)){
            categoryId = categoryService.getCategoryByName(category.trim()).getCategory_Id();
        }
        if (WebUtils.isNotNull(minprice)){
            min = Double.parseDouble(minprice.trim());
        }
        if (WebUtils.isNotNull(maxprice)){
            max = Double.parseDouble(maxprice.trim());
        }
        Map<String,Object> map = new HashMap<String, Object>();
        map.put("product_Name",productName);
        map.put("category",categoryId);
        map.put("minPrice",min);
        map.put("maxPrice",max);
        request.setAttribute("name",productName);
        request.setAttribute("category",category);
        request.setAttribute("minprice",min);
        request.setAttribute("maxprice",max);
        request.setAttribute("productlist",productService.getAllProduct(map));
        return "productlist";
    }

    /**
     * 打开商品详情页面
     * @param prodId 商品ID
     * @param request 请求参数 发送商品对象到前台页面
     * @return 转向商品详情信息页
     */
    @RequestMapping(value = "/productinfo/{prodId}")
    public String productInfo(@PathVariable Long prodId,
                              HttpServletRequest request){
        request.setAttribute("product",productService.getProductById(prodId));
        return "productinfo";
    }

    /**
     * 添加购物车
     * @param prodId 商品ID
     * @param request 请求参数 从session域中获取购物车Map,如果没有则创建
     * @return 转向购物车页面
     */
    @RequestMapping(value = "/addcart/{prodId}")
    public String addCart(@PathVariable Long prodId,
                          HttpServletRequest request){
        Product product = productService.getProductById(prodId);
        Object cartObject = request.getSession().getAttribute("cart");
        Map<Product,Integer> cart = null;
        if (cartObject!=null){
            cart = (Map<Product, Integer>) cartObject;
        }else {
            cart = new HashMap<Product, Integer>();
            request.getSession().setAttribute("cart",cart);
        }
        if (cart.containsKey(product)){
            cart.put(product,cart.get(product)+1);
        }else {
            cart.put(product,1);
        }
        return "cart";
    }

    /**
     * 删除购物车中的商品
     * @param prodId 商品ID
     * @param request 请求参数 从session域中获取购物车对象
     * @return 转向购物车页面
     */
    @RequestMapping(value = "/deletecart/{prodId}")
    public String deleteCart(@PathVariable Long prodId,
                             HttpServletRequest request){
        Object cartObj = request.getSession().getAttribute("cart");
        if (cartObj==null){
            return "productlist";
        }
        Map<Product,Integer> cart = (Map<Product, Integer>) cartObj;
        Product product = productService.getProductById(prodId);
        Product product1 = new Product();
        product1.setProduct_Name(product.getProduct_Name());
        cart.remove(product1);
        return "cart";
    }

    /**
     * 修改购物车商品数量
     * @param prodId 商品ID
     * @param prodNum 商品数量
     * @param request 请求参数 从session域中获取购物车对象,并在商品数量超出库存时向前台返回错误信息
     * @return 转向购物车页面
     */
    @RequestMapping(value = "/editcart/{prodId}/{prodNum}")
    public String editCart(@PathVariable Long prodId,
                           @PathVariable Integer prodNum,
                           HttpServletRequest request){
        Object cartObj = request.getSession().getAttribute("cart");
        if (cartObj==null){
            return "productlist";
        }
        if (prodNum>productService.getProductById(prodId).getProduct_Stock()){
            request.setAttribute("msg","库存数量不足");
            return "cart";
        }
        Map<Product,Integer> cart = (Map<Product, Integer>) cartObj;
        Product product = productService.getProductById(prodId);
        Product product1 = new Product();
        product1.setProduct_Name(product.getProduct_Name());
        cart.put(product1,prodNum);
        return "cart";
    }

    @RequestMapping(value = "/updatecart/{prodId}/{buyNum}")
    public String updateCart(@PathVariable Long prodId,
                             @PathVariable Integer buyNum,
                             HttpServletRequest request){
        Product prod = productService.getProductById(prodId);
        Object cartObject = request.getSession().getAttribute("cart");
        Map<Product,Integer> cart = null;
        if (cartObject!=null){
            cart = (Map<Product, Integer>) cartObject;
        }else {
            cart = new HashMap<Product, Integer>();
            request.getSession().setAttribute("cart",cart);
        }
        if(buyNum < 0){
            cart.remove(prod);
        }else{
            cart.put(prod, cart.containsKey(prod) ?
                    cart.get(prod) + buyNum : buyNum);
        }
        return "cart";
    }

    @RequestMapping(value = "/checkprodstock/{prodId}/{buyNum}",method = RequestMethod.GET)
    public void checkProductStock(@PathVariable Long prodId,
                                  @PathVariable Integer buyNum,
                                  HttpServletResponse response){
        if (buyNum >= productService.getProductById(prodId).getProduct_Stock()){
            try {
                response.getWriter().write("false");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else {
            try {
                response.getWriter().write("true");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 打开订单添加页
     * @param request 请求参数 从session域中获取登陆的user对象,并向前台发送用户的收货信息列表
     * @return 如果用户未登录,转向用户登陆页面,否则转向订单添加页
     */
    @RequestMapping(value = "/orderaddpage")
    public String orderAddPage(HttpServletRequest request){
        Object objectUser = request.getSession().getAttribute(CommonConstant.USER_CONTEXT);
        if (objectUser==null){
            return "../../login";
        }
        User user = (User) objectUser;
        request.setAttribute("recives",userService.getFullUserById(user.getUser_Id()).getRecives());
        return "add/order_add";
    }

    /**
     * 打开我的购物车页
     * @return 转向购物车页面
     */
    @RequestMapping(value = "/mycart")
    public String myCartPage(){
        return "cart";
    }
}
