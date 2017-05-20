package stages;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.example.illegalaliens.IllegalAliensMain;
import factories.ActorFactory;
import map.Map;

public class MainMenuStage extends AbstractStage {

    private IllegalAliensMain game;
    private StageSwitcher stageSwitcher;

    public MainMenuStage(IllegalAliensMain game, StageSwitcher stageSwitcher) {
        this.game = game;
        this.stageSwitcher = stageSwitcher;

        this.addActor(addAlienImage());
        this.addActor(addWelcomeText());
        this.addActor(addTable());
    }

    private Actor addAlienImage() {
        return ActorFactory.createImage(centerWidth, centerHeight + 100, 180, center,
                new Texture("aliens/alien/alien.png")
        );
    }

    private Table addTable() {
        Table table = new Table();

        table.setWidth(150f);
        table.setHeight(200f);
        table.setPosition(centerWidth, centerHeight - 100, center);

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

    private Actor addStartButton() {
        Actor startButton = ActorFactory.createTextButton("Start");

        startButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y){
                game.startGame(new Map("map1", new Texture("maps/map1.png")));
//                stageSwitcher.showStage(stageSwitcher.getMapSelectStage());
            }
        });

        return startButton;
    }

    private Actor addAboutButton() {
        Actor aboutButton = ActorFactory.createTextButton("About");

        aboutButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y){
                stageSwitcher.showStage(stageSwitcher.getAboutStage());
            }
        });

        return aboutButton;
    }

    private Actor addSettingsButton() {
        Actor settingsButton = ActorFactory.createTextButton("Settings");

        settingsButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y){
                //TODO
            }
        });

        return settingsButton;
    }

    private Actor addExitButton() {
        Actor exitButton = ActorFactory.createTextButton("Exit");

        exitButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y){
                game.shutdown();
            }
        });

        return exitButton;
    }

    private Actor addHiscoreButton() {
        Actor hiscoreButton = ActorFactory.createTextButton("Hiscore");

        hiscoreButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y){
                //TODO
            }
        });

        return hiscoreButton;
    }

    private Actor addWelcomeText() {
        return ActorFactory.createTextField("You will perish",
                centerWidth, centerHeight, 150f, 30f, true, center
        );
    }
}
