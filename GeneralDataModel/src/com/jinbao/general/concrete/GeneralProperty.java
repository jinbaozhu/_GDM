package com.jinbao.general.concrete;

import java.util.ArrayList;
import java.util.List;

import com.jinbao.general.interfaces.GeneralPropertyI;
import com.jinbao.general.interfaces.GeneralTagI;
import com.jinbao.general.interfaces.ValueI;

public class GeneralProperty extends GeneralBase implements GeneralPropertyI {
	Value 		value;
	List<GeneralTagI> tags = new ArrayList<GeneralTagI>();

	public GeneralProperty(String name) {
		super(name);
	}
	
	@Override
	public ValueI getValue() {
		return value;
	}

	@Override
	public void setValue(ValueI value) {
		this.value = (Value) value;
	}

	@Override
	public List<GeneralTagI> getTags() {
		return tags;
	}

	@Override
	public void addTag(GeneralTagI tag) {
		tags.add(tag);
	}

	@Override
	public void removeTag(GeneralTagI tag) {
		for(int i = 0;i<tags.size();i++){
			if(tags.get(i).equals(tag.getId())){
				tags.remove(i);
				break;
			}
		}
	}

	@Override
	public GeneralTagI getTag(Long id) {
		for(int i = 0;i<tags.size();i++){
			if(tags.get(i).equals(id)){
				return tags.get(i);
			}
		}
		return null;
	}

}
