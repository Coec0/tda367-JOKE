package com.example.illegalaliens.views.stages;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class MainMenuStage extends AbstractStage {

    private ClickListener mainMenuController;

    public MainMenuStage(ClickListener mainMenuController) {
        this.mainMenuController = mainMenuController;

        this.addActor(addAlienImage());
        this.addActor(addWelcomeText());
        this.addActor(addTable());

        this.setVisible(false);
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
        return ActorFactory.createTextButton("mapSelectStage", "Start", mainMenuController);
    }

    private Actor addAboutButton() {
        return ActorFactory.createTextButton("aboutStage", "About", mainMenuController);
    }

    private Actor addSettingsButton() {
        Actor settingsButton = ActorFactory.createTextButton("Settings");

        settingsButton.addListener(mainMenuController);

        return settingsButton;
    }

    private Actor addExitButton() {
        Actor exitButton = ActorFactory.createTextButton("Exit");

        exitButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y){
            	Gdx.app.exit();
            }
        });

        return exitButton;
    }

    private Actor addHiscoreButton() {
        Actor hiscoreButton = ActorFactory.createTextButton("Hiscore");

        hiscoreButton.addListener(mainMenuController);

        return hiscoreButton;
    }

    private Actor addWelcomeText() {
        return ActorFactory.createTextField("You will perish",
                centerWidth, centerHeight, 150f, 30f, true, center
        );
    }
}
