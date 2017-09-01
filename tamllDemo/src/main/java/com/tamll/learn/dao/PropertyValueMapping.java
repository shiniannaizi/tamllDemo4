package com.tamll.learn.dao;


import com.tamll.learn.entiy.PropertyValue;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 属性值数据库操作接口
 */
public interface PropertyValueMapping {

    int insert(PropertyValue propertyValue);

    List<PropertyValue> selectPropertyValueByProductId(long productId);

    List<PropertyValue> selectPropertyValueByPropertyId(Integer propertyId);

    PropertyValue selectFullePropertyValue(Integer propertyValueId);

    int updatePropertyValueByProductIdAndPropertyId(@Param("productId")long productId,
                                                    @Param("propertyId")Integer propertyId);

    int deletePropertyValueByProductIdAndPropertyId(@Param("productId")long productId,
                                                    @Param("propertyId")Integer propertyId);
}
