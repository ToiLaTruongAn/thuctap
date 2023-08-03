package com.project.urban.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.urban.Entity.Position;

public interface PositionRepository extends JpaRepository<Position, Long>{

}
