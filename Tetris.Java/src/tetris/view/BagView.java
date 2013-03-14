package tetris.view;

import tetris.model.Bag;
import tetris.model.Model;

public class BagView extends View
{

	public BagView(Model model) {
		super(model);
	}
	
	public Bag getModel ()
	{
		return (Bag)model;
	}

}
