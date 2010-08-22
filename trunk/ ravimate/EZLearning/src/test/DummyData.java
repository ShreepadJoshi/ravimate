package test;

import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;

import com.education.transferobj.TestQuestion;
import com.education.util.EducationConstant;

public class DummyData {

	public static Map getSampleTestData(){
		//SortedSet questionSet = new TreeSet(new TestQuestionComparator() );
		TreeMap questionSet = new TreeMap();
		//Test data for 25 questions
		for(int i=0;i<25;i++){
			if(i>=0 && i< 10)
				questionSet.put(new Integer(i+1),
						new	TestQuestion(i,(i+1),"Answer"+i,EducationConstant.TEST_QUESTION_STATUS_COMPLETED));
			if(i>=10 && i< 20)
				questionSet.put(new Integer(i+1),
						new	TestQuestion(i,(i+1),"Answer"+i,EducationConstant.TEST_QUESTION_STATUS_TOATTEMPT));
			if(i>=20 && i< 25)
				questionSet.put(new Integer(i+1), 						
						new TestQuestion(i,(i+1),"Answer"+i,EducationConstant.TEST_QUESTION_STATUS_TOREVIST));			
		}		
		return questionSet;
	}
}


class TestQuestionComparator implements Comparator{

	public int compare(Object obj1, Object obj2) {
		int result= 0;
		TestQuestion question1 = (TestQuestion)obj1;
		TestQuestion question2 = (TestQuestion)obj2;
		
		if(question2.getTestpaperQuestionNumber() > question1.getTestpaperQuestionNumber())
			result = 1;
		else if(question2.getTestpaperQuestionNumber() < question1.getTestpaperQuestionNumber())
			result = -1;
		else
			result =0;
		return result;
	}
	
}


