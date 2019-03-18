package model;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataOutputStream;

public class Paper {
	private int version;
	private String title;
	private String author;
	private String content;
	private boolean approved;
	
	public Paper (String paperName, String paperTitle) {
		this.author = "";
		this.version = 0;
		this.approved = false;
		this.title = paperTitle;
	}
	
	public String getContent() {
		return this.content;
	}
	
	public void setAuthor(String authorName) {
		this.author = authorName;
	}

	public String getAuthor() {
		return this.author;
	}
	
	public void setVersion(int paperVersion) {
		this.version = paperVersion;
	}
	
	public int getVersion() {
		return this.version;
	}

	public void setTitle(String titleName) {
		this.title = titleName;
	}

	public String getTitle() {
		return this.title;
	}
	
	public void approvePaper() {
		this.approved = true;
	}
	
	public void rejectPaper() {
		this.approved = false;
	}
	
	public boolean isApproved() {
		return this.approved;
	}
}
