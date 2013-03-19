package tetris.imp.awt;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import tetris.model.GameInfo;
import tetris.model.GameList;
import tetris.view.GameListView;

public class GameListViewAwt extends GameListView
{
	DefaultListModel listModel = new DefaultListModel();
	JList list = new JList(listModel);
	
	public GameListViewAwt (GameList model)
	{
		super(model);

		this.setSize(200,400);
		this.add(list);
		
		list.addListSelectionListener(new ListSelectionListener() {
			int lastSelection = -1;
			public void valueChanged(ListSelectionEvent event)
			{
				if (event.getValueIsAdjusting())
					return;
				
				if (event.getFirstIndex() != lastSelection)
					getModel().joinGame(getModel().getGames().get(event.getFirstIndex()));
				
				lastSelection = event.getFirstIndex();
				GameListViewAwt.this.frame.requestFocusInWindow();
			}
		});
	}
	
	public void onDraw ()
	{
		listModel.clear();
		
		for (GameInfo info : getModel().getGames())
		{
			listModel.addElement(info.getName());
		}
	}
	
	
}
