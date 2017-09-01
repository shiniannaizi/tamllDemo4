package com.tamll.learn.controller;

import com.tamll.learn.entiy.Category;
import com.tamll.learn.entiy.Product;
import com.tamll.learn.entiy.ProductImage;
import com.tamll.learn.entiy.PropertyValue;
import com.tamll.learn.service.CategoryService;
import com.tamll.learn.service.ProductService;
import com.tamll.learn.service.PropertyService;
import com.tamll.learn.service.PropertyValueService;
import com.tamll.learn.utils.IDUtils;
import com.tamll.learn.utils.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

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

    @RequestMapping(value = "/backend/addProd")
    public String addProductPage(HttpServletRequest request){
        request.setAttribute("categorys",categoryService.getAllCategory());
        return "/add/addproduct";
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }

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
        String product_First_Image = null;
        Category category = categoryService.getCategoryById(Integer.parseInt(product_Category));
        try {
            List<MultipartFile> files = request.getFiles("prod_first_image");
            for (int i = 0; i < files.size(); i++) {
                if (!files.get(i).isEmpty()) {
                    byte[] bytes = files.get(i).getBytes();
                    String srcFileName = files.get(i).getOriginalFilename();
                    String fileExt = srcFileName.substring(srcFileName
                            .lastIndexOf('.'));
                    String fileName = IDUtils.getImageName() + fileExt;
                    String fullFilePath =
                            "E:/Ideaprojects/tamllDemo/src/main/webapp/WEB-INF/uploads/" + fileName;
                    FileOutputStream fos = new FileOutputStream(fullFilePath); // 写入文件
                    fos.write(bytes);
                    fos.close();
                    product_First_Image = fileName;
                }
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        productService.insertProduct(product_Id,product_Name,product_Orign_Price,product_Promote_Price,
                product_Stock,product_Create_Date,product_Update_Date,product_Subtitle,product_First_Image,
                category,product_Status);
        return "redirect:/productinfo/"+product_Id;
    }

    @RequestMapping(value = "/productinfo/{prodId}")
    public String productInfo(@PathVariable Long prodId,
                              HttpServletRequest request){
        request.setAttribute("product",productService.getProductById(prodId));
        return "productinfo";
    }

    @RequestMapping(value = "/productlist")
    public String productList(HttpServletRequest request){
        request.setAttribute("productlist",productService.getAllProduct());
        return "productlist";
    }

    @RequestMapping(value = "/backend/prodlist")
    public String backendProdList(HttpServletRequest request){
        request.setAttribute("productlist",productService.getAllProduct());
        return "/back/prod/prodlist";
    }

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

    @RequestMapping(value = "/backend/addproperty/{prodId}")
    public String addPropertyPage(@PathVariable Long prodId,
                                  HttpServletRequest request){
        request.setAttribute("propertys",propertyService.getAllProperty());
        request.setAttribute("productId",prodId);
        return "back/prod/addproperty";
    }

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
}
