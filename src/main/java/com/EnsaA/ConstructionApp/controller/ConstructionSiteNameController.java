package com.EnsaA.ConstructionApp.controller;

import com.EnsaA.ConstructionApp.model.ConstructionSiteName;
import com.EnsaA.ConstructionApp.model.Response;
import com.EnsaA.ConstructionApp.service.ConstructionSiteNameService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.EnsaA.ConstructionApp.controller.CustomResponse.response;

@RestController
@RequestMapping("/api/v1/ConstructionSiteName")
@AllArgsConstructor
public class ConstructionSiteNameController {

    private final ConstructionSiteNameService constructionSiteNameService;
    
    @GetMapping(value = "/page/{pageNum}")
    public List<ConstructionSiteName> displayAllConstructionSiteNames(@PathVariable(name = "pageNum") int pageNum, @RequestParam("pageSize") int pageSize) {
        Page<ConstructionSiteName> accountPage = constructionSiteNameService.showAllConstructionSiteNames(  pageNum, pageSize);
        return accountPage.getContent();
    }

    @GetMapping(value = "/page")
    public List<ConstructionSiteName> getAllConstructionSiteNames() {
        return constructionSiteNameService.getAllConstruction();
    }


    @GetMapping("/{id}")
    public ResponseEntity<ConstructionSiteName> displayConstructionSiteName(@PathVariable(name = "id") int id) {
        return ResponseEntity.status(HttpStatus.OK).body(constructionSiteNameService.find(id));
    }

    @PostMapping
    public ResponseEntity<ConstructionSiteName> saveConstructionSiteName(@Valid @RequestBody ConstructionSiteName ConstructionSiteName){
        constructionSiteNameService.create(ConstructionSiteName);
        return ResponseEntity.status(HttpStatus.OK).body(ConstructionSiteName);
    }

    @PutMapping
    public ResponseEntity<Response> updateConstructionSiteName(@Valid @RequestBody ConstructionSiteName ConstructionSiteName) {
        constructionSiteNameService.update(ConstructionSiteName);
        return response("ConstructionSite successfully updated", HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Response> deleteConstructionSiteName(@PathVariable int id) {
        constructionSiteNameService.delete(id);
        return response("ConstructionSite successfully deleted", HttpStatus.NO_CONTENT);
    }

    @GetMapping("/count")
    public ResponseEntity<Long> countConstructionSiteName() {
        return ResponseEntity.status(HttpStatus.OK).body(constructionSiteNameService.count());
    }
}
