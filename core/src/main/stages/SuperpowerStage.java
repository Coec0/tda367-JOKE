package stages;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

/**
 * Created by Emil on 2017-05-07.
 */
public class SuperpowerStage extends Stage {


    private Skin skin;
    private Table table;

    public SuperpowerStage(ClickListener superC){
        skin = new Skin(Gdx.files.internal("ui/uiskin.json"));
        this.table = new Table();
        table.setDebug(true);
        table.setPosition(Gdx.graphics.getWidth() - Gdx.graphics.getWidth()/3, 0);
        table.setHeight(400);
        table.setWidth(1600);

        table.add(addSuperpowerTable(superC));
        this.addActor(table);

    }

    private Table addSuperpowerTable(ClickListener superC){

        Table table = new Table();
        table.add(addSuperpowerButton(400,"nuke", superC)).expand().top();
        table.add(addSuperpowerButton(800,"wall", superC));
        return table;
    }

    private TextButton addSuperpowerButton(int x, String name, ClickListener CL){
        int width = 400;
        int height = 400;
        int y = 100;
        return addTextButton(x, y, width, height, name, "Superpower: " + name, CL);
    }

    private TextButton addTextButton(int x, int y, float width, float height, String name, String text ,ClickListener CL){
        TextButton textButton = new TextButton(text, skin, "default");

        textButton.setName(name);
        textButton.setTransform(false);
        textButton.setWidth(width);
        textButton.setHeight(height);
        textButton.setPosition(x, y);
        textButton.addListener(CL);

        return textButton;
    }
}
