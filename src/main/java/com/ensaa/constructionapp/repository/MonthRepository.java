package com.ensaa.constructionapp.repository;

import com.ensaa.constructionapp.model.Month;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface MonthRepository extends JpaRepository<Month,Integer> {
    Month getMonthByDateAndEmployeeEmployeeId(Date date,int i);
    List<Month> getMonthByEmployeeEmployeeId(int id);
}
