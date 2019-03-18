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

public class Journal {
	private int status;
	private int version; // ask proof for detail
	private String author;
	public String title = "src/journals";
	private ArrayList<String> majorOpinions = new ArrayList<>();
	private ArrayList<String> minorOpinions = new ArrayList<>();
	
	// Load a journal with author name and the title name
	// which used for searching for the file for the journal
	public Journal(String authorName, String titleName) {
		this.version = 0;
		this.author = authorName;
		this.title += titleName;
		this.status = 0;
	}
	
	// bunch of set and get methods for the variables
	// may not used for now
	public void setAuthor(String authorName) {
		this.author = authorName;
	}

	public String getAuthor() {
		return this.author;
	}
	
	public int getVersion() {
		return this.version;
	}

	public void settitle(String titleName) {
		this.title = titleName;
	}

	public String gettitle() {
		return this.title;
	}

	public void addMajorOpinions(String majorOpinion) {
		this.majorOpinions.add(majorOpinion);
	}

	public ArrayList<String> getMajorOpinions() {
		return this.majorOpinions;
	}

	public void addMinorOpinions(String minorOpinion) {
		this.minorOpinions.add(minorOpinion);
	}

	public ArrayList<String> getMinorOpinions() {
		return this.minorOpinions;
	}
	
	public int getStatus() {
		return this.status;
	}

	public void submit() {
		this.version++; // increment the version number
		this.minorOpinions.clear(); // clear all the opinions for
		this.majorOpinions.clear(); // the previous version
		this.status = 1; // change the process to submitted
	}

	// get the journal content as string
	public String getJournal() {
		String thisJournal = "";
		try {
			System.out.println(this.title);
			BufferedReader br = new BufferedReader(new FileReader(this.title));
			String line;
			while ((line = br.readLine()) != null) {
				// process the line
				thisJournal += line;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		thisJournal.substring(6);
		System.out.println(thisJournal);
		return thisJournal;
	}
	
	// create and write the journal content file
	public void writeJournal(String content) {
		String fileContent = content;

		FileOutputStream outputStream;
		try {
			outputStream = new FileOutputStream(this.title);
			DataOutputStream dataOutStream = new DataOutputStream(new BufferedOutputStream(outputStream));
			dataOutStream.writeUTF(fileContent);

			dataOutStream.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	// delete the whole journal and point author to null
	// also delete the journal file
	public void deleteJournal() {
		File file = new File(this.title);
		file.delete();
		this.author = null;
		this.version = -1;
		this.title = null;
		this.minorOpinions.clear(); // clear all the opinions for
		this.majorOpinions.clear(); // the previous version
		this.status = -1;
	}
}
