package com.example.swiftxyback.repositories;

import com.example.swiftxyback.model.DeepLink;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface DeeplinkRepository extends JpaRepository<DeepLink, UUID> {
    @Override
    Optional<DeepLink> findById(UUID uuid);
}
