package isepddiamniadio.pff.Gestion_performance.service;

import java.util.List;
import java.util.Optional;

import isepddiamniadio.pff.Gestion_performance.dao.RoleRepository;
import isepddiamniadio.pff.Gestion_performance.entities.Role;
import jakarta.persistence.EntityExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

    @Service

    public class RoleService {

        @Autowired
        private RoleRepository roleRepository;

        public List<Role> getAllRoles() {
            return roleRepository.findAll();
        }

        public void create(Role role) {
            Optional<Role> dbRole= roleRepository.findById(role.getCode());
            if (dbRole.isPresent()) {
                throw new EntityExistsException("Le role dont le code est "+ role.getCode()+" existe deja.");
            }

            roleRepository.save(role);
        }

        public long count(){
            return  roleRepository.count();
        }
    }


