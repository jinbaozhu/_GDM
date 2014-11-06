package com.jinbao.general.interfaces;

import java.util.List;

public interface GeneralObjectI extends GeneralI {
	public abstract boolean setPropertyValue(Long propId,ValueI value);
	public abstract ValueI getPropertyValue(Long propId);
	
	
	public abstract GeneralPropertyI getProperty(Long propId);
	public abstract boolean addProperty(GeneralPropertyI property);
	public abstract boolean removeProperty(Long propId);
	
	// meta
	public abstract GeneralObjectI meta();
	
	// owner
	public abstract void setOwner(GeneralObjectI owner);
	public abstract GeneralObjectI owner();
	
	// ownees
	public abstract GeneralObjectI getOwnee(Long objctId);
	public abstract List<GeneralObjectI> getOwnees(GeneralObjectI parent);

}
