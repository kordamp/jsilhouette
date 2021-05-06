/*
 * SPDX-License-Identifier: Apache-2.0
 *
 * Copyright 2015-2021 Andres Almiray
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
 * Star
 * <p>
 * Defines a star shape.
 *
 * <h3>Constraints</h3>
 * <ul>
 *  <li>ir &lt;= or</li>
 *  <li>ir &gt; 0 &amp;&amp; or &gt; 0</li>
 *  <li>sides &gt;= 2</li>
 * </ul>
 * <h3>Style Classes</h3>
 * <ul>
 *  <li>silhouette</li>
 *  <li>silhouette-star</li>
 * </ul>
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
        this(cx, cy, or, ir, 0);
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
