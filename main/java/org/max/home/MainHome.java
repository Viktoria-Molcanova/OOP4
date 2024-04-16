package org.max.home;
package org.max.home.migration;
import org.flywaydb.core.Flyway;
import org.hibernate.HibernateException;
import org.hibernate.Metamodel;
import org.hibernate.query.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import javax.persistence.metamodel.EntityType;

import java.util.Map;

public class MainHome {
    private static final SessionFactory ourSessionFactory;

    static {
        try {
            Configuration configuration = new Configuration();
            configuration.configure();

            ourSessionFactory = configuration.buildSessionFactory();
        } catch (Throwable ex) {
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static Session getSession() throws HibernateException {
        return ourSessionFactory.openSession();
    }

    public class HomeMigration {
        public static void main(final String[] args) throws Exception {
            HomeMigration.main(args);
            // Создание экземпляра Flyway
            Flyway flyway = Flyway.configure()
                    .dataSource("jdbc:mysql://localhost:3306/main", "username", "password")
                    .locations("db/home/migration")
                    .load();
            // Выполнение миграции
            flyway.migrate();

            final Session session = getSession();
            try {
                System.out.println("Запрос всех управляемых сущностей...");
                final Metamodel metamodel = session.getSessionFactory().getMetamodel();
                for (EntityType<?> entityType : metamodel.getEntities()) {
                    final String entityName = entityType.getName();
                    final Query query = session.createQuery("from " + entityName);
                    System.out.println("Выполнение запроса: " + query.getQueryString());
                    for (Object o : query.list()) {
                        System.out.println("  " + o);
                    }
                }
            } finally {
                session.close();
            }
        }
    }
}