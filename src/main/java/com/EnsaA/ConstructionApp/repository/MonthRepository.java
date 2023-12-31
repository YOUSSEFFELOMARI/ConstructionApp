package com.EnsaA.ConstructionApp.repository;

import com.EnsaA.ConstructionApp.dto.MonthDto;
import com.EnsaA.ConstructionApp.model.Month;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Set;

@Repository
public interface MonthRepository extends JpaRepository<Month,Integer> {
    Month getMonthByDateAndEmployeeEmployeeId(Date date,int i);
    List<Month> getMonthByEmployeeEmployeeId(int id);
}
