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
import javafx.beans.property.StringProperty;
import javafx.geometry.Point3D;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Shape;
import javafx.scene.shape.StrokeLineCap;
import javafx.scene.shape.StrokeLineJoin;
import javafx.scene.shape.StrokeType;

/**
 * @author Andres Almiray
 */
public interface Silhouette {
    Shape getShape();

    ObjectProperty<Shape> shapeProperty();

    Paint getFill();

    ObjectProperty<Paint> fillProperty();

    void setFill(Paint fill);

    boolean isSmooth();

    BooleanProperty smoothProperty();

    void setSmooth(boolean smooth);

    double getStrokeDashOffset();

    DoubleProperty strokeDashOffsetProperty();

    void setStrokeDashOffset(double strokeDashOffset);

    StrokeLineCap getStrokeLineCap();

    ObjectProperty<StrokeLineCap> strokeLineCapProperty();

    void setStrokeLineCap(StrokeLineCap strokeLineCap);

    StrokeLineJoin getStrokeLineJoin();

    ObjectProperty<StrokeLineJoin> strokeLineJoinProperty();

    void setStrokeLineJoin(StrokeLineJoin strokeLineJoin);

    double getStrokeMiterLimit();

    DoubleProperty strokeMiterLimitProperty();

    void setStrokeMiterLimit(double strokeMiterLimit);

    Paint getStroke();

    ObjectProperty<Paint> strokeProperty();

    void setStroke(Paint stroke);

    StrokeType getStrokeType();

    ObjectProperty<StrokeType> strokeTypeProperty();

    void setStrokeType(StrokeType strokeType);

    double getStrokeWidth();

    DoubleProperty strokeWidthProperty();

    void setStrokeWidth(double strokeWidth);

    static double normalizeAngle(double angle) {
        angle = angle % 360;
        return angle < 0 ? angle + 360 : angle;
    }

    String getId();

    StringProperty idProperty();

    void setId(String id);

    boolean isManaged();

    BooleanProperty managedProperty();

    void setManaged(boolean managed);

    double getOpacity();

    DoubleProperty opacityProperty();

    void setOpacity(double opacity);

    double getRotate();

    DoubleProperty rotateProperty();

    void setRotate(double rotate);

    Point3D getRotationAxis();

    ObjectProperty<Point3D> rotationAxisProperty();

    void setRotationAxis(Point3D rotationAxis);

    double getScaleX();

    DoubleProperty scaleXProperty();

    void setScaleX(double scaleX);

    double getScaleY();

    DoubleProperty scaleYProperty();

    void setScaleY(double scaleY);

    double getScaleZ();

    DoubleProperty scaleZProperty();

    void setScaleZ(double scaleZ);

    String getStyle();

    StringProperty styleProperty();

    void setStyle(String style);

    double getTranslateX();

    DoubleProperty translateXProperty();

    void setTranslateX(double translateX);

    double getTranslateY();

    DoubleProperty translateYProperty();

    void setTranslateY(double translateY);

    double getTranslateZ();

    DoubleProperty translateZProperty();

    void setTranslateZ(double translateZ);

    boolean isVisible();

    BooleanProperty visibleProperty();

    void setVisible(boolean visible);
}
