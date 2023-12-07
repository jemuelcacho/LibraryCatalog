package main;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;

import data_structures.ArrayList;
import data_structures.DoublyLinkedList;
import data_structures.SinglyLinkedList;
import interfaces.FilterFunction;
import interfaces.List;
import data_structures.*;
public class LibraryCatalog {
	
	private List<Book> Books;
	private List<User> Users;
	
	public LibraryCatalog() throws IOException {
		this.Books = this.getBooksFromFiles();
		this.Users = this.getUsersFromFiles();
	}
	private List<Book> getBooksFromFiles() throws IOException {

		/*I am using the ArrayList ADT for two main reasons. One because it uses less space than any linked list
		and the other reason is two access each element is less complex. Also all the other methods they add at the last position or the
		parameter is by index making the getter O(1). The only exception will be in the removeBook were it will be O(N). */
		
		List<Book> result = new ArrayList<Book>();

		String path= "data/catalog.csv";
		String line="";
		int id=0;
		
		int index=0;
		String Author;
		String Genre;
		String Title;
		boolean isCheckedOut;
		LocalDate checkedOutDate = null;
		String[] values = {};
		
		
			
		try {
			BufferedReader br = new BufferedReader(new FileReader(path));
			while((line = br.readLine())!= null) {
				values = line.split(",");
				
				for(String i: values) {
					
					System.out.printf("%-10s", i);
					
				}
				System.out.println("");
		if(index!=0) {		
				id= Integer.parseInt(values[0]);
				Title= values[1];
				Author = values[2];
				Genre = values[3];
				checkedOutDate = LocalDate.parse(values[4]);
				isCheckedOut = Boolean.parseBoolean(values[5]);
				
				Book newBook = new Book(id,Title, Author, Genre, checkedOutDate, isCheckedOut);
				result.add(newBook);
		}
		
		index++;
		
			}
			
		}
		
		finally {
			
		}
			
		return result;
	}
	
	private List<User> getUsersFromFiles() throws IOException {		
		
	List<User> result = new ArrayList<User>();

	String path= "data/user.csv";
	String line="";
	int id=0;
	
	int index=0;
	String name; 
	
	String[] values = {};
	String[] books= {};	
	
		
	try {
		BufferedReader br = new BufferedReader(new FileReader(path));
		while((line = br.readLine())!= null) {
			values = line.split(",");
			
			for(String i: values) {
				
				System.out.printf("%-10s", i);

			}
			System.out.println("");
			
	if(index!=0) {
		id= Integer.parseInt(values[0]);
		name = values[1];
		List<Book> checked = new ArrayList<Book>();
		if(values.length >=3) {
			
			String bookstring = values[2].replaceAll("[{}]", "");
			System.out.println(bookstring);
			
				books = bookstring.split(" ");
				for(int j=0; j < books.length; j++) {
					
					int iBook = Integer.parseInt(books[j]) - 1;
					Book newBook = this.getBookCatalog().get(iBook);
					System.out.println(newBook.getTitle());
					System.out.println(newBook.calculateFees());
					checked.add(newBook);

			}
			System.out.println(checked.size());

		}
			User newUser = new User(id, name, checked);
			result.add(newUser);

		
		}
	index++;
	
		}
		
	}

	
	finally {	
	}
	for(User u: result) {
		System.out.println(u.getName()+ " with this id "+ u.getId() + " owes this amount of books: "+ u.getCheckedOutList().size());
	}
	
	return result;
	}
	
	public List<Book> getBookCatalog() {
		//create a double linked list for books cause it will be easier to add and remove data;
		
		
		return Books;
	}
	public List<User> getUsers() {
		
		return Users;
	}
	
	public void addBook(String title, String author, String genre) throws IOException {

		Book newBook = new Book(this.getBookCatalog().size() +1 , title, author, genre, LocalDate.parse("2023-09-15"), false);
		
		this.Books.add(newBook);
		return;
	}
	
	public void removeBook(int id) {
		Books.remove(id-1);
		return;
	}	
	
	public boolean checkOutBook(int id) {
		return Books.get(id).isCheckedOut();
	}
	public boolean returnBook(int id) {
		if(id >= this.getBookCatalog().size()) return false;
		
		Book changeBook = this.getBookCatalog().get(id-1);
		
		if(changeBook.isCheckedOut()) {
			changeBook.setCheckedOut(false);
			return true;
		}
		//use the double linked list
		return false;
	}
	
	public boolean getBookAvailability(int id) {
		//use the same librarycatalog
		return !Books.get(id -1).isCheckedOut();
	}
	
