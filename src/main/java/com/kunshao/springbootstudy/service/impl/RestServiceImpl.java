package com.kunshao.springbootstudy.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kunshao.springbootstudy.common.Constant;
import com.kunshao.springbootstudy.model.dto.TouTiaoResultDto;
import com.kunshao.springbootstudy.model.entity.ApiLink;
import com.kunshao.springbootstudy.model.entity.Link;
import com.kunshao.springbootstudy.service.RestService;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.StringRedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.io.IOException;
import java.text.MessageFormat;
import java.util.List;

/**
 * RestTemplate 、 ObjectMapper 和 RedisTemplate 的实战(今日头条热搜API)
 * @author 王坤
 * @date 2019-03-22
 */
@Service
public class RestServiceImpl implements RestService {
//
//    @Autowired
//    private ObjectMapper objectMapper;
//
//    @Autowired
//    private StringRedisTemplate redis;
//
//    public void genLink(Link link) {
//
//        try(Response response = doRequest(link)) {
//            ApiLink apiLink = transformResponse(response.body().string());
//
//            if(!CollectionUtils.isEmpty(apiLink.getApiData())) {
//                sendApiLink(apiLink.getApiData(), link);
//            }
//
//            if(apiLink.hasMore()) {
//                Link listLink = link.clone();
//                listLink.setCurrentPage(listLink.getCurrentPage() + 1);
//
//                try {
//                    redis.opsForSet().add(Constant.PSE_LINK_PENDING_PREFIX + link.getSite(),
//                            objectMapper.writeValueAsString(link));
//                } catch(DataAccessException e) {
//                    System.out.println("[头条API]添加{}ApiLink列表失败 :{}" + link.getSite() + e.getMessage());
//                } catch(JsonProcessingException e) {
//                    System.out.println("[头条API]添加{}ApiLink列表失败 :{}" + link.getSite() + e.getMessage());
//                }
//            }
//        } catch(IOException e) {
//            System.out.println(e.getMessage() + e);
//        }
//    }
//
//    private void sendApiLink(List<? extends ApiLink> apiLinks, Link link) {
//
//        redis.executePipelined(new RedisCallback<Object>() {
//
//            @Override
//            public Object doInRedis(RedisConnection connection) throws DataAccessException {
//
//                StringRedisConnection conn = (StringRedisConnection) connection;
//
//                for(ApiLink apiData : apiLinks) {
//                    if(apiData.filter()) {
//                        try {
//                            Link detailLink = link.clone();
//                            detailLink.setApi(false);
//                            detailLink.setUrl(apiData.getUrl());
//                            detailLink.setText(apiData.getText());
//
//                            conn.sAdd(Constant.PSE_LINK_PENDING_PREFIX + detailLink.getSite(),
//                                    objectMapper.writeValueAsString(detailLink));
//                        } catch(DataAccessException e) {
//                            System.out.println("[API]添加{}ApiLink详情页失败 :{}" + link.getSite() + e.getMessage());
//                        } catch(JsonProcessingException e) {
//                            System.out.println("[API]添加{}ApiLink详情页失败 :{}" + link.getSite() + e.getMessage());
//                        }
//                    }
//                }
//
//                return null;
//            }
//        });
//    }
//
//    private Response doRequest(Link link) throws IOException {
//
//        Request request = new Request.Builder().url(transformUrl(link)).build();
//
//        return new OkHttpClient().newCall(request).execute();
//    }
//
//    private ApiLink transformResponse(String responseBody) throws IOException {
//
//        return objectMapper.readValue(responseBody, TouTiaoResultDto.class);
//    }
//
//    private String transformUrl(Link link) {
//        //热搜API
//        String url = "https://www.toutiao.com/api/pc/feed/?category=news_hot";
//        //搜索API
//        String url1 = "https://www.toutiao.com/api/search/content/?aid=24&app_name=web_search&offset={}&format=json&keyword={}&autoload=true&count={}&en_qc=1&cur_tab=1&from=search_tab&pd=synthesis&timestamp=1553235738299";
//
//
//        return MessageFormat.format(url, link.getCurrentPage(), link.getKeyWord() ,Constant.DEFAULT_API_DATA_COUNT);
//    }
    private final String URL_PREFIX = "https://www.toutiao.com/group/";

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private StringRedisTemplate redis;

    public void genLink(Link link) {
        try (Response response = doRequest(link)) {
            if(response.code() == HttpStatus.OK.value()){
                String bodyString = response.body().string();
                if(bodyString != null && !bodyString.equals("")){
                    ApiLink apiLink = this.objectMapper.readValue(bodyString, TouTiaoResultDto.class);
                    if(apiLink != null){
                        if(CollectionUtils.isEmpty(apiLink.getApiData())){
                            sendApiLink(apiLink.getApiData(),link);
                        }

                        //添加列表页
                        if(apiLink.hasMore()){
                            Link linkList = link.clone();
                            linkList.setCurrentPage(link.getCurrentPage() + 1);

                            try {
                                redis.opsForSet().add(Constant.PSE_LINK_PENDING_PREFIX + link.getSite(), objectMapper.writeValueAsString(apiLink));
                            } catch (JsonProcessingException e) {
                                e.printStackTrace();
//                            console.error("[头条API]添加{}ApiLink列表失败 :{}", link.getSite(), e.getMessage());
                            }
                        }
                    }
                }
            }
        } catch (IOException e) {
        }
    }
    private void sendApiLink(List<? extends ApiLink> apiLinks,Link link){
        redis.executePipelined(new RedisCallback<Object>() {
            @Override
            public Object doInRedis(RedisConnection connection) throws DataAccessException {
                StringRedisConnection conn = (StringRedisConnection) connection;
                //添加详情页(非video)
                if(apiLinks != null){
                    for (ApiLink apiLink : apiLinks) {
                        if (apiLink.filter()){
                            try {
                                Link detailLink = link.clone();
                                detailLink.setApi(false);
                                detailLink.setUrl(URL_PREFIX + apiLink.getUrl());
                                detailLink.setText(apiLink.getText());

                                conn.sAdd(Constant.PSE_LINK_PENDING_PREFIX + link.getSite(),
                                        objectMapper.writeValueAsString(detailLink));
                            } catch (JsonProcessingException e) {
                                e.printStackTrace();
//                                console.error("[API]添加{}ApiLink详情页失败 :{}", apiLink.getSite(), e.getMessage());
                            }
                        }
                    }
                }
                connection.close();
                return null;
            }
        });
    }

    /**
     * http请求操作
     * @param link
     * @return
     * @throws IOException
     */
    private Response doRequest(Link link) throws IOException {
        Request request = new Request.Builder().url(getUrl(link)).build();
        return new OkHttpClient().newCall(request).execute();
    }

    /**
     * 获取准确的url
     * @param link
     * @return
     */
    private String getUrl(Link link){
        //搜索API
//        String url = "https://www.toutiao.com/api/search/content/?aid=24&app_name=web_search&offset={0}&format=json&keyword={1}&autoload=true&count={2}&en_qc=1&cur_tab=1&from=search_tab&pd=synthesis&timestamp=1553235738299";
        //热点API
        String url = "https://www.toutiao.com/api/pc/feed/?category=news_hot";

        return MessageFormat.format(url,link.getCurrentPage(),link.getKeyWord(),Constant.DEFAULT_API_DATA_COUNT);
    }
}
