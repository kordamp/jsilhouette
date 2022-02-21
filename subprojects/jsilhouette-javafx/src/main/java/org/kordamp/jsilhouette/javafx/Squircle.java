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
import java.util.logging.Logger;

/**
 * Squircle
 * <p>
 * Defines a squircle shape based on BezierRectangle.
 * Squircle is a special case of BezierRectangle with following Conditions:
 *
 * RectangleWidth = 0
 * RectangleHeight = 0
 * bezierFactor = € double  (nice Squircle bezierFactor =1 )
 *
 *
 * bf1 = (bezierFactor,0)
 * bf2 = (bezierFactor,0)
 * bf3 = (0,bezierFactor)
 * bf4 = (0,bezierFactor)
 * bf5 = (bezierFactor,0)
 * bf6 = (bezierFactor,0)
 * bf7 = (0,bezierFactor)
 * bf8 = (0,bezierFactor)
 *
 * <h3>Style Classes</h3>
 * <ul>
 *  <li>silhouette</li>
 *  <li>silhouette-squircle</li>
 * </ul>
 *
 * @author Burkhard Mayer
 */
public class Squircle extends BezierRectangle {
    private static final Logger LOG = Logger.getLogger(Squircle.class.getName());

    private DoubleProperty bezierFactor;

    public Squircle(double cx, double cy, double w, double h,double bezierFactor) {
        super(cy, cy, w, h,0,0,
                bezierFactor,0,
                bezierFactor,0,
                0,bezierFactor,
                0,bezierFactor,
                bezierFactor,0,
                bezierFactor,0,
                0,bezierFactor,
                0,bezierFactor);
    }

    @Override
    protected void calculateShape() {
        super.calculateShape();
        path.getStyleClass().clear();
        path.getStyleClass().addAll("silhouette", "silhoutte-squircle");

        setShape(path);
    }

}
