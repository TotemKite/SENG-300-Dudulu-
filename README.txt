To use the journal system
create a journal first  
	Journal j = new Journal(authorName, journalFileName);
then create the journal content and the local file
	j.writeJournal("content");
to display the journal
	String contentOfJournal = j.getJournal;
to delete the journal
	j.deleteJournal();