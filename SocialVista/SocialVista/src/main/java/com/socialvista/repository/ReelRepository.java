package com.socialvista.repository;

import com.socialvista.model.Reel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReelRepository extends JpaRepository<Reel,Integer> {
    public List<Reel> findByUserId(Integer userid);


}
