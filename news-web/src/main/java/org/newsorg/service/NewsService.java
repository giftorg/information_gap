package org.newsorg.service;


import org.newsorg.domain.entity.News;
import org.springframework.data.domain.Page;

import java.util.List;

public interface NewsService {

    /**
     * 展示文档集合
     * @return
     */
    List<News> list();

    /**
     * 分页查询
     * @param category
     * @param page
     * @param size
     * @return
     */
    Page<News> findByCategoryQuery(String category,Integer page,Integer size);



}
