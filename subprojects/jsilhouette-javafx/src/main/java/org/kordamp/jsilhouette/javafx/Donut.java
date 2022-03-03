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
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Shape;

import java.util.logging.Logger;

/**
 * Donut
 * <p>
 * Defines a donut shape based on circles or regular polygons.
 *
 * <h3>Constraints</h3>
 * <ul>
 *  <li>ir &lt;= or</li>
 *  <li>ir &gt; 0 &amp;&amp; or &gt; 0</li>
 *  <li>sides == 0 || sides &gt; 2</li>
 * </ul>
 * <h3>Style Classes</h3>
 * <ul>
 *  <li>silhouette</li>
 *  <li>silhouette-donut</li>
 * </ul>
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
            LOG.finest("'ir' can not be equal greater than 'or' [ir=" + ir + ", or=" + or + "]");
            ir = 3;
            or = 8;
        }
        if (ir < 0 || or < 0) {
            LOG.finest("radii can not be less than zero [ir=" + ir + ", or=" + or + "]");
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
