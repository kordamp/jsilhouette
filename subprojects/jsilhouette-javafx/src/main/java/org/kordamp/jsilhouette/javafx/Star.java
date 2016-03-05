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
import javafx.scene.shape.Shape;

import java.util.logging.Logger;

/**
 * = Star
 *
 * Defines a star shape.
 *
 * image::shape_star.png[]
 *
 * == Constraints
 *
 *  . `ir` +<=+ `or`
 *  . `ir` > 0 && `or` > 0
 *  . `sides` >= 2
 *
 * == Style Classes
 *
 *  . `silhouette`
 *  . `silhouette-star`
 *
 * @author Andres Almiray
 */
public class Star extends AbstractCenteredSilhouette {
    private static final Logger LOG = Logger.getLogger(Star.class.getName());

    private DoubleProperty ir;
    private DoubleProperty or;
    private IntegerProperty sides;

    public Star() {
    }

    public Star(double cx, double cy, double or, double ir) {
        this(cy, cy, or, ir, 0);
    }

    public Star(double cx, double cy, double or, double ir, int sides) {
        initializing = true;
        setCenterX(cx);
        setCenterY(cy);
        setOr(or);
        setIr(ir);
        setSides(sides);
        initializing = false;
        calculateShape();
    }

    public double getOr() {
        return orProperty().get();
    }

    @SuppressWarnings("unchecked")
    public DoubleProperty orProperty() {
        if (or == null) {
            or = new SimpleDoubleProperty(this, "or", 0);
            or.addListener(updateListener);
        }
        return or;
    }

    public void setOr(double or) {
        orProperty().set(or);
    }

    public double getIr() {
        return irProperty().get();
    }

    @SuppressWarnings("unchecked")
    public DoubleProperty irProperty() {
        if (ir == null) {
            ir = new SimpleDoubleProperty(this, "ir", 0);
            ir.addListener(updateListener);
        }
        return ir;
    }

    public void setIr(double ir) {
        irProperty().set(ir);
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
        double cx = getCenterX();
        double cy = getCenterY();
        double or = getOr();
        double ir = getIr();
        int s = getSides();

        if (s < 2) {
            LOG.info("sides (" + s + ") can not be less than 2");
            s = 2;
        }

        if (ir >= or) {
            LOG.info("'ir' can not be equal greater than 'or' [ir=" + ir + ", or=" + or + "]");
            ir = 3;
            or = 8;
        }
        if (ir < 0 || or < 0) {
            LOG.info("radii can not be less than zero [ir=" + ir + ", or=" + or + "]");
            ir = 3;
            or = 8;
        }

        double t = 360 / s;
        double a = 90;
        double b = 90 + (t / 2);
        b = b > 360 ? b - 360 : b;
        PathBuilder p = new PathBuilder();
        for (int i = 0; i < s; i++) {
            double ra = Math.toRadians(a);
            double ox = Math.abs(or * Math.cos(ra));
            double oy = Math.abs(or * Math.sin(ra));
            if (a <= 90) {
                ox = cx + ox;
                oy = cy - oy;
            } else if (a <= 180) {
                ox = cx - ox;
                oy = cy - oy;
            } else if (a <= 270) {
                ox = cx - ox;
                oy = cy + oy;
            } else if (a <= 360) {
                ox = cx + ox;
                oy = cy + oy;
            }

            double rb = Math.toRadians(b);
            double ix = Math.abs(ir * Math.cos(rb));
            double iy = Math.abs(ir * Math.sin(rb));
            if (b <= 90) {
                ix = cx + ix;
                iy = cy - iy;
            } else if (b <= 180) {
                ix = cx - ix;
                iy = cy - iy;
            } else if (b <= 270) {
                ix = cx - ix;
                iy = cy + iy;
            } else if (b <= 360) {
                ix = cx + ix;
                iy = cy + iy;
            }

            if (i == 0) {
                p.moveTo(ox, oy);
                p.lineTo(ix, iy);
            } else {
                p.lineTo(ox, oy);
                p.lineTo(ix, iy);
            }
            a += t;
            a = a > 360 ? a - 360 : a;
            b += t;
            b = b > 360 ? b - 360 : b;
        }

        Shape shape = p.build();
        shape.getStyleClass().addAll("silhouette", "silhoutte-star");

        setShape(shape);
    }
}
