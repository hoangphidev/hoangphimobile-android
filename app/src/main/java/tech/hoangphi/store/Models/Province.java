package tech.hoangphi.store.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Province {

@SerializedName("district_id")
@Expose
private String districtId;
@SerializedName("district_name")
@Expose
private String districtName;
@SerializedName("province_id")
@Expose
private String provinceId;

public String getDistrictId() {
return districtId;
}

public void setDistrictId(String districtId) {
this.districtId = districtId;
}

public String getDistrictName() {
return districtName;
}

public void setDistrictName(String districtName) {
this.districtName = districtName;
}

public String getProvinceId() {
return provinceId;
}

public void setProvinceId(String provinceId) {
this.provinceId = provinceId;
}

}