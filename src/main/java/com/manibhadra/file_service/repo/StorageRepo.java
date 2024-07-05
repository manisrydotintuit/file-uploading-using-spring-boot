package com.manibhadra.file_service.repo;

import com.manibhadra.file_service.model.ImageData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StorageRepo extends JpaRepository<ImageData, Long> {
    Optional<ImageData> findByName(String fileName);

    boolean existsByName(String name);
}
