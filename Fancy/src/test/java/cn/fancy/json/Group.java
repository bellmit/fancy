package cn.fancy.json;

import java.util.ArrayList;
import java.util.List;

public class Group {

	private Long id;
	private String name;
	private List<Dog> dog = new ArrayList<Dog>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void addDog(Dog dogs) {
		dog.add(dogs);
	}

	public List<Dog> getDog() {
		return dog;
	}

	public void setDog(List<Dog> dog) {
		this.dog = dog;
	}


	
}