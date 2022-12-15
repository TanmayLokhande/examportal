package com.examportal.dto;

import com.examportal.entities.User;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class requestDto {

	private String totalMarks;
	private String quizTitle;
	private String correctAnswers;
	private String attemptesQuestions;
	private String noOfQuestions;

	
	private User user;
	
}
