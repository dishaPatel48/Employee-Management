package com.organization.employeeManagement.controller;

import com.organization.employeeManagement.dto.OrganizationDTO;
import com.organization.employeeManagement.dto.OrganizationObjDTO;
import com.organization.employeeManagement.dto.OrganizationFetchDTO;
import com.organization.employeeManagement.service.OrganizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/organizations")
public class OrganizationController {

    @Autowired
    private OrganizationService organizationService;

    @GetMapping()
    public ResponseEntity<List<OrganizationObjDTO>> getOrganizations() {
        List<OrganizationObjDTO> organizations = organizationService.getOrganizations();
        return ResponseEntity.ok(organizations);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrganizationFetchDTO> getOrganization(@PathVariable int id) {
        OrganizationFetchDTO organization = organizationService.getOrganization(id);
        return ResponseEntity.ok(organization);
    }

    @PostMapping()
    public ResponseEntity<Void> addOrganization(@RequestBody OrganizationDTO dto) {
        organizationService.addOrganization(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(null);
    }

    @PutMapping()
    public ResponseEntity<Void> updateOrganization(@RequestBody OrganizationDTO dto, Integer id) {
        organizationService.updateOrganization(dto, id);
        return ResponseEntity.status(HttpStatus.CREATED).body(null);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrganization(@PathVariable Integer id) {
        organizationService.deleteOrganization(id);
        return ResponseEntity.status(HttpStatus.CREATED).body(null);
    }


}
