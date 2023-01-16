package com.example.quizapp

import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import androidx.core.content.ContextCompat

class QuizQuestionActivity : AppCompatActivity(), View.OnClickListener {

    //Execution variables
    private var mCurrentPosition : Int = 1
    //Needs to be assigned in the onCreate method
    private var mQuestionsList : ArrayList<Question>? = null
    //Default case ie. No option has been selected
    private var mSelectedOptionPosition : Int = 0
    private var userName : String? = null
    private var correctAnswers : Int = 0
    //Execution variables

    //Widget variables
    private var tvQuestion : TextView? = null
    private var ivFlagImage : ImageView? = null
    private var progressBar : ProgressBar? = null
    private var tvProgress : TextView? = null
    private var optionOne: TextView? = null
    private var optionTwo: TextView? = null
    private var optionThree: TextView? = null
    private var optionFour: TextView? = null
    private var submitButton : Button? = null
    //Widget variables

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz_question)

        userName = intent.getStringExtra(Constants.USER_NAME)

        mQuestionsList = Constants.getQuestions()

        tvQuestion = findViewById(R.id.tvQuestion)
        ivFlagImage = findViewById(R.id.ivFlagImage)
        progressBar = findViewById(R.id.progressBar)
        tvProgress = findViewById(R.id.tvProgress)
        optionOne = findViewById(R.id.optionOne)
        optionTwo = findViewById(R.id.optionTwo)
        optionThree = findViewById(R.id.optionThree)
        optionFour = findViewById(R.id.optionFour)
        submitButton = findViewById(R.id.btnSubmit)

        //Set onClickListeners
        optionOne?.setOnClickListener(this)
        optionTwo?.setOnClickListener(this)
        optionThree?.setOnClickListener(this)
        optionFour?.setOnClickListener(this)
        submitButton?.setOnClickListener(this)

        setQuestion()

    }

    private fun setQuestion() {
        defaultOptionsView()
        val question: Question = mQuestionsList!!.get(mCurrentPosition - 1)
        ivFlagImage?.setImageResource(question.image)
        progressBar?.progress = mCurrentPosition
        tvProgress?.text = "$mCurrentPosition/${progressBar?.max}"
        tvQuestion?.text = question.question
        optionOne?.text = question.optionOne
        optionTwo?.text = question.optionTwo
        optionThree?.text = question.optionThree
        optionFour?.text = question.optionFour

        if(mCurrentPosition == progressBar?.max ){
            submitButton?.text = "FINISH"
        }else{
            submitButton?.text = "SUBMIT"
        }
    }
    //Set the default colors
    private fun defaultOptionsView(){
        val options = ArrayList<TextView>()
        optionOne?.let{
            options.add(0,it)
        }
        optionTwo?.let{
            options.add(1,it)
        }
        optionThree?.let{
            options.add(2,it)
        }
        optionFour?.let{
            options.add(3,it)
        }

        for(option in options){
            option.setTextColor(Color.parseColor("#7A8089"))
            option.typeface = Typeface.DEFAULT
            option.background = ContextCompat.getDrawable(this,R.drawable.default_option_border)
        }
    }

    //Set the selected option
    private fun selectedOptionView(tv:TextView,selectedOptionNumber : Int){
        //This position is to reset the default view within the same question and allow another option to be selected
        defaultOptionsView()
        mSelectedOptionPosition = selectedOptionNumber
        tv.setTextColor(Color.parseColor("#363A43"))
        tv.setTypeface(tv.typeface, Typeface.BOLD)
        tv.background = ContextCompat.getDrawable(this,R.drawable.selected_option_border)
    }

    private fun answerView(answerSelected: Int , drawableView : Int) {
        when (answerSelected) {
            1 -> {
                optionOne?.background = ContextCompat.getDrawable(this, drawableView)
            }
            2 -> {
                optionTwo?.background = ContextCompat.getDrawable(this, drawableView)
            }
            3 -> {
                optionThree?.background = ContextCompat.getDrawable(this, drawableView)
            }
            4 -> {
                optionFour?.background = ContextCompat.getDrawable(this, drawableView)
            }
        }
    }

    override fun onClick(view: View?) {
        when(view?.id){
            R.id.optionOne -> {
                optionOne?.let{
                    selectedOptionView(it,1)
                }
            }
            R.id.optionTwo -> {
                optionTwo?.let{
                    selectedOptionView(it,2)
                }
            }
            R.id.optionThree -> {
                optionThree?.let{
                    selectedOptionView(it,3)
                }
            }
            R.id.optionFour -> {
                optionFour?.let{
                    selectedOptionView(it,4)
                }
            }
            R.id.btnSubmit -> {
                //No option has been selected so no color change therefore go to next. If quiz is over go to result activity
                if(mSelectedOptionPosition == 0){
                    //Once the submit button is clicked the current position is increased
                    mCurrentPosition++
                    when{
                        mCurrentPosition <= mQuestionsList!!.size -> {
                            setQuestion()
                        }
                        else -> {
                            val intent = Intent(this,ResultActivity::class.java)
                            intent.putExtra(Constants.USER_NAME,userName)
                            intent.putExtra(Constants.CORRECT_ANSWERS,correctAnswers)
                            intent.putExtra(Constants.TOTAL_QUESTIONS,mQuestionsList?.size)
                            startActivity(intent)
                            finish()
                        }
                    }
                }
                else
                //Option selected,check if its right or wrong then execute appropriately
                {
                    //Get details about the question based on the current position
                    val question = mQuestionsList?.get(mCurrentPosition - 1)
                    if (question!!.correctAnswer != mSelectedOptionPosition) {
                        answerView(mSelectedOptionPosition, R.drawable.wrong_answer_background)
                    }else{
                        correctAnswers++
                    }
                    //Even when the wrong answer has been selected the right one should still be highlighted
                    answerView(question.correctAnswer, R.drawable.correct_answer_background)
                    if(mCurrentPosition == mQuestionsList!!.size){
                        submitButton?.text = "FINISH"
                    }else{
                        submitButton?.text = "GO TO NEXT QUESTION"
                    }
                    //Restore to default case
                    mSelectedOptionPosition = 0
                }
            }
        }
    }
}