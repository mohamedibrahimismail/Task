package com.example.miestro.task;

/**
 * Created by MIESTRO on 01/02/2018.
 */

public class Contents {
    String product_Description,price,image,height;

    Contents(String product_Description, String price, String image,String height){

        setProduct_Description(product_Description);
        setPrice(price);
        setImage(image);
        setHeight(height);

    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getProduct_Description() {
        return product_Description;
    }

    public void setProduct_Description(String product_Description) {
        this.product_Description = product_Description;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
