//package com.EnsaA.ConstructionApp.service;
//
//import com.EnsaA.ConstructionApp.model.User;
//import com.EnsaA.ConstructionApp.model.User;
//import com.EnsaA.ConstructionApp.repository.UserRepository;
//import com.EnsaA.ConstructionApp.repository.UserRepository;
//import jakarta.persistence.EntityExistsException;
//import jakarta.persistence.EntityNotFoundException;
//import lombok.AllArgsConstructor;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.PageRequest;
//import org.springframework.data.domain.Pageable;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Service;
//
//@Service
//@AllArgsConstructor
//public class UserService {
//
//    private final UserRepository userRepository;
//
//    private final PasswordEncoder passwordEncoder;
//
//    public Page<User> showAllUser(int pageNum, int pageSize) {
//        Pageable pageable = PageRequest.of(pageNum - 1, pageSize);
//        return userRepository.findAll(pageable);
//    }
//
//    public void create(User user) {
//        user.setPassword(passwordEncoder.encode(user.getPassword()));
//        if (userRepository.existsById(user.getUserId()))
//            throw new EntityExistsException("User already stored in database - ID : "+user.getUserId()) {};
//        userRepository.save(user);
//    }
//
//    public void delete(int id) {
//        if (!userRepository.existsById(id))
//            throw new EntityNotFoundException("User not found - ID : "+id) {};
//        userRepository.deleteById(id);
//    }
//
//    public void update(User user) {
//        if (!userRepository.existsById(user.getUserId()))
//            throw new EntityNotFoundException("User not found - ID : "+user.getUserId()) {};
//        userRepository.save(user);
//    }
//
//    public User find(int id) {
//        return userRepository.findById(id).orElseThrow(() ->
//                new EntityNotFoundException("User not found - ID : "+id) {});
//    }
//
//    public long count() {
//        return userRepository.count();
//    }
//}
