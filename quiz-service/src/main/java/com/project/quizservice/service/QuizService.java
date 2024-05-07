package com.project.quizservice.service;

import com.project.quizservice.dao.QuizDao;
import com.project.quizservice.feign.QuizInterface;
import com.project.quizservice.model.Response;
import com.project.quizservice.model.QuestionWrapper;
import com.project.quizservice.model.Quiz;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuizService {

    @Autowired
    QuizDao quizDao;

    @Autowired
    QuizInterface quizInterface;


    public ResponseEntity<String> createQuiz(String category, int numQ, String title) {

        List<Integer> questions = quizInterface.getQuestionsForQuiz(category, numQ).getBody();
        Quiz quiz = new Quiz();
        quiz.setTitle(title);
        quiz.setQuestionIds(questions);
        quizDao.save(quiz);

        return new ResponseEntity<>("Success", HttpStatus.CREATED);

    }

    public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(Integer id) {
          Quiz quiz = quizDao.findById(id).get();
          List<Integer> questionIds = quiz.getQuestionIds();
        return quizInterface.getQuestionsFromId(questionIds);

    }

    public ResponseEntity<Integer> calculateResult(Integer id, List<Response> responses) {
        boolean q = quizDao.existsById(id);
        if (!q)
        {
            throw new RuntimeException("not exist");
        }

//        for (Response r : responses) {
//            // Accessing the id property of each Response object
//            Integer ansId = r.getId();
//            System.out.println(ansId);
//            // Do something with the id here
//        }



//        Quiz quiz = quizDao.findById(id).get();
//
//        List<Integer> questionIds = quiz.getQuestionIds();
//
//        List<Integer> matchedIds = new ArrayList<>();
//        for (Response r : responses) {
//            Integer ansId = r.getId();
//            if (questionIds.contains(ansId)) {
//                matchedIds.add(ansId);
//            }
//        }
//        System.out.println(matchedIds);
//
//        return quizInterface.getScore(responses);

        Quiz quiz = quizDao.findById(id).get();
        List<Integer> questionIds = quiz.getQuestionIds();

// Create a list to store the filtered responses
        List<Response> filteredResponses = new ArrayList<>();

// Iterate through the responses to filter out non-matching IDs
        for (Response r : responses) {
            Integer ansId = r.getId();
            if (questionIds.contains(ansId)) {
                filteredResponses.add(r);
            }
        }



// Return the score calculation using the filtered responses
        return quizInterface.getScore(filteredResponses);

    }
}
