package org.newsorg.service.impl;

import org.newsorg.dao.NewsRepository;
import org.newsorg.domain.entity.News;
import org.newsorg.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NewsServiceImpl implements NewsService {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    private NewsRepository newsRepository;

    @Autowired
    private RedisTemplate redisTemplate;



    /**
     * 查询MongoDB集合下的所有文档
     * @return
     */
    @Override
//    @CachePut(cacheNames = "newsCache") TODO redis细粒度未解决
    public List<News> list() {
        return  mongoTemplate.findAll(News.class);
    }


    /**
     * 根据分类进行分页查询
     * @param category
     * @param page
     * @param size
     * @return
     */
    @Override
    public Page<News> findByCategoryQuery(String category,Integer page,Integer size){
        PageRequest pageRequest = PageRequest.of(page-1, size);
        return newsRepository.findByCategory(category,pageRequest);
    }

}
