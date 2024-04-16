package org.max.home;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.max.seminar.CurrentEntity;

import javax.persistence.PersistenceException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Optional;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class CustomerTest extends AbstractTest{
    @Test
    @Order(1)
    void createCustomer_whenValid_shouldCreate() {
        //given
        CustomersEntity newCustomer = new CustomersEntity();
        newCustomer.setCustomerId(20);
        newCustomer.setName("Иванов");
        newCustomer.setEmail("ivanov@example.com");

        //when
        Session session = getSession();
        session.beginTransaction();
        session.save(newCustomer);
        session.getTransaction().commit();

        //then
        final Query query = getSession()
                .createSQLQuery("SELECT * FROM customers WHERE customer_id=" + 20).addEntity(CustomersEntity.class);
        Optional<CustomersEntity> createdCustomer = (Optional<CustomersEntity>) query.uniqueResultOptional();
        Assertions.assertTrue(createdCustomer.isPresent());
    }

    @Test
    @Order(2)
    void updateCustomer_whenValid_shouldUpdate() {

        final Query query = getSession()
                .createSQLQuery("SELECT * FROM customers WHERE customer_id=" + 20).addEntity(CustomersEntity.class);
        Optional<CustomersEntity> customerToUpdate = (Optional<CustomersEntity>) query.uniqueResultOptional();
        Assumptions.assumeTrue(customerToUpdate.isPresent());


        Session session = getSession();
        session.beginTransaction();
        customerToUpdate.get().setName("Петров");
        session.update(customerToUpdate.get());
        session.getTransaction().commit();


        final Query queryAfterUpdate = getSession()
                .createSQLQuery("SELECT * FROM customers WHERE customer_id=" + 20).addEntity(CustomersEntity.class);
        Optional<CustomersEntity> updatedCustomer = (Optional<CustomersEntity>) queryAfterUpdate.uniqueResultOptional();
        Assertions.assertEquals("Петров", updatedCustomer.get().getName());
    }

    @Test
    @Order(3)
    void retrieveCustomer_whenValid_shouldRetrieve() {
        //given
        final Query query = getSession()
                .createSQLQuery("SELECT * FROM customers WHERE customer_id=" + 20).addEntity(CustomersEntity.class);

        //when
        Optional<CustomersEntity> retrievedCustomer = (Optional<CustomersEntity>) query.uniqueResultOptional();

        //then
        Assertions.assertTrue(retrievedCustomer.isPresent());
    }


}
