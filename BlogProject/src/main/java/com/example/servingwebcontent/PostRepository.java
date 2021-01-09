package com.example.servingwebcontent;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
interface PostRepository extends CrudRepository<Post, Integer> {
}
