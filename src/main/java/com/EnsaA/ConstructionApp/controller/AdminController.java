package com.EnsaA.ConstructionApp.controller;

import com.EnsaA.ConstructionApp.model.Admin;
import com.EnsaA.ConstructionApp.model.Response;
import com.EnsaA.ConstructionApp.service.AdminService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.EnsaA.ConstructionApp.controller.CustomResponse.response;

@Slf4j
@RestController
@RequestMapping("/auth/admins")
@AllArgsConstructor
public class AdminController {
    private final AdminService adminService;


    @GetMapping(value = "/page/{pageNum}")
    public List<Admin> displayAllAdmins(@PathVariable(name = "pageNum") int pageNum, @RequestParam("pageSize") int pageSize) {
        Page<Admin> accountPage = adminService.showAllAdmin(pageNum, pageSize);
        return accountPage.getContent();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Admin> displayAdmin(@PathVariable(name = "id") int id) {
        return ResponseEntity.status(HttpStatus.OK).body(adminService.find(id));
    }

    @PostMapping
    public ResponseEntity<Admin> saveAdmin(@Valid @RequestBody Admin admin) {
        adminService.create(admin);
        return ResponseEntity.status(HttpStatus.OK).body(admin);
    }

    @PutMapping
    public ResponseEntity<Response> updateAdmin(@Valid @RequestBody Admin admin) {
        adminService.update(admin);
        return response("Admin successfully updated", HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Response> deleteAdmin(@PathVariable int id) {
        adminService.delete(id);
        return response("Admin successfully deleted", HttpStatus.NO_CONTENT);
    }

    @GetMapping("/count")
    public ResponseEntity<Long> countAdmin(){
        return ResponseEntity.status(HttpStatus.OK).body(adminService.count());
    }
}
