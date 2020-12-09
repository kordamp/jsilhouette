/*
 * SPDX-License-Identifier: Apache-2.0
 *
 * Copyright 2015-2020 Andres Almiray
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

import javafx.scene.shape.ArcTo;
import javafx.scene.shape.ClosePath;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.shape.PathElement;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Andres Almiray
 */
public class PathBuilder {
    private final List<PathElement> elements = new ArrayList<>();

    public PathBuilder moveTo(double x, double y) {
        elements.add(new MoveTo(x, y));
        return this;
    }

    public PathBuilder arcTo(double x, double y, double rx, double ry) {
        return arcTo(x, y, rx, ry, true, false);
    }

    public PathBuilder arcTo(double x, double y, double rx, double ry, boolean sweep) {
        return arcTo(x, y, rx, ry, sweep, false);
    }

    public PathBuilder arcTo(double x, double y, double rx, double ry, boolean sweep, boolean large) {
        elements.add(new ArcTo(rx, ry, 0, x, y, large, sweep));
        return this;
    }

    public PathBuilder lineTo(double x, double y) {
        elements.add(new LineTo(x, y));
        return this;
    }

    public PathBuilder close() {
        elements.add(new ClosePath());
        return this;
    }

    public Path build() {
        Path path = new Path();
        path.getElements().addAll(elements);
        path.getElements().add(new ClosePath());
        elements.clear();
        return path;
    }
}
