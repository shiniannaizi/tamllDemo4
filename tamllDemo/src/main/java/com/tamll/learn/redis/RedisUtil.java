package com.tamll.learn.redis;

import com.tamll.learn.entiy.Product;
import com.tamll.learn.utils.IDUtils;
import com.tamll.learn.utils.SerializeUtils;
import redis.clients.jedis.Jedis;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class RedisUtil {
    public static void main(String[] args) {
        Product product = new Product();
        product.setProduct_Id(IDUtils.getProductId());
        product.setProduct_Name("aaa");
        product.setProduct_Stock(100);
        product.setProduct_Orignal_Price(100.0);
        product.setProduct_Update_Date(new Date());
        product.setProduct_Create_Date(new Date());

        Product product1 = new Product();
        product1.setProduct_Id(IDUtils.getProductId());
        product1.setProduct_Name("bbbb");

        Product product2 = new Product();
        product2.setProduct_Id(IDUtils.getProductId());
        product2.setProduct_Name("cccc");

        List<Product> list = new ArrayList<Product>();
        list.add(product);
        list.add(product1);
        list.add(product2);

        JedisClient jedisClient = new JedisClientSingle();

        jedisClient.setList("prodlist",list);
        List<Product> productList = (List<Product>) jedisClient.getList("prodlist");

        for (Product p:productList){
            System.out.println(p.getProduct_Id());
            System.out.println(p.getProduct_Name());
        }

        System.out.println(jedisClient.del("prodlist"));
    }
}
