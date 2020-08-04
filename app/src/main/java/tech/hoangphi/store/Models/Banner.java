package tech.hoangphi.store.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Banner implements Serializable {

@SerializedName("id")
@Expose
private Integer id;
@SerializedName("title")
@Expose
private String title;
@SerializedName("images")
@Expose
private String images;
@SerializedName("description")
@Expose
private String description;

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

public String getImages() {
return images;
}

public void setImages(String images) {
this.images = images;
}

public String getDescription() {
return description;
}

public void setDescription(String description) {
this.description = description;
}

}