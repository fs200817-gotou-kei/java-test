package com.example.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.models.WorkSite;

@Repository
public interface WorkSiteRepository extends JpaRepository<WorkSite, Long> {

}
