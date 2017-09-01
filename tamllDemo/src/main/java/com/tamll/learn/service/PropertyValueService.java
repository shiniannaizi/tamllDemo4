package com.tamll.learn.service;


import com.tamll.learn.dao.ProductMapping;
import com.tamll.learn.dao.PropertyMapping;
import com.tamll.learn.dao.PropertyValueMapping;
import com.tamll.learn.entiy.Product;
import com.tamll.learn.entiy.Property;
import com.tamll.learn.entiy.PropertyValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PropertyValueService {

    @Autowired
    @SuppressWarnings("SpringJavaAutowiringInspection")
    private PropertyValueMapping propertyValueMapping;

    @Autowired
    @SuppressWarnings("SpringJavaAutowiringInspection")
    private ProductMapping productMapping;

    @Autowired
    @SuppressWarnings("SpringJavaAutowiringInspection")
    private PropertyMapping propertyMapping;

    public PropertyValue getFullPropertyValueById(Integer propertyValueId){
        return propertyValueMapping.selectFullePropertyValue(propertyValueId);
    }

    public void insert(Long productId,String propertyName,String propertyValue){
        PropertyValue value = new PropertyValue();
        value.setProperty_Value_Value(propertyValue);
        Product product = productMapping.selectFullProductById(productId);
        Property property = propertyMapping.selectPropertyByName(propertyName);
        value.setProperty(property);
        value.setProduct(product);
        propertyValueMapping.insert(value);
    }
}
