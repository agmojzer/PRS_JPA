package business;
import java.time.LocalDate;
import java.util.ArrayList;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import java.text.NumberFormat;

@Entity
public class PurchaseRequest {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@ManyToOne
	@JoinColumn(name = "UserID")
	private User user;
	private String description;
	private String justification;
	private LocalDate dateNeeded;
	private String deliveryMode;
	@ManyToOne
	@JoinColumn(name = "StatusID")
	private Status status;
	private String total;
	private LocalDate submittedDate;
	@OneToMany (fetch = FetchType.EAGER, cascade=CascadeType.ALL)
	@JoinColumn(name="PurchaseRequestID")
	private ArrayList <PurchaseRequestLineItem> prLineItems;
	
	public PurchaseRequest(int id, User user, String description, String justification, LocalDate dateNeeded, String deliveryMode,
			Status status, String total, LocalDate submittedDate, ArrayList <PurchaseRequestLineItem> prLineItems) {
		super();
		this.id = id;
		this.user = user;
		this.description = description;
		this.justification = justification;
		this.dateNeeded = dateNeeded;
		this.deliveryMode = deliveryMode;
		this.status = status;
		this.total = total;
		this.submittedDate = submittedDate;
		this.prLineItems = prLineItems;
	}
	
	public PurchaseRequest() {
		id = 0;
		user=null;
		description = "";
		justification = "";
		dateNeeded = LocalDate.now();
		deliveryMode = "";
		status = null;
		total = "";
		submittedDate = LocalDate.now();
		prLineItems = new ArrayList<>();
		
		
	}
	
	public PurchaseRequest(User user, String desc, String just, LocalDate dn, String dlvmode, 
			Status status, String total, LocalDate sd) {
		setUser(user);
		setDescription(desc);
		setJustification(just);
		setDateNeeded(dn);
		setDeliveryMode(dlvmode);
		setStatus(status);
		setTotal(total);
		setSubmittedDate(sd);
	}
	
	public ArrayList<PurchaseRequestLineItem> getLineItems() {
		return prLineItems;
	}

	public LocalDate getDateNeeded() {
		return dateNeeded;
	}


	public void setDateNeeded(LocalDate dateNeeded) {
		this.dateNeeded = dateNeeded;
	}


	public LocalDate getSubmittedDate() {
		return submittedDate;
	}


	public void setSubmittedDate(LocalDate submittedDate) {
		this.submittedDate = submittedDate;
		
	}


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public String getJustification() {
		return justification;
	}

	public void setJustification(String justification) {
		this.justification = justification;
	}

	public String getDeliveryMode() {
		return deliveryMode;
	}

	public void setDeliveryMode(String deliveryMode) {
		this.deliveryMode = deliveryMode;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public String getTotal() {
		return total;
	}

	public void setTotal(String total) {
		this.total = total;
	}
	
	public void addLineItem(PurchaseRequestLineItem prli) {
		prLineItems.add(prli);
	}

	

	@Override
	public String toString() {
		return "PurchaseRequest [id=" + id + ", user=" + user + ", description=" + description + ", justification="
				+ justification + ", dateNeeded=" + dateNeeded + ", deliveryMode=" + deliveryMode + ", status=" + status
				+ ", total=" +total + ", submittedDate=" + submittedDate + ", prLineItems=" + prLineItems+"]\n";
		
		//@Override
		//public String toString() {
		//	return "PurchaseRequest [id=" + id + ", user=" + user + ", description=" + description + ", justification="
		//			+ justification + ", dateNeeded=" + dateNeeded + ", deliveryMode=" + deliveryMode + ", status=" + status
		//			+ ", total=" +total + ", submittedDate=" + submittedDate +"]\n";
	}
}	
	