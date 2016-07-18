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
    default Shape getShape() {
        return shapeProperty().get();
    }

    ObjectProperty<Shape> shapeProperty();

    default Paint getFill() {
        return fillProperty().get();
    }

    ObjectProperty<Paint> fillProperty();

    default void setFill(Paint fill) {
        fillProperty().set(fill);
    }

    default boolean isSmooth() {
        return smoothProperty().get();
    }

    BooleanProperty smoothProperty();

    default void setSmooth(boolean smooth) {
        smoothProperty().set(smooth);
    }

    default double getStrokeDashOffset() {
        return strokeDashOffsetProperty().get();
    }

    DoubleProperty strokeDashOffsetProperty();

    default void setStrokeDashOffset(double strokeDashOffset) {
        strokeDashOffsetProperty().set(strokeDashOffset);
    }

    default StrokeLineCap getStrokeLineCap() {
        return strokeLineCapProperty().get();
    }

    ObjectProperty<StrokeLineCap> strokeLineCapProperty();

    default void setStrokeLineCap(StrokeLineCap strokeLineCap) {
        strokeLineCapProperty().set(strokeLineCap);
    }

    default StrokeLineJoin getStrokeLineJoin() {
        return strokeLineJoinProperty().get();
    }

    ObjectProperty<StrokeLineJoin> strokeLineJoinProperty();

    default void setStrokeLineJoin(StrokeLineJoin strokeLineJoin) {
        strokeLineJoinProperty().set(strokeLineJoin);
    }

    default double getStrokeMiterLimit() {
        return strokeMiterLimitProperty().get();
    }

    DoubleProperty strokeMiterLimitProperty();

    default void setStrokeMiterLimit(double strokeMiterLimit) {
        strokeMiterLimitProperty().set(strokeMiterLimit);
    }

    default Paint getStroke() {
        return strokeProperty().get();
    }

    ObjectProperty<Paint> strokeProperty();

    default void setStroke(Paint stroke) {
        strokeProperty().set(stroke);
    }

    default StrokeType getStrokeType() {
        return strokeTypeProperty().get();
    }

    ObjectProperty<StrokeType> strokeTypeProperty();

    default void setStrokeType(StrokeType strokeType) {
        strokeTypeProperty().set(strokeType);
    }

    default double getStrokeWidth() {
        return strokeWidthProperty().get();
    }

    DoubleProperty strokeWidthProperty();

    default void setStrokeWidth(double strokeWidth) {
        strokeWidthProperty().set(strokeWidth);
    }

    static double normalizeAngle(double angle) {
        angle = angle % 360;
        return angle < 0 ? angle + 360 : angle;
    }

    default String getId() {
        return idProperty().get();
    }

    StringProperty idProperty();

    default void setId(String id) {
        idProperty().set(id);
    }

    default boolean isManaged() {
        return managedProperty().get();
    }

    BooleanProperty managedProperty();

    default void setManaged(boolean managed) {
        managedProperty().set(managed);
    }

    default double getOpacity() {
        return opacityProperty().get();
    }

    DoubleProperty opacityProperty();

    default void setOpacity(double opacity) {
        opacityProperty().set(opacity);
    }

    default double getRotate() {
        return rotateProperty().get();
    }

    DoubleProperty rotateProperty();

    default void setRotate(double rotate) {
        rotateProperty().set(rotate);
    }

    default Point3D getRotationAxis() {
        return rotationAxisProperty().get();
    }

    ObjectProperty<Point3D> rotationAxisProperty();

    default void setRotationAxis(Point3D rotationAxis) {
        rotationAxisProperty().set(rotationAxis);
    }

    default double getScaleX() {
        return scaleXProperty().get();
    }

    DoubleProperty scaleXProperty();

    default void setScaleX(double scaleX) {
        scaleXProperty().set(scaleX);
    }

    default double getScaleY() {
        return scaleYProperty().get();
    }

    DoubleProperty scaleYProperty();

    default void setScaleY(double scaleY) {
        scaleYProperty().set(scaleY);
    }

    default double getScaleZ() {
        return scaleZProperty().get();
    }

    DoubleProperty scaleZProperty();

    default void setScaleZ(double scaleZ) {
        scaleZProperty().set(scaleZ);
    }

    default String getStyle() {
        return styleProperty().get();
    }

    StringProperty styleProperty();

    default void setStyle(String style) {
        styleProperty().set(style);
    }

    default double getTranslateX() {
        return translateXProperty().get();
    }

    DoubleProperty translateXProperty();

    default void setTranslateX(double translateX) {
        translateXProperty().set(translateX);
    }

    default double getTranslateY() {
        return translateYProperty().get();
    }

    DoubleProperty translateYProperty();

    default void setTranslateY(double translateY) {
        translateYProperty().set(translateY);
    }

    default double getTranslateZ() {
        return translateZProperty().get();
    }

    DoubleProperty translateZProperty();

    default void setTranslateZ(double translateZ) {
        translateZProperty().set(translateZ);
    }

    default boolean isVisible() {
        return visibleProperty().get();
    }

    BooleanProperty visibleProperty();

    default void setVisible(boolean visible) {
        visibleProperty().set(visible);
    }
}
