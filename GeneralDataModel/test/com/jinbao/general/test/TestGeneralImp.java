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
import com.jinbao.general.interfaces.GeneralObjectI;
import com.jinbao.general.interfaces.GeneralPropertyI;
import com.jinbao.general.interfaces.IdentityI;

public class TestGeneralImp {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void newIdentity(){
		Identity id = new Identity("jinbao");
		id.setId(100L);
	}
	
	@Test
	public void testIdentityManager(){
		Identity id = new Identity("jinbao");
		id.setId(100L);
		
		IdentityManager idmgr = IdentityManager.instance();
		Long idl = idmgr.register(id);
		
		assertEquals(idmgr.getIdentity(idl).name(),"jinbao");
		assertEquals(idmgr.getCount(),1);
		
		idmgr.unregister(id);
		assertEquals(idmgr.getCount(),0);
	}
	
	
	@Test
	public void testModels(){
		// M3 ------------------ancestor--------------------------------
		GeneralModel m3 = new GeneralModel("M3");
		GeneralObjectI ancestor = m3.addOwnee(new GeneralObject("Ancestor"));
		
		// M2 -------------------------------------------------------------
		GeneralModel m2 = new GeneralModel("M2",m3);
		GeneralObjectI object_type = m2.addOwnee(new GeneralObject("Object",ancestor));
		GeneralObjectI relationship_type = m2.addOwnee(new GeneralObject("Relationship",ancestor));
		
		// M1 --------------------------------------------------------------
		GeneralModel m1 = new GeneralModel("M1",m2);
		GeneralPropertyI nickname = m1.addProperty(new GeneralProperty("NickName"));
		nickname.setValue(new Value("MySql Meta Model"));
		
		GeneralObjectI table_type = m1.addOwnee(new GeneralObject("Table",object_type));
		GeneralObjectI domain_type = m1.addOwnee(new GeneralObject("Domain",object_type));
		GeneralObjectI column_type = table_type.addOwnee(new GeneralObject("Column",object_type));
		column_type.addReference("Domain",domain_type);
		
		GeneralObjectI ForeignKey_type = m1.addOwnee(new GeneralObject("ForeignKey",relationship_type));
		
		ForeignKey_type.addReference("ParentTable",table_type);
		ForeignKey_type.addReference("ChildTable",table_type);
		
		ForeignKey_type.addReference("ParentTableColumns",column_type);
		ForeignKey_type.addReference("ChildTableColumns",column_type);
		
		m1.addOwnee(new GeneralObject("Relationship",ancestor));
		
		
		// M0 -----------------------------------------------------------------
		GeneralModel m0 = new GeneralModel("M0",m1);
		m0.setPropertyValue(nickname.getId(), new Value("MySql Data Model 1"));
		
		GeneralObjectI domain_integer = m0.addOwnee(new GeneralObject("Integer",domain_type));
		GeneralObjectI domain_string = m0.addOwnee(new GeneralObject("String",domain_type));
		
		// M0.table1
		GeneralObjectI table1 = m0.addOwnee(new GeneralObject("Table1",table_type));
				
		GeneralObjectI column11 = table1.addOwnee(new GeneralObject("Column11",column_type));
		column11.addReference("Domain",domain_integer);
		
		GeneralObjectI column12 = table1.addOwnee(new GeneralObject("Column12",column_type));
		column12.addReference("Domain",domain_string);
		
		// M0.table1
		GeneralObjectI table2 = m0.addOwnee(new GeneralObject("Table2",table_type));
						
		GeneralObjectI column21 = table2.addOwnee(new GeneralObject("Column21",column_type));
		column21.addReference("Domain",domain_integer);
				
		GeneralObjectI column22 = table2.addOwnee(new GeneralObject("Column22",column_type));
		column22.addReference("Domain",domain_string);
		
		GeneralObjectI ForeignKey_table1table2 = m1.addOwnee(new GeneralObject("table1table2",ForeignKey_type));
		
		ForeignKey_table1table2.addReference("ParentTable",table2);
		ForeignKey_table1table2.addReference("ChildTable",table2);
		
		ForeignKey_table1table2.addReference("ParentTableColumns",column11);
		ForeignKey_table1table2.addReference("ChildTableColumns",column21);
		
		System.out.println(IdentityManager.instance().toString());
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
