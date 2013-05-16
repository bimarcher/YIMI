package com.yimi.archer.eo;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.junit.Test;

import com.yimi.archer.util.HibernateUtil;

public class UserEOTest {
	@Test
	public void test01() {

		Session session = null;
		try {
			UserEO u = new UserEO();
			u.setName("张三");
			u.setPs("123");

			session = HibernateUtil.openSession();
			// 开启事务
			session.beginTransaction();

			session.save(u);
			// 提交事务
			HibernateUtil.commitTransaction(session);
		} catch (HibernateException e) {
			e.printStackTrace();
			if (session != null)
				HibernateUtil.rollbackTransaction(session);
		} finally {
			if (session != null)
				session.close();
		}
	}
}
