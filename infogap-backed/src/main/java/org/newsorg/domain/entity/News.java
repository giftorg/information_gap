package org.newsorg.domain.entity;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.annotation.Id;

import java.io.Serializable;
import java.util.List;


@Data
@Builder
@ToString
@Document("news")   //对应数据库集合名称
public class News implements Serializable {

    @Id
    private String id;      //id

    private String title;   //标题

    private String description;   //

    private String source;   //

    private String category;   //

    private String link;   //

    private String publish_date;   //

    private String publish_timestamp;   //

    private List<String> keyword_list;   //

    private String image;
}
