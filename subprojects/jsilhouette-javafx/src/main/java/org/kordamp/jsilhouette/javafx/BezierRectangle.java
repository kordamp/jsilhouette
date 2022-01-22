/*
 * SPDX-License-Identifier: Apache-2.0
 *
 * Copyright 2015-2021 Burkhard Mayer
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
import javafx.geometry.Point2D;
import javafx.scene.shape.Path;

import java.util.logging.Logger;

/**
 * BezierRectangle
 * <p>
 * Defines a BezierRectangle shape based on bezier curves.
 * <h3>Parameter</h3>
 * <ul>
 *  <li>Width: width of shape</li>
 *  <li>Height: height of shape</li>
 *  <li>RectangleWidth: width of inner rectangle (excluding corners)</li>
 *  <li>RectangleHeight: height of inner rectangle (excluding corners)</li>
 *  <li>bf{n}_x: defines the x component of a bezierPoint for Point n. Its defined with as a bezierFactor which is the relative distance from point n based on the difference of width and rectangleWidth</li>
 *  <li>bf{n}_y: defines the y component of a bezierPoint for Point n. Its defined with as a bezierFactor which is the relative distance from point n based on the difference of height and rectangleHeight</li>
 * </ul>
 *
 * <h3>Style Classes</h3>
 * <ul>
 *  <li>silhouette</li>
 *  <li>silhouette-bezier-rectangle</li>
 * </ul>
 *
 *        |---------------w----------------|
 *            |----------rw-----------|
 *  -     ----p6---------------------p5----
 *  |     |                                |
 *  |  -  p7                               p4
 *  |  |  |                                |
 *  h  rh |               c                |
 *  |  |  |                                |
 *  |  -  p8                               p3
 *  |     |                                |
 *  -     ----p1---------------------p2----
 *
 *
 * @author Burkhard Mayer
 */

public class BezierRectangle extends AbstractCenteredSilhouette {
    private static final Logger LOG = Logger.getLogger(BezierRectangle.class.getName());

    private DoubleProperty w,h;
    private DoubleProperty rw,rh;

    //BezierFactor: fraction distance of bezier points relative to  x:(w-rw/2) and y: (h-rh)/2
    private DoubleProperty bf1_x,bf1_y;
    private DoubleProperty bf2_x,bf2_y;
    private DoubleProperty bf3_x,bf3_y;
    private DoubleProperty bf4_x,bf4_y;
    private DoubleProperty bf5_x,bf5_y;
    private DoubleProperty bf6_x,bf6_y;
    private DoubleProperty bf7_x,bf7_y;
    private DoubleProperty bf8_x,bf8_y;

    protected Path path;
    public BezierRectangle() {
    }
    public BezierRectangle(double cx, double cy, double w, double h,double rw, double rh,
                           double bf){
        this(cx,cy,w,h,rw,rh,
                bf,0,
                bf,0,
                0,bf,
                0,bf,
                bf,0,
                bf,0,
                0,bf,
                0,bf);
    }
    public BezierRectangle(double cx, double cy, double w, double h,double rw, double rh,
                           double bf1_x,double bf1_y,
                           double bf2_x,double bf2_y,
                           double bf3_x,double bf3_y,
                           double bf4_x,double bf4_y,
                           double bf5_x,double bf5_y,
                           double bf6_x,double bf6_y,
                           double bf7_x,double bf7_y,
                           double bf8_x,double bf8_y) {
        initializing = true;
        setCenterX(cx);
        setCenterY(cy);
        setWidth(w);
        setHeight(h);
        setRectangleWidth(rw);
        setRectangleHeight(rh);
        setBezierFactor1_x(bf1_x);
        setBezierFactor1_y(bf1_y);
        setBezierFactor2_x(bf2_x);
        setBezierFactor2_y(bf2_y);
        setBezierFactor3_x(bf3_x);
        setBezierFactor3_y(bf3_y);
        setBezierFactor4_x(bf4_x);
        setBezierFactor4_y(bf4_y);
        setBezierFactor5_x(bf5_x);
        setBezierFactor5_y(bf5_y);
        setBezierFactor6_x(bf6_x);
        setBezierFactor6_y(bf6_y);
        setBezierFactor7_x(bf7_x);
        setBezierFactor7_y(bf7_y);
        setBezierFactor8_x(bf8_x);
        setBezierFactor8_y(bf8_y);
        initializing = false;
        calculateShape();
    }

