package com.main.itmo.backend.entity;

import org.springframework.stereotype.Component;
import com.main.itmo.backend.entity.Point;

@Component
public class Area {
    private boolean check(Double x, Double y, Double r) {
        boolean triangle = x <= 0 && y <= 0 && y >= -x-r/2;
        boolean square = x >= 0 && y <= 0 && x <= r && y >= -r/2;
        boolean sector = x <= 0 && y >= 0 && (x*x + y*y) <= r*r/4;
        return triangle || square || sector;
    }

    public boolean isInArea(Double x, Double y, Double r) {
        return check(x,y,r);
    }

    public boolean isInArea(Point point) {
        return check(point.getX(),point.getY(),point.getR());
    }
}
