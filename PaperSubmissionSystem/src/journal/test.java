package journal;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ArrayList<String> a = new ArrayList<>();
		String fileName = "test2.txt";
		String b;
		Journal journal = new Journal("a", fileName);
		journal.writeJournal("here is a simple sample file@!#");
		b = journal.getJournal();
//		b = b.substring(6);
//		a = journal.getJournal();
		System.out.println(journal.getJournal());
//		journal.deleteJournal();
//		journal.writeJournal();
		
	}

}
