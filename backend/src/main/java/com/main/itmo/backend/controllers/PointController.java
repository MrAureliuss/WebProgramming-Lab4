package com.main.itmo.backend.controllers;


import com.main.itmo.backend.DAO.PointRepository;
import com.main.itmo.backend.entity.Area;
import com.main.itmo.backend.entity.Point;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/api/point")
public class PointController {
    private final PointRepository pointRepository;
    private final Area area;

    PointController(PointRepository pointRepository, Area area) {
        this.pointRepository = pointRepository;
        this.area = area;
    }

    @CrossOrigin
    @PostMapping("/addEntry")
    ResponseEntity<?> newPoint(@RequestBody Point newPoint) {
        System.out.println("New point for " + newPoint.getUsername());
        if (newPoint.getR() < 0 || newPoint.getR() > 5 || newPoint.getX() < -3 || newPoint.getX() > 5 || newPoint.getY() < -3 || newPoint.getY() > 5) {
            return new ResponseEntity<>("Radius must be positive or X {'-3','-2','-1','0','1','2','3','4','5'}, Y less than 3 and more than -5",
                    HttpStatus.CONFLICT);
        }
        Point point=new Point(newPoint.getX(), newPoint.getY(), newPoint.getR(),
                area.isInArea(newPoint.getX(), newPoint.getY(), newPoint.getR()));
        point.setUsername(newPoint.getUsername());
        return new ResponseEntity<>(
                pointRepository.save(point),
                HttpStatus.OK);
    }

    @CrossOrigin
    @RequestMapping("/getEntries")
    ResponseEntity<?> allPoints(@RequestParam(value = "username") String username) {
        System.out.println("all points request from " + username);
        return new ResponseEntity<>(
                pointRepository.findAllUserPointsById(username),
                HttpStatus.OK);
    }
}
