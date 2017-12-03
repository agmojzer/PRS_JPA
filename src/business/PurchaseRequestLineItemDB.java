package business;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import db.DBUtil;

public class PurchaseRequestLineItemDB {
	
	public static PurchaseRequestLineItem getPRLIById(int Id) {
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		try {
			PurchaseRequestLineItem lineitem = em.find(PurchaseRequestLineItem.class, Id);
			return lineitem;
		} finally {
			em.close();
		}
	}
	
	public static boolean addPRLI(PurchaseRequestLineItem prli) {
		boolean success = false;
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		EntityTransaction et = em.getTransaction();
		try {
			et.begin();
			em.persist(prli);
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
	
	public static void deletePRLI(int id){
		PurchaseRequestLineItem li = getPRLIById(id);
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		EntityTransaction et = em.getTransaction();
		try {
			et.begin();
			em.persist(li);
			et.commit();
		
		}catch (Exception e) {
			e.printStackTrace();
			et.rollback();
		
		}finally {
			em.close();
		}
	}
}
