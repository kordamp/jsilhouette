/*
 * SPDX-License-Identifier: Apache-2.0
 *
 * Copyright 2015-2020 Andres Almiray
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
import javafx.scene.shape.Circle;
import javafx.scene.shape.Shape;

import static javafx.scene.shape.Shape.intersect;

/**
 * = Almond
 *
 * Defines an almond link:http://en.wikipedia.org/wiki/Vesica_piscis[Vesica Piscis] shape
 *
 * image::shape_almond.png[]
 *
 * == Style Classes
 *
 *  . `silhouette`
 *  . `silhouette-almond`
 *
 * @author Andres Almiray
 */
public class Almond extends AbstractCenteredSilhouette {
    private DoubleProperty radius;

    public Almond() {
    }

    public Almond(double cx, double cy, double radius) {
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
        double cx = getCenterX();
        double cy = getCenterY();
        double r = getRadius();

        Circle left = new Circle(cx - (r / 2), cy, r);
        Circle right = new Circle(cx + (r / 2), cy, r);

        Shape shape = intersect(left, right);
        shape.getStyleClass().addAll("silhouette", "silhoutte-almond");

        setShape(shape);
    }
}
