package com.sparta.week05miniprojectbackend.repository;

import com.sparta.week05miniprojectbackend.entity.Img;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ImgRepository extends JpaRepository<Img, Long> {
    List<Img> findAllByPostId(Long id);
}
