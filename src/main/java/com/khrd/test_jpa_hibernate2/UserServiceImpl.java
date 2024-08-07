package com.khrd.test_jpa_hibernate2;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.PersistenceUnit;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class UserServiceImpl {
    private final UserRepository userRepository;

    @PersistenceContext
    private EntityManager entityManager;

    @PersistenceUnit
    private EntityManagerFactory entityManagerFactory;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    public Users createUser(UserRuquest userRuquest) {
        Users users = new Users();
        users.setName(userRuquest.getName());
        return userRepository.save(users);
    }

    @Transactional
    public Users edit(Integer id) {
        Optional<Users> users = userRepository.findById(id);

        if (users.isPresent()) {
            Users user = users.get();
            user.setName("Koko");

            entityManager.merge(user);
            return user;
        } else {
            System.out.println("No data...!");
        }
        return null;
    }

    @Transactional
    public Users getUserById(Integer id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            return entityManager.find(Users.class, id);
        } finally {
            entityManager.close();
        }
    }
}
