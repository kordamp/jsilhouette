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
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.geometry.Point3D;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Shape;
import javafx.scene.shape.StrokeLineCap;
import javafx.scene.shape.StrokeLineJoin;
import javafx.scene.shape.StrokeType;
import javafx.scene.transform.Rotate;

import java.util.function.Consumer;

/**
 * @author Andres Almiray
 */
public abstract class AbstractSilhouette implements Silhouette {
    private ObjectProperty<Shape> shape;
    private ObjectProperty<Paint> fill;
    private BooleanProperty smooth;
    private DoubleProperty strokeDashOffset;
    private ObjectProperty<StrokeLineCap> strokeLineCap;
    private ObjectProperty<StrokeLineJoin> strokeLineJoin;
    private DoubleProperty strokeMiterLimit;
    private ObjectProperty<Paint> stroke;
    private ObjectProperty<StrokeType> strokeType;
    private DoubleProperty strokeWidth;

    private StringProperty id;
    private BooleanProperty managed;
    private DoubleProperty opacity;
    private DoubleProperty rotate;
    private ObjectProperty<Point3D> rotationAxis;
    private DoubleProperty scaleX;
    private DoubleProperty scaleY;
    private DoubleProperty scaleZ;
    private StringProperty style;
    private DoubleProperty translateX;
    private DoubleProperty translateY;
    private DoubleProperty translateZ;
    private BooleanProperty visible;

    private static final StrokeType DEFAULT_STROKE_TYPE = StrokeType.CENTERED;
    private static final double DEFAULT_STROKE_WIDTH = 1.0;
    private static final StrokeLineJoin DEFAULT_STROKE_LINE_JOIN = StrokeLineJoin.MITER;
    private static final StrokeLineCap DEFAULT_STROKE_LINE_CAP = StrokeLineCap.SQUARE;
    private static final double DEFAULT_STROKE_MITER_LIMIT = 10.0;
    private static final double DEFAULT_STROKE_DASH_OFFSET = 0;

    protected volatile boolean initializing = false;
    protected final ChangeListener updateListener = (v, o, n) -> {
        if (!initializing) {
            calculateShape();
        }
    };

    protected void setShape(Shape shape) {
        shapeProperty().set(shape);
    }

    @Override
    public ObjectProperty<Shape> shapeProperty() {
        if (shape == null) {
            shape = new SimpleObjectProperty<>(this, "shape");
            shape.addListener((observable, oldShape, newShape) -> {
                if (newShape != null) {
                    // forwards property values directly
                    newShape.setFill(getFill());
                    newShape.setSmooth(isSmooth());
                    newShape.setStrokeDashOffset(getStrokeDashOffset());
                    newShape.setStrokeLineCap(getStrokeLineCap());
                    newShape.setStrokeLineJoin(getStrokeLineJoin());
                    newShape.setStrokeMiterLimit(getStrokeMiterLimit());
                    newShape.setStroke(getStroke());
                    newShape.setStrokeType(getStrokeType());
                    newShape.setStrokeWidth(getStrokeWidth());
                    newShape.setId(getId());
                    newShape.setManaged(isManaged());
                    newShape.setOpacity(getOpacity());
                    newShape.setRotate(getRotate());
                    newShape.setRotationAxis(getRotationAxis());
                    newShape.setScaleX(getScaleX());
                    newShape.setScaleY(getScaleY());
                    newShape.setScaleZ(getScaleZ());
                    newShape.setStyle(getStyle());
                    newShape.setTranslateX(getTranslateX());
                    newShape.setTranslateY(getTranslateY());
                    newShape.setTranslateZ(getTranslateZ());
                    newShape.setVisible(isVisible());
                }
            });
        }
        return shape;
    }

    protected abstract void calculateShape();

    @Override
    public ObjectProperty<Paint> fillProperty() {
        if (fill == null) {
            fill = new SimpleObjectProperty<>(this, "fill", Color.BLACK);
            fill.addListener((v, o, n) -> forwardShapeProperty(s -> s.setFill(n)));
        }
        return fill;
    }

