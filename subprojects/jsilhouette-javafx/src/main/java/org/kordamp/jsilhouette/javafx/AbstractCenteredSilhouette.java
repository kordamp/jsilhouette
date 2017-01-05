/*
 * Copyright 2015-2017 Andres Almiray
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

/**
 * @author Andres Almiray
 */
public abstract class AbstractCenteredSilhouette extends AbstractSilhouette implements CenteredSilhouette {
    private DoubleProperty centerX;
    private DoubleProperty centerY;

    @Override
    @SuppressWarnings("unchecked")
    public DoubleProperty centerXProperty() {
        if (centerX == null) {
            centerX = new SimpleDoubleProperty(this, "centerX", 0);
            centerX.addListener(updateListener);
        }
        return centerX;
    }

    @Override
    @SuppressWarnings("unchecked")
    public DoubleProperty centerYProperty() {
        if (centerY == null) {
            centerY = new SimpleDoubleProperty(this, "centerY", 0);
            centerY.addListener(updateListener);
        }
        return centerY;
    }
}
