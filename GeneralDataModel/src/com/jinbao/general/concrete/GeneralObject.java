package com.jinbao.general.concrete;

import java.util.HashMap;
import java.util.List;

import com.jinbao.general.interfaces.GeneralObjectI;
import com.jinbao.general.interfaces.GeneralPropertyI;
import com.jinbao.general.interfaces.ValueI;

public class GeneralObject extends GeneralBase implements GeneralObjectI {
	/// Propertys
	HashMap<Long,GeneralPropertyI> properties = new HashMap<Long,GeneralPropertyI>();
	HashMap<String,List<GeneralPropertyI>> nameProperties = new HashMap<String,List<GeneralPropertyI>>();

	/// Owner
	GeneralObjectI owner;
	
	/// Parent
	GeneralObjectI meta;
	
	/// Ownee
	HashMap<Long,GeneralObjectI> ownees = new HashMap<Long,GeneralObjectI>();
	HashMap<GeneralObjectI,List<GeneralObjectI>> typedOwnees = new HashMap<GeneralObjectI,List<GeneralObjectI>>();

	
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
	public boolean addProperty(GeneralPropertyI property) {
		return properties.put(property.getId(), property) != null;
	}
	
	@Override
	public boolean removeProperty(Long propId) {
		return properties.remove(propId) != null;
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
		GeneralObjectI go = ownees.get(objctId);
		return go;
	}


	@Override
	public List<GeneralObjectI> getOwnees(GeneralObjectI parent) {
		List<GeneralObjectI> gol = typedOwnees.get(parent);
		return gol;
	}


}
