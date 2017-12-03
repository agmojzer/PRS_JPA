package business;

import java.util.ArrayList;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import db.DBUtil;

public class ProductDB {

	public static Product getProductByID(int productId) {
			EntityManager em = DBUtil.getEmFactory().createEntityManager();
			try {
				Product p = em.find(Product.class, productId);
				return p;
			}
			finally {
				em.close();
			}
		}

	public static ArrayList<Product> getAllProducts() {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        try {
            TypedQuery <Product>query = em.createQuery("SELECT p FROM Product p", Product.class);
            ArrayList<Product> allProducts = new ArrayList<Product>(query.getResultList());
            return allProducts;
        }
        finally {
            em.close();
        }
    }
}
