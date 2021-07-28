package com.bayrak.hrms.service;

import com.bayrak.hrms.exception.CityNotFoundException;
import com.bayrak.hrms.model.City;
import com.bayrak.hrms.repository.CityDao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CityService {

    private final CityDao cityDao;

    public City findById(int id) {
        return cityDao.findById(id).orElseThrow(() -> {
            throw new CityNotFoundException(id);
        });
    }

    public List<City> getAll(){
        return cityDao.findAll();
    }
}
