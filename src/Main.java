import UserSession.UserSession;
import View.MainView;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Main extends Application {

    private Timeline animation;

    public static void main(String[] args){
        Application.launch();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        MainView view = new MainView();
        UserSession userSession = new UserSession(view,"nodes.NodeClasses");

        Scene scene = view.getMyScene();

        primaryStage.setResizable(false);
        primaryStage.setMaximized(false);
        primaryStage.setScene(scene);
        primaryStage.setTitle("SLogo");
        primaryStage.show();

        view.getUserScreen().initializeScreen(view.getUserScreen().getWidth()/2, view.getUserScreen().getHeight()/2);

        var frame = new KeyFrame(Duration.millis(60), e -> step(userSession));
        animation = new Timeline();
        animation.setCycleCount(Timeline.INDEFINITE);
        animation.getKeyFrames().add(frame);
        animation.play();
    }

    private void step(UserSession userSession){
        userSession.updateSession();
    }

}
