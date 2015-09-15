package com.excelsiorsoft.genesis.json.deserialization.tradeking;

import static org.junit.Assert.assertTrue;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import com.excelsiorsoft.daedalus.util.Significant;
import com.fasterxml.jackson.databind.JsonNode;

@Significant
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
		protected Foo deserializeSingleNode(JsonNode node, Map<String, Object> context) {return new Foo(node.asText()); }

		@Override
		public JsonNode cursor(JsonNode root) {

			return root.path("dates");
		}
		
	}
	
	@Before
	public void setUp(){
		fooDeserializer = new FooDeserializer();
	}
	
	@Test
	public void capturingType() throws Throwable {
		
		/*ArrayNode containerNode = new ArrayNode(JsonNodeFactory.instance);

		containerNode.add(containerNode.textNode("date-1"));
		containerNode.add(containerNode.textNode("date-2"));*/
		
		String json = "{\"response\": {\"dates\":[\"date-1\", \"date-2\"]}}";

		List<Foo> result = fooDeserializer.deserialize(/*containerNode*/json, new HashMap());
		
		assertTrue("wrong number of deserialized elements", result.size()==2);

		
	}

}
