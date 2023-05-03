package com.experimental.blog.repository;

import com.experimental.blog.entity.Post;
import org.springframework.data.repository.CrudRepository;

public interface PostRepository extends CrudRepository<Post, Long> {


}
