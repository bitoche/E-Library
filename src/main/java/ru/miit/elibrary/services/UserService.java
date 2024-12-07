package ru.miit.elibrary.services;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.miit.elibrary.models.User;
import ru.miit.elibrary.models.UserRole;
import ru.miit.elibrary.repository.IUserRepository;
import ru.miit.elibrary.repository.IUserRoleRepository;

import java.util.*;

@Service
@AllArgsConstructor
@Transactional
public class UserService{
    @Autowired
    private IUserRepository userRepository;
    @Autowired
    private IUserRoleRepository userRoleRepository;
    public List<UserRole> getAllUserRoles(){
        return userRoleRepository.findAll();
    }
    public List<User> getAll() {
        return userRepository.findAll();
    }

    public User getById(Long aLong) {
        return userRepository.findById(aLong).get();
    }
    public UserRole saveUserRole(UserRole userRole){
        return userRoleRepository.save(userRole);
    }

    public User save(User obj) {
        var allAvailableRoles = getAllUserRoles();
        Set<UserRole> grantedRoles = new HashSet<>();
        for (UserRole reqRole :
                obj.getRoles()){
            if(allAvailableRoles.stream().anyMatch(r->
                    Objects.equals(r.getRole_name(), reqRole.getRole_name()))){
                grantedRoles.add(
                        allAvailableRoles.stream().filter(r->
                                Objects.equals(r.getRole_name(), reqRole.getRole_name()))
                                .findFirst().get());
            }
        }
        for (UserRole grRole : grantedRoles){

        }
        obj.setRoles(grantedRoles);
        PasswordEncoder pe = new BCryptPasswordEncoder();
        obj.setPassword(pe.encode(obj.getPassword()));
        return userRepository.save(obj);
    }

    public void deleteById(Long aLong) {
        userRepository.deleteById(aLong);
    }
    public ResponseEntity<?> deleteById(Integer userRoleId){
        if(userRoleRepository.findById(userRoleId).isPresent()){
            userRoleRepository.deleteById(userRoleId);
            return ResponseEntity.ok("Successful deleting userRole="+userRoleRepository.findById(userRoleId).get().getRole_name());
        }
        return ResponseEntity.notFound().build();
    }
}
