
package com.fixzone.repository;

import com.fixzone.model.ServiceRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ServiceRequestRepository
        extends JpaRepository<ServiceRequest, Long> {

    Optional<ServiceRequest> findByServiceId(String serviceId);
}