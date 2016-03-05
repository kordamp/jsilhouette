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
import javafx.scene.shape.Circle;
import javafx.scene.shape.Shape;

import java.util.logging.Logger;

/**
 * = Donut
 *
 * Defines a donut shape based on circles or regular polygons.
 *
 * image::shape_donut.png[]
 *
 * == Constraints
 *
 *  . `ir` +<=+ `or`
 *  . `ir` > 0 && `or` > 0
 *  . `sides` == 0 || `sides` > 2
 *
 * == Style Classes
 *
 *  . `silhouette`
 *  . `silhouette-donut`
 *
 * @author Andres Almiray
 */
public class Donut extends AbstractCenteredSilhouette {
    private static final Logger LOG = Logger.getLogger(Donut.class.getName());

    private DoubleProperty ir;
    private DoubleProperty or;
    private IntegerProperty sides;

    public Donut() {
    }

    public Donut(double cx, double cy, double or, double ir) {
        this(cy, cy, or, ir, 0);
    }

    public Donut(double cx, double cy, double or, double ir, int sides) {
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

        Shape innerShape = null;
        Shape outerShape = null;

        if (s > 2) {
            outerShape = new RegularPolygon(cx, cy, or, s).getShape();
            innerShape = new RegularPolygon(cx, cy, ir, s).getShape();

        } else {
            outerShape = new Circle(cx, cy, or);
            innerShape = new Circle(cx, cy, ir);
        }

        Shape shape = Shape.subtract(outerShape, innerShape);
        shape.getStyleClass().addAll("silhouette", "silhoutte-donut");

        setShape(shape);
    }
}
