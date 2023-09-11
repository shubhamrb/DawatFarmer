package com.dawat.farmer.mamits.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class FarmerBasicDetails implements Serializable {

    @SerializedName("id")
    String id;

    @SerializedName("farmer_code")
    String farmer_code = "";

    @SerializedName("farmer_type")
    String farmer_type= "";

    @SerializedName("father_name")
    String father_name= "";

    @SerializedName("name")
    String name= "";

    @SerializedName("email")
    String email= "";

    @SerializedName("mobile")
    String mobile= "";

    @SerializedName("gender")
    String gender= "";

    @SerializedName("profile_image")
    String profile_image= "";

    @SerializedName("created_at")
    String created_at= "";

    @SerializedName("status")
    String status= "";

    @SerializedName("fk_country")
    CountryModel fk_country;

    @SerializedName("fk_state")
    CountryModel fk_state;

    @SerializedName("fk_district")
    CountryModel fk_district;

    @SerializedName("block")
    String block= "";

    @SerializedName("tehseel")
    String tehseel= "";

    @SerializedName("pin_code")
    String pin_code= "";

    @SerializedName("village_name")
    String village_name= "";

    @SerializedName("full_address")
    String full_address= "";

    @SerializedName("aadhaar_no")
    String aadhaar_no= "";

    @SerializedName("pan_no")
    String pan_no= "";

    @SerializedName("bank_name")
    String bank_name= "";

    @SerializedName("bank_account_no")
    String bank_account_no= "";

    @SerializedName("ifsc_code")
    String ifsc_code= "";

    @SerializedName("branch")
    String branch= "";

    @SerializedName("aadhaar_photo_front")
    String aadhaar_photo_front= "";

    @SerializedName("aadhaar_photo_back")
    String aadhaar_photo_back= "";

    @SerializedName("pan_photo")
    String pan_photo= "";

    @SerializedName("bank_passbook_photo")
    String bank_passbook_photo= "";

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFarmer_code() {
        return farmer_code;
    }

    public void setFarmer_code(String farmer_code) {
        this.farmer_code = farmer_code;
    }

    public String getFarmer_type() {
        return farmer_type;
    }

    public void setFarmer_type(String farmer_type) {
        this.farmer_type = farmer_type;
    }

    public String getFather_name() {
        return father_name;
    }

    public void setFather_name(String father_name) {
        this.father_name = father_name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getProfile_image() {
        return profile_image;
    }

    public void setProfile_image(String profile_image) {
        this.profile_image = profile_image;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public CountryModel getFk_country() {
        return fk_country;
    }

    public void setFk_country(CountryModel fk_country) {
        this.fk_country = fk_country;
    }

    public CountryModel getFk_state() {
        return fk_state;
    }

    public void setFk_state(CountryModel fk_state) {
        this.fk_state = fk_state;
    }

    public CountryModel getFk_district() {
        return fk_district;
    }

    public void setFk_district(CountryModel fk_district) {
        this.fk_district = fk_district;
    }

    public String getBlock() {
        return block;
    }

    public void setBlock(String block) {
        this.block = block;
    }

    public String getTehseel() {
        return tehseel;
    }

    public void setTehseel(String tehseel) {
        this.tehseel = tehseel;
    }

    public String getPin_code() {
        return pin_code;
    }

    public void setPin_code(String pin_code) {
        this.pin_code = pin_code;
    }

    public String getVillage_name() {
        return village_name;
    }

    public void setVillage_name(String village_name) {
        this.village_name = village_name;
    }

    public String getFull_address() {
        return full_address;
    }

    public void setFull_address(String full_address) {
        this.full_address = full_address;
    }

    public String getAadhaar_no() {
        return aadhaar_no;
    }

    public void setAadhaar_no(String aadhaar_no) {
        this.aadhaar_no = aadhaar_no;
    }

    public String getPan_no() {
        return pan_no;
    }

    public void setPan_no(String pan_no) {
        this.pan_no = pan_no;
    }

    public String getBank_name() {
        return bank_name;
    }

    public void setBank_name(String bank_name) {
        this.bank_name = bank_name;
    }

    public String getBank_account_no() {
        return bank_account_no;
    }

    public void setBank_account_no(String bank_account_no) {
        this.bank_account_no = bank_account_no;
    }

    public String getIfsc_code() {
        return ifsc_code;
    }

    public void setIfsc_code(String ifsc_code) {
        this.ifsc_code = ifsc_code;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public String getAadhaar_photo_front() {
        return aadhaar_photo_front;
    }

    public void setAadhaar_photo_front(String aadhaar_photo_front) {
        this.aadhaar_photo_front = aadhaar_photo_front;
    }

    public String getAadhaar_photo_back() {
        return aadhaar_photo_back;
    }

    public void setAadhaar_photo_back(String aadhaar_photo_back) {
        this.aadhaar_photo_back = aadhaar_photo_back;
    }

    public String getPan_photo() {
        return pan_photo;
    }

    public void setPan_photo(String pan_photo) {
        this.pan_photo = pan_photo;
    }

    public String getBank_passbook_photo() {
        return bank_passbook_photo;
    }

    public void setBank_passbook_photo(String bank_passbook_photo) {
        this.bank_passbook_photo = bank_passbook_photo;
    }

    @Override
    public String toString() {
        return "{" +
                "id='" + id + '\'' +
                ", farmer_code='" + farmer_code + '\'' +
                ", farmer_type='" + farmer_type + '\'' +
                ", father_name='" + father_name + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", mobile='" + mobile + '\'' +
                ", gender='" + gender + '\'' +
                ", profile_image='" + profile_image + '\'' +
                ", created_at='" + created_at + '\'' +
                ", status='" + status + '\'' +
                ", fk_country=" + fk_country +
                ", fk_state=" + fk_state +
                ", fk_district=" + fk_district +
                ", block='" + block + '\'' +
                ", tehseel='" + tehseel + '\'' +
                ", pin_code='" + pin_code + '\'' +
                ", village_name='" + village_name + '\'' +
                ", full_address='" + full_address + '\'' +
                ", aadhaar_no='" + aadhaar_no + '\'' +
                ", pan_no='" + pan_no + '\'' +
                ", bank_name='" + bank_name + '\'' +
                ", bank_account_no='" + bank_account_no + '\'' +
                ", ifsc_code='" + ifsc_code + '\'' +
                ", branch='" + branch + '\'' +
                ", aadhaar_photo_front='" + aadhaar_photo_front + '\'' +
                ", aadhaar_photo_back='" + aadhaar_photo_back + '\'' +
                ", pan_photo='" + pan_photo + '\'' +
                ", bank_passbook_photo='" + bank_passbook_photo + '\'' +
                '}';
    }
}
