package com.rentagirlfriend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rentagirlfriend.entities.Profile;

public interface ProfileRepository extends JpaRepository<Profile, Long> {
    
}
