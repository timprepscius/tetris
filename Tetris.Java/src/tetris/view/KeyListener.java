package tetris.view;

import tetris.model.KeyState;

public interface KeyListener
{
    public boolean keyPress(int keyCode);
	public void keyRepeat(KeyState keyState);
}