    @Override
    public BooleanProperty smoothProperty() {
        if (smooth == null) {
            smooth = new SimpleBooleanProperty(this, "smooth", true);
            smooth.addListener((v, o, n) -> forwardShapeProperty(s -> s.setSmooth(n)));
        }
        return smooth;
    }

    @Override
    public DoubleProperty strokeDashOffsetProperty() {
        if (strokeDashOffset == null) {
            strokeDashOffset = new SimpleDoubleProperty(this, "strokeDashOffset", DEFAULT_STROKE_DASH_OFFSET);
            strokeDashOffset.addListener((v, o, n) -> {
                forwardShapeProperty(s -> {
                    if (n != null) {
                        getShape().setStrokeDashOffset(n.doubleValue());
                    }
                });
            });
        }
        return strokeDashOffset;
    }

    @Override
    public ObjectProperty<StrokeLineCap> strokeLineCapProperty() {
        if (strokeLineCap == null) {
            strokeLineCap = new SimpleObjectProperty<>(this, "strokeLineCap", DEFAULT_STROKE_LINE_CAP);
            strokeLineCap.addListener((v, o, n) -> forwardShapeProperty(s -> s.setStrokeLineCap(n)));
        }
        return strokeLineCap;
    }

    @Override
    public ObjectProperty<StrokeLineJoin> strokeLineJoinProperty() {
        if (strokeLineJoin == null) {
            strokeLineJoin = new SimpleObjectProperty<>(this, "strokeLineJoin", DEFAULT_STROKE_LINE_JOIN);
            strokeLineJoin.addListener((v, o, n) -> forwardShapeProperty(s -> setStrokeLineJoin(n)));
        }
        return strokeLineJoin;
    }

    @Override
    public DoubleProperty strokeMiterLimitProperty() {
        if (strokeMiterLimit == null) {
            strokeMiterLimit = new SimpleDoubleProperty(this, "strokeMiterLimit", DEFAULT_STROKE_MITER_LIMIT);
            strokeMiterLimit.addListener((v, o, n) -> {
                forwardShapeProperty(s -> {
                    if (n != null) {
                        getShape().setStrokeMiterLimit(n.doubleValue());
                    }
                });
            });
        }
        return strokeMiterLimit;
    }

    @Override
    public ObjectProperty<Paint> strokeProperty() {
        if (stroke == null) {
            stroke = new SimpleObjectProperty<>(this, "stroke", Color.BLACK);
            stroke.addListener((v, o, n) -> forwardShapeProperty(s -> s.setStroke(n)));
        }
        return stroke;
    }

    @Override
    public ObjectProperty<StrokeType> strokeTypeProperty() {
        if (strokeType == null) {
            strokeType = new SimpleObjectProperty<>(this, "strokeType", DEFAULT_STROKE_TYPE);
            strokeType.addListener((v, o, n) -> forwardShapeProperty(s -> s.setStrokeType(n)));
        }
        return strokeType;
    }

    @Override
    public DoubleProperty strokeWidthProperty() {
        if (strokeWidth == null) {
            strokeWidth = new SimpleDoubleProperty(this, "strokeWidth", DEFAULT_STROKE_WIDTH);
            strokeWidth.addListener((v, o, n) -> {
                forwardShapeProperty(s -> {
                    if (n != null) {
                        getShape().setStrokeWidth(n.doubleValue());
                    }
                });
            });
        }
        return strokeWidth;
    }

    @Override
    public StringProperty idProperty() {
        if (id == null) {
            id = new SimpleStringProperty(this, "id");
            id.addListener((v, o, n) -> forwardShapeProperty(s -> s.setId(n)));
        }
        return id;
    }

    @Override
    public BooleanProperty managedProperty() {
        if (managed == null) {
            managed = new SimpleBooleanProperty(this, "managed", true);
            managed.addListener((v, o, n) -> forwardShapeProperty(s -> s.setManaged(n)));
        }
        return managed;
    }

