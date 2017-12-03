package business;

import javax.persistence.EntityManager;

import db.DBUtil;

public class StatusDB {
	
	public static Status getStatusByID(int id) {
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		try {
			Status status = em.find(Status.class, id);
			return status;
		}finally {
			em.close();
		}
	}

}
