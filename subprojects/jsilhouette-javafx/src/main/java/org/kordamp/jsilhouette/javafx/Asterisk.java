/*
 * Copyright 2015-2018 Andres Almiray
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
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

import java.util.logging.Logger;

/**
 * = Asterisk
 *
 * Defines an asterisk shape that may have round corners.
 *
 * image::shape_asterisk.png[]
 *
 * == Constraints
 *
 *  . `beamCount` > 1
 *  . 0 +<=+ `roundness` +<=+ 1
 *  . `width` +<= `radius`* 2
 *
 * == Style Classes
 *
 *  . `silhouette`
 *  . `silhouette-asterisk`
 *
 * @author Andres Almiray
 */
public class Asterisk extends AbstractCenteredSilhouette {
    private static final Logger LOG = Logger.getLogger(Asterisk.class.getName());

    private DoubleProperty width;
    private DoubleProperty radius;
    private DoubleProperty roundness;
    private IntegerProperty beamCount;

    public Asterisk() {
    }

    public Asterisk(double cx, double cy, double radius, double width) {
        this(cy, cy, radius, width, 3, 0);
    }

    public Asterisk(double cx, double cy, double radius, double width, int beamCount) {
        this(cy, cy, radius, width, beamCount, 0);
    }

    public Asterisk(double cx, double cy, double radius, double width, int beamCount, double roundness) {
        initializing = true;
        setCenterX(cx);
        setCenterY(cy);
        setRadius(radius);
        setWidth(width);
        setRoundness(roundness);
        setBeamCount(beamCount);
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

    @Override
    protected void calculateShape() {
        double cx = getCenterX();
        double cy = getCenterY();
        double r = getRadius();
        double n = validateRoundness(getRoundness());
        double w = validateWidth(getWidth(), r);
        double bc = validateBeamCount(getBeamCount());

        double awh = w * n;

        double t = 180 / bc;
        double a = 0;

        Rectangle beam1 = beam(cx, cy, r, w, awh, a);
        a = sweepAngle(a, t);
        Rectangle beam2 = beam(cx, cy, r, w, awh, a);
        a = sweepAngle(a, t);
        Shape shape = Shape.union(beam1, beam2);
        for (int i = 2; i < bc; i++) {
            Rectangle beam = beam(cx, cy, r, w, awh, a);
            a = sweepAngle(a, t);
            shape = Shape.union(shape, beam);
        }

        shape.getStyleClass().addAll("silhouette", "silhoutte-asterisk");

        setShape(shape);
    }

    private int validateBeamCount(int beamCount) {
        if (beamCount < 2) {
            LOG.info(() -> "beamCount (" + beamCount + ") can not be less than 2");
            return 2;
        }
        return beamCount;
    }

    private double validateRoundness(double roundness) {
        if (roundness < 0) {
            LOG.info(() -> "roundness (" + roundness + ") must be inside the range [0..1]");
            return 0;
        } else if (roundness > 1) {
            LOG.info(() -> "roundness (" + roundness + " ) must be inside the range [0..1]");
            return 1;
        }
        return roundness;
    }

    private double validateWidth(double width, double radius) {
        if (width > radius * 2) {
            LOG.info(() -> "width (" + width + ") can not be greater than radius * 2 (" + (radius * 2) + ")");
            return radius * 2;
        }
        return width;
    }

    private Rectangle beam(double cx, double cy, double r, double w, double awh, double a) {
        Rectangle beam = new Rectangle(cx - r, cy - (w / 2), r * 2, w);
        beam.setArcWidth(awh);
        beam.setArcHeight(awh);
        beam.setRotate(a);
        return beam;
    }

    private double sweepAngle(double a, double t) {
        double na = a + t;
        na = na > 360 ? na - 360 : na;
        return na;
    }
}
