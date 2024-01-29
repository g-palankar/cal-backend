package com.cal.calbackend.nutritionalInfo.repository;

import com.cal.calbackend.nutritionalInfo.model.NutritionalInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NutritionalInfoRepository extends JpaRepository<NutritionalInfo, Long> {
    boolean existsById(Long id);
}
