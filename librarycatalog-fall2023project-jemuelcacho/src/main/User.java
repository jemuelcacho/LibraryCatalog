package main;

import interfaces.List;


public class User {
	private int id;
	private String Name;
	private List<Book> checkedOutList;
	/**
	 * 
	 * @param id The id of the User of the library, is not the same as an index of a List.
	 * @param name The Full Name of the User.
	 * @param checkedOutList A list of Books that a User currently has. If the List is empty that means that does not have any.
	 */
	
	public User(int id, String name, List<Book> checkedOutList) {
		this.id=id;
		this.Name= name;
		this.checkedOutList= checkedOutList;
	}
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id=id;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		this.Name=name;
	}

	public List<Book> getCheckedOutList() {
		
		return checkedOutList;
	}

	public void setCheckedOutList(List<Book> checkedOutList) {
		this.checkedOutList= checkedOutList;
	}
	
}
