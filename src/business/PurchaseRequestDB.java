package business;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import db.DBUtil;

public class PurchaseRequestDB {
	
	public static PurchaseRequest getPRbyId(int purchaserequestId) {
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		try {
			PurchaseRequest purchaserequest = em.find(PurchaseRequest.class, purchaserequestId);
			return purchaserequest;
		}finally {
			em.close();
		}
	}
	
	public static boolean addPurchaseRequest(PurchaseRequest pr){
		boolean success = false;
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		EntityTransaction et = em.getTransaction();
		
		try {
			et.begin();
			em.persist(pr);
			et.commit();
			success = true;
			
		}
		catch (Exception e) {
			e.printStackTrace();
			et.rollback();
		}
		finally {
			em.close();
		}
		return success;
	}
	

}
