package com.vaidilya.sih;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

//@Generated("jsonschema2pojo")
public class Data {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("deletehash")
    @Expose
    private String deletehash;
    @SerializedName("account_id")
    @Expose
    private Object accountId;
    @SerializedName("account_url")
    @Expose
    private Object accountUrl;
    @SerializedName("ad_type")
    @Expose
    private Object adType;
    @SerializedName("ad_url")
    @Expose
    private Object adUrl;
    @SerializedName("title")
    @Expose
    private Object title;
    @SerializedName("description")
    @Expose
    private Object description;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("width")
    @Expose
    private Integer width;
    @SerializedName("height")
    @Expose
    private Integer height;
    @SerializedName("size")
    @Expose
    private Integer size;
    @SerializedName("views")
    @Expose
    private Integer views;
    @SerializedName("section")
    @Expose
    private Object section;
    @SerializedName("vote")
    @Expose
    private Object vote;
    @SerializedName("bandwidth")
    @Expose
    private Integer bandwidth;
    @SerializedName("animated")
    @Expose
    private Boolean animated;
    @SerializedName("favorite")
    @Expose
    private Boolean favorite;
    @SerializedName("in_gallery")
    @Expose
    private Boolean inGallery;
    @SerializedName("in_most_viral")
    @Expose
    private Boolean inMostViral;
    @SerializedName("has_sound")
    @Expose
    private Boolean hasSound;
    @SerializedName("is_ad")
    @Expose
    private Boolean isAd;
    @SerializedName("nsfw")
    @Expose
    private Object nsfw;
    @SerializedName("link")
    @Expose
    private String link;
    @SerializedName("tags")
    @Expose
    private List<Object> tags = null;
    @SerializedName("datetime")
    @Expose
    private Integer datetime;
    @SerializedName("mp4")
    @Expose
    private String mp4;
    @SerializedName("hls")
    @Expose
    private String hls;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDeletehash() {
        return deletehash;
    }

    public void setDeletehash(String deletehash) {
        this.deletehash = deletehash;
    }

    public Object getAccountId() {
        return accountId;
    }

    public void setAccountId(Object accountId) {
        this.accountId = accountId;
    }

    public Object getAccountUrl() {
        return accountUrl;
    }

    public void setAccountUrl(Object accountUrl) {
        this.accountUrl = accountUrl;
    }

    public Object getAdType() {
        return adType;
    }

    public void setAdType(Object adType) {
        this.adType = adType;
    }

    public Object getAdUrl() {
        return adUrl;
    }

    public void setAdUrl(Object adUrl) {
        this.adUrl = adUrl;
    }

    public Object getTitle() {
        return title;
    }

    public void setTitle(Object title) {
        this.title = title;
    }

    public Object getDescription() {
        return description;
    }

    public void setDescription(Object description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getWidth() {
        return width;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public Integer getViews() {
        return views;
    }

    public void setViews(Integer views) {
        this.views = views;
    }

    public Object getSection() {
        return section;
    }

    public void setSection(Object section) {
        this.section = section;
    }

    public Object getVote() {
        return vote;
    }

    public void setVote(Object vote) {
        this.vote = vote;
    }

    public Integer getBandwidth() {
        return bandwidth;
    }

    public void setBandwidth(Integer bandwidth) {
        this.bandwidth = bandwidth;
    }

    public Boolean getAnimated() {
        return animated;
    }

    public void setAnimated(Boolean animated) {
        this.animated = animated;
    }

    public Boolean getFavorite() {
        return favorite;
    }

    public void setFavorite(Boolean favorite) {
        this.favorite = favorite;
    }

    public Boolean getInGallery() {
        return inGallery;
    }

    public void setInGallery(Boolean inGallery) {
        this.inGallery = inGallery;
    }

    public Boolean getInMostViral() {
        return inMostViral;
    }

    public void setInMostViral(Boolean inMostViral) {
        this.inMostViral = inMostViral;
    }

    public Boolean getHasSound() {
        return hasSound;
    }

    public void setHasSound(Boolean hasSound) {
        this.hasSound = hasSound;
    }

    public Boolean getIsAd() {
        return isAd;
    }

    public void setIsAd(Boolean isAd) {
        this.isAd = isAd;
    }

    public Object getNsfw() {
        return nsfw;
    }

    public void setNsfw(Object nsfw) {
        this.nsfw = nsfw;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public List<Object> getTags() {
        return tags;
    }

    public void setTags(List<Object> tags) {
        this.tags = tags;
    }

    public Integer getDatetime() {
        return datetime;
    }

    public void setDatetime(Integer datetime) {
        this.datetime = datetime;
    }

    public String getMp4() {
        return mp4;
    }

    public void setMp4(String mp4) {
        this.mp4 = mp4;
    }

    public String getHls() {
        return hls;
    }

    public void setHls(String hls) {
        this.hls = hls;
    }

}