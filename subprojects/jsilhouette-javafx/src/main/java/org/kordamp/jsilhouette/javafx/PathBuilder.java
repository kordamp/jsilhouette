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
    private List<PathElement> elements = new ArrayList<>();

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
