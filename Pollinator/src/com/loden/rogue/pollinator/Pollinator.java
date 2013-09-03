package com.loden.rogue.pollinator;

import com.badlogic.gdx.Game;
import com.loden.rogue.pollinator.views.screens.MainGameScreen;

public class Pollinator extends Game
{
		@Override
		public void create(){
				setScreen(new MainGameScreen(this));
		}

}