package com.fixzone.service;

import com.fixzone.model.ServiceRequest;
import com.fixzone.repository.ServiceRequestRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
public class ServiceRequestService {

    private final ServiceRequestRepository repository;

    public ServiceRequestService(ServiceRequestRepository repository) {
        this.repository = repository;
    }

    // Create a new service request
    public ServiceRequest createService(ServiceRequest request) {

        String serviceId = generateServiceId();

        request.setServiceId(serviceId);

        // Initial status
        request.setStatus("RECEIVED");

        return repository.save(request);
    }

    // Get one service request using Service ID
    public Optional<ServiceRequest> getServiceById(String serviceId) {

        return repository.findByServiceId(serviceId);
    }

    // Get all service requests
    public List<ServiceRequest> getAllServices() {

        return repository.findAll();
    }

    // Update service status
    public Optional<ServiceRequest> updateStatus(
            String serviceId,
            String status) {

        Optional<ServiceRequest> service =
                repository.findByServiceId(serviceId);

        if (service.isPresent()) {

            ServiceRequest request = service.get();

            request.setStatus(status);

            return Optional.of(repository.save(request));
        }

        return Optional.empty();
    }

    // Generate Service ID
    private String generateServiceId() {

        Random random = new Random();

        int number = 10000 + random.nextInt(90000);

        return "FZ" + number;
    }
}