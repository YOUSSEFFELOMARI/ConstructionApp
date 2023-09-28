package com.EnsaA.ConstructionApp.controller;

import com.EnsaA.ConstructionApp.model.ConstructionSite;
import com.EnsaA.ConstructionApp.model.Response;
import com.EnsaA.ConstructionApp.service.ConstructionSiteService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.EnsaA.ConstructionApp.controller.CustomResponse.response;

@Slf4j
@RestController
@RequestMapping("/api/v1/ConstructionSites")
@AllArgsConstructor
public class ConstructionSiteController {
    private final ConstructionSiteService constructionSiteService;

    @GetMapping(value = "/page/{pageNum}")
    public List<ConstructionSite> displayAllConstructionSites(@PathVariable(name = "pageNum") int pageNum, @RequestParam("pageSize") int pageSize) {
        Page<ConstructionSite> accountPage = constructionSiteService.showAllConstructionSites(pageNum, pageSize);
        return accountPage.getContent();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ConstructionSite> displayConstructionSite(@PathVariable(name = "id") int id) {
        return ResponseEntity.status(HttpStatus.OK).body(constructionSiteService.find(id));
    }

    @PostMapping
    public ResponseEntity<ConstructionSite> saveConstructionSite(@Valid @RequestBody ConstructionSite admin) {
        constructionSiteService.create(admin);
        return ResponseEntity.status(HttpStatus.OK).body(admin);
    }

    @PutMapping
    public ResponseEntity<Response> updateConstructionSite(@Valid @RequestBody ConstructionSite admin) {
        constructionSiteService.update(admin);
        return response("ConstructionSite successfully updated", HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Response> deleteConstructionSite(@PathVariable int id) {
        constructionSiteService.delete(id);
        return response("ConstructionSite successfully deleted", HttpStatus.NO_CONTENT);
    }

    @GetMapping("/count")
    public ResponseEntity<Long> countConstructionSite(){
        return ResponseEntity.status(HttpStatus.OK).body(constructionSiteService.count());
    }
}
