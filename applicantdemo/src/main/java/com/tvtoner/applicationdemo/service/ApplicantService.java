package com.tvtoner.applicationdemo.service;

import com.tvtoner.applicationdemo.entity.Applicant;

import java.util.List;

public interface ApplicantService {

    List<Applicant> findAll();

    Applicant findById(int theId);

    Applicant save(Applicant theApplicant);

    void deleteById(int theId);
}
