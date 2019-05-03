/*
 * SPDX-License-Identifier: Apache-2.0
 *
 * Copyright 2015-2019 Andres Almiray
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

import java.util.logging.Logger;

/**
 * = Arrow
 *
 * Defines an arrow shape
 *
 * image::shape_arrow.png[]
 *
 * == Constraints
 *
 *  . 0 +<=+ `depth` +<=+ 1
 *  . 0 +<=+ `rise` +<=+ 1
 *
 * == Style Classes
 *
 *  . `silhouette`
 *  . `silhouette-arrow`
 *
 * @author Andres Almiray
 */
public class Arrow extends AbstractSilhouette {
    private static final Logger LOG = Logger.getLogger(Arrow.class.getName());

    private DoubleProperty x;
    private DoubleProperty y;
    private DoubleProperty width;
    private DoubleProperty height;
    private DoubleProperty depth;
    private DoubleProperty rise;

    public Arrow() {
    }

    public Arrow(double x, double y, double width, double height) {
        this(x, y, width, height, 0.5, 0.5);
    }

    public Arrow(double x, double y, double width, double height, double depth, double rise) {
        initializing = true;
        setX(x);
        setY(y);
        setWidth(width);
        setHeight(height);
        setDepth(depth);
        setRise(rise);
        initializing = false;
        calculateShape();
    }

    public double getX() {
        return xProperty().get();
    }

    @SuppressWarnings("unchecked")
    public DoubleProperty xProperty() {
        if (x == null) {
            x = new SimpleDoubleProperty(this, "x", 0);
            x.addListener(updateListener);
        }
        return x;
    }

    public void setX(double value) {
        xProperty().set(value);
    }

    public double getY() {
        return yProperty().get();
    }

    @SuppressWarnings("unchecked")
    public DoubleProperty yProperty() {
        if (y == null) {
            y = new SimpleDoubleProperty(this, "y", 0);
            y.addListener(updateListener);
        }
        return y;
    }

    public void setY(double value) {
        yProperty().set(value);
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

    public double getDepth() {
        return depthProperty().get();
    }

    @SuppressWarnings("unchecked")
    public DoubleProperty depthProperty() {
        if (depth == null) {
            depth = new SimpleDoubleProperty(this, "depth", 0.5);
            depth.addListener(updateListener);
        }
        return depth;
    }

    public void setDepth(double depth) {
        depthProperty().set(depth);
    }

    public double getRise() {
        return riseProperty().get();
    }

    @SuppressWarnings("unchecked")
    public DoubleProperty riseProperty() {
        if (rise == null) {
            rise = new SimpleDoubleProperty(this, "rise", 0.5);
            rise.addListener(updateListener);
        }
        return rise;
    }

    public void setRise(double rise) {
        riseProperty().set(rise);
    }

    @Override
    protected void calculateShape() {
        double x = getX();
        double y = getY();
        double w = getWidth();
        double h = getHeight();
        double d = w * validateDepth(getDepth());
        double r = h * validateRise(getRise()) / 2;

        Path shape = new PathBuilder()
            .moveTo(x, y + (h / 2) - r)
            .lineTo(x + d, y + (h / 2) - r)
            .lineTo(x + d, y)
            .lineTo(x + w, y + (h / 2))
            .lineTo(x + d, y + h)
            .lineTo(x + d, y + (h / 2) + r)
            .lineTo(x, y + (h / 2) + r)
            .build();

        shape.getStyleClass().addAll("silhouette", "silhoutte-arrow");

        setShape(shape);
    }

    private double validateDepth(double depth) {
        if (depth < 0 || depth > 1) {
            LOG.finest(() -> "depth (" + depth + ") must be inside the range [0..1]");
            return 0.5;
        }
        return depth;
    }

    private double validateRise(double rise) {
        if (rise < 0 || rise > 1) {
            LOG.finest(() -> "rise (" + rise + ") must be inside the range [0..1]");
            return 0.5;
        }
        return rise;
    }
}
