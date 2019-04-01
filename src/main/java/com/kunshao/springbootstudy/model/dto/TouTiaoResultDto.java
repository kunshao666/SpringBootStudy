package com.kunshao.springbootstudy.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.kunshao.springbootstudy.model.entity.ApiLink;

import java.util.List;

/**
 * 获取头条中的重要信息
 * @author 王坤
 * @date 2019-03-20
 */
public class TouTiaoResultDto implements ApiLink{

    private final String URL_PREFIX = "https://www.toutiao.com/group/";

    // 链接数据
    private List<TouTiaoResultDto> data;

    // 文档ID
    @JsonProperty(value = "item_id")
    private String itemId;

    // 是否是视频
    @JsonProperty(value = "has_video")
    private boolean hasVideo;

    // 标题
    private String title;

    // 是否还有下一页
    @JsonProperty(value = "has_more")
    private boolean hasMore;

    public List<TouTiaoResultDto> getData() {

        return data;
    }

    public void setData(List<TouTiaoResultDto> data) {

        this.data = data;
    }

    public String getTitle() {

        return title;
    }

    public void setTitle(String title) {

        this.title = title;
    }

    public boolean isHasMore() {

        return hasMore;
    }

    public void setHasMore(boolean hasMore) {

        this.hasMore = hasMore;
    }

    @Override
    public boolean hasMore() {

        return hasMore;
    }

    @Override
    public List<? extends ApiLink> getApiData() {

        return data;
    }

    @Override
    public String getText() {

        return title;
    }

    @Override
    public boolean filter() {

        return !hasVideo;
    }

    @Override
    public String getUrl() {

        return URL_PREFIX + getItemId();
    }

    public String getItemId() {

        return itemId;
    }

    public void setItemId(String itemId) {

        this.itemId = itemId;
    }

    public boolean isHasVideo() {

        return hasVideo;
    }

    public void setHasVideo(boolean hasVideo) {

        this.hasVideo = hasVideo;
    }
//    //链接数据
//    private ApiLink[] data;
//
//    //是否还有下一页（0:false,1:true）
////    @JsonProperty(value = "has_more")
//    @JsonIgnore
//    private int hasMore;
//
//    public ApiLink[] getData() {
//        return data;
//    }
//
//    public void setData(ApiLink[] data) {
//        this.data = data;
//    }
//
//    public int getHasMore() {
//        return hasMore;
//    }
//
//    public void setHasMore(int hasMore) {
//        this.hasMore = hasMore;
//    }
//
//    @Override
//    public String toString() {
//        return "TouTiaoResultDto{" +
//                "data=" + Arrays.toString(data) +
//                ", hasMore=" + hasMore +
//                '}';
//    }
}
