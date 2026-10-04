package com.fixzone.controller;

import com.fixzone.model.ServiceRequest;
import com.fixzone.service.ServiceRequestService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/services")
@CrossOrigin(origins = "*")
public class ServiceController {

    private final ServiceRequestService serviceRequestService;

    public ServiceController(ServiceRequestService serviceRequestService) {
        this.serviceRequestService = serviceRequestService;
    }

    // Create a new service request
    @PostMapping
    public ResponseEntity<ServiceRequest> createService(
            @RequestBody ServiceRequest request) {

        ServiceRequest savedRequest =
                serviceRequestService.createService(request);

        return ResponseEntity.ok(savedRequest);
    }

    // Get all service requests
    @GetMapping
    public ResponseEntity<List<ServiceRequest>> getAllServices() {

        List<ServiceRequest> services =
                serviceRequestService.getAllServices();

        return ResponseEntity.ok(services);
    }

    // Get one service request using Service ID
    @GetMapping("/{serviceId}")
    public ResponseEntity<ServiceRequest> getService(
            @PathVariable String serviceId) {

        Optional<ServiceRequest> service =
                serviceRequestService.getServiceById(serviceId);

        if (service.isPresent()) {
            return ResponseEntity.ok(service.get());
        }

        return ResponseEntity.notFound().build();
    }

    // Update service status
    @PutMapping("/{serviceId}/status")
    public ResponseEntity<ServiceRequest> updateStatus(
            @PathVariable String serviceId,
            @RequestParam String status) {

        Optional<ServiceRequest> updatedService =
                serviceRequestService.updateStatus(serviceId, status);

        if (updatedService.isPresent()) {
            return ResponseEntity.ok(updatedService.get());
        }

        return ResponseEntity.notFound().build();
    }
}