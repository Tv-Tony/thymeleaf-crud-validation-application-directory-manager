package com.tvtoner.applicationdemo.dao;

import com.tvtoner.applicationdemo.entity.Applicant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface ApplicantRepository extends JpaRepository<Applicant, Integer> {

    /**
     * Uses spring technology, looks for keywords in the method name in this case, findAllBy and then OrderByLastNameAsc.
     * this way we don't even have to define the method in an implementation, and it is done all by Jpa repository
     * @return returns applicants ordered by last name
     */
    public List<Applicant> findAllByOrderByLastNameAsc();

}