    /**Width**/
    public double getWidth() {
        return wProperty().get();
    }

    @SuppressWarnings("unchecked")
    public DoubleProperty wProperty() {
        if (w == null) {
            w = new SimpleDoubleProperty(this, "w", 0);
            w.addListener(updateListener);
        }
        return w;
    }

    public void setWidth(double w) {
        wProperty().set(w);
    }

    /**Height**/
    public double getHeight() {
        return hProperty().get();
    }

    @SuppressWarnings("unchecked")
    public DoubleProperty hProperty() {
        if (h == null) {
            h = new SimpleDoubleProperty(this, "h", 0);
            h.addListener(updateListener);
        }
        return h;
    }

    public void setHeight(double h) {
        hProperty().set(h);
    }

    /**RectangleWidth**/
    public double getRectangleWidth() {
        return rwProperty().get();
    }

    @SuppressWarnings("unchecked")
    public DoubleProperty rwProperty() {
        if (rw == null) {
            rw = new SimpleDoubleProperty(this, "rw", 0);
            rw.addListener(updateListener);
        }
        return rw;
    }

    public void setRectangleWidth(double rw) {
        rwProperty().set(rw);
    }


    /**RectangleHeight**/
    public double getRectangleHeight() {
        return rhProperty().get();
    }

    @SuppressWarnings("unchecked")
    public DoubleProperty rhProperty() {
        if (rh == null) {
            rh = new SimpleDoubleProperty(this, "rh", 0);
            rh.addListener(updateListener);
        }
        return rh;
    }

    public void setRectangleHeight(double rh) {
        rhProperty().set(rh);
    }

    /**BezierFactor1**/
    public double getBezierFactor1_x() {
        return bf1_xProperty().get();
    }

    @SuppressWarnings("unchecked")
    public DoubleProperty bf1_xProperty() {
        if (bf1_x == null) {
            bf1_x = new SimpleDoubleProperty(this, "bf1_x", 0);
            bf1_x.addListener(updateListener);
        }
        return bf1_x;
    }

    public void setBezierFactor1_x(double bf1_x) {
        bf1_xProperty().set(bf1_x);
    }

    public double getBezierFactor1_y() {
        return bf1_yProperty().get();
    }

    @SuppressWarnings("unchecked")
    public DoubleProperty bf1_yProperty() {
        if (bf1_y == null) {
            bf1_y = new SimpleDoubleProperty(this, "bf1_y", 0);
            bf1_y.addListener(updateListener);
        }
        return bf1_y;
    }

    public void setBezierFactor1_y(double bf1_y) {
        bf1_yProperty().set(bf1_y);
    }

    /**BezierFactor2**/
    public double getBezierFactor2_x() {
        return bf2_xProperty().get();
    }

    @SuppressWarnings("unchecked")
    public DoubleProperty bf2_xProperty() {
        if (bf2_x == null) {
            bf2_x = new SimpleDoubleProperty(this, "bf2_x", 0);
            bf2_x.addListener(updateListener);
        }
        return bf2_x;
    }

    public void setBezierFactor2_x(double bf2_x) {
        bf2_xProperty().set(bf2_x);
    }

    public double getBezierFactor2_y() {
        return bf2_yProperty().get();
    }

    @SuppressWarnings("unchecked")
    public DoubleProperty bf2_yProperty() {
        if (bf2_y == null) {
            bf2_y = new SimpleDoubleProperty(this, "bf2_y", 0);
            bf2_y.addListener(updateListener);
        }
        return bf2_y;
    }

    public void setBezierFactor2_y(double bf2_y) {
        bf2_yProperty().set(bf2_y);
    }

    /**BezierFactor3**/
    public double getBezierFactor3_x() {
        return bf3_xProperty().get();
    }

    @SuppressWarnings("unchecked")
    public DoubleProperty bf3_xProperty() {
        if (bf3_x == null) {
            bf3_x = new SimpleDoubleProperty(this, "bf3_x", 0);
            bf3_x.addListener(updateListener);
        }
        return bf3_x;
    }

    public void setBezierFactor3_x(double bf3_x) {
        bf3_xProperty().set(bf3_x);
    }

