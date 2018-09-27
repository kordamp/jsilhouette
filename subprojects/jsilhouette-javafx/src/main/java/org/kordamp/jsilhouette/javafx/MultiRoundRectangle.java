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
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.shape.Shape;

import java.util.logging.Logger;

/**
 * = MultiRoundRectangle
 *
 * Defines a rounded rectangle, each corner may have a different roundness factor.
 *
 * image::shape_multiround_rectangle.png[]
 *
 * == Constraints
 *
 *  . `topLeftWidth` + `topRightWidth` +<=+ `width`
 *  . `topLeftHeight` + `topRightHeight` +<=+ `height`
 *  . `bottomLeftWidth` + `bottomRightWidth` +<=+ `width`
 *  . `bottomLeftHeight` + `bottomRightHeight` +<=+ `height`
 *
 * == Style Classes
 *
 *  . `silhouette`
 *  . `silhouette-multiround-rectangle`
 *
 * @author Andres Almiray
 */
public class MultiRoundRectangle extends AbstractSilhouette {
    private static final Logger LOG = Logger.getLogger(MultiRoundRectangle.class.getName());

    private DoubleProperty x;
    private DoubleProperty y;
    private DoubleProperty width;
    private DoubleProperty height;
    private DoubleProperty topLeftWidth;
    private DoubleProperty topLeftHeight;
    private DoubleProperty topRightWidth;
    private DoubleProperty topRightHeight;
    private DoubleProperty bottomLeftWidth;
    private DoubleProperty bottomLeftHeight;
    private DoubleProperty bottomRightWidth;
    private DoubleProperty bottomRightHeight;

    public MultiRoundRectangle() {
    }

    public MultiRoundRectangle(double x, double y, double width, double height, double round) {
        this(x, y, width, height, round, round, round, round, round, round, round, round);
    }

    public MultiRoundRectangle(double x, double y, double width, double height, double topLeft, double topRight,
                               double bottomLeft, double bottomRight) {
        this(x, y, width, height, topLeft, topLeft, topRight, topRight, bottomLeft, bottomLeft, bottomRight, bottomRight);
    }

