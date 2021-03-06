package com.example.illegalaliens.views.stages;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.IntArray;

/**
 * Stage for Hiscore
 * @author Johan Svennungsson
 */
public class HiscoreStage extends AbstractStage {

    private ClickListener mainMenuController;

    public HiscoreStage(ClickListener mainMenuController, IntArray hiscores) {
        this.mainMenuController = mainMenuController;

        this.addActor(addHiscoreTable(hiscores));

        this.setVisible(false);
    }

    private Actor addHiscoreLabel() {
        return ActorFactory.createLabel("Hiscore - Aliens killed");
    }

    private Table addHiscoreTable(IntArray hiscores) {
        Table hiscoreTable = new Table();

        hiscoreTable.setPosition(centerWidth, centerHeight, center);

        hiscoreTable.add(addHiscoreLabel()).width(center);
        hiscoreTable.row();

        if (hiscores.size == 0) {
            hiscoreTable.add(ActorFactory.createLabel(
                    "No hiscores are currently available. Play the game!"
            ));
            hiscoreTable.row();
        } else {
            hiscores.sort();
            hiscores.reverse();

            for (int i = 0; i < hiscores.size; i++) {
                if (hiscores.size < 20) {
                    hiscoreTable.add(addHiscoreIndexLabel(i)).width(100);
                    hiscoreTable.add(addHiscoreLabel(hiscores.get(i))).width(100);
                    hiscoreTable.row();
                }
            }
        }

        hiscoreTable.add(addMainMenuButton()).pad(30);

        return hiscoreTable;
    }

    private Actor addHiscoreIndexLabel(int i) {
        return ActorFactory.createLabel(String.valueOf(i + 1));
    }

    private Actor addHiscoreLabel(int i) {
        return ActorFactory.createLabel(String.valueOf(i));
    }

    private Actor addMainMenuButton() {
        return ActorFactory.createTextButton("backToMainMenu",
                "Back to Main Menu", mainMenuController);
    }

    public void refreshHiscoreTable(IntArray hiscores) {
        this.removeActors();
        this.addActor(addHiscoreTable(hiscores));
    }
    

}
