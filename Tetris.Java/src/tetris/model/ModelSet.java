///////////////////////////////////////////////////////////////////////////////
// Copyright (C) 2013 Timothy Prepscius
//
// This is a viewable source license.  No copying or modification or use is
// permitted.
//
// If you would like to participate in its development, or
// if you would like a different license, please contact the author.
///////////////////////////////////////////////////////////////////////////////

package tetris.model;

import java.util.ArrayList;
import java.util.List;

public class ModelSet extends Model
{
	List<Model> models = new ArrayList<Model>();
	
	public void addModel (Model model)
	{
		models.add(model);
		model.parent = this;
	}
	
	public void removeModel (Model model)
	{
		model.parent = null;
		models.remove(model);
	}
	
	public void tick ()
	{
		super.tick();
		for (Model model : models)
			model.tick();
	}	
}
