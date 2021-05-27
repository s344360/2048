package jpp.numbergame.gui;

import javafx.application.Application;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import jpp.numbergame.Direction;
import jpp.numbergame.NumberGame;

public class NumberGui extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {
		primaryStage.setTitle("2048");
		NumberGame game = new NumberGame(4, 4, 2);
		BorderPane borderPane = new BorderPane();
		Group group = new Group();
		borderPane.setCenter(group);

		HBox hbox = new HBox();
		borderPane.setBottom(hbox);
		Label label = new Label("Center");
		Label label1 = new Label("Left");
		Label label2 = new Label("Right");
		Label label3 = new Label("GAME OVER!");
		label3.setFont(Font  .font(40));

		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				Pane stack = new Pane();
				stack.setStyle("-fx-background-color: antiquewhite;");
				stack.setPrefSize(100, 100);
				stack.relocate(i * 104 + 40, j * 104 + 40);
				group.getChildren().add(stack);
				Label test = new Label(String.valueOf(game.get(i, j)));
				test.setFont(Font.font(20));
				test.setPadding(new Insets(42, 42, 42, 42));
				stack.getChildren().add(test);

			}
		}

		String str = String.valueOf(game.getPoints());
		Label label4 = new Label("SCORE: " + game.getPoints());
		hbox.getChildren().add(label4);
		hbox.setPadding(new Insets(15, 12, 15, 200));

		Scene scene = new Scene(borderPane, 500, 500);
		scene.setOnKeyPressed(new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent event) {
				if (game.canMove()) {
					switch (event.getCode()) {
					case UP:
						if (game.canMove(Direction.UP)) {
							game.move(Direction.UP);
							game.addRandomTile();
							for (int i = 0; i < 4; i++) {
								for (int j = 0; j < 4; j++) {

									Pane stack = new Pane();
									stack.setStyle("-fx-background-color: antiquewhite;");
									stack.setPrefSize(100, 100);
									stack.relocate(i * 104 + 40, j * 104 + 40);
									group.getChildren().add(stack);
									Label test = new Label(String.valueOf(game.get(i, j)));
									test.setFont(Font.font("Arial",20));
									test.setPadding(new Insets(42, 42, 42, 42));
									stack.getChildren().add(test);

								}
							}
							label4.setText("SCORE: " + game.getPoints());
						}
						break;
					case DOWN:
						if (game.canMove(Direction.DOWN)) {
							game.move(Direction.DOWN);
							game.addRandomTile();
							for (int i = 0; i < 4; i++) {
								for (int j = 0; j < 4; j++) {

									Pane stack = new Pane();
									stack.setStyle("-fx-background-color: antiquewhite;");
									stack.setPrefSize(100, 100);
									stack.relocate(i * 104 + 40, j * 104 + 40);
									group.getChildren().add(stack);
									Label test = new Label(String.valueOf(game.get(i, j)));
									test.setFont(Font.font(20));
									test.setPadding(new Insets(42, 42, 42, 42));
									stack.getChildren().add(test);

								}
							}
							label4.setText("SCORE: " + game.getPoints());

						}

						break;
					case LEFT:
						if (game.canMove(Direction.LEFT)) {
							game.move(Direction.LEFT);
							game.addRandomTile();
							for (int i = 0; i < 4; i++) {
								for (int j = 0; j < 4; j++) {

									Pane stack = new Pane();
									stack.setStyle("-fx-background-color: antiquewhite;");
									stack.setPrefSize(100, 100);
									stack.relocate(i * 104 + 40, j * 104 + 40);
									group.getChildren().add(stack);
									Label test = new Label(String.valueOf(game.get(i, j)));
									test.setFont(Font.font(20));
									test.setPadding(new Insets(42, 42, 42, 42));
									stack.getChildren().add(test);

								}
							}
							label4.setText("SCORE: " + game.getPoints());

						}

						break;
					case RIGHT:
						if (game.canMove(Direction.RIGHT)) {
							game.move(Direction.RIGHT);
							game.addRandomTile();
							for (int i = 0; i < 4; i++) {
								for (int j = 0; j < 4; j++) {

									Pane stack = new Pane();
									stack.setStyle("-fx-background-color: antiquewhite;");
									stack.setPrefSize(100, 100);
									stack.relocate(i * 104 + 40, j * 104 + 40);
									group.getChildren().add(stack);
									Label test = new Label(String.valueOf(game.get(i, j)));
									test.setFont(Font.font(20));
									test.setPadding(new Insets(42, 42, 42, 42));
									stack.getChildren().add(test);

								}
							}
							label4.setText("SCORE: " + game.getPoints());

						}

						break;

					}
				} else {
					borderPane.setCenter(label3);
				}
			}
		});

		primaryStage.setScene(scene);
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}

}