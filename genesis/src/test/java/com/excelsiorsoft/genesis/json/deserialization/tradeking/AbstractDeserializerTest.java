package com.excelsiorsoft.genesis.json.deserialization.tradeking;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.BaseJsonNode;
import com.fasterxml.jackson.databind.node.ContainerNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.node.TextNode;

public class AbstractDeserializerTest {

	FooDeserializer fooDeserializer;
	
	public static class Foo{

		private String value;
		
		public Foo(String value){
			this.value = value;
		}
		
		@Override
		public String toString() {
			return String.format("Foo [%s]", value);
		}
		
	}
	
	public static class FooDeserializer extends AbstractDeserializer<Foo>{

		protected FooDeserializer() {
			super(Foo.class);
			
		}

		@Override
		protected Foo deserializeSingleNode(JsonNode node) {
			
			return new Foo(node.asText());
		}
		
	}
	
	@Before
	public void setUp(){
		fooDeserializer = new FooDeserializer();
	}
	
	@Test
	public void capturingType() throws Throwable {
		
		TextNode a = JsonNodeFactory.instance.textNode("A");
		
		ArrayNode containerNode = new ArrayNode(JsonNodeFactory.instance);

		containerNode.add(containerNode.textNode("A"));
		containerNode.add(containerNode.textNode("B"));

		List<Foo> result = fooDeserializer.deserialize(containerNode);
		assertTrue("wrong number of deserialized elements",result.size()==2);
		
	}

}
