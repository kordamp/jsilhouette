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
 * Astroid
 * <p>
 * Defines an <a href="http://en.wikipedia.org/wiki/Astroid">Astroid</a> shape.
 *
 * <h3>Style Classes</h3>
 * <ul>
 *  <li>silhouette</li>
 *  <li>silhouette-astroid</li>
 * </ul>
 *
 * @author Andres Almiray
 */
public class Astroid extends AbstractCenteredSilhouette {
    private DoubleProperty radius;

    public Astroid() {
    }

    public Astroid(double cx, double cy, double radius) {
        initializing = true;
        setCenterX(cx);
        setCenterY(cy);
        setRadius(radius);
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

    @Override
    protected void calculateShape() {
        PathBuilder p = new PathBuilder();

        double cx = getCenterX();
        double cy = getCenterY();
        double r = getRadius();

        Path path = p.moveTo(cx - r, cy)
            .arcTo(cx, cy - r, r, r, false)
            .arcTo(cx + r, cy, r, r, false)
            .arcTo(cx, cy + r, r, r, false)
            .arcTo(cx - r, cy, r, r, false)
            .build();

        path.getStyleClass().addAll("silhouette", "silhoutte-astroid");

        setShape(path);
    }
}
