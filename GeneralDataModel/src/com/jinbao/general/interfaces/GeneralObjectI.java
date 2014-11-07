package com.jinbao.general.interfaces;

import java.util.List;

public interface GeneralObjectI extends GeneralI {
	public abstract boolean setPropertyValue(Long propId,ValueI value);
	public abstract ValueI getPropertyValue(Long propId);
	
	
	public abstract GeneralPropertyI getProperty(Long propId);
	public abstract GeneralPropertyI addProperty(GeneralPropertyI property);
	public abstract GeneralPropertyI removeProperty(Long propId);
	
	// meta
	public abstract GeneralObjectI meta();
	
	// owner
	public abstract void setOwner(GeneralObjectI owner);
	public abstract GeneralObjectI owner();
	
	// ownee
	public abstract GeneralObjectI getOwnee(Long objctId);
	public abstract List<GeneralObjectI> getOwnees(GeneralObjectI parent);
	public abstract GeneralObjectI addOwnee(GeneralObjectI ownee);
	public abstract GeneralObjectI removeOwnee(GeneralObjectI ownee);
	
	// reference
	public abstract List<GeneralObjectI> addReference(String refenceName, List<GeneralObjectI> reference);
	public abstract List<GeneralObjectI> getReference(String refenceName);
	public abstract List<GeneralObjectI> removetReference(String refenceName);
	public abstract GeneralObjectI addReference(String refenceName, GeneralObjectI reference);
	public abstract GeneralObjectI getFirstReference(String refenceName);
}
