package isepddiamniadio.pff.Gestion_performance.service;

import java.util.List;
import java.util.Optional;

import isepddiamniadio.pff.Gestion_performance.dao.UserRepository;
import isepddiamniadio.pff.Gestion_performance.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
    public class UserService {

        @Autowired
        private UserRepository userRepository;


        public List<isepddiamniadio.pff.Gestion_performance.entities.User> findAll() {
            return userRepository.findAll();
        }

        public void save(User user) {
           userRepository.save(user);
        }

        public Optional<isepddiamniadio.pff.Gestion_performance.entities.User> findByEmail(String email) {
            return userRepository.findByEmail(email);
        }

    }

