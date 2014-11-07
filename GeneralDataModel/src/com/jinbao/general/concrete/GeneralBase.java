package com.jinbao.general.concrete;

import com.jinbao.general.interfaces.GeneralI;
import com.jinbao.general.interfaces.IdentityI;

public class GeneralBase implements GeneralI {
	IdentityI 	identity;

	GeneralBase(String name){
		identity = Identity.newRegistered(name);	
	}
	
	@Override
	public IdentityI getIdentity() {
		return identity;
	}
	
	@Override
	public Long getId() {
		return identity.Id();
	}

	@Override
	public String getName() {
		return identity.name();
	}
}
