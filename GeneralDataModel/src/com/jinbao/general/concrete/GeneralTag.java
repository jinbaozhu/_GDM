package com.jinbao.general.concrete;

import com.jinbao.general.interfaces.GeneralTagI;

public class GeneralTag extends GeneralBase implements GeneralTagI{
	String value;
	public GeneralTag(String name,String value) {
		super(name);
		this.value = value;
	}

	@Override
	public String value() {
		return value;
	}

}
