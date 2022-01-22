/*
 * SPDX-License-Identifier: Apache-2.0
 *
 * Copyright 2015-2021 Andres Almiray
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
package org.kordamp.jsilhouette.sampler;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Shape;
import javafx.stage.Stage;
import org.kordamp.jsilhouette.javafx.*;

import java.net.URL;

/**
 * @author Burkhard Mayer
 */
public class SamplerBezierSquircle extends Application {
    public static void main(String[] args) {
        launch(SamplerBezierSquircle.class);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        URL location = getClass().getResource("sampler.fxml");
        FXMLLoader fxmlLoader = new FXMLLoader(location);

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(20, 20, 20, 20));
        grid.setHgap(20);
        grid.setVgap(20);
        int[] arr={1,2,3,4,5,6,7};
        //for(int i :arr) grid.add(new MultiRoundRectangle(50, 50, 100,50,0,0,50,25,50,25,0,0).getShape(), i, 0);
        grid.add(new Label("Squircle"),4,0);
        grid.add(new Label("BezierRectangle"),4,5);
        grid.add(new Label("BezierFactor"),0,2);
        grid.add(new Label("H:100 W:100"),0,3);
        grid.add(new Label("H:60 W:100"),0,4);
        grid.add(new Label("H:100 W:100\nRH:30 RW:60"),0,6);
        grid.add(new Label("H:60 W:100\nRH:10 RW:60"),0,7);
        for(int i :arr) {
            double bf=0+(i-1)*0.25;
            grid.add(new Label(""+bf),i,2);
            grid.add(new Squircle(50, 50, 100,100,bf).getShape(), i, 3);
            grid.add(new Squircle(50, 50, 100,60,bf).getShape(), i, 4);

            grid.add(new BezierRectangle(50, 50, 100,100,60,30,bf).getShape(), i, 6);
            grid.add(new BezierRectangle(50, 50, 100,60,60,10,bf).getShape(), i, 7);

        }



        Scene scene = new Scene(grid);
        scene.getStylesheets().add("org/kordamp/jsilhouette/sampler/sampler.css");

        primaryStage.setTitle("JSilhouette Sampler");
        primaryStage.setScene(scene);
        primaryStage.setWidth(1100);
        primaryStage.setHeight(800);
        primaryStage.show();
    }

    private Shape r(Silhouette silhouette, double rotate) {
        silhouette.setRotate(rotate);
        return silhouette.getShape();
    }
}