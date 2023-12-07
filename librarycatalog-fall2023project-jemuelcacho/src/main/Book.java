package main;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Book {
	private int id;
	private String Title;
	private String Author;
	private String Genre;
	private LocalDate LastCheckOut;
	private boolean CheckedOut;
	

		/**
		 * 
		 * @param id the specific id of that book, not the location in the list.
		 * @param Title Title of the book.
		 * @param Author Writer of the book.
		 * @param Genre The name of the Genre of the book.
		 * @param LastCheckOut The last time that book was Checked Out.
		 * @param CheckedOut Whether the book is currently in the Library or not.
		 */
	public Book(int id, String Title, String Author, String Genre, LocalDate LastCheckOut, boolean CheckedOut) {
		this.id= id;
		this.Title= Title;
		this.Author = Author;
		this.Genre =  Genre;
		this.LastCheckOut= LastCheckOut;
		this.CheckedOut= CheckedOut;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id= id;
	}
	public String getTitle() {
		return Title;
	}
	public void setTitle(String title) {
		this.Title= title;
	}
	public String getAuthor() {
		return Author;
	}
	public void setAuthor(String author) {
		this.Author= author;
	}
	public String getGenre() {
		return Genre;
	}
	public void setGenre(String genre) {
		this.Genre= genre;
	}
	public LocalDate getLastCheckOut() {
		return LastCheckOut;
	}
	public void setLastCheckOut(LocalDate lastCheckOut) {
		this.LastCheckOut = lastCheckOut;
	}
	public boolean isCheckedOut() {
		return CheckedOut;
	}
	public void setCheckedOut(boolean checkedOut) {
		this.CheckedOut= checkedOut;
	}

	@Override
	public String toString() {
		/*
		 * This is supposed to follow the format
		 * 
		 * {TITLE} By {AUTHOR}
		 * 
		 * Both the title and author are in uppercase.
		 */
		String upperTitle;
		upperTitle = this.getTitle().toUpperCase();
		String upperAuthor;
		upperAuthor = this.getAuthor().toUpperCase();
		return upperTitle + " BY "+ upperAuthor;
	}
	/**
	 * 
	 * @return the member method of CalculateFees will calculate how much is the fee on that specific book. 
	 * If the days between today(2023-09-15) and the last day it was checked out are equal or greater than 31
	 * there will be fee and also a base fee of $10.
	 */
	public float calculateFees() {
		/*
		 * fee (if applicable) = base fee + 1.5 per additional day
		 */
		/**
		 * For the calculations of days in between I use an java.time import called ChronoUnit
		 * that will help no matter how long has been.
		 */
		LocalDate today = LocalDate.parse("2023-09-15");
		float fee=0;
		long daysBetween = ChronoUnit.DAYS.between(this.LastCheckOut,today);
		
		if(!this.CheckedOut) return 0;
		
		if(daysBetween >= 31) {
			fee = (float) (10 + 1.5*(daysBetween-31));
		}
		
	
		
		
		return fee;
	}
}
