package com.bayrak.hrms.repository;

import com.bayrak.hrms.model.City;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CityDao extends JpaRepository<City, Integer> {}
