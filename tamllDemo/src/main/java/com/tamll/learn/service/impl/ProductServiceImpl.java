package com.tamll.learn.service.impl;

import com.tamll.learn.dao.ProductImageMapping;
import com.tamll.learn.dao.ProductMapping;
import com.tamll.learn.dao.PropertyValueMapping;
import com.tamll.learn.entiy.Category;
import com.tamll.learn.entiy.Product;
import com.tamll.learn.utils.RedisUtils;
import com.tamll.learn.service.ProductService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 商品服务层
 */
@Service
public class ProductServiceImpl implements ProductService {

    private Logger logger = Logger.getLogger(this.getClass());

    @Autowired
    private RedisUtils redisUtil;

    @Autowired
    @SuppressWarnings("SpringJavaAutowiringInspection")
    private ProductMapping productMapping;

    @Autowired
    @SuppressWarnings("SpringJavaAutowiringInspection")
    private ProductImageMapping productImageMapping;

    @Autowired
    @SuppressWarnings("SpringJavaAutowiringInspection")
    private PropertyValueMapping propertyValueMapping;

    @Value("${REDIS_ITEM_KEY}")
    private String REDIS_ITEM_KEY;
    @Value("${REDIS_ITEM_EXPIRE}")
    private Integer REDIS_ITEM_EXPIRE;

    /**
     * 插入一条商品记录
     * @param prodId 商品ID
     * @param prodName 商品名称
     * @param prodOP 商品价格
     * @param prodPP 促销价
     * @param prodStock 商品库存
     * @param prodCD 商品生产日期
     * @param prodUD 商品更新日期
     * @param prodSubTitle 商品简介
     * @param prodFI 商品封面图片
     * @param category 商品分类对象
     * @param prodStatus 商品状态
     */
    @Transactional
    public void insertProduct(long prodId, String prodName, double prodOP, double prodPP, Integer prodStock,
                              Date prodCD, Date prodUD, String prodSubTitle, String prodFI, Category category,
                              Integer prodStatus){
        Product product = new Product();
        product.setProduct_Id(prodId);
        product.setProduct_Name(prodName);
        product.setProduct_Orignal_Price(prodOP);
        product.setProduct_Promote_Price(prodPP);
        product.setProduct_Stock(prodStock);
        product.setProduct_Create_Date(prodCD);
        product.setProduct_Update_Date(prodUD);
        product.setProduct_Subtitle(prodSubTitle);
        product.setProduct_First_Image(prodFI);
        product.setCategory(category);
        product.setProduct_Status(prodStatus);
        try {
            productMapping.insert(product);
        } catch (Exception e){
            logger.info("商品添加接口,添加异常,异常信息:"+e);
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }

    }

    /**
     * 通过商品ID获取商品
     * @param productId 商品ID
     * @return 返回商品对象
     */
    public Product getProductById(long productId){
        //添加缓存逻辑
        //从缓存中取商品，商品id对应的信息
        Object o = redisUtil.getRedis(REDIS_ITEM_KEY+":"+productId+":base");
        if (o!=null){
            //把Object对象转化为Product
            return (Product) o;
        }
        Product product = productMapping.selectProductById(productId);

        //把商品信息写入缓存
        redisUtil.setRedies(REDIS_ITEM_KEY+":"+productId+":base",
                product,REDIS_ITEM_EXPIRE);
        return product;
    }

    /**
     * 获取所有的商品
     * @param map 查询条件map
     * @return 返回商品列表
     */
    public List<Product> getAllProduct(Map<String,Object> map){
        if (map.get("product_Name")!=null||map.get("category")!=null||map.get("minPrice")!=null
                ||map.get("maxPrice")!=null){
            return productMapping.selectAllProduct(map);
        }
        //添加缓存逻辑
        //从缓存中取商品列表
        List<Product> list = (List<Product>) redisUtil.getRedisList(REDIS_ITEM_KEY+":productList:base");
        if(list!=null)
        {
            return list;
        }
        List<Product> productList = productMapping.selectAllProduct(map);

        //把商品列表写入缓存
        //设置key的有效期
        redisUtil.setRedisList(REDIS_ITEM_KEY+":productList:base",productList,REDIS_ITEM_EXPIRE);

        return productList;
    }

    /**
     * 通过商品ID获取商品及其关联对象
     * @param productId 商品ID
     * @return 返回一个商品对象
     */
    public Product getFullProductById(long productId){
        Object o = redisUtil.getRedis(REDIS_ITEM_KEY+":"+productId+":full");
        if (o!=null){
            return (Product) o;
        }
        Product product = productMapping.selectFullProductById(productId);
        redisUtil.setRedies(REDIS_ITEM_KEY+":"+productId+":full",
                product,REDIS_ITEM_EXPIRE);

        return product;
    }

