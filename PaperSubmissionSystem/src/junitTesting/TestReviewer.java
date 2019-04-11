package junitTesting;
import org.junit.Test;

import reviewer.Reviewer;

import static org.junit.Assert.*;
public class TestReviewer {
	String testFlag = null;
	Reviewer re = new Reviewer(testFlag);
	@Test
	public void testAIBoundCheck_InsideNotHit() throws Exception{
		
//		System.out.println(world[2][2]);
		assertEquals("1.txt", re.getFileName(0));
		assertEquals("2.txt", re.getFileName(1));
		assertEquals("3.txt", re.getFileName(2));
	}
	
	@Test
	public void testWhole_Reviewer(){
		Reviewer.main(null);
	}
}
