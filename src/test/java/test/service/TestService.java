package test.service;

import java.sql.SQLException;
import java.util.UUID;

import org.hibernate.FlushMode;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

import test.domain.TestEntity;

public class TestService extends HibernateDaoSupport {

	private TransactionTemplate transactionTemplate;

	public void doTest() {
		transactionTemplate.execute(new TransactionCallback<Void>() {

			@Override
			public Void doInTransaction(TransactionStatus status) {
				getHibernateTemplate().execute(new HibernateCallback<Void>() {

					@Override
					public Void doInHibernate(Session session) throws HibernateException, SQLException {
						session.setFlushMode(FlushMode.MANUAL);

						TestEntity testEntity = new TestEntity();
						testEntity.setGuid(UUID.randomUUID());
						testEntity.setLabel("foo");
						session.save(testEntity);
						testEntity.setLabel("bar");
						session.save(testEntity);

						session.flush();
						return null;
					}
				});
				return null;
			}

		});

	}

	public void setTransactionTemplate(TransactionTemplate transactionTemplate) {
		this.transactionTemplate = transactionTemplate;
	}

}
