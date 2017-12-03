package business;

import java.io.Serializable;
import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class User implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String username;
	private String password;
	private String firstName;
	private String lastName;
	private String phone;
	private String email;
	@Column(name = "isReviewer")
	private boolean reviewer;
	@Column(name = "isAdmin")
	private boolean admin;
	private Timestamp dateCreated;
	
	public User() {
		username = "";
		password ="";
	}

	public User(int id, String username, String password, String firstName, String lastName, String phone,
			String email, boolean reviewer, boolean admin) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.phone = phone;
		this.email = email;
		this.reviewer = reviewer;
		this.admin = admin;
	}
	
	public User(String un, String pw, String fn, String ln, String pn, String e, boolean m, boolean a) {
		setUsername(un);
		setPassword(pw);
		setFirstName(fn);
		setLastName(ln);
		setPhone(pn);
		setEmail(e);
		setReviewer(m);
		setAdmin(a);
	}
	
	public boolean isReviewer() {
		return reviewer;
	}

	public void setReviewer(boolean reviewer) {
		this.reviewer = reviewer;
	}

	public boolean isAdmin() {
		return admin;
	}

	public void setAdmin(boolean admin) {
		this.admin = admin;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Timestamp getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(Timestamp dateCreated) {
		this.dateCreated = dateCreated;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", password=" + password + ", firstName=" + firstName
				+ ", lastName=" + lastName + ", phone=" + phone + ", email=" + email + ", reviewer=" + reviewer
				+ ", admin=" + admin + ", dateCreated=" + dateCreated+"]";
	}
	
	

//	@Override
//	public String toString() {
//		String userinfo =
//				"User:	id=" +id+", username=" +username+", password="+password+", firstName="+firstName+", lastName="
//						+lastName+", phone="+phone+", email"+email+", reviewer="+reviewer+", admin="+admin +"\n";
//				return userinfo;
//		
//	}
	
}


