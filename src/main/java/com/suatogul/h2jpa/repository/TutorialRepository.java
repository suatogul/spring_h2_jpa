package com.suatogul.h2jpa.repository;

import com.suatogul.h2jpa.model.Tutorial;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TutorialRepository extends JpaRepository<Tutorial, Long> {
    List<Tutorial> findByPublished(boolean published);
    List<Tutorial> findByTitleContaining(String title);
    List<Tutorial> findByDescriptionContaining(String description);
}
