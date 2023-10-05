//package com.EnsaA.ConstructionApp.controller;
//
//import com.EnsaA.ConstructionApp.model.User;
//import com.EnsaA.ConstructionApp.model.Response;
//import com.EnsaA.ConstructionApp.service.UserService;
//import com.EnsaA.ConstructionApp.service.UserService;
//import jakarta.validation.Valid;
//import lombok.AllArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.data.domain.Page;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//import static com.EnsaA.ConstructionApp.controller.CustomResponse.response;
//
//@Slf4j
//@RestController
//@RequestMapping("/auth/users")
//@AllArgsConstructor
//public class UserController {
//    private final UserService userService;
//
//
//    @GetMapping(value = "/page/{pageNum}")
//    public List<User> displayAllUsers(@PathVariable(name = "pageNum") int pageNum, @RequestParam("pageSize") int pageSize) {
//        Page<User> accountPage = userService.showAllUser(pageNum, pageSize);
//        return accountPage.getContent();
//    }
//
//    @GetMapping("/{id}")
//    public ResponseEntity<User> displayUser(@PathVariable(name = "id") int id) {
//        return ResponseEntity.status(HttpStatus.OK).body(userService.find(id));
//    }
//
//    @PostMapping
//    public ResponseEntity<User> saveUser(@Valid @RequestBody User user) {
//        userService.create(user);
//        return ResponseEntity.status(HttpStatus.OK).body(user);
//    }
//
//    @PutMapping
//    public ResponseEntity<Response> updateUser(@Valid @RequestBody User user) {
//        userService.update(user);
//        return response("User successfully updated", HttpStatus.OK);
//    }
//
//    @DeleteMapping("/{id}")
//    public ResponseEntity<Response> deleteUser(@PathVariable int id) {
//        userService.delete(id);
//        return response("User successfully deleted", HttpStatus.NO_CONTENT);
//    }
//
//    @GetMapping("/count")
//    public ResponseEntity<Long> countUser(){
//        return ResponseEntity.status(HttpStatus.OK).body(userService.count());
//    }
//}
