package com.MSBootCoinBootCamp.domain.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

@Data
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
@RedisHash("posts")
public class Post {

    @Id
    private String id;
    private String title;
    private String content;

}