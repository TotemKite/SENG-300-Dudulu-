package model;

import java.io.File;
import java.util.Date;
import java.util.ArrayList;

public class Paper {
	private File file;
	private String recdate;
	public static String pathToDir = "../journals/";
	
	public Paper(File upload) {
		this.setFile(upload);
		Date datenow = new Date();
		this.setRecdate(datenow.toString());
	}
	
	public static ArrayList<Paper> loadJournalEntries() {
		ArrayList<Paper> uploaded = new ArrayList<Paper>();
        File file = new File(pathToDir);
        File[] files = file.listFiles();
        for(File fi: files){
            Paper loaded = new Paper(fi);
            uploaded.add(loaded);
        }
        return uploaded;
	}

	public String getRecdate() {
		return recdate;
	}

	public void setRecdate(String recdate) {
		this.recdate = recdate;
	}

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}
}
