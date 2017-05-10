package stages;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.example.illegalaliens.IllegalAliensMain;
import factories.ActorFactory;

/**
 * Stage for About
 * @author Johan Svennungsson
 */
public class AboutStage extends AbstractStage {

    private IllegalAliensMain game;
    private StageSwitcher stageSwitcher;

    public AboutStage(IllegalAliensMain game, StageSwitcher stageSwitcher) {
        this.game = game;
        this.stageSwitcher = stageSwitcher;

        this.addActor(addBackButton());
        this.addActor(addAboutLabel());
        this.addActor(addAboutTextArea());
    }

    private Actor addBackButton() {
        Actor backButton = ActorFactory.createTextButton("Back to Main menu",
                centerWidth, centerHeight - 150, center);

        backButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y){
                stageSwitcher.showStage(stageSwitcher.getMainMenuStage());
            }
        });

        return backButton;
    }

    private Actor addAboutLabel() {
        return ActorFactory.createLabel("About", centerWidth, centerHeight + 150, center);
    }

    private Actor addAboutTextArea() {
        return ActorFactory.createTextArea(
                "Developed by four students at Chalmers University of Technology "
                        + "as part of a software engineering project.",
                centerWidth, centerHeight, 200f, 200f, center);
    }

}
