package com.aryan.examportal_backend.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class QuizResultDTO {
		double totalMarksObtained;
		double totalAttempted;
		double correctAnswers;
		boolean result;
}