    public double getBezierFactor3_y() {
        return bf3_yProperty().get();
    }

    @SuppressWarnings("unchecked")
    public DoubleProperty bf3_yProperty() {
        if (bf3_y == null) {
            bf3_y = new SimpleDoubleProperty(this, "bf3_y", 0);
            bf3_y.addListener(updateListener);
        }
        return bf3_y;
    }

    public void setBezierFactor3_y(double bf3_y) {
        bf3_yProperty().set(bf3_y);
    }

    /**BezierFactor4**/
    public double getBezierFactor4_x() {
        return bf4_xProperty().get();
    }

    @SuppressWarnings("unchecked")
    public DoubleProperty bf4_xProperty() {
        if (bf4_x == null) {
            bf4_x = new SimpleDoubleProperty(this, "bf4_x", 0);
            bf4_x.addListener(updateListener);
        }
        return bf4_x;
    }

    public void setBezierFactor4_x(double bf4_x) {
        bf4_xProperty().set(bf4_x);
    }

    public double getBezierFactor4_y() {
        return bf4_yProperty().get();
    }

    @SuppressWarnings("unchecked")
    public DoubleProperty bf4_yProperty() {
        if (bf4_y == null) {
            bf4_y = new SimpleDoubleProperty(this, "bf4_y", 0);
            bf4_y.addListener(updateListener);
        }
        return bf4_y;
    }

    public void setBezierFactor4_y(double bf4_y) {
        bf4_yProperty().set(bf4_y);
    }

    /**BezierFactor5**/
    public double getBezierFactor5_x() {
        return bf5_xProperty().get();
    }

    @SuppressWarnings("unchecked")
    public DoubleProperty bf5_xProperty() {
        if (bf5_x == null) {
            bf5_x = new SimpleDoubleProperty(this, "bf5_x", 0);
            bf5_x.addListener(updateListener);
        }
        return bf5_x;
    }

    public void setBezierFactor5_x(double bf5_x) {
        bf5_xProperty().set(bf5_x);
    }

    public double getBezierFactor5_y() {
        return bf5_yProperty().get();
    }

    @SuppressWarnings("unchecked")
    public DoubleProperty bf5_yProperty() {
        if (bf5_y == null) {
            bf5_y = new SimpleDoubleProperty(this, "bf5_y", 0);
            bf5_y.addListener(updateListener);
        }
        return bf5_y;
    }

    public void setBezierFactor5_y(double bf5_y) {
        bf5_yProperty().set(bf5_y);
    }

    /**BezierFactor6**/
    public double getBezierFactor6_x() {
        return bf6_xProperty().get();
    }

    @SuppressWarnings("unchecked")
    public DoubleProperty bf6_xProperty() {
        if (bf6_x == null) {
            bf6_x = new SimpleDoubleProperty(this, "bf6_x", 0);
            bf6_x.addListener(updateListener);
        }
        return bf6_x;
    }

    public void setBezierFactor6_x(double bf6_x) {
        bf6_xProperty().set(bf6_x);
    }

    public double getBezierFactor6_y() {
        return bf6_yProperty().get();
    }

    @SuppressWarnings("unchecked")
    public DoubleProperty bf6_yProperty() {
        if (bf6_y == null) {
            bf6_y = new SimpleDoubleProperty(this, "bf6_y", 0);
            bf6_y.addListener(updateListener);
        }
        return bf6_y;
    }

    public void setBezierFactor6_y(double bf6_y) {
        bf6_yProperty().set(bf6_y);
    }

    /**BezierFactor7**/
    public double getBezierFactor7_x() {
        return bf7_xProperty().get();
    }

    @SuppressWarnings("unchecked")
    public DoubleProperty bf7_xProperty() {
        if (bf7_x == null) {
            bf7_x = new SimpleDoubleProperty(this, "bf7_x", 0);
            bf7_x.addListener(updateListener);
        }
        return bf7_x;
    }

    public void setBezierFactor7_x(double bf7_x) {
        bf7_xProperty().set(bf7_x);
    }

    public double getBezierFactor7_y() {
        return bf7_yProperty().get();
    }

    @SuppressWarnings("unchecked")
    public DoubleProperty bf7_yProperty() {
        if (bf7_y == null) {
            bf7_y = new SimpleDoubleProperty(this, "bf7_y", 0);
            bf7_y.addListener(updateListener);
        }
        return bf7_y;
    }

