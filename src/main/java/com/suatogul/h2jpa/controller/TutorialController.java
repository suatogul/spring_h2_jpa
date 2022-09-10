package com.suatogul.h2jpa.controller;


import com.suatogul.h2jpa.model.Tutorial;
import com.suatogul.h2jpa.service.TutorialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api")
public class TutorialController {
    @Autowired
    TutorialService tutorialService;

    @GetMapping("/tutorials")
    public ResponseEntity<List<Tutorial>> getAllTutorials(@RequestParam(required = false) String title) {
        try {
            List<Tutorial> tutorials;
                tutorials= tutorialService.getTheAllTutorials(title);
                if (tutorials.isEmpty()) {
                        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
                    }else {
                            return new ResponseEntity<>(tutorials, HttpStatus.OK);
                    }
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


//    @GetMapping("/tutorials/description")
//    public ResponseEntity<List<Tutorial>> getAllTutorialsDescription(@RequestParam(required = false) String description) {
//        try {
//            List<Tutorial> tutorials = new ArrayList<Tutorial>();
//            if (description == null)
//                tutorialRepository.findAll().forEach(tutorials::add);
//            else
//                tutorialRepository.findByDescriptionContaining(description).forEach(tutorials::add);
//            if (tutorials.isEmpty()) {
//                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//            }
//            return new ResponseEntity<>(tutorials, HttpStatus.OK);
//        } catch (Exception e) {
//            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }
//
    @GetMapping("/tutorials/{id}")
    public ResponseEntity<Tutorial> getTutorialById(@PathVariable("id") long id) {
        Optional<Tutorial> tutorialData = tutorialService.getByIdTutorial(id);
        if (tutorialData.isPresent()) {
            return new ResponseEntity<>(tutorialData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/tutorials")
    public ResponseEntity<Tutorial> createTutorial(@RequestBody Tutorial tutorial) {
        try {
            Tutorial _tutorial = tutorialService.createTutorial(tutorial);

            return new ResponseEntity<>(_tutorial, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/tutorials/{id}")
    public ResponseEntity<Tutorial> updateTutorial(@PathVariable("id") long id, @RequestBody Tutorial tutorial) {
        ResponseEntity<Tutorial> updatedTutorial=tutorialService.updateSelectedTutorial(id,tutorial);
        return updatedTutorial;
    }
    @DeleteMapping("/tutorials/{id}")
    public ResponseEntity<Tutorial> deleteTutorial(@PathVariable("id") long id) {
        return tutorialService.deleteSelectedTutorial(id);
    }
    @DeleteMapping("/tutorials")
    public ResponseEntity<Tutorial> deleteAllTutorials() {
        return tutorialService.deleteTheAllTutorial();
    }
    @GetMapping("/tutorials/published")
    public ResponseEntity<List<Tutorial>> findByPublished() {
        return tutorialService.findByPublished();
    }

}
