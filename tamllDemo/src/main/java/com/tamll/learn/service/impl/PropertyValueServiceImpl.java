package com.tamll.learn.service.impl;


import com.tamll.learn.dao.ProductMapping;
import com.tamll.learn.dao.PropertyMapping;
import com.tamll.learn.dao.PropertyValueMapping;
import com.tamll.learn.entiy.Product;
import com.tamll.learn.entiy.Property;
import com.tamll.learn.entiy.PropertyValue;
import com.tamll.learn.redis.JedisClient;
import com.tamll.learn.service.PropertyValueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 商品属性值服务层
 */
@Service
public class PropertyValueServiceImpl implements PropertyValueService{

    @Autowired
    @SuppressWarnings("SpringJavaAutowiringInspection")
    private PropertyValueMapping propertyValueMapping;

    @Autowired
    @SuppressWarnings("SpringJavaAutowiringInspection")
    private ProductMapping productMapping;

    @Autowired
    @SuppressWarnings("SpringJavaAutowiringInspection")
    private PropertyMapping propertyMapping;

    @Autowired
    private JedisClient jedisClient;

    @Value("${REDIS_ITEM_KEY}")
    private String REDIS_ITEM_KEY;
    @Value("${REDIS_ITEM_EXPIRE}")
    private Integer REDIS_ITEM_EXPIRE;

    /**
     * 通过ID获取属性值及其关联对象
     * @param propertyValueId 属性值ID
     * @return 返回一个属性值对象
     */
    public PropertyValue getFullPropertyValueById(Integer propertyValueId){
        return propertyValueMapping.selectFullePropertyValue(propertyValueId);
    }

    /**
     * 插入一条属性值记录
     * @param productId 商品名称
     * @param propertyName 属性名称
     * @param propertyValue 属性值
     */
    public void insert(Long productId,String propertyName,String propertyValue){
        PropertyValue value = new PropertyValue();
        value.setProperty_Value_Value(propertyValue);
        Product product = productMapping.selectFullProductById(productId);
        Property property = propertyMapping.selectPropertyByName(propertyName);
        List<PropertyValue> list = propertyValueMapping.selectFullPropertyValueByProductId(productId);
        for (PropertyValue propertyValue1:list){
            if (propertyValue1.getProperty().getProperty_Name().equals(propertyName)){
                propertyValueMapping.updatePropertyValueByProductIdAndPropertyId(propertyValue,
                        productId,propertyValue1.getProperty().getProperty_Id());
                try {
                    jedisClient.set(REDIS_ITEM_KEY+":"+productId+":base",
                            productMapping.selectProductById(productId));
                    jedisClient.expire(REDIS_ITEM_KEY+":"+productId+":base",REDIS_ITEM_EXPIRE);
                    jedisClient.set(REDIS_ITEM_KEY+":"+productId+":full",
                            productMapping.selectFullProductById(productId));
                    jedisClient.expire(REDIS_ITEM_KEY+":"+productId+":full",REDIS_ITEM_EXPIRE);
                } catch (Exception e){
                    e.printStackTrace();
                }
            }else {
                value.setProperty(property);
                value.setProduct(product);
                propertyValueMapping.insert(value);
            }
        }
    }

    /**
     * 通过商品ID删除属性值
     * @param productId 商品ID
     */
    public void deletePropertyValueByProductId(long productId){
        propertyValueMapping.deletePropertyValueByProductId(productId);
    }
}
