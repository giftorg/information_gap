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
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/news")
@Slf4j
public class NewsController {

    @Autowired
    private NewsService newsService;

    @Autowired
    private RedisTemplate redisTemplate;

    @GetMapping("/list")
    public Result<List<News>> test() {
        List<News> newsList = newsService.list();
        log.info("展示所有文档成功");
        return Result.success(newsList);
    }

    /**
     * 根据分类查询MongoDB
     *
     * @param category
     * @param page
     * @param size
     * @return
     */
    @GetMapping("/categoryquery")
    @Cacheable(cacheNames = "newsCache", key = "#category + '::' +#page")
    public Result<Page<News>> categoryQuery(@RequestParam String category, @RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "10") Integer size) {
        Page<News> categoryQuery = newsService.findByCategoryQuery(category, page, size);
        return Result.success(categoryQuery);
    }

    /**
     * 基于redis的日期搜索
     *
     * @param date
     * @return
     */
    @GetMapping("/datequery")
    public Result dataQuery(@RequestParam String date) {
        return Result.success(redisTemplate.opsForValue().get("daily_new::" + date));
    }

    @GetMapping("/information_gap")
    public Result informationGap() {
        Set<String> keys = redisTemplate.keys("daily_new::????-??-??");
        if (keys != null && !keys.isEmpty()) {
            List<String> values = redisTemplate.opsForValue().multiGet(keys);
            return Result.success(values);
        }
        return Result.error("get information gap failed");
    }

    @GetMapping("/query")
    public Result<List<News>> query(@RequestParam String key) {
        List<News> newsList = newsService.query(key);
        log.info("news list: {}", newsList);
        return Result.success(newsList);
    }

    @GetMapping(value = "/image", produces = MediaType.IMAGE_PNG_VALUE)
    public ResponseEntity<byte[]> getImage(@RequestParam String url) throws IOException {
        String[] spl = url.split("/.");
        String ext = spl[spl.length - 1];

        Path tempFile = Files.createTempFile("tmp", "." + ext);
        Files.copy(new URL(url).openStream(), tempFile, StandardCopyOption.REPLACE_EXISTING);

        byte[] imageBytes = Files.readAllBytes(tempFile);
        return ResponseEntity.ok().body(imageBytes);
    }

}
