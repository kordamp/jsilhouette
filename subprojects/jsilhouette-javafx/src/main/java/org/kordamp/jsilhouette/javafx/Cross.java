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
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

import java.util.logging.Logger;

/**
 * = Cross
 *
 * Defines a cross shape that may have round corners.
 *
 * image::shape_cross.png[]
 *
 * == Constraints
 *
 *  . 0 +<=+ `roundness` +<=+ 1
 *  . `width` +<= `radius`* 2
 *
 * == Style Classes
 *
 *  . `silhouette`
 *  . `silhouette-cross`
 *
 * @author Andres Almiray
 */
public class Cross extends AbstractCenteredSilhouette {
    private static final Logger LOG = Logger.getLogger(Cross.class.getName());

    private DoubleProperty width;
    private DoubleProperty radius;
    private DoubleProperty roundness;

    public Cross() {
    }

    public Cross(double cx, double cy, double radius, double width) {
        this(cy, cy, radius, width, 0);
    }

    public Cross(double cx, double cy, double radius, double width, double roundness) {
        initializing = true;
        setCenterX(cx);
        setCenterY(cy);
        setRadius(radius);
        setWidth(width);
        setRoundness(roundness);
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

    public double getRoundness() {
        return roundnessProperty().get();
    }

    @SuppressWarnings("unchecked")
    public DoubleProperty roundnessProperty() {
        if (roundness == null) {
            roundness = new SimpleDoubleProperty(this, "roundness", 0);
            roundness.addListener(updateListener);
        }
        return roundness;
    }

    public void setRoundness(double roundness) {
        roundnessProperty().set(roundness);
    }

    public double getWidth() {
        return widthProperty().get();
    }

    @SuppressWarnings("unchecked")
    public DoubleProperty widthProperty() {
        if (width == null) {
            width = new SimpleDoubleProperty(this, "width", 0);
            width.addListener(updateListener);
        }
        return width;
    }

    public void setWidth(double width) {
        widthProperty().set(width);
    }

    @Override
    protected void calculateShape() {
        double cx = getCenterX();
        double cy = getCenterY();
        double r = getRadius();
        double n = validateRoundness(getRoundness());
        double w = validateWidth(getWidth(), r);

        double arcWH = w * n;
        Rectangle beam1 = new Rectangle(cx - r, cy - (w / 2), r * 2, w);
        Rectangle beam2 = new Rectangle(cx - (w / 2), cy - r, w, r * 2);
        beam1.setArcWidth(arcWH);
        beam1.setArcHeight(arcWH);
        beam2.setArcWidth(arcWH);
        beam2.setArcHeight(arcWH);
        Shape shape = Shape.union(beam1, beam2);

        shape.getStyleClass().addAll("silhouette", "silhoutte-cross");

        setShape(shape);
    }

    protected double validateRoundness(double roundness) {
        if (roundness < 0) {
            LOG.info(() -> "roundness (" + roundness + ") must be inside the range [0..1]");
            return 0;
        } else if (roundness > 1) {
            LOG.info(() -> "roundness (" + roundness + " ) must be inside the range [0..1]");
            return 1;
        }
        return roundness;
    }

    protected double validateWidth(double width, double radius) {
        if (width > radius * 2) {
            LOG.info(() -> "width (" + width + ") can not be greater than radius * 2 (" + (radius * 2) + ")");
            return radius * 2;
        }
        return width;
    }
}
