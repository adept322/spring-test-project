package com.experimental.blog.DAO;

import com.experimental.blog.Models.PostModel;
import org.springframework.data.repository.CrudRepository;

public interface PostDAO extends CrudRepository<PostModel, Long> {


}
