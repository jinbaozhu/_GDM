package com.jinbao.general.concrete;

import com.jinbao.general.interfaces.*;

public class Identity implements IdentityI {

	private Long id;
	private String name;
	
	public static Identity newRegistered(String name){
		Identity identity = new Identity();
		identity.setName(name);
		
		IdentityManager idmgr = IdentityManager.instance();
		idmgr.register(identity);
		return identity;
	}
	
	@Override
	public void setId(Long id){
		this.id = id;
	}
	@Override
	public Long Id() {
		return id;
	}

	@Override
	public void setName(String name){
		this.name = name;
	}
	@Override
	public String name() {
		return name;
	}
}
