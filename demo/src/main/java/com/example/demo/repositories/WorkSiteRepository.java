package com.example.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.models.WorkSite;

public interface WorkSiteRepository extends JpaRepository<WorkSite, Long> {

}
