package com.main.itmo.backend.DAO;

import com.main.itmo.backend.entity.Point;
import com.main.itmo.backend.entity.User;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Collection;

public interface PointRepository extends JpaRepository<Point, Long> {
    @Query("SELECT u FROM Point u WHERE u.username = :username")
    Collection<Point> findAllUserPointsById(@Param("username") String username);
}