	public int bookCount(String title) {
		int count=0;
		
		for(int i=0; i < this.getBookCatalog().size(); i++) {
			if(this.getBookCatalog().get(i).getTitle().equals(title)) {
				count++;
			}
		}
		return count;
	}
	public int countGenre(String genre) {
		int count=0;
		
		for(int i=0; i < this.getBookCatalog().size(); i++) {
			if(this.getBookCatalog().get(i).getGenre().equals(genre)) {
				count++;
			}
		}
		return count;
	}
	
	
	public void generateReport() throws IOException {
		

		
		String output = "\t\t\t\tREPORT\n\n";
		output += "\t\tSUMMARY OF BOOKS\n";
		output += "GENRE\t\t\t\t\t\tAMOUNT\n";
		/*
		 * In this section you will print the amount of books per category.
		 * 
		 * Place in each parenthesis the specified count. 
		 * 
		 * Note this is NOT a fixed number, you have to calculate it because depending on the 
		 * input data we use the numbers will differ.
		 * 
		 * How you do the count is up to you. You can make a method, use the searchForBooks()
		 * function or just do the count right here.
		 */
		output += "Adventure\t\t\t\t\t" + this.countGenre("Adventure") + "\n";
		output += "Fiction\t\t\t\t\t\t" + this.countGenre("Fiction") + "\n";
		output += "Classics\t\t\t\t\t" + this.countGenre("Classics") + "\n";
		output += "Mystery\t\t\t\t\t\t" + this.countGenre("Mystery") + "\n";
		output += "Science Fiction\t\t\t\t\t" + this.countGenre("Science Fiction") + "\n";
		output += "====================================================\n";
		output += "\t\t\tTOTAL AMOUNT OF BOOKS\t" + this.Books.size() + "\n\n";
		
		/*
		 * This part prints the books that are currently checked out
		 */
		output += "\t\t\tBOOKS CURRENTLY CHECKED OUT\n\n";
		/*
		 * Here you will print each individual book that is checked out.
		 * 
		 * Remember that the book has a toString() method. 
		 * Notice if it was implemented correctly it should print the books in the 
		 * expected format.
		 * 
		 * PLACE CODE HERE
		 */
		int count=0;
		for(int i=0; i < this.getBookCatalog().size(); i++) {
			if(this.Books.get(i).isCheckedOut()) {
				output+= this.Books.get(i).getTitle() + "\n";
				count++;
			}
		}
		
		
		output += "====================================================\n";
		output += "\t\t\tTOTAL AMOUNT OF BOOKS\t" + count + "\n\n";
		
		
		/*
		 * Here we will print the users the owe money.
		 */
		output += "\n\n\t\tUSERS THAT OWE BOOK FEES\n\n";
		/*
		 * Here you will print all the users that owe money.
		 * The amount will be calculating taking into account 
		 * all the books that have late fees.
		 * 
		 * For example if user Jane Doe has 3 books and 2 of them have late fees.
		 * Say book 1 has $10 in fees and book 2 has $78 in fees.
		 * 
		 * You would print: Jane Doe\t\t\t\t\t$88.00
		 * 
		 * Notice that we place 5 tabs between the name and fee and 
		 * the fee should have 2 decimal places.
		 * 
		 * PLACE CODE HERE!
		 */
		float sum;
		float totalSum=0;
		for(int i=0; i < this.getUsers().size(); i++) {
			sum=0;
			List<Book> checkedOut = this.getUsers().get(i).getCheckedOutList();
			String FullName = this.getUsers().get(i).getName();
			
			for(int j=0; j < checkedOut.size(); j++) {
				sum+= checkedOut.get(j).calculateFees();
			}

				String formValue = String.format("0.2f", sum);
				output+= FullName +"\t\t\t\t\t" + "$" + sum + "\n";
				totalSum+= sum;
			}

			
		output += "====================================================\n";
		output += "\t\t\t\tTOTAL DUE\t$" + totalSum + "\n\n\n";
		output += "\n\n";
		System.out.println(output);// You can use this for testing to see if the report is as expected.
		
		/*
		 * Here we will write to the file.
		 * 
		 * The variable output has all the content we need to write to the report file.
		 * 
		 * PLACE CODE HERE!!
		 */		
			try {
		String path = "report/expected_report.txt";
		BufferedWriter bw = new BufferedWriter(new FileWriter(path));
		bw.write(output);
			}
			finally {
				
			}
		
	}
	
	/*
	 * BONUS Methods
	 * 
	 * You are not required to implement these, but they can be useful for
	 * other parts of the project.
	 */
	public List<Book> searchForBook(FilterFunction<Book> func) {
		return null;
	}
	
	public List<User> searchForUsers(FilterFunction<User> func) {
		return null;
	}
	
}
