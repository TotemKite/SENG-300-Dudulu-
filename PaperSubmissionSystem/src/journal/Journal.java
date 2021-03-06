package journal;
import java.util.ArrayList;
import java.util.List;
import java.io.*;

public class Journal {
	// all private variable for the journal
	private String author;
	private int version; // ask proof for detail
	private ArrayList<String> majorOpinions = new ArrayList<>();
	private ArrayList<String> minorOpinions = new ArrayList<>();
	private String title = "src/journals/";
	// 0 = submitting
	// 1 = submitted
	// 2 = created process
	// 3 = under review
	// 4 = major opinion accept
	// 5 = major opinion reject
	// 6 = minor opinion accept
	// 7 = minor opinion reject
	private int status;

	// init a journal with author name and the title name
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

//	public void intergardeVersion() {
//		this.version++;
//	}

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

	public void nextProcess() {
		this.status++;
//		if(this.status > )
	}

//	public ArrayList<String> getJournal() {
//		ArrayList<String> thisJournal = new ArrayList<>();
//		try {
////			File file = new File(this.title);
////			FileReader fr;
////			fr = new FileReader(file);
//			System.out.println(this.title);
//			BufferedReader br = new BufferedReader(new FileReader(this.title));
//			String line;
//			while((line = br.readLine()) != null){
//			    //process the line
//			    thisJournal.add(line);
//			}
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		return thisJournal;
//		
//	}

	// get the journal content as string
	public String getJournal() {
		String thisJournal = "";
		try {
//			File file = new File(this.title);
//			FileReader fr;
//			fr = new FileReader(file);
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
