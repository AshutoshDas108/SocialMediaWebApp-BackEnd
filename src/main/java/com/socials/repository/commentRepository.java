package com.socials.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.socials.models.Comment;

@Repository
public interface commentRepository extends JpaRepository<Comment, Integer> {

}
