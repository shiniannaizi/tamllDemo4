package com.tamll.learn.service;

import com.tamll.learn.dao.PropertyMapping;
import com.tamll.learn.entiy.Property;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PropertyService {

    @Autowired
    @SuppressWarnings("SpringJavaAutowiringInspection")
    private PropertyMapping propertyMapping;

    public List<Property> getAllProperty(){
        return propertyMapping.selectPropertyList();
    }

    public Property getPropertyByName(String propertyName){
        return propertyMapping.selectPropertyByName(propertyName);
    }
}
