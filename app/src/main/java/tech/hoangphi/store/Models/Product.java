package tech.hoangphi.store.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Product implements Serializable {

@SerializedName("id")
@Expose
private Integer id;
@SerializedName("name")
@Expose
private String name;
@SerializedName("price")
@Expose
private Integer price;
@SerializedName("description")
@Expose
private String description;
@SerializedName("images")
@Expose
private String images;
@SerializedName("screen")
@Expose
private String screen;
@SerializedName("os")
@Expose
private String os;
@SerializedName("front_camera")
@Expose
private String frontCamera;
@SerializedName("back_camera")
@Expose
private String backCamera;
@SerializedName("cpu")
@Expose
private String cpu;
@SerializedName("rom")
@Expose
private String rom;
@SerializedName("ram")
@Expose
private String ram;
@SerializedName("color")
@Expose
private String color;
@SerializedName("battery")
@Expose
private String battery;
@SerializedName("count")
@Expose
private Integer count;
@SerializedName("sale")
@Expose
private Integer sale;
@SerializedName("brand_id")
@Expose
private Integer brandId;

public Integer getId() {
return id;
}

public void setId(Integer id) {
this.id = id;
}

public String getName() {
return name;
}

public void setName(String name) {
this.name = name;
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

public String getScreen() {
return screen;
}

public void setScreen(String screen) {
this.screen = screen;
}

public String getOs() {
return os;
}

public void setOs(String os) {
this.os = os;
}

public String getFrontCamera() {
return frontCamera;
}

public void setFrontCamera(String frontCamera) {
this.frontCamera = frontCamera;
}

public String getBackCamera() {
return backCamera;
}

public void setBackCamera(String backCamera) {
this.backCamera = backCamera;
}

public String getCpu() {
return cpu;
}

public void setCpu(String cpu) {
this.cpu = cpu;
}

public String getRom() {
return rom;
}

public void setRom(String rom) {
this.rom = rom;
}

public String getRam() {
return ram;
}

public void setRam(String ram) {
this.ram = ram;
}

public String getColor() {
return color;
}

public void setColor(String color) {
this.color = color;
}

public String getBattery() {
return battery;
}

public void setBattery(String battery) {
this.battery = battery;
}

public Integer getCount() {
return count;
}

public void setCount(Integer count) {
this.count = count;
}

public Integer getSale() {
return sale;
}

public void setSale(Integer sale) {
this.sale = sale;
}

public Integer getBrandId() {
return brandId;
}

public void setBrandId(Integer brandId) {
this.brandId = brandId;
}

}