package com.examportal.dto;



import com.examportal.entities.exam.Quiz;

public class QuestionDto {

	
	private long questionId;
	
	
	private String content;
	private String image;
	private String option1;
	private String option2;
	private String option3;
	private String option4;
	
	
	
	
	
	private String givenAnswer;
	
	
	private Quiz quiz;


	public QuestionDto(long questionId, String content, String image, String option1, String option2, String option3,
			String option4, String givenAnswer, Quiz quiz) {
		super();
		this.questionId = questionId;
		this.content = content;
		this.image = image;
		this.option1 = option1;
		this.option2 = option2;
		this.option3 = option3;
		this.option4 = option4;
		this.givenAnswer = givenAnswer;
		this.quiz = quiz;
	}


	public QuestionDto() {
		super();
		// TODO Auto-generated constructor stub
	}


	public long getQuestionId() {
		return questionId;
	}


	public void setQuestionId(long questionId) {
		this.questionId = questionId;
	}


	public String getContent() {
		return content;
	}


	public void setContent(String content) {
		this.content = content;
	}


	public String getImage() {
		return image;
	}


	public void setImage(String image) {
		this.image = image;
	}


	public String getOption1() {
		return option1;
	}


	public void setOption1(String option1) {
		this.option1 = option1;
	}


	public String getOption2() {
		return option2;
	}


	public void setOption2(String option2) {
		this.option2 = option2;
	}


	public String getOption3() {
		return option3;
	}


	public void setOption3(String option3) {
		this.option3 = option3;
	}


	public String getOption4() {
		return option4;
	}


	public void setOption4(String option4) {
		this.option4 = option4;
	}


	public String getGivenAnswer() {
		return givenAnswer;
	}


	public void setGivenAnswer(String givenAnswer) {
		this.givenAnswer = givenAnswer;
	}


	public Quiz getQuiz() {
		return quiz;
	}


	public void setQuiz(Quiz quiz) {
		this.quiz = quiz;
	}
	
	
	
}
