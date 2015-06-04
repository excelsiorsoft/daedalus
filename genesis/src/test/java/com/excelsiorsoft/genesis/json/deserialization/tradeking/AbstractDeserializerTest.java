package com.excelsiorsoft.genesis.json.deserialization.tradeking;

import static org.junit.Assert.*;


import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;

public class AbstractDeserializerTest {

	FooDeserializer fooDeserializer;
	
	public static class Foo {

		private String value;
		
		public Foo(String value){ this.value = value;}
		
		@Override
		public String toString() { return String.format("Foo [%s]", value); }
		
	}
	
	public static class FooDeserializer extends AbstractDeserializer<Foo>{

		protected FooDeserializer() {
			super(Foo.class);
			
		}

		@Override
		protected Foo deserializeSingleNode(JsonNode node) { return new Foo(node.asText()); }
		
	}
	
	@Before
	public void setUp(){
		fooDeserializer = new FooDeserializer();
	}
	
	@Test
	public void capturingType() throws Throwable {
		
		ArrayNode containerNode = new ArrayNode(JsonNodeFactory.instance);

		containerNode.add(containerNode.textNode("date-1"));
		containerNode.add(containerNode.textNode("date-2"));

		List<Foo> result = fooDeserializer.deserialize(containerNode);
		
		assertTrue("wrong number of deserialized elements", result.size()==2);

		
	}

}
