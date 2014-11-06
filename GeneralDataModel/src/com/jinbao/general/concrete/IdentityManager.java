package com.jinbao.general.concrete;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.jinbao.general.interfaces.IdentityI;

public class IdentityManager {
	static IdentityManager instance;
	
	long nextId;
	HashMap<Long,IdentityI> hukou = new HashMap<Long,IdentityI>();
	HashMap<String,List<IdentityI>> names = new HashMap<String,List<IdentityI>>();
	
	public IdentityI getIdentity(Long id){
		return hukou.get(id);
	}
	public List<IdentityI> getIdentity(String name){
		return names.get(name);
	}
	/*
	public IdentityI getIdentity(String name,class className){
		List<IdentityI> idl = names.get(name);
		if(isl != null){
			for(int i = 0;i<isl.size();i++){
				if(isl.get(i) == identity.Id())
					isl.remove(i);
			}
		}
	}
	*/
	public Long register(IdentityI identity){
		if(identity.Id() == null)
			identity.setId(nextId++);
		
		hukou.put(identity.Id(), identity);
		
		List<IdentityI> isl = getIdentity(identity.name());
		if(isl == null){
			isl = new ArrayList<IdentityI>();
			names.put(identity.name(), isl);
		}
		isl.add(identity);

		return identity.Id();
	}
	
	public void unregister(IdentityI identity){
		hukou.remove(identity.Id());
		
		List<IdentityI> isl = getIdentity(identity.name());
		if(isl != null){
			for(int i = 0;i<isl.size();i++){
				if(isl.get(i).Id() == identity.Id())
					isl.remove(i);
			}
		}
	}
	
	public int getCount(){
		return hukou.size();
	}
	
	public static IdentityManager instance(){
		if(instance == null)
			instance = new IdentityManager();
		return instance;
	}
}