    @Override
    public DoubleProperty opacityProperty() {
        if (opacity == null) {
            opacity = new SimpleDoubleProperty(this, "opacity", 1);
            opacity.addListener((v, o, n) -> {
                forwardShapeProperty(s -> {
                    if (n != null) {
                        getShape().setOpacity(n.doubleValue());
                    }
                });
            });
        }
        return opacity;
    }

    @Override
    public DoubleProperty rotateProperty() {
        if (rotate == null) {
            rotate = new SimpleDoubleProperty(this, "rotate", 0);
            rotate.addListener((v, o, n) -> {
                forwardShapeProperty(s -> {
                    if (n != null) {
                        getShape().setRotate(n.doubleValue());
                    }
                });
            });
        }
        return rotate;
    }

    @Override
    public ObjectProperty<Point3D> rotationAxisProperty() {
        if (rotationAxis == null) {
            rotationAxis = new SimpleObjectProperty<>(this, "rotationAxis", Rotate.Z_AXIS);
            rotationAxis.addListener((v, o, n) -> forwardShapeProperty(s -> s.setRotationAxis(n)));
        }
        return rotationAxis;
    }

    @Override
    public DoubleProperty scaleXProperty() {
        if (scaleX == null) {
            scaleX = new SimpleDoubleProperty(this, "scaleX", 1);
            scaleX.addListener((v, o, n) -> {
                forwardShapeProperty(s -> {
                    if (n != null) {
                        getShape().setScaleX(n.doubleValue());
                    }
                });
            });
        }
        return scaleX;
    }

    @Override
    public DoubleProperty scaleYProperty() {
        if (scaleY == null) {
            scaleY = new SimpleDoubleProperty(this, "scaleY", 1);
            scaleY.addListener((v, o, n) -> {
                forwardShapeProperty(s -> {
                    if (n != null) {
                        getShape().setScaleY(n.doubleValue());
                    }
                });
            });
        }
        return scaleY;
    }

    @Override
    public DoubleProperty scaleZProperty() {
        if (scaleZ == null) {
            scaleZ = new SimpleDoubleProperty(this, "scaleZ", 1);
            scaleZ.addListener((v, o, n) -> {
                forwardShapeProperty(s -> {
                    if (n != null) {
                        getShape().setScaleZ(n.doubleValue());
                    }
                });
            });
        }
        return scaleZ;
    }

    @Override
    public StringProperty styleProperty() {
        if (style == null) {
            style = new SimpleStringProperty(this, "id");
            style.addListener((v, o, n) -> forwardShapeProperty(s -> s.setStyle(n)));
        }
        return style;
    }

    @Override
    public DoubleProperty translateXProperty() {
        if (translateX == null) {
            translateX = new SimpleDoubleProperty(this, "translateX", 0);
            translateX.addListener((v, o, n) -> {
                forwardShapeProperty(s -> {
                    if (n != null) {
                        getShape().setTranslateX(n.doubleValue());
                    }
                });
            });
        }
        return translateX;
    }

    @Override
    public DoubleProperty translateYProperty() {
        if (translateY == null) {
            translateY = new SimpleDoubleProperty(this, "translateY", 0);
            translateY.addListener((v, o, n) -> {
                forwardShapeProperty(s -> {
                    if (n != null) {
                        getShape().setTranslateY(n.doubleValue());
                    }
                });
            });
        }
        return translateY;
    }

    @Override
    public DoubleProperty translateZProperty() {
        if (translateZ == null) {
            translateZ = new SimpleDoubleProperty(this, "translateZ", 0);
            translateZ.addListener((v, o, n) -> {
                forwardShapeProperty(s -> {
                    if (n != null) {
                        getShape().setTranslateZ(n.doubleValue());
                    }
                });
            });
        }
        return translateZ;
    }

    @Override
    public BooleanProperty visibleProperty() {
        if (visible == null) {
            visible = new SimpleBooleanProperty(this, "managed", true);
            visible.addListener((v, o, n) -> forwardShapeProperty(s -> s.setVisible(n)));
        }
        return visible;
    }

    protected void forwardShapeProperty(Consumer<Shape> consumer) {
        if (shape != null && getShape() != null) {
            consumer.accept(getShape());
        }
    }
}
