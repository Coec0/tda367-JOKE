package stages;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;

public class TopLeftGameUIStage extends Stage {
	private Label health;
	private Label money;
	private Skin skin;
	private Table table;

	public TopLeftGameUIStage() {
		skin = new Skin(Gdx.files.internal("ui/uiskin.json"));
		health = new Label("No Lives", skin);
		money = new Label("No money", skin);

		table = new Table();
		//table.setDebug(true);
		table.setPosition(0, Gdx.graphics.getHeight() - health.getHeight());
		table.add(health).padRight(10);
		table.add(money).right();
		updateTable();
		this.addActor(table);
	}

	private boolean updateHealth(String health) {
		if (!this.health.getText().equals(health)) {
			this.health.setText("Lives: " + health);
			return true;
		}
		return false;
	}

	private boolean updateMoney(String money) {
		if (!this.money.getText().equals(money)) {
			this.money.setText("Money: " + money);
			return true;
		}
		return false;
	}

	public void updateUI(String money, String health) {
		boolean moneyB = updateMoney(money);
		boolean healthB = updateHealth(health);
		if(healthB || moneyB)
			updateTable();
	}

	private void updateTable() {
		System.out.println("Update");
		health.pack();
		money.pack();
		table.setWidth(health.getWidth() + money.getWidth()+ 10); //10 is padding
	}
}
