package com.bayrak.hrms.dataAccess.abstracts;

import com.bayrak.hrms.entity.concretes.City;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CityDao extends JpaRepository<City, Integer> {}
