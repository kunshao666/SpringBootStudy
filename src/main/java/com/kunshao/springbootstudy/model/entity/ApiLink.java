package com.kunshao.springbootstudy.model.entity;

import java.util.List;

/**
 * API获取链接数据
 */
public interface ApiLink {
    List<? extends ApiLink> getApiData();

    boolean hasMore();

    default int getNextPage() {
        return 0;
    }

    String getText();

    String getUrl();

    default boolean filter() {
        return true;
    }

//    //文档ID
//    @JsonProperty(value = "item_id")
//    private String itemId;
//
//    //是否是视频
//    @JsonProperty(value = "has_video")
//    private boolean hasVideo;
//
//    //标题
//    private String title;
//
//    //当前页码
//    private int currentPage;
//
//    //关键词
//    private String keyWord;
//
//    //是否是API
//    private boolean isApi;
//
//    //站点名称
//    private String site;
//
//    public String getSite() {
//        return site;
//    }
//
//    public void setSite(String site) {
//        this.site = site;
//    }
//
//    public String getItemId() {
//        return itemId;
//    }
//
//    public void setItemId(String itemId) {
//        this.itemId = itemId;
//    }
//
//    public boolean isHasVideo() {
//        return hasVideo;
//    }
//
//    public String getKeyWord() {
//        return keyWord;
//    }
//
//    public void setKeyWord(String keyWord) {
//        this.keyWord = keyWord;
//    }
//
//    public void setHasVideo(boolean hasVideo) {
//        this.hasVideo = hasVideo;
//    }
//
//    public String getTitle() {
//        return title;
//    }
//
//    public void setTitle(String title) {
//        this.title = title;
//    }
//
//    public int getCurrentPage() {
//        return currentPage;
//    }
//
//    public void setCurrentPage(int currentPage) {
//        this.currentPage = currentPage;
//    }
//
//    public boolean isApi() {
//        return isApi;
//    }
//
//    public void setApi(boolean api) {
//        isApi = api;
//    }
//
//    @Override
//    public String toString() {
//        return "ApiLink{" +
//                "itemId='" + itemId + '\'' +
//                ", hasVideo=" + hasVideo +
//                ", title='" + title + '\'' +
//                ", currentPage=" + currentPage +
//                ", keyWord='" + keyWord + '\'' +
//                ", isApi=" + isApi +
//                ", site='" + site + '\'' +
//                '}';
//    }
}