    /**
     * 商品更新
     * @param productId 商品ID
     * @param prodName 商品名
     * @param prodOP 商品价格
     * @param prodPP 促销价
     * @param prodStock 商品库存
     * @param prodUD 商品更新日期
     * @param prodSubTitle 商品简介
     * @param prodFI 商品封面
     * @param prodStatus 商品状态
     * @param category 商品分类对象
     */
    @Transactional
    public void updateProductById(long productId,String prodName, double prodOP, double prodPP, Integer prodStock,
                                  Date prodUD, String prodSubTitle, String prodFI ,Integer prodStatus,
                                  Category category){
        Product product = productMapping.selectProductById(productId);
        product.setProduct_Name(prodName);
        product.setProduct_Orignal_Price(prodOP);
        product.setProduct_Promote_Price(prodPP);
        product.setProduct_Stock(prodStock);
        product.setProduct_Update_Date(prodUD);
        product.setProduct_Subtitle(prodSubTitle);
        product.setProduct_Status(prodStatus);
        product.setProduct_First_Image(prodFI);
        product.setCategory(category);
        try {
            productMapping.updateProductById(product);
        }catch (Exception e){
            logger.info("商品更新接口,更新异常,异常信息:"+e);
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
        redisUtil.setRedies(REDIS_ITEM_KEY+":"+productId+":base",
                productMapping.selectFullProductById(productId),REDIS_ITEM_EXPIRE);
        redisUtil.setRedies(REDIS_ITEM_KEY+":"+productId+":full",
                productMapping.selectFullProductById(productId),REDIS_ITEM_EXPIRE);
        Map<String,Object> map = new HashMap<String, Object>();
        map.put("product_Name",null);
        map.put("category",null);
        map.put("minPrice",null);
        map.put("maxPrice",null);
        redisUtil.setRedisList(REDIS_ITEM_KEY+":productList:base",
                productMapping.selectAllProduct(map),REDIS_ITEM_EXPIRE);
    }

    /**
     * 商品更新方法重载
     * @param productId 商品ID
     * @param prodName 商品名
     * @param prodOP 商品价格
     * @param prodPP 促销价
     * @param prodStock 商品库存
     * @param prodUD 商品更新日期
     * @param prodSubTitle 商品简介
     * @param prodStatus 商品状态
     * @param category 商品分类对象
     */
    public void updateProductById(long productId,String prodName, double prodOP, double prodPP, Integer prodStock,
                                  Date prodUD, String prodSubTitle, Integer prodStatus, Category category){
        Product product = productMapping.selectProductById(productId);
        product.setProduct_Name(prodName);
        product.setProduct_Orignal_Price(prodOP);
        product.setProduct_Promote_Price(prodPP);
        product.setProduct_Stock(prodStock);
        product.setProduct_Update_Date(prodUD);
        product.setProduct_Subtitle(prodSubTitle);
        product.setProduct_Status(prodStatus);
        product.setCategory(category);
        try {
            productMapping.updateProductById(product);
        }catch (Exception e){
            logger.info("商品更新接口,更新异常,异常信息:"+e);
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
        redisUtil.setRedies(REDIS_ITEM_KEY+":"+productId+":base",
                productMapping.selectFullProductById(productId),REDIS_ITEM_EXPIRE);
        redisUtil.setRedies(REDIS_ITEM_KEY+":"+productId+":full",
                productMapping.selectFullProductById(productId),REDIS_ITEM_EXPIRE);
        Map<String,Object> map = new HashMap<String, Object>();
        map.put("product_Name",null);
        map.put("category",null);
        map.put("minPrice",null);
        map.put("maxPrice",null);
        redisUtil.setRedisList(REDIS_ITEM_KEY+":productList:base",
                productMapping.selectAllProduct(map),REDIS_ITEM_EXPIRE);
    }

    /**
     * 商品状态更新
     * @param product 商品对象
     */
    public void updateProductStock(Product product){
        try {
            productMapping.updateProductStockById(product);
        }catch (Exception e){
            logger.info("商品状态更新接口,更新异常,异常信息:"+e);
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
        redisUtil.setRedies(REDIS_ITEM_KEY+":"+product.getProduct_Id()+":base",
                productMapping.selectFullProductById(product.getProduct_Id()),REDIS_ITEM_EXPIRE);
        redisUtil.setRedies(REDIS_ITEM_KEY+":"+product.getProduct_Id()+":full",
                productMapping.selectFullProductById(product.getProduct_Id()),REDIS_ITEM_EXPIRE);
        Map<String,Object> map = new HashMap<String, Object>();
        map.put("product_Name",null);
        map.put("category",null);
        map.put("minPrice",null);
        map.put("maxPrice",null);
        redisUtil.setRedisList(REDIS_ITEM_KEY+":productList:base",
                productMapping.selectAllProduct(map),REDIS_ITEM_EXPIRE);
    }

    /**
     * 通过ID删除商品
     * @param productId 商品ID
     */
    public void deleteProductById(long productId){
        try {
            productMapping.deleteProductById(productId);
            productImageMapping.deleteProductImageByProductId(productId);
            propertyValueMapping.deletePropertyValueByProductId(productId);
        }catch (Exception e){
            logger.info("商品删除接口,删除异常,异常信息:"+e);
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
        redisUtil.delRedis(REDIS_ITEM_KEY+":"+productId+":base");
        redisUtil.delRedis(REDIS_ITEM_KEY+":"+productId+":full");

        Map<String,Object> map = new HashMap<String, Object>();
        map.put("product_Name",null);
        map.put("category",null);
        map.put("minPrice",null);
        map.put("maxPrice",null);
        redisUtil.setRedisList(REDIS_ITEM_KEY+":productList:base",
                productMapping.selectAllProduct(map),REDIS_ITEM_EXPIRE);
    }
}
