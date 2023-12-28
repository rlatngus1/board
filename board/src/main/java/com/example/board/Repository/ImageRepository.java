package com.example.board.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.board.Entity.ArticleImage;

public interface ImageRepository extends JpaRepository <ArticleImage, Long>{
   

    
}
