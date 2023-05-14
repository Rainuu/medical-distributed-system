package com.example.dao;

import com.example.model.Patient;

import java.util.List;

public interface PatientMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Patient record);

    Patient selectByPrimaryKey(Long id);

    List<Patient> selectAll();

    int updateByPrimaryKey(Patient record);
}