    public void setBezierFactor7_y(double bf7_y) {
        bf7_yProperty().set(bf7_y);
    }

    /**BezierFactor8**/
    public double getBezierFactor8_x() {
        return bf8_xProperty().get();
    }

    @SuppressWarnings("unchecked")
    public DoubleProperty bf8_xProperty() {
        if (bf8_x == null) {
            bf8_x = new SimpleDoubleProperty(this, "bf8_x", 0);
            bf8_x.addListener(updateListener);
        }
        return bf8_x;
    }

    public void setBezierFactor8_x(double bf8_x) {
        bf8_xProperty().set(bf8_x);
    }

    public double getBezierFactor8_y() {
        return bf8_yProperty().get();
    }

    @SuppressWarnings("unchecked")
    public DoubleProperty bf8_yProperty() {
        if (bf8_y == null) {
            bf8_y = new SimpleDoubleProperty(this, "bf8_y", 0);
            bf8_y.addListener(updateListener);
        }
        return bf8_y;
    }

    public void setBezierFactor8_y(double bf8_y) {
        bf8_yProperty().set(bf8_y);
    }

    @Override
    protected void calculateShape() {
        PathBuilder p = new PathBuilder();
        double cx = getCenterX();
        double cy = getCenterY();
        double w = getWidth();
        double h = getHeight();
        double rw = getRectangleWidth();
        double rh = getRectangleHeight();
        double bd_x= (w-rw)/2;
        double bd_y=(h-rh)/2;

        if(w<0) {
            LOG.warning("width invalid: width " + w + " < 0");
            w=-w;
        }
        if(h<0) {
            LOG.warning("height invalid: height " + h + " < 0");
            h=-h;
        }
        if(rw<0) {
            LOG.warning("rectangleWidth invalid: rectangleWidth " + rw + " < 0");
            rw=-rw;
        }
        if(rh<0) {
            LOG.warning("rectangleHeight invalid: rectangleHeight " + rh + " < 0");
            rh=-rh;
        }

        if (w < rw) {
            LOG.warning("width and rectangleWidht are invalid: width " + w + " < " + rw + " rectangleWidth" );
            rw  = 0;
        }

        if (h < rh) {
            LOG.warning("height and rectangleHeight are invalid: height " + h + " < " + rh + " rectangleHeight" );
            rh  = 0;
        }

        p=p.moveTo(cx-rw/2,cy+h/2);
        if(rw!=0) p=p.lineTo(cx+rw/2,cy+h/2);
        p=p.cubicCurveTo(cx+rw/2+bd_x*getBezierFactor2_x(), cy+h/2+bd_y*getBezierFactor2_y(), cx+w/2+bd_x*getBezierFactor3_x(), cy+rh/2+bd_y*getBezierFactor3_y() ,cx+w/2,cy+rh/2);
        if(rh!=0) p=p.lineTo(cx+w/2,cy-rh/2);
        p=p.cubicCurveTo(cx+w/2+bd_x*getBezierFactor4_x(), cy-rh/2-bd_y*getBezierFactor4_y(), cx+rw/2+bd_x*getBezierFactor5_x(), cy-h/2-bd_y*getBezierFactor5_y() ,cx+rw/2,cy-h/2);
        if(rw!=0) p=p.lineTo(cx-rw/2,cy-h/2);
        p=p.cubicCurveTo(cx-rw/2-bd_x*getBezierFactor6_x(), cy-h/2-bd_y*getBezierFactor6_y(), cx-w/2-bd_x*getBezierFactor7_x(), cy-rh/2-bd_y*getBezierFactor7_y() ,cx-w/2,cy-rh/2);
        if(rh!=0) p=p.lineTo(cx-w/2,cy+rh/2);
        p=p.cubicCurveTo(cx-w/2-bd_x*getBezierFactor8_x(), cy+rh/2+bd_y*getBezierFactor8_y(), cx-rw/2-bd_x*getBezierFactor1_x(), cy+h/2+bd_y*getBezierFactor1_y() ,cx-rw/2,cy+h/2);

        path=p.build();

        path.getStyleClass().addAll("silhouette", "silhoutte-bezier-rectangle");

        setShape(path);
    }
}