    public MultiRoundRectangle(double x, double y, double width, double height, double topLeftWidth, double topLeftHeight,
                               double topRightWidth, double topRightHeight, double bottomLeftWidth, double bottomLeftHeight,
                               double bottomRightWidth, double bottomRightHeight) {
        initializing = true;
        setX(x);
        setY(y);
        setWidth(width);
        setHeight(height);
        setTopLeftWidth(topLeftWidth);
        setTopLeftHeight(topLeftHeight);
        setTopRightWidth(topRightWidth);
        setTopRightHeight(topRightHeight);
        setBottomLeftWidth(bottomLeftWidth);
        setBottomLeftHeight(bottomLeftHeight);
        setBottomRightWidth(bottomRightWidth);
        setBottomRightHeight(bottomRightHeight);
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

    public double getTopLeftHeight() {
        return topLeftHeightProperty().get();
    }

    @SuppressWarnings("unchecked")
    public DoubleProperty topLeftHeightProperty() {
        if (topLeftHeight == null) {
            topLeftHeight = new SimpleDoubleProperty(this, "topLeftHeight", 0);
            topLeftHeight.addListener(updateListener);
        }
        return topLeftHeight;
    }

    public void setTopLeftHeight(double topLeftHeight) {
        topLeftHeightProperty().set(topLeftHeight);
    }

    public double getTopLeftWidth() {
        return topLeftWidthProperty().get();
    }

    @SuppressWarnings("unchecked")
    public DoubleProperty topLeftWidthProperty() {
        if (topLeftWidth == null) {
            topLeftWidth = new SimpleDoubleProperty(this, "topLeftWidth", 0);
            topLeftWidth.addListener(updateListener);
        }
        return topLeftWidth;
    }

    public void setTopLeftWidth(double topLeftWidth) {
        topLeftWidthProperty().set(topLeftWidth);
    }

    public double getTopRightHeight() {
        return topRightHeightProperty().get();
    }

    @SuppressWarnings("unchecked")
    public DoubleProperty topRightHeightProperty() {
        if (topRightHeight == null) {
            topRightHeight = new SimpleDoubleProperty(this, "topRightHeight", 0);
            topRightHeight.addListener(updateListener);
        }
        return topRightHeight;
    }

    public void setTopRightHeight(double topRightHeight) {
        topRightHeightProperty().set(topRightHeight);
    }

    public double getTopRightWidth() {
        return topRightWidthProperty().get();
    }

    @SuppressWarnings("unchecked")
    public DoubleProperty topRightWidthProperty() {
        if (topRightWidth == null) {
            topRightWidth = new SimpleDoubleProperty(this, "topRightWidth", 0);
            topRightWidth.addListener(updateListener);
        }
        return topRightWidth;
    }

    public void setTopRightWidth(double topRightWidth) {
        topRightWidthProperty().set(topRightWidth);
    }

    public double getBottomLeftHeight() {
        return bottomLeftHeightProperty().get();
    }

    @SuppressWarnings("unchecked")
    public DoubleProperty bottomLeftHeightProperty() {
        if (bottomLeftHeight == null) {
            bottomLeftHeight = new SimpleDoubleProperty(this, "bottomLeftHeight", 0);
            bottomLeftHeight.addListener(updateListener);
        }
        return bottomLeftHeight;
    }

    public void setBottomLeftHeight(double bottomLeftHeight) {
        bottomLeftHeightProperty().set(bottomLeftHeight);
    }

    public double getBottomLeftWidth() {
        return bottomLeftWidthProperty().get();
    }

    @SuppressWarnings("unchecked")
    public DoubleProperty bottomLeftWidthProperty() {
        if (bottomLeftWidth == null) {
            bottomLeftWidth = new SimpleDoubleProperty(this, "bottomLeftWidth", 0);
            bottomLeftWidth.addListener(updateListener);
        }
        return bottomLeftWidth;
    }

    public void setBottomLeftWidth(double bottomLeftWidth) {
        bottomLeftWidthProperty().set(bottomLeftWidth);
    }

    public double getBottomRightHeight() {
        return bottomRightHeightProperty().get();
    }

    @SuppressWarnings("unchecked")
    public DoubleProperty bottomRightHeightProperty() {
        if (bottomRightHeight == null) {
            bottomRightHeight = new SimpleDoubleProperty(this, "bottomRightHeight", 0);
            bottomRightHeight.addListener(updateListener);
        }
        return bottomRightHeight;
    }

    public void setBottomRightHeight(double bottomRightHeight) {
        bottomRightHeightProperty().set(bottomRightHeight);
    }

    public double getBottomRightWidth() {
        return bottomRightWidthProperty().get();
    }

    @SuppressWarnings("unchecked")
    public DoubleProperty bottomRightWidthProperty() {
        if (bottomRightWidth == null) {
            bottomRightWidth = new SimpleDoubleProperty(this, "bottomRightWidth", 0);
            bottomRightWidth.addListener(updateListener);
        }
        return bottomRightWidth;
    }

    public void setBottomRightWidth(double bottomRightWidth) {
        bottomRightWidthProperty().set(bottomRightWidth);
    }

    @Override
    protected void calculateShape() {
        double x = getX();
        double y = getY();
        double w = getWidth();
        double h = getHeight();

        double tlw = getTopLeftWidth();
        double tlh = getTopLeftHeight();
        double trw = getTopRightWidth();
        double trh = getTopRightHeight();
        double blw = getBottomLeftWidth();
        double blh = getBottomLeftHeight();
        double brw = getBottomRightWidth();
        double brh = getBottomRightHeight();

        if (blw + brw > w) {
            LOG.info("bottom rounding factors are invalid: " + blw + " + " + brw + " > " + w);
            blw = brw = 0;
        }

        if (tlh + blh > h) {
            LOG.info("left rounding factors are invalid: " + tlh + " + " + blh + " > " + h);
            tlh = blh = 0;
        }

        if (trh + brh > h) {
            LOG.info("right rounding factors are invalid: " + trh + " + " + brh + " > " + h);
            trh = brh = 0;
        }

        if (tlw + trw > w) {
            LOG.info("top rounding factors are invalid: " + tlw + " + " + trw + " > " + w);
            tlw = trw = 0;
        }

        PathBuilder p = new PathBuilder();

        if (tlw > 0 && tlh > 0) {
            p.moveTo(x, y + tlh);
            p.arcTo(x + tlw, y, tlw, tlh);
        } else {
            p.moveTo(x, y);
        }

        if (trw > 0 && trh > 0) {
            p.lineTo(x + w - trw, y);
            p.arcTo(x + w, y + trh, trw, trh);
        } else {
            p.lineTo(x + w, y);
        }

        if (brw > 0 && brh > 0) {
            p.lineTo(x + w, y + h - brh);
            p.arcTo(x + w - brw, y + h, brw, brh);
        } else {
            p.lineTo(x + w, y + h);
        }

        if (blw > 0 && blh > 0) {
            p.lineTo(x + blw, y + h);
            p.arcTo(x, y + h - blh, blw, blh);
        } else {
            p.lineTo(x, y + h);
        }

        Shape shape = p.build();
        shape.getStyleClass().addAll("silhouette", "silhoutte-multiround-rectangle");

        setShape(shape);
    }
}
