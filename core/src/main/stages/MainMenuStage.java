package stages;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.example.illegalaliens.IllegalAliensMain;
import screens.MainMenuScreen;

import static com.badlogic.gdx.utils.Align.center;

public class MainMenuStage extends Stage {

    private IllegalAliensMain game;
    private MainMenuScreen mainMenuScreen;
    private Skin skin;

    private Table table;
    private Image alienImage;
    private TextField welcomeText;

    public MainMenuStage(IllegalAliensMain game, MainMenuScreen mainMenuScreen) {
        this.game = game;
        this.mainMenuScreen = mainMenuScreen;

        skin = game.getSkin();

        this.addActor(addAlienImage());
        this.addActor(addWelcomeText());
        this.addActor(addTable());
    }

    private Image addAlienImage() {
        alienImage = new Image(new Texture("alien.png"));

        alienImage.setOrigin(alienImage.getWidth() / 2, alienImage.getHeight() / 2);
        alienImage.rotateBy(180);
        alienImage.setPosition(Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight()/2 + 100, center);

        return alienImage;
    }

    private Table addTable() {
        table = new Table();

        table.setWidth(150f);
        table.setHeight(200f);
        table.setPosition(Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight()/2 - 100, center);

        return fillTable(table);
    }

    private Table fillTable(Table table) {

        table.add(addStartButton()).width(100);
        table.row();
        table.add(addHiscoreButton()).width(100);
        table.row();
        table.add(addSettingsButton()).width(100);
        table.row();
        table.add(addAboutButton()).width(100);
        table.row();
        table.add(addExitButton()).width(100);

        return table;
    }

    private TextButton addStartButton() {
        TextButton startButton = new TextButton("Start game", skin, "default");

        startButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y){
                game.setGameScreen();
            }
        });

        return startButton;
    }

    private TextButton addAboutButton() {
        TextButton aboutButton = new TextButton("About", skin, "default");

        aboutButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y){
                mainMenuScreen.showAboutStage();
            }
        });

        return aboutButton;
    }

    private TextButton addSettingsButton() {
        TextButton settingsButton = new TextButton("Settings", skin, "default");

        settingsButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y){
                //TODO
            }
        });

        return settingsButton;
    }

    private TextButton addExitButton() {
        TextButton exitButton = new TextButton("Exit game", skin, "default");

        exitButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y){
                game.shutdown();
            }
        });

        return exitButton;
    }

    private TextButton addHiscoreButton() {
        TextButton hiscoreButton = new TextButton("Hiscore", skin, "default");

        hiscoreButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y){
                //TODO
            }
        });

        return hiscoreButton;
    }

    private TextField addWelcomeText() {
        welcomeText = new TextField("", skin, "default");

        welcomeText.setText("You will perish.");
        welcomeText.setAlignment(center);
        welcomeText.setWidth(150f);
        welcomeText.setHeight(30f);
        welcomeText.setDisabled(true);
        welcomeText.setPosition(Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 2, center);

        return welcomeText;
    }

    public void setVisible(boolean b) {
        alienImage.setVisible(b);
        table.setVisible(b);
        welcomeText.setVisible(b);
    }
}
