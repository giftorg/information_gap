package org.newsorg.dao;

import org.newsorg.domain.entity.News;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface NewsRepository extends MongoRepository<News,String> {

    /**
     * 根据分类的分页查询
     * @param category
     * @param pageable
     * @return
     */
    Page<News> findByCategory(String category, Pageable pageable);

}
