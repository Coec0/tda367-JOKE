package stages;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.example.illegalaliens.IllegalAliensMain;

public class MainMenuStage extends Stage {

    private IllegalAliensMain game;
    private Skin skin;

    public MainMenuStage(IllegalAliensMain game) {
        this.game = game;
        skin = new Skin(Gdx.files.internal("ui/uiskin.json"));

        addWelcomeText();
        addStartButton();
    }

    private void addWelcomeText() {
        TextField welcomeText = new TextField("", skin, "default");

        welcomeText.setText("Welcome to Illegal Aliens. Press the Start button to start the game.");
        welcomeText.setWidth(500f);
        welcomeText.setHeight(30f);
        welcomeText.setDisabled(true);
        welcomeText.setPosition(Gdx.graphics.getWidth()/2 - 150, Gdx.graphics.getHeight()/2 + 100);

        this.addActor(welcomeText);
    }

    private void addStartButton() {
        TextButton startButton = new TextButton("Start game", skin, "default");

        startButton.setWidth(200f);
        startButton.setHeight(20f);
        startButton.setPosition(Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight()/2);

        startButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y){
                game.setGameScreen();
            }
        });

        this.addActor(startButton);
    }
}
