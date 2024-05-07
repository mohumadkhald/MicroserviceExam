Microservice Quiz Application

Designed and developed a microservice-based quiz application utilizing Java Spring Boot framework. The application comprises several RESTful endpoints to manage quiz-related functionalities:

    Quiz Management:
        Implemented endpoints for creating quizzes, fetching quiz questions, and submitting quiz responses.
        Utilized Spring Web to handle HTTP requests and responses efficiently.

    Question Service:
        Created endpoints for retrieving questions based on categories, generating questions for quizzes, and retrieving questions by their IDs.
        Leveraged MongoDB for question storage, ensuring scalability and flexibility in managing question data.

    Quiz Service:
        Developed endpoints for creating quizzes with customizable parameters such as category, number of questions, and title.
        Implemented logic to calculate quiz results based on user responses, providing real-time feedback to users.


gateway http://localhost:8000


endpoint of question service 
http://localhost:8000/question-service/question



endpoint of quiz service 
http://localhost:8000/quiz-service/quiz

