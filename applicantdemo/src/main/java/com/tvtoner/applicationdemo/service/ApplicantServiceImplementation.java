package com.tvtoner.applicationdemo.service;

import com.tvtoner.applicationdemo.dao.ApplicantRepository;
import com.tvtoner.applicationdemo.entity.Applicant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ApplicantServiceImplementation implements ApplicantService{

    private ApplicantRepository applicantRepository;

    //Constructor
    @Autowired
    public ApplicantServiceImplementation(ApplicantRepository applicantRepository){
        this.applicantRepository = applicantRepository;
    }

    //Methods given by service interface
    @Override
    public List<Applicant> findAll() {
        return applicantRepository.findAllByOrderByLastNameAsc();
    }

    @Override
    public Applicant findById(int theId) {

        Optional<Applicant> result = applicantRepository.findById(theId);

        Applicant theApplicant = null;

        if (result.isPresent()){
            theApplicant = result.get();
        }
        else {
            throw new RuntimeException("Did not find an applicant by the id of " + theId);
        }
        return theApplicant;
    }

    @Override
    public Applicant save(Applicant theApplicant) {
        return applicantRepository.save(theApplicant);
    }

    @Override
    public void deleteById(int theId) {
        applicantRepository.deleteById(theId);
    }
}
