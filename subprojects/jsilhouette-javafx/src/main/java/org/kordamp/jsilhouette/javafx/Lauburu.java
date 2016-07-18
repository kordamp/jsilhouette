/*
 * Copyright 2015-2016 Andres Almiray. <aalmiray@yahoo.com>
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
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.shape.Shape;

/**
 * = Lauburu
 *
 * Defines a link:http://en.wikipedia.org/wiki/Lauburu[Lauburu] shape.
 * 
 * image::shape_lauburu.png[]
 * 
 * == Style Classes
 * 
 * . `silhouette`
 * . `silhouette-lauburu`
 *
 * @author Andres Almiray
 */
public class Lauburu extends AbstractCenteredSilhouette {
    public enum Direction {
        CLOCKWISE,
        ANTICLOCKWISE
    }

    private DoubleProperty radius;
    private ObjectProperty<Direction> direction;

    public Lauburu() {
    }

    public Lauburu(double cx, double cy, double radius) {
        this(cx, cy, radius, Direction.CLOCKWISE);
    }

    public Lauburu(double cx, double cy, double radius, Direction direction) {
        initializing = true;
        setCenterX(cx);
        setCenterY(cy);
        setRadius(radius);
        setDirection(direction);
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

    public Direction getDirection() {
        return directionProperty().get();
    }

    @SuppressWarnings("unchecked")
    public ObjectProperty<Direction> directionProperty() {
        if (direction == null) {
            direction = new SimpleObjectProperty(this, "direction", Direction.CLOCKWISE);
            direction.addListener(updateListener);
        }
        return direction;
    }

    public void setDirection(Direction direction) {
        directionProperty().set(direction);
    }

    @Override
    protected void calculateShape() {
        double cx = getCenterX();
        double cy = getCenterY();
        double r = getRadius();
        Direction d = getDirection();

        Shape shape = new PathBuilder().moveTo(cx, cy)
            .arcTo(cx + r, cy, r / 2, r / 2, d == Direction.CLOCKWISE)
            .arcTo(cx + (r / 2), cy, r / 4, r / 4, d == Direction.CLOCKWISE)
            .arcTo(cx, cy, r / 4, r / 4, d != Direction.CLOCKWISE)
            .close()
            .arcTo(cx - r, cy, r / 2, r / 2, d == Direction.CLOCKWISE)
            .arcTo(cx - (r / 2), cy, r / 4, r / 4, d == Direction.CLOCKWISE)
            .arcTo(cx, cy, r / 4, r / 4, d != Direction.CLOCKWISE)
            .close()
            .arcTo(cx, cy + r, r / 2, r / 2, d == Direction.CLOCKWISE)
            .arcTo(cx, cy + (r / 2), r / 4, r / 4, d == Direction.CLOCKWISE)
            .arcTo(cx, cy, r / 4, r / 4, d != Direction.CLOCKWISE)
            .close()
            .arcTo(cx, cy - r, r / 2, r / 2, d == Direction.CLOCKWISE)
            .arcTo(cx, cy - (r / 2), r / 4, r / 4, d == Direction.CLOCKWISE)
            .arcTo(cx, cy, r / 4, r / 4, d != Direction.CLOCKWISE)
            .close()
            .build();

        shape.getStyleClass().addAll("silhouette", "silhoutte-lauburu");

        setShape(shape);
    }
}
