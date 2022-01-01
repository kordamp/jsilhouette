/*
 * SPDX-License-Identifier: Apache-2.0
 *
 * Copyright 2015-2022 Andres Almiray
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
import javafx.scene.shape.Path;

/**
 * RoundPin
 * <p>
 * Defines a rounded pushpin shape.
 *
 * <h3>Style Classes</h3>
 * <ul>
 *  <li>silhouette</li>
 *  <li>silhouette-roundpin</li>
 * </ul>
 *
 * @author Andres Almiray
 */
public class RoundPin extends AbstractCenteredSilhouette {
    private DoubleProperty height;
    private DoubleProperty radius;

    public RoundPin() {
    }

    public RoundPin(double cx, double cy, double radius) {
        this(cx, cy, radius, radius * 2);
    }

    public RoundPin(double cx, double cy, double radius, double height) {
        initializing = true;
        setCenterX(cx);
        setCenterY(cy);
        setRadius(radius);
        setHeight(height);
        initializing = false;
        calculateShape();
    }

    public double getHeight() {
        return heightProperty().get();
    }

    @SuppressWarnings("unchecked")
    public DoubleProperty heightProperty() {
        if (height == null) {
            height = new SimpleDoubleProperty(this, "height", 0);
            height.addListener(updateListener);
        }
        return height;
    }

    public void setHeight(double height) {
        heightProperty().set(height);
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

    @Override
    protected void calculateShape() {
        PathBuilder p = new PathBuilder();

        double cx = getCenterX();
        double cy = getCenterY();
        double r = getRadius();
        double h = getHeight();

        Path path = p.moveTo(cx - r, cy)
            .arcTo(cx + r, cy, r, r)
            .lineTo(cx, cy + h)
            .build();

        path.getStyleClass().addAll("silhouette", "silhoutte-roundpin");

        setShape(path);
    }
}
