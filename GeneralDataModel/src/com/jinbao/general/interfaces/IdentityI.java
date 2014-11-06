package com.jinbao.general.interfaces;

public interface IdentityI {
	public Long Id();
	public abstract String name();
	
	public abstract void setId(Long id);
	public abstract void setName(String name);
}
