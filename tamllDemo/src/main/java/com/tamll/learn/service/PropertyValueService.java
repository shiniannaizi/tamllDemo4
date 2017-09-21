package com.tamll.learn.service;

import com.tamll.learn.entiy.PropertyValue;

public interface PropertyValueService {

    public PropertyValue getFullPropertyValueById(Integer propertyValueId);

    public void insert(Long productId,String propertyName,String propertyValue);

    public void deletePropertyValueByProductId(long productId);
}
