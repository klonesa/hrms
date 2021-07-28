package com.bayrak.hrms.controller;

import com.bayrak.hrms.model.City;
import com.bayrak.hrms.service.CityService;
import com.bayrak.hrms.utils.results.DataResult;
import com.bayrak.hrms.utils.results.Result;
import com.bayrak.hrms.utils.results.SuccessDataResult;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/cities")
public class CityController {

    private final CityService cityService;

    @GetMapping
    public DataResult<List<City>> findAll(){
        return new SuccessDataResult<>(cityService.getAll());
    }

    @GetMapping("/{id}")
    public DataResult<City> findById(@PathVariable int id){
        return new SuccessDataResult<>(cityService.findById(id));
    }



}
