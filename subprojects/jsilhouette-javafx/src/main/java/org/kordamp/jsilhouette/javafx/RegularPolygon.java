/*
 * Copyright 2015-2016 Andres Almiray. <aalmiray@yahoo.com>
 *
 * This file is part of JSilhouette
 *
 * JSilhouette is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * JSilhouette is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with JSilhouette. If not, see <http://www.gnu.org/licenses/>.
 *
 * ========================================================================
 *
 * This library is distributed under the terms of the GNU General Public
 * License with the following clarification and special exception:
 *
 * Linking this library statically or dynamically with other modules is
 * making a combined work based on this library. Thus, the terms and
 * conditions of the GNU General Public License cover the whole
 * combination.
 *
 * As an special exception, the copyright holders of this library give
 * you permission to use this library under the terms of the Apache
 * Software License v2 and forego the licensing terms of the GNU General
 * Public License if and only if the library is used as part of an
 * executable and/or application that makes use of the APIs of either the
 * Griffon Framework (https://github.com/griffon/griffon) or the
 * Basilisk Framework (https://github.com/basilisk-fw/basilisk).
 *
 * ========================================================================
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kordamp.jsilhouette.javafx;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.shape.Path;

import java.util.logging.Logger;

/**
 * = RegularPlygon
 *
 * Defines a regular polygon shape.
 *
 * image::shape_regular_polygon.png[]
 *
 * == Constraints
 *
 *  . `sides` > 2
 *
 * == Style Classes
 *
 *  . `silhouette`
 *  . `silhouette-regular-polygon`
 *
 * @author Andres Almiray
 */
public class RegularPolygon extends AbstractCenteredSilhouette {
    private static final Logger LOG = Logger.getLogger(RegularPolygon.class.getName());

    private DoubleProperty radius;
    private IntegerProperty sides;

    public RegularPolygon() {
    }

    public RegularPolygon(double cx, double cy, double radius, int sides) {
        initializing = true;
        setCenterX(cx);
        setCenterY(cy);
        setRadius(radius);
        setSides(sides);
        initializing = false;
        calculateShape();
    }

    public double getRadius() {
        return radiusProperty().get();
    }

    @SuppressWarnings("unchecked")
    public DoubleProperty radiusProperty() {
        if (radius == null) {
            radius = new SimpleDoubleProperty(this, "radius", 0);
            radius.addListener(updateListener);
        }
        return radius;
    }

    public void setRadius(double radius) {
        radiusProperty().set(radius);
    }

    public int getSides() {
        return sidesProperty().get();
    }

    @SuppressWarnings("unchecked")
    public IntegerProperty sidesProperty() {
        if (sides == null) {
            sides = new SimpleIntegerProperty(this, "sides", 3);
            sides.addListener(updateListener);
        }
        return sides;
    }

    public void setSides(int sides) {
        sidesProperty().set(sides);
    }

    @Override
    protected void calculateShape() {
        PathBuilder p = new PathBuilder();

        double cx = getCenterX();
        double cy = getCenterY();
        double r = getRadius();
        int s = validateSides(getSides());


        double t = 360 / s;
        double a = 0;
        for (int i = 0; i < s; i++) {
            double ra = Math.toRadians(a);
            double x = Math.abs(r * Math.cos(ra));
            double y = Math.abs(r * Math.sin(ra));
            if (a <= 90) {
                x = cx + x;
                y = cy - y;
            } else if (a <= 180) {
                x = cx - x;
                y = cy - y;
            } else if (a <= 270) {
                x = cx - x;
                y = cy + y;
            } else if (a <= 360) {
                x = cx + x;
                y = cy + y;
            }
            if (i == 0) {
                p.moveTo(x, y);
            } else {
                p.lineTo(x, y);
            }
            a += t;
            a = a > 360 ? a - 360 : a;
        }
        Path path = p.build();

        path.getStyleClass().addAll("silhouette", "silhoutte-regular-polygon");

        setShape(path);
    }

    private int validateSides(int sides) {
        if (sides < 3) {
            LOG.info(() -> "sides (" + sides + ") can not be less than 3");
            return 3;
        }
        return sides;
    }
}
