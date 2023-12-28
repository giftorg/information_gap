package org.newsorg.controller;

import lombok.extern.slf4j.Slf4j;
import org.newsorg.domain.entity.News;
import org.newsorg.domain.result.Result;
import org.newsorg.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/news")
@Slf4j
public class NewsController {

    @Autowired
    private NewsService newsService;

    @Autowired
    private RedisTemplate redisTemplate;

    @GetMapping("/list")
    public Result<List<News>> test(){
        List<News> newsList = newsService.list();
        log.info("展示所有文档成功");
        return Result.success(newsList);
    }

    /**
     * 根据分类查询MongoDB
     * @param category
     * @param page
     * @param size
     * @return
     */
    @GetMapping("/cateforyquery")
    @Cacheable(cacheNames = "newsCache",key = "#category + '::' +#page")
    public Result<Page<News>> categoryQuery(@RequestParam String category, @RequestParam(defaultValue = "1") Integer page,@RequestParam(defaultValue = "10") Integer size){
        log.info("开始查询分类");
        Page<News> categoryQuery = newsService.findByCategoryQuery(category, page, size);
        log.info("走的是缓存");
        return Result.success(categoryQuery);
    }

    /**
     * 基于redis的日期搜索
     * @param data
     * @return
     */
    @GetMapping("/dataquerry")
    public Result dataQuery(@RequestParam String data){
        return Result.success(redisTemplate.opsForValue().get("daily_new::" + data));
    }

    @GetMapping("query")
    public Result<List<News>> query(@RequestParam String key){
        List<News> newsList = newsService.query(key);
        return Result.success();
    }

}
