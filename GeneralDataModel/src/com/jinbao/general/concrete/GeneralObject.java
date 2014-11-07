package com.jinbao.general.concrete;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.jinbao.general.interfaces.GeneralObjectI;
import com.jinbao.general.interfaces.GeneralPropertyI;
import com.jinbao.general.interfaces.ValueI;

public class GeneralObject extends GeneralBase implements GeneralObjectI {
	
	/// Property
	HashMap<Long,GeneralPropertyI> properties = new HashMap<Long,GeneralPropertyI>();
	HashMap<String,List<GeneralPropertyI>> nameProperties = new HashMap<String,List<GeneralPropertyI>>();

	/// Owner
	GeneralObjectI owner;
	
	/// Parent
	GeneralObjectI meta;
	
	/// Ownee
	HashMap<Long,GeneralObjectI> ownees = new HashMap<Long,GeneralObjectI>();
	HashMap<GeneralObjectI,List<GeneralObjectI>> typedOwnees = new HashMap<GeneralObjectI,List<GeneralObjectI>>();

	/// Reference
	HashMap<String,List<GeneralObjectI>> references = new HashMap<String,List<GeneralObjectI>>();
	
	//------------------------------Access interface------------------------------------
	
	public GeneralObject(String name) {
		super(name);
	}
	
	public GeneralObject(String name,GeneralObjectI meta) {
		super(name);
		this.meta = meta;
	}

	@Override
	public boolean setPropertyValue(Long propId, ValueI value) {
		GeneralPropertyI property = properties.get(propId);
		if(property != null){
			property.setValue(value);
			return true;
		}
		return false;
	}

	@Override
	public ValueI getPropertyValue(Long propId) {
		GeneralPropertyI property = properties.get(propId);
		if(property != null){
			return property.getValue();
		}
		return null;
	}

	@Override
	public GeneralPropertyI getProperty(Long propId) {
		return properties.get(propId);
	}
	
	@Override
	public GeneralPropertyI addProperty(GeneralPropertyI property) {
		properties.put(property.getId(), property);
		return property;
	}
	
	@Override
	public GeneralPropertyI removeProperty(Long propId) {
		return properties.remove(propId);
	}

	@Override
	public GeneralObjectI owner() {
		return owner;
	}
	
	@Override
	public GeneralObjectI meta() {
		return meta;
	}
	
	@Override
	public void setOwner(GeneralObjectI owner) {
		this.owner = owner;
	}

	@Override
	public GeneralObjectI getOwnee(Long objctId) {
		return ownees.get(objctId);
	}

	@Override
	public GeneralObjectI addOwnee(GeneralObjectI ownee) {
		ownee.setOwner(this);
		List<GeneralObjectI> list = typedOwnees.get(ownee.meta());
		if(list == null){
			list = new ArrayList<GeneralObjectI>();
			typedOwnees.put(ownee.meta(), list);
		}
		list.add(ownee);
		
		ownees.put(ownee.getId(), ownee);
		
		return ownee;
	}

	@Override
	public GeneralObjectI removeOwnee(GeneralObjectI ownee) {
		ownee.setOwner(null);
		List<GeneralObjectI> list = typedOwnees.get(ownee.meta());
		if(list != null){
			list.remove(ownee);
		}
		
		return ownees.remove(ownee.getId());
	}
	
	@Override
	public List<GeneralObjectI> getOwnees(GeneralObjectI parent) {
		List<GeneralObjectI> gol = typedOwnees.get(parent);
		return gol;
	}

	@Override
	public List<GeneralObjectI> addReference(String refenceName, List<GeneralObjectI> reference) {
		references.put(refenceName, reference);
		return reference;
	}
	
	@Override
	public GeneralObjectI addReference(String refenceName, GeneralObjectI reference) {
		List<GeneralObjectI> referencelist = references.get(refenceName);
		if(referencelist == null){
			referencelist = new ArrayList<GeneralObjectI>();
			references.put(refenceName, referencelist);
		}
		return reference;
	}
	
	@Override
	public List<GeneralObjectI> getReference(String refenceName) {
		return references.get(refenceName);
	}
	
	@Override
	public List<GeneralObjectI> removetReference(String refenceName) {
		return references.remove(refenceName);
	}

	@Override
	public GeneralObjectI getFirstReference(String refenceName) {
		List<GeneralObjectI> referencelist = references.get(refenceName);
		if(referencelist != null && referencelist.size()>0)
			return referencelist.get(0);
		return null;
	}
}
