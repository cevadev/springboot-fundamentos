package com.fundamentosplatzi.springboot.fundamentos.service;

import com.fundamentosplatzi.springboot.fundamentos.entity.User;
import com.fundamentosplatzi.springboot.fundamentos.repository.UserRepository;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class UserService {
    private static final Log log = LogFactory.getLog(UserService.class);

    // inyeccion de la dependencia UserRepository
    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // metodo de guardar un user que se realizara dentro de un contexto de transaccion de BD
    @Transactional
    public void saveTransactional(List<User> users){
        users.stream()
            // peek -> mostramos en pantalla cada elemento de la lista users
            .peek(user -> log.info("Added: " + user))
            .forEach(user -> userRepository.save(user));
    }

    public List<User> getAllUsers(){
        return userRepository.findAll();
    }
}
