package junitTesting;

import java.lang.reflect.*;
import static org.junit.Assert.*;
import org.junit.*;
import login_Sys.Login_S;
import signup_Sys.Signup;

public class TestingLoginAndSignUp {

	String AuthorsInfoPath = "resources/authors.txt";
	String ReviewersInfoPath = "resources/reviewers.txt";
	String AdministratorInfoPath = "resources/administrator.txt";
	public static Login_S login_S;
	public static Signup signup;

	@BeforeClass
	public static void beforeClass() {
		login_S = new Login_S();
		signup = new Signup();
	}

	/**
	 * Testing the log in function for author
	 */
	@Test
	public void testAuthorLogin() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
		String authorUserName = "mok";
		String authorPassword = "123";
		Method method = Login_S.class.getDeclaredMethod("checker", String.class, String.class, String.class);
		method.setAccessible(true);
		boolean output = (boolean) method.invoke(login_S, authorUserName, authorPassword, AuthorsInfoPath);
		assertEquals(true, output);
	}

	/**
	 * Testing the log in function for Administrator
	 */
	@Test
	public void testAdministratorLogin()
			throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
		String AdministratorUserName = "admin@admin";
		String AdministratorPassword = "1";
		Method method = Login_S.class.getDeclaredMethod("checker", String.class, String.class, String.class);
		method.setAccessible(true);
		boolean output = (boolean) method.invoke(login_S, AdministratorUserName, AdministratorPassword,
				AdministratorInfoPath);
		assertEquals(true, output);
	}

	/**
	 * Testing the log in function for Reviewer
	 */
	@Test
	public void testReviewerLogin() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
		String ReviewerUserName = "456";
		String ReviewerPassword = "567";
		Method method = Login_S.class.getDeclaredMethod("checker", String.class, String.class, String.class);
		method.setAccessible(true);
		boolean output = (boolean) method.invoke(login_S, ReviewerUserName, ReviewerPassword, ReviewersInfoPath);
		assertEquals(true, output);
	}

	/**
	 * Testing the sign up function for Author
	 */
	@Test
	public void testAuthorSignup() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
		String authorUserName = "christian";
		String authorPassword = "1567";
		Method method = Signup.class.getDeclaredMethod("OpenAndWrite", String.class, String.class, String.class);
		method.setAccessible(true);
		method.invoke(signup, authorUserName, authorPassword, AuthorsInfoPath);
		Method method1 = Login_S.class.getDeclaredMethod("checker", String.class, String.class, String.class);
		method1.setAccessible(true);
		boolean output = (boolean) method1.invoke(login_S, authorUserName, authorPassword, AuthorsInfoPath);
		assertEquals(true, output);
	}

	/**
	 * Testing the sign up function for Reviewer
	 */
	@Test
	public void testReviewerSignup() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
		String ReviewerUserName = "Mok";
		String ReviewerPassword = "1562227";
		Method method = Signup.class.getDeclaredMethod("OpenAndWrite", String.class, String.class, String.class);
		method.setAccessible(true);
		method.invoke(signup, ReviewerUserName, ReviewerPassword, ReviewersInfoPath);
		Method method1 = Login_S.class.getDeclaredMethod("checker", String.class, String.class, String.class);
		method1.setAccessible(true);
		boolean output = (boolean) method1.invoke(login_S, ReviewerUserName, ReviewerPassword, ReviewersInfoPath);
		assertEquals(true, output);
	}

}
