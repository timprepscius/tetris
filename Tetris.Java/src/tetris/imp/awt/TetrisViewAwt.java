package tetris.imp.awt;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

import tetris.model.Tetris;
import tetris.view.TetrisView;

@SuppressWarnings("serial")
public class TetrisViewAwt extends TetrisView implements ActionListener 
{
	Timer timer;

	public TetrisViewAwt (Tetris model)
	{
		super(model);
	}
	
	public void onStart ()
	{
		timer = new Timer(400, this);
		timer.start(); 
		timer.setRepeats(true);
	}

	@Override
	public void actionPerformed(ActionEvent arg0)
	{
		getModel().tick();
	}

}
