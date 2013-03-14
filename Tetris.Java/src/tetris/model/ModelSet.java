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
