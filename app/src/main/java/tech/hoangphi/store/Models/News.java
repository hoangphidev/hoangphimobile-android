package tech.hoangphi.store.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class News implements Serializable {

@SerializedName("id")
@Expose
private Integer id;
@SerializedName("title")
@Expose
private String title;
@SerializedName("summary")
@Expose
private String summary;
@SerializedName("body")
@Expose
private String body;
@SerializedName("images")
@Expose
private String images;
@SerializedName("view")
@Expose
private Integer view;
@SerializedName("hot")
@Expose
private Integer hot;
@SerializedName("brand_id")
@Expose
private Integer brandId;
@SerializedName("created_at")
@Expose
private String createdAt;

public Integer getId() {
return id;
}

public void setId(Integer id) {
this.id = id;
}

public String getTitle() {
return title;
}

public void setTitle(String title) {
this.title = title;
}

public String getSummary() {
return summary;
}

public void setSummary(String summary) {
this.summary = summary;
}

public String getBody() {
return body;
}

public void setBody(String body) {
this.body = body;
}

public String getImages() {
return images;
}

public void setImages(String images) {
this.images = images;
}

public Integer getView() {
return view;
}

public void setView(Integer view) {
this.view = view;
}

public Integer getHot() {
return hot;
}

public void setHot(Integer hot) {
this.hot = hot;
}

public Integer getBrandId() {
return brandId;
}

public void setBrandId(Integer brandId) {
this.brandId = brandId;
}

public String getCreatedAt() {
return createdAt;
}

public void setCreatedAt(String createdAt) {
this.createdAt = createdAt;
}

}