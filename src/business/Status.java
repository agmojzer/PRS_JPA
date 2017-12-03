package business;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Status {
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private int id;
	private String description;
	
	public Status() {
		id = 0;
		description = "";
	}
	
	
	public Status(int id, String description) {
		super();
		this.id = id;
		this.description = description;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}


	@Override
	public String toString() {
		return "Status [id=" + id + ", description=" + description + "]";
	}
	
	

}
