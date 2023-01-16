package com.example.quizapp

object Constants {

    const val USER_NAME : String = "user_name"
    const val TOTAL_QUESTIONS : String = "total_questions"
    const val CORRECT_ANSWERS : String = "correct_answers"


    //Function to return all the available questions
    fun getQuestions():ArrayList<Question>{
        val questionList = ArrayList<Question>()
        val question1 = Question(
            1,
            "What country does this flag belong to?",
            R.drawable.ic_flag_of_argentina,
            "Argentina",
            "Australia",
            "Armenia",
            "Austria",
            1
        )
        questionList.add(question1)
        val question2 = Question(
            2,
            "What country does this flag belong to?",
            R.drawable.ic_flag_of_australia,
            "Argentina","Australia","Armenia","Austria",
            2
        )
        questionList.add(question2)
        val question3 = Question(
            3,
            "What country does this flag belong to?",
            R.drawable.ic_flag_of_belgium,
            "Argentina","Australia","Belgium","Austria",
            3
        )
        questionList.add(question3)
        val question4 = Question(
            4,
            "What country does this flag belong to?",
            R.drawable.ic_flag_of_brazil,
            "Argentina","Australia","Armenia","Brazil",
            4
        )
        questionList.add(question4)
        val question5 = Question(
            5,
            "What country does this flag belong to?",
            R.drawable.ic_flag_of_denmark,
            "Denmark","Fiji","Germany","India",
            1
        )
        questionList.add(question5)
        val question6 = Question(
            6,
            "What country does this flag belong to?",
            R.drawable.ic_flag_of_fiji,
            "Denmark","Fiji","Germany","India",
            2
        )
        questionList.add(question6)
        val question7 = Question(
            7,
            "What country does this flag belong to?",
            R.drawable.ic_flag_of_germany,
            "Denmark","Fiji","Germany","India",
            3
        )
        questionList.add(question7)
        val question8 = Question(
            8,
            "What country does this flag belong to?",
            R.drawable.ic_flag_of_india,
            "Denmark","Fiji","Germany","India",
            4
        )
        questionList.add(question8)
        val question9 = Question(
            9,
            "What country does this flag belong to?",
            R.drawable.ic_flag_of_kuwait,
            "Kuwait","Fiji","Germany","India",
            1
        )
        questionList.add(question9)
        val question10 = Question(
            10,
            "What country does this flag belong to?",
            R.drawable.ic_flag_of_new_zealand,
            "Denmark","Fiji","New Zealand","India",
            3
        )
        questionList.add(question10)

        return questionList
    }
}