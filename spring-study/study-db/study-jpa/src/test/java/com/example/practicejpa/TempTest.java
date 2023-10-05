package com.example.practicejpa;

import com.example.practicejpa.entity.User;
import com.example.practicejpa.repository.UserRepository;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class TempTest {

    @Autowired
    UserRepository repository;

    @Autowired
    EntityManagerFactory emf;


    @Test
    void updateTest2() {
        EntityManager entityManager = emf.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();

        transaction.begin();

        User user = new User("name", 1, );


        Customer customer = new Customer();
        customer.setId(1L);
        customer.setFirstName("kyle");
        customer.setLastName("kim");

        entityManager.persist(customer); // 비영속 -> 영속

        Customer customer1 = entityManager.find(Customer.class, 1L);
        System.out.println(customer1);

        customer1.setLastName("Gang");

        // persistence context의 1차 캐시 모습을 찍어보면 됨

        Customer customer2 = entityManager.find(Customer.class, 1L);
        System.out.println(customer2);

        transaction.commit();
    }


}


class PersistenceContextTest {

    private final CustomerRepository customerRepository;
    private final EntityManagerFactory emf;

    @Autowired
    public PersistenceContextTest(CustomerRepository customerRepository, EntityManagerFactory emf) {
        this.customerRepository = customerRepository;
        this.emf = emf;
    }

    @BeforeEach
    void setUp() {
        customerRepository.deleteAll();
    }

    @Test
    void saveTest() {
        EntityManager entityManager = emf.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();

        transaction.begin();

        Customer customer = new Customer();
        customer.setId(1L);
        customer.setFirstName("kyle");
        customer.setLastName("kim");

        entityManager.persist(customer); // 비영속 -> 영속

        transaction.commit();
    }

    @Test
    @DisplayName("영속성 컨텍스트의 1차 캐시를 이용한 조회 -> SQL 쿼리문 X")
    void findTest1() {
        EntityManager entityManager = emf.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();

        transaction.begin();

        Customer customer = new Customer();
        customer.setId(1L);
        customer.setFirstName("kyle");
        customer.setLastName("kim");

        entityManager.persist(customer); // 비영속 -> 영속

        transaction.commit();

        entityManager.detach(customer); // 영속 -> 준영속

        Customer findCustomer = entityManager.find(Customer.class, 1L);
        log.info("{} {}", findCustomer.getFirstName(), findCustomer.getLastName());
    }

    @Test
    @DisplayName("영속성 컨텍스트의 1차 캐시를 이용하지 않은 조회 -> SQL 쿼리문 O")
    void findTest2() {
        EntityManager entityManager = emf.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();

        transaction.begin();

        Customer customer = new Customer();
        customer.setId(1L);
        customer.setFirstName("kyle");
        customer.setLastName("kim");

        entityManager.persist(customer); // 비영속 -> 영속

        transaction.commit();

        Customer findCustomer = entityManager.find(Customer.class, 1L);
        log.info("{} {}", findCustomer.getFirstName(), findCustomer.getLastName());
    }

    @Test
    void updateTest() {
        EntityManager entityManager = emf.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();

        transaction.begin();

        Customer customer = new Customer();
        customer.setId(1L);
        customer.setFirstName("kyle");
        customer.setLastName("kim");

        entityManager.persist(customer); // 비영속 -> 영속

        transaction.commit();

        // update
        transaction.begin();

        customer.setFirstName("alex");
        customer.setLastName("kwon");

        transaction.commit();
    }

    @Test
    void deleteTest() {
        EntityManager entityManager = emf.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();

        transaction.begin();

        Customer customer = new Customer();
        customer.setId(1L);
        customer.setFirstName("kyle");
        customer.setLastName("kim");

        entityManager.persist(customer); // 비영속 -> 영속

        transaction.commit();

        // delete
        transaction.begin();

        entityManager.remove(customer);

        transaction.commit();
    }

