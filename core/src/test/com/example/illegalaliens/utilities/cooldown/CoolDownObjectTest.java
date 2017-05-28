package com.example.illegalaliens.utilities.cooldown;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import com.example.illegalaliens.utilities.cooldown.CooldownObject;

public class CoolDownObjectTest {
	/**
	 * Test class for CoolDownObject
	 * @author Oscar 
	 */
	
	private CooldownObject CDO;
	
	@Before
	public void setUp() throws Exception {
		CDO = new CooldownObject(100);
	}

	@Test
	public void setCooldownTime() throws Exception {
		CDO.setCooldownTime(200);
		assertEquals(CDO.getCooldownTime(), 200, 0);
	}

	@Test
	public void increaseCooldownTime() throws Exception{
		CDO.setCooldownTime(200);
		CDO.increaseCooldownTime(400);
		//200 + 400 = 600
		assertEquals(CDO.getCooldownTime(), 600, 0);
	}
	
	@Test
	public void decreaseCooldownTime() throws Exception{
		CDO.setCooldownTime(400);
		CDO.decreaseCooldownTime(200);
		//400 - 200 = 200
		assertEquals(CDO.getCooldownTime(), 200, 0);
		CDO.decreaseCooldownTime(400);
		//200 - 400 = -200
		assertEquals(CDO.getCooldownTime(), -200, 0);
	}

	@Test
	public void setOnCooldown() throws Exception{
	
		CDO.setOnCooldown(true);
		assertEquals(CDO.getCooldownTicks(), CDO.getCooldownTime(), 0);
		CDO.setOnCooldown(false);
		assertEquals(CDO.getCooldownTicks(), 0, 0);
	}

	@Test
	public void resetTicks() throws Exception{
		CDO.resetTicks();
		assertEquals(CDO.getCooldownTicks(), CDO.getCooldownTime(), 0);
	}

	@Test
	public void setCooldownTicks() throws Exception{
		CDO.setCooldownTicks(200);
		assertEquals(CDO.getCooldownTicks(), 200, 0);
	}
	
	@Test
	public void tick() throws Exception{
		CDO.setCooldownTicks(200);
		CDO.tick();
		assertEquals(CDO.getCooldownTicks(), 199, 0);
	}
}
