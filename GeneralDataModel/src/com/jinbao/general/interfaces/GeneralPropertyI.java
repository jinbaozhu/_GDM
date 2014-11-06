package com.jinbao.general.interfaces;

import java.util.List;

public interface GeneralPropertyI extends GeneralI {
	public abstract ValueI getValue();
	public abstract void setValue(ValueI value);
	
	public abstract List<GeneralTagI> getTags();
	public abstract GeneralTagI getTag(Long id);
	public abstract void addTag(GeneralTagI tag);
	public abstract void removeTag(GeneralTagI tag);
}
