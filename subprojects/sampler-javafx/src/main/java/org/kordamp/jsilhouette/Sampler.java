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
package org.kordamp.jsilhouette;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Shape;
import javafx.stage.Stage;
import org.kordamp.jsilhouette.javafx.Lauburu;
import org.kordamp.jsilhouette.javafx.Silhouette;


/**
 * @author Andres Almiray
 */
public class Sampler extends Application {
    public static void main(String[] args) {
        launch(Sampler.class);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(20, 20, 20, 20));
        grid.setHgap(20);
        grid.setVgap(20);

        grid.add(new Lauburu(50, 50, 50).getShape(), 0, 0);

        /*
        grid.add(new Rays(50, 50, 50, 2, 0.5, true).getShape(), 0, 0);
        grid.add(new Rays(50, 50, 50, 3, 0.5, true).getShape(), 1, 0);
        grid.add(new Rays(50, 50, 50, 4, 0.5, true).getShape(), 2, 0);
        grid.add(new Rays(50, 50, 50, 5, 0.5, true).getShape(), 3, 0);
        grid.add(new Rays(50, 50, 50, 6, 0.5, true).getShape(), 4, 0);

        grid.add(new Cross(50, 50, 50, 20, 0.5).getShape(), 0, 0);
        grid.add(r(new Cross(50, 50, 50, 20, 0.5), 45), 1, 0);
        grid.add(new Almond(50, 50, 50).getShape(), 2, 0);
        grid.add(r(new Almond(50, 50, 50), 90), 3, 0);
        grid.add(new Astroid(50, 50, 50).getShape(), 4, 0);

        grid.add(new Asterisk(50, 50, 50, 20, 2, 0).getShape(), 0, 1);
        grid.add(new Asterisk(50, 50, 50, 20, 3, 0).getShape(), 1, 1);
        grid.add(new Asterisk(50, 50, 50, 20, 4, 0).getShape(), 2, 1);
        grid.add(new Asterisk(50, 50, 50, 20, 5, 0).getShape(), 3, 1);
        grid.add(new Asterisk(50, 50, 50, 20, 6, 0).getShape(), 4, 1);

        grid.add(new Asterisk(50, 50, 50, 20, 2, 1).getShape(), 0, 2);
        grid.add(new Asterisk(50, 50, 50, 20, 3, 1).getShape(), 1, 2);
        grid.add(new Asterisk(50, 50, 50, 20, 4, 1).getShape(), 2, 2);
        grid.add(new Asterisk(50, 50, 50, 20, 5, 1).getShape(), 3, 2);
        grid.add(new Asterisk(50, 50, 50, 20, 6, 1).getShape(), 4, 2);

        grid.add(new RoundPin(30, 30, 30).getShape(), 0, 3);
        grid.add(new Arrow(0, 0, 100, 100).getShape(), 1, 3);
        grid.add(r(new Arrow(0, 0, 100, 100), -90), 2, 3);
        grid.add(new Arrow(0, 0, 100, 100, 0.1, 0.5).getShape(), 3, 3);
        grid.add(new Arrow(0, 0, 100, 100, 0.5, 0.2).getShape(), 4, 3);

        grid.add(new RegularPolygon(50, 50, 50, 3).getShape(), 0, 4);
        grid.add(new RegularPolygon(50, 50, 50, 4).getShape(), 1, 4);
        grid.add(new RegularPolygon(50, 50, 50, 5).getShape(), 2, 4);
        grid.add(new RegularPolygon(50, 50, 50, 6).getShape(), 3, 4);
        grid.add(new RegularPolygon(50, 50, 50, 7).getShape(), 4, 4);

        grid.add(new Donut(50, 50, 50, 20, 3).getShape(), 0, 5);
        grid.add(new Donut(50, 50, 50, 20, 4).getShape(), 1, 5);
        grid.add(new Donut(50, 50, 50, 20, 5).getShape(), 2, 5);
        grid.add(new Donut(50, 50, 50, 20, 6).getShape(), 3, 5);
        grid.add(new Donut(50, 50, 50, 20, 7).getShape(), 4, 5);

        // grid.add(new MultiRoundRectangle(0, 0, 100, 100, 51).getShape(), 0, 6);
        grid.add(new Star(50, 50, 50, 20, 3).getShape(), 0, 6);
        grid.add(new Star(50, 50, 50, 20, 4).getShape(), 1, 6);
        grid.add(new Star(50, 50, 50, 20, 5).getShape(), 2, 6);
        grid.add(new Star(50, 50, 50, 20, 6).getShape(), 3, 6);
        grid.add(new Star(50, 50, 50, 20, 7).getShape(), 4, 6);
        */

        Scene scene = new Scene(grid);
        scene.getStylesheets().add("org/kordamp/jsilhouette/sampler.css");

        primaryStage.setTitle("JSilhouette Sampler");
        primaryStage.setScene(scene);
        primaryStage.setWidth(1024);
        primaryStage.setHeight(1024);
        primaryStage.show();
    }

    private Shape r(Silhouette silhouette, double rotate) {
        silhouette.setRotate(rotate);
        return silhouette.getShape();
    }
}