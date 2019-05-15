package com.sbw.atrue.Order.Entity;

import java.io.Serializable;

/**
 * ClassName: Food <br>
 * description: 菜品的实体类。定义序列化是为了用于Intent直接传递对象 <br>
 * author: 沈滨伟-13042299081 <br>
 * Date: 2019/2/18 22:08
 */
public class Food implements Serializable {
    //菜品名称
    private String name;
    /**
     * 菜品图像的存储路径，如“R.drawable.mango”
     */
    private int imageId;
    //菜品描述
    private String description;

    public Food(String name, int imageId,String description) {
        this.name = name;
        this.imageId = imageId;
        this.description=description;
    }

    public String getName() {
        return name;
    }

    public int getImageId() {
        return imageId;
    }

    public  String getDescription(){return description;}

}