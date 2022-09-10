package com.suatogul.h2jpa.service;

import com.suatogul.h2jpa.model.Tutorial;
import com.suatogul.h2jpa.repository.TutorialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;

@Service
public class TutorialService {

    @Autowired
    TutorialRepository tutorialRepository;

    public List<Tutorial> getTheAllTutorials(String title) {

        List<Tutorial> tutorials = new ArrayList<Tutorial>();
            if (title == null)
                tutorialRepository.findAll().forEach(tutorials::add);
            else
                tutorialRepository.findByTitleContaining(title).forEach(tutorials::add);
//            if (tutorials.isEmpty()) {
//                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//            }
            return tutorials;
    }


    public Tutorial createTutorial(Tutorial tutorial) {

            Tutorial _tutorial = tutorialRepository
                    .save(new Tutorial(tutorial.getTitle(), tutorial.getDescription(), false));
            return _tutorial;
    }
}
