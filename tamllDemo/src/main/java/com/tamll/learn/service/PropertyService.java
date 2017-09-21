package com.tamll.learn.service;

import com.tamll.learn.entiy.Property;

import java.util.List;

public interface PropertyService {

    public List<Property> getAllProperty();

    public Property getPropertyByName(String propertyName);
}
