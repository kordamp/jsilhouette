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

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.shape.Shape;

import java.util.logging.Logger;

/**
 * = Rays
 *
 * Defines a radial shape.
 * 
 * image::shape_rays.png[]
 * 
 * == Constraints
 * 
 * . `beamCount` > 1
 * . 0 +<=+ `extent` +<=+ 1
 * 
 * == Style Classes
 * 
 * . `silhouette`
 * . `silhouette-rays`
 *
 * @author Andres Almiray
 */
public class Rays extends AbstractCenteredSilhouette {
    private static final Logger LOG = Logger.getLogger(Rays.class.getName());

    private DoubleProperty extent;
    private DoubleProperty radius;
    private IntegerProperty beamCount;
    private BooleanProperty rounded;

    public Rays() {
    }

    public Rays(double cx, double cy, double radius, int beamCount) {
        this(cy, cy, radius, beamCount, 0.5, false);
    }

    public Rays(double cx, double cy, double radius, int beamCount, double extent) {
        this(cy, cy, radius, beamCount, extent, false);
    }

    public Rays(double cx, double cy, double radius, int beamCount, double extent, boolean rounded) {

        initializing = true;
        setCenterX(cx);
        setCenterY(cy);
        setRadius(radius);
        setExtent(extent);
        setBeamCount(beamCount);
        setRounded(rounded);
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

    public double getExtent() {
        return extentProperty().get();
    }

    @SuppressWarnings("unchecked")
    public DoubleProperty extentProperty() {
        if (extent == null) {
            extent = new SimpleDoubleProperty(this, "extent", 0);
            extent.addListener(updateListener);
        }
        return extent;
    }

    public void setExtent(double extent) {
        extentProperty().set(extent);
    }

    public int getBeamCount() {
        return beamCountProperty().get();
    }

    @SuppressWarnings("unchecked")
    public IntegerProperty beamCountProperty() {
        if (beamCount == null) {
            beamCount = new SimpleIntegerProperty(this, "beamCount", 3);
            beamCount.addListener(updateListener);
        }
        return beamCount;
    }

    public void setBeamCount(int beamCount) {
        beamCountProperty().set(beamCount);
    }

    public boolean isRounded() {
        return roundedProperty().get();
    }

    public BooleanProperty roundedProperty() {
        if (rounded == null) {
            rounded = new SimpleBooleanProperty(this, "managed", true);
            rounded.addListener(updateListener);
        }
        return rounded;
    }

    public void setRounded(boolean rounded) {
        roundedProperty().set(rounded);
    }

    @Override
    protected void calculateShape() {
        double cx = getCenterX();
        double cy = getCenterY();
        int bc = validateBeamCount(getBeamCount());
        double rad = getRadius();
        boolean n = isRounded();

        double sides = bc * 2;
        double t = 360 / sides;
        double a = 0;
        double e = (validateExtent(getExtent()) * t * 2) - t;
        double[][] points = new double[bc * 2][];
        double[] angles = new double[bc * 2];
        for (int i = 0; i < sides; i++) {
            double r = i % 2 == 0 ? a : a + e;
            r = r < 0 ? 360 + r : r;
            double ra = Math.toRadians(r);
            double x = Math.abs(rad * Math.cos(ra));
            double y = Math.abs(rad * Math.sin(ra));
            if (r <= 90 || r > 360) {
                x = cx + x;
                y = cy - y;
            } else if (r <= 180) {
                x = cx - x;
                y = cy - y;
            } else if (r <= 270) {
                x = cx - x;
                y = cy + y;
            } else if (r <= 360) {
                x = cx + x;
                y = cy + y;
            }
            points[i] = new double[]{x, y};
            angles[i] = r;
            a += t;
            a = a > 360 ? a - 360 : a;
        }

        PathBuilder p = new PathBuilder();
        p.moveTo(cx, cy);
        for (int i = 0; i < bc; i++) {
            p.lineTo(points[(2 * i)][0], points[(2 * i)][1]);
            if (n) {
                p.arcTo(points[(2 * i) + 1][0], points[(2 * i) + 1][1], rad, rad, false, false);
            } else {
                p.lineTo(points[(2 * i) + 1][0], points[(2 * i) + 1][1]);
            }
            p.close();
        }

        Shape shape = p.build();

        shape.getStyleClass().addAll("silhouette", "silhoutte-rays");

        setShape(shape);
    }

    private int validateBeamCount(int beamCount) {
        if (beamCount < 2) {
            LOG.info(() -> "beamCount (" + beamCount + ") can not be less than 2");
            return 2;
        }
        return beamCount;
    }

    protected double validateExtent(double extent) {
        if (extent < 0) {
            LOG.info(() -> "extent (" + extent + ") must be inside the range [0..1]");
            return 0;
        } else if (extent > 1) {
            LOG.info(() -> "extent (" + extent + " ) must be inside the range [0..1]");
            return 1;
        }
        return extent;
    }
}
