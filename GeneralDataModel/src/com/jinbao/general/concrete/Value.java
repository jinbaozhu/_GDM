package com.jinbao.general.concrete;

import com.jinbao.general.interfaces.ValueI;

public class Value implements ValueI {
	private Object value;

	public Value(Object value){
		this.value = value;
	}
	
	@Override
	public String asString() {
		return value.toString();
	}
}
