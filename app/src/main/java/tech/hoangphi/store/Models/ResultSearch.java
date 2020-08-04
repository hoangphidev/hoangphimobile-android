package tech.hoangphi.store.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class ResultSearch implements Serializable {

@SerializedName("message")
@Expose
private Boolean message;
@SerializedName("oder_id")
@Expose
private Integer oderId;
@SerializedName("name")
@Expose
private String name;
@SerializedName("phone")
@Expose
private String phone;
@SerializedName("product_name")
@Expose
private String productName;
@SerializedName("price")
@Expose
private Integer price;
@SerializedName("description")
@Expose
private String description;
@SerializedName("images")
@Expose
private String images;
@SerializedName("status")
@Expose
private Integer status;
@SerializedName("date")
@Expose
private String date;

public Boolean getMessage() {
return message;
}

public void setMessage(Boolean message) {
this.message = message;
}

public Integer getOderId() {
return oderId;
}

public void setOderId(Integer oderId) {
this.oderId = oderId;
}

public String getName() {
return name;
}

public void setName(String name) {
this.name = name;
}

public String getPhone() {
return phone;
}

public void setPhone(String phone) {
this.phone = phone;
}

public String getProductName() {
return productName;
}

public void setProductName(String productName) {
this.productName = productName;
}

public Integer getPrice() {
return price;
}

public void setPrice(Integer price) {
this.price = price;
}

public String getDescription() {
return description;
}

public void setDescription(String description) {
this.description = description;
}

public String getImages() {
return images;
}

public void setImages(String images) {
this.images = images;
}

public Integer getStatus() {
return status;
}

public void setStatus(Integer status) {
this.status = status;
}

public String getDate() {
return date;
}

public void setDate(String date) {
this.date = date;
}

}