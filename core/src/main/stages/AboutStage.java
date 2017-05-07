package stages;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextArea;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.example.illegalaliens.IllegalAliensMain;
import screens.MainMenuScreen;

import static com.badlogic.gdx.utils.Align.center;

/**
 * Stage for About
 * @author Johan Svennungsson
 */
public class AboutStage extends Stage {

    private IllegalAliensMain game;
    private MainMenuScreen mainMenuScreen;
    private Skin skin;

    private TextButton backButton;
    private Label aboutLabel;
    private TextArea aboutTextArea;

    public AboutStage(IllegalAliensMain game, MainMenuScreen mainMenuScreen) {
        this.game = game;
        this.mainMenuScreen = mainMenuScreen;

        skin = game.getSkin();

        this.addActor(addBackButton());
        this.addActor(addAboutLabel());
        this.addActor(addAboutTextArea());

        this.setVisible(false);
    }

    private TextButton addBackButton() {
        backButton = new TextButton("Back to Main menu", skin, "default");

        backButton.setPosition(Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 2 - 150, center);

        backButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y){
                mainMenuScreen.showMainMenuStage();
            }
        });

        return backButton;
    }

    private Label addAboutLabel() {
        aboutLabel = new Label("About", skin);

        aboutLabel.setPosition(Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 2 + 150, center);

        return aboutLabel;
    }

    private TextArea addAboutTextArea() {
        aboutTextArea = new TextArea("",skin, "default");

        aboutTextArea.setText(
                "Developed by four students at Chalmers University of Technology " +
                "as part of a software engineering project."
        );
        aboutTextArea.setWidth(200f);
        aboutTextArea.setHeight(200f);
        aboutTextArea.setPosition(Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 2, center);

        return aboutTextArea;
    }

    public void setVisible(boolean b) {
        backButton.setVisible(b);
        aboutLabel.setVisible(b);
        aboutTextArea.setVisible(b);
    }

}
