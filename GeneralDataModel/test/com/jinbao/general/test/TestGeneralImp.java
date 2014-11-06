package com.jinbao.general.test;


import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.jinbao.general.concrete.GeneralModel;
import com.jinbao.general.concrete.GeneralObject;
import com.jinbao.general.concrete.Identity;
import com.jinbao.general.concrete.IdentityManager;
import com.jinbao.general.concrete.GeneralProperty;
import com.jinbao.general.concrete.GeneralTag;
import com.jinbao.general.concrete.Value;
import com.jinbao.general.interfaces.IdentityI;

public class TestGeneralImp {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void newIdentity(){
		Identity id = new Identity();
		id.setId(100L);
		id.setName("jinbao");
	}
	
	@Test
	public void testIdentityManager(){
		Identity id = new Identity();
		id.setId(100L);
		id.setName("jinbao");
		
		IdentityManager idmgr = IdentityManager.instance();
		Long idl = idmgr.register(id);
		
		assertEquals(idmgr.getIdentity(idl).name(),"jinbao");
		assertEquals(idmgr.getCount(),1);
		
		idmgr.unregister(id);
		assertEquals(idmgr.getCount(),0);
	}
	
	
	@Test
	public void testModels(){
		GeneralModel M2 = new GeneralModel("M2",null);
		GeneralObject meta = new GeneralObject("Ancestor");
		
//		nameProperty.setValue(new Value("Jinbao"));
//		
//		assertEquals(nameProperty.getValue().asString(),"Jinbao");
//		
//		nameProperty.addTag(new GeneralTag("Meta","1"));
//		nameProperty.addTag(new GeneralTag("Prefetch","false"));
//		nameProperty.addTag(new GeneralTag("MaxLength","100"));
//		
//		assertEquals(nameProperty.getTags().size(),3);
//		
//		List<IdentityI> ids = IdentityManager.instance().getIdentity("Meta");
//		if(ids != null){
//			GeneralTag tag = (GeneralTag) nameProperty.getTag(ids.get(0).Id());
//			assertEquals(tag.value(),"1");
//		}
	}
	
	
	@Test
	public void testProperties(){
		GeneralProperty nameProperty = new GeneralProperty("Name");
		
		nameProperty.setValue(new Value("Jinbao"));
		
		assertEquals(nameProperty.getValue().asString(),"Jinbao");
		
		nameProperty.addTag(new GeneralTag("Meta","1"));
		nameProperty.addTag(new GeneralTag("Prefetch","false"));
		nameProperty.addTag(new GeneralTag("MaxLength","100"));
		
		assertEquals(nameProperty.getTags().size(),3);
		
		List<IdentityI> ids = IdentityManager.instance().getIdentity("Meta");
		if(ids != null){
			GeneralTag tag = (GeneralTag) nameProperty.getTag(ids.get(0).Id());
			assertEquals(tag.value(),"1");
		}
	}
}
