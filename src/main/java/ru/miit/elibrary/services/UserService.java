package ru.miit.elibrary.services;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
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
    public boolean existsByRoleName(String roleName){ // существует ли роль с названием roleName
        return userRoleRepository.existsUserRoleByRoleName(roleName);
    }
    public UserRole saveUserRole(UserRole ur){return userRoleRepository.save(ur);}
    public UserRole getUserRoleByRole_name(String role_name){
        if (existsByRoleName(role_name)){
            return userRoleRepository.getUserRoleByRoleName(role_name);
        }
        return null;
    }
    public List<User> getAll() {
        return userRepository.findAll();
    }

    public User getById(Long aLong) {
        return userRepository.findById(aLong).get();
    }

    public boolean save(User obj) {
        // убираем на бэке возможность регать два аккаунта на одну почту
        if (userRepository.findAppUserByEmail(obj.getEmail()).isPresent()){ // пользователь уже существует?
            return false;
        }
        castStringArrRolesToType(obj); // заменяем без id из запроса на роли из бд
        //устанавливаем хешированный пароль
        PasswordEncoder pe = new BCryptPasswordEncoder();
        obj.setPassword(pe.encode(obj.getPassword()));
        userRepository.save(obj);
        return true;
    }
    //различие с save в том, что здесь нам НЕОБХОДИМО, чтобы пользователь уже существовал
    public boolean update(User obj){
        if (userRepository.findAppUserByEmail(obj.getEmail()).isEmpty()){ // пользователь не существует?
            return false;
        }
        castStringArrRolesToType(obj); // заменяем без id из запроса на роли из бд
        //устанавливаем хешированный пароль
        PasswordEncoder pe = new BCryptPasswordEncoder();
        obj.setPassword(pe.encode(obj.getPassword()));
        userRepository.save(obj);
        return true;
    }
    private User castStringArrRolesToType(User obj){
        var allAvailableRoles = getAllUserRoles();
        Set<UserRole> grantedRoles = new HashSet<>();
        for (UserRole reqRole :
                obj.getRoles()){
            if(allAvailableRoles.stream().anyMatch(r->
                    Objects.equals(r.getRoleName(), reqRole.getRoleName()))){
                grantedRoles.add(
                        allAvailableRoles.stream().filter(r->
                                        Objects.equals(r.getRoleName(), reqRole.getRoleName()))
                                .findFirst().get());
            }
        }
        if (grantedRoles.isEmpty()){ // если нет ни одной роли даем deactivated
            grantedRoles.add(
                    allAvailableRoles.stream().filter(
                                    r-> Objects.equals(r.getRoleName(), "DEACTIVATED"))
                            .findFirst().get());
        }
        obj.setRoles(grantedRoles);
        return obj;
    }
    public void deleteById(Long aLong) {
        userRepository.deleteById(aLong);
    }
    public ResponseEntity<?> deleteById(Integer userRoleId){
        if(userRoleRepository.findById(userRoleId).isPresent()){
            userRoleRepository.deleteById(userRoleId);
            return ResponseEntity.ok("Successful deleting userRole="+userRoleRepository.findById(userRoleId).get().getRoleName());
        }
        return ResponseEntity.notFound().build();
    }

    public boolean existsByUsername(String email){
        return userRepository.findAppUserByEmail(email).isPresent();
    }
    public User getUserByUsername(String email){
        return existsByUsername(email) ? userRepository.findAppUserByEmail(email).get() : null;
    }
}
