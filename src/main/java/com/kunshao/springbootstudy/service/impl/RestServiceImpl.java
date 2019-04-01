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

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private StringRedisTemplate redis;

    public void genLink(Link link) {

        try(Response response = doRequest(link)) {
            ApiLink apiLink = transformResponse(response.body().string());

            if(!CollectionUtils.isEmpty(apiLink.getApiData())) {
                sendApiLink(apiLink.getApiData(), link);
            }

            if(apiLink.hasMore()) {
                Link listLink = link.clone();
                listLink.setCurrentPage(listLink.getCurrentPage() + 1);

                try {
                    redis.opsForSet().add(Constant.PSE_LINK_PENDING_PREFIX + link.getSite(),
                            objectMapper.writeValueAsString(link));
                } catch(DataAccessException e) {
                    System.out.println("[头条API]添加{}ApiLink列表失败 :{}" + link.getSite() + e.getMessage());
                } catch(JsonProcessingException e) {
                    System.out.println("[头条API]添加{}ApiLink列表失败 :{}" + link.getSite() + e.getMessage());
                }
            }
        } catch(IOException e) {
            System.out.println(e.getMessage() + e);
        }
    }

    private void sendApiLink(List<? extends ApiLink> apiLinks, Link link) {

        redis.executePipelined(new RedisCallback<Object>() {

            @Override
            public Object doInRedis(RedisConnection connection) throws DataAccessException {

                StringRedisConnection conn = (StringRedisConnection) connection;

                for(ApiLink apiData : apiLinks) {
                    if(apiData.filter()) {
                        try {
                            Link detailLink = link.clone();
                            detailLink.setApi(false);
                            detailLink.setUrl(apiData.getUrl());
                            detailLink.setText(apiData.getText());

                            conn.sAdd(Constant.PSE_LINK_PENDING_PREFIX + detailLink.getSite(),
                                    objectMapper.writeValueAsString(detailLink));
                        } catch(DataAccessException e) {
                            System.out.println("[API]添加{}ApiLink详情页失败 :{}" + link.getSite() + e.getMessage());
                        } catch(JsonProcessingException e) {
                            System.out.println("[API]添加{}ApiLink详情页失败 :{}" + link.getSite() + e.getMessage());
                        }
                    }
                }

                return null;
            }
        });
    }

    private Response doRequest(Link link) throws IOException {

        Request request = new Request.Builder().url(transformUrl(link)).build();

        return new OkHttpClient().newCall(request).execute();
    }

    private ApiLink transformResponse(String responseBody) throws IOException {

        return objectMapper.readValue(responseBody, TouTiaoResultDto.class);
    }

    private String transformUrl(Link link) {
        //热搜API
        String url = "https://www.toutiao.com/api/pc/feed/?category=news_hot";
        //搜索API
        String url1 = "https://www.toutiao.com/api/search/content/?aid=24&app_name=web_search&offset={}&format=json&keyword={}&autoload=true&count={}&en_qc=1&cur_tab=1&from=search_tab&pd=synthesis&timestamp=1553235738299";


        return MessageFormat.format(url, link.getCurrentPage(), link.getKeyWord() ,Constant.DEFAULT_API_DATA_COUNT);
    }
//    private final String URL_PREFIX = "https://www.toutiao.com/group/";
//
//    @Autowired
//    private RestTemplate restTemplate;
//
//    @Autowired
//    private ObjectMapper objectMapper;
//
//    @Autowired
//    private StringRedisTemplate redis;
//
//    public void genLink(Link link) {
//        OkHttpClient okHttpClient = new OkHttpClient();
//
//        int currentPage = link.getCurrentPage();
//        int offset = Constant.DEFAULT_API_DATA_COUNT*(currentPage - 1);
////        String url = "https://www.toutiao.com/api/search/content/?aid=24&app_name=web_search&offset=" + offset + "&format=json&keyword=" + link.getKeyWord() + "&autoload=true&count=" + Constant.DEFAULT_API_DATA_COUNT + "&en_qc=1&cur_tab=1&from=search_tab&pd=synthesis&timestamp=1553235738299";
//        String url = "https://www.toutiao.com/api/pc/feed/?category=news_hot";
//
//        Request request = new Request.Builder()
//                .url(url.toString())
//                .build();
//
//        String bodyString = "";
//        try (Response response = okHttpClient.newCall(request).execute()) {
//            bodyString = response.body().string();
////            System.out.println("搜索新闻body" + bodyString);
//            //转化为需要的数据结构
////            list = searchNewsConvert(bodyString,labels,size);
//        } catch (IOException e) {
////            log.error(e.getMessage(),e);
////            AssertHttp.notFound("网络异常，请稍后重试");
//        }
//
////        ResponseEntity<String> result = restTemplate.getForEntity(url, String.class);
////        System.out.println(result);
//        if(null != bodyString){
//            try {
//                TouTiaoResultDto touTiaoResultDto = this.objectMapper.readValue(bodyString, TouTiaoResultDto.class);
//                if(touTiaoResultDto != null){
//                    System.out.println(touTiaoResultDto);
//                    redis.executePipelined(new RedisCallback<Object>() {
//                        @Override
//                        public Object doInRedis(RedisConnection connection) throws DataAccessException {
//                            StringRedisConnection conn = (StringRedisConnection) connection;
//                            //添加列表页
//                            if(touTiaoResultDto.getHasMore() == 1){
//                                Link apiLink = new Link();
//                                apiLink.setApi(true);
//                                apiLink.setSite(link.getSite());
//                                apiLink.setKeyWord(link.getKeyWord());
//                                apiLink.setCurrentPage(currentPage + 1);
//
//                                try {
//                                    conn.sAdd(Constant.PSE_LINK_PENDING_PREFIX + link.getSite(), objectMapper.writeValueAsString(apiLink));
//                                } catch (JsonProcessingException e) {
//                                    e.printStackTrace();
////                            console.error("[头条API]添加{}ApiLink列表失败 :{}", link.getSite(), e.getMessage());
//                                }
//                            }
//
//                            //添加详情页(非video)
//                            if(touTiaoResultDto.getData() != null && touTiaoResultDto.getData() != null){
//                                for (ApiLink apiLink : touTiaoResultDto.getData()) {
//                                    if (!apiLink.isHasVideo() && null != apiLink.getTitle() && null != apiLink.getItemId()){
//                                        try {
//                                            System.out.println(apiLink);
//                                            Link detailLink = new Link();
//                                            detailLink.setApi(false);
//                                            detailLink.setUrl(URL_PREFIX + apiLink.getItemId());
//                                            detailLink.setText(apiLink.getTitle());
//                                            detailLink.setSite(link.getSite());
//
//                                            conn.sAdd(Constant.PSE_LINK_PENDING_PREFIX + link.getSite(),objectMapper.writeValueAsString(detailLink));
//                                        } catch (JsonProcessingException e) {
//                                            e.printStackTrace();
////                                console.error("[API]添加{}ApiLink详情页失败 :{}", apiLink.getSite(), e.getMessage());
//                                        }
//                                    }
//                                }
//                            }
//
//                            connection.close();
//                            return null;
//                        }
//                    });
//                }
//            } catch (DataAccessException e){
//                e.printStackTrace();
//            } catch (IOException e) {
//                e.printStackTrace();
////            console.error("[API]获取{}数据失败 :{}",apiLink.getTitle(), e.getMessage());
//            }
//        }
//
////        return null;
//    }
}
