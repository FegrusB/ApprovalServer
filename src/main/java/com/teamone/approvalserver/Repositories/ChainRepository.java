package com.teamone.approvalserver.Repositories;

import com.teamone.approvalserver.Models.ChainModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChainRepository extends JpaRepository<ChainModel, Integer> {
}
