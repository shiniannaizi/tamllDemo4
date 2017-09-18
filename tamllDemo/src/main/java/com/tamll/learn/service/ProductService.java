package com.tamll.learn.service;

import com.tamll.learn.dao.ProductMapping;
import com.tamll.learn.entiy.Category;
import com.tamll.learn.entiy.Product;
import com.tamll.learn.redis.JedisClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 商品服务层
 */
@Service
public class ProductService {

    @Autowired
    @SuppressWarnings("SpringJavaAutowiringInspection")
    private ProductMapping productMapping;

    @Autowired
    private JedisClient jedisClient;

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

        productMapping.insert(product);
    }

    /**
     * 通过商品ID获取商品
     * @param productId 商品ID
     * @return 返回商品对象
     */
    public Product getProductById(long productId){
        try {
            //添加缓存逻辑
            //从缓存中取商品，商品id对应的信息
            Object o = jedisClient.get(REDIS_ITEM_KEY+":"+productId+":base");
            if(o!=null) {
                //把Object对象转化为Product
                return (Product) o;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        Product product = productMapping.selectProductById(productId);

        //把商品信息写入缓存
        try {
            jedisClient.set(REDIS_ITEM_KEY+":"+productId+":base", product);
            //设置key的有效期
            jedisClient.expire(REDIS_ITEM_KEY+":"+productId+":base", REDIS_ITEM_EXPIRE);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return product;
    }

    /**
     * 获取所有的商品
     * @param map 查询条件map
     * @return 返回商品列表
     */
    public List<Product> getAllProduct(Map<String,Object> map){
        try {
            //添加缓存逻辑
            //从缓存中取商品列表
            List<Product> list = (List<Product>) jedisClient.getList(REDIS_ITEM_KEY+":productList:base");

            if(list!=null)
            {
                return list;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        List<Product> productList = productMapping.selectAllProduct(map);

        //把商品列表写入缓存
        try {
            jedisClient.setList(REDIS_ITEM_KEY+":productList:base", productList);
            //设置key的有效期
            jedisClient.expire(REDIS_ITEM_KEY+":productList:base", REDIS_ITEM_EXPIRE);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return productList;
    }

    /**
     * 通过商品ID获取商品及其关联对象
     * @param productId 商品ID
     * @return 返回一个商品对象
     */
    public Product getFullProductById(long productId){
        try {
            if(jedisClient.get(REDIS_ITEM_KEY+":"+productId+":full")!=null) {
                return (Product) jedisClient.get(REDIS_ITEM_KEY+":"+productId+":full");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        Product product = productMapping.selectFullProductById(productId);
        try {
            jedisClient.set(REDIS_ITEM_KEY+":"+productId+":full",product);
            jedisClient.expire(REDIS_ITEM_KEY+":"+productId+":full",REDIS_ITEM_EXPIRE);
        } catch (Exception e){
            e.printStackTrace();
        }
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
            jedisClient.set(REDIS_ITEM_KEY+":"+productId+":base",product);
            jedisClient.expire(REDIS_ITEM_KEY+":"+productId+":base",REDIS_ITEM_EXPIRE);
            jedisClient.set(REDIS_ITEM_KEY+":"+productId+":full",product);
            jedisClient.expire(REDIS_ITEM_KEY+":"+productId+":full",REDIS_ITEM_EXPIRE);
        } catch (Exception e){
            e.printStackTrace();
        }
        productMapping.updateProductById(product);
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
            jedisClient.set(REDIS_ITEM_KEY+":"+productId+":base",product);
            jedisClient.expire(REDIS_ITEM_KEY+":"+productId+":base",REDIS_ITEM_EXPIRE);
            jedisClient.set(REDIS_ITEM_KEY+":"+productId+":full",product);
            jedisClient.expire(REDIS_ITEM_KEY+":"+productId+":full",REDIS_ITEM_EXPIRE);
        } catch (Exception e){
            e.printStackTrace();
        }
        productMapping.updateProductById(product);
    }

    /**
     * 商品状态更新
     * @param product 商品对象
     */
    public void updateProductStock(Product product){
        try {
            jedisClient.set(REDIS_ITEM_KEY+":"+product.getProduct_Id()+":base",product);
            jedisClient.expire(REDIS_ITEM_KEY+":"+product.getProduct_Id()+":base",REDIS_ITEM_EXPIRE);
            jedisClient.set(REDIS_ITEM_KEY+":"+product.getProduct_Id()+":full",product);
            jedisClient.expire(REDIS_ITEM_KEY+":"+product.getProduct_Id()+":full",REDIS_ITEM_EXPIRE);
        } catch (Exception e){
            e.printStackTrace();
        }
        productMapping.updateProductStockById(product);
    }

    /**
     * 通过ID删除商品
     * @param productId 商品ID
     */
    public void deleteProductById(long productId){
        try {
            jedisClient.del(REDIS_ITEM_KEY+":"+productId+":base");
            jedisClient.del(REDIS_ITEM_KEY+":"+productId+":full");
        } catch (Exception e){
            e.printStackTrace();
        }
        productMapping.deleteProductById(productId);
    }
}
