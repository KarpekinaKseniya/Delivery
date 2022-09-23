package com.self.education.delivery.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import com.self.education.delivery.domain.Area;

public interface AreaRepository extends JpaRepository<Area, Long>, JpaSpecificationExecutor<Area> {
}
