package App;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.util.Objects;

public class Main extends Application {
    private Stage primaryStage;
    @Override
    public void start(Stage primaryStage) throws Exception {
        this.primaryStage = primaryStage;
        showLoginScene();
    }
    public void showLoginScene() {
        try {
            FXMLLoader loader = new FXMLLoader(Main.class.getResource("login-view.fxml"));
            Scene scene = new Scene(loader.load(), 750, 560);
            primaryStage.setTitle("Login in to your account");
            primaryStage.getIcons().add(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/logo.png"))));
            primaryStage.setScene(scene);

            LoginController controller = loader.getController();
            controller.setMain(this);

            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void showHomeScene() {
        try {
            FXMLLoader loader = new FXMLLoader(Main.class.getResource("Home-view.fxml"));
            Scene scene = new Scene(loader.load(), 750, 560);
            primaryStage.setTitle("Home");
            primaryStage.getIcons().add(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/logo.png"))));
            primaryStage.setScene(scene);

            HomeController controller = loader.getController();
            controller.setMain(this);

            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch();
    }
}
