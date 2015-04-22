package org.pti.poster.webutil.filter;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.BeanPropertyWriter;
import com.fasterxml.jackson.databind.ser.PropertyWriter;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;

public class PostFilter extends SimpleBeanPropertyFilter {

	@Override
	protected boolean include(BeanPropertyWriter beanPropertyWriter) {
		return true;
	}

	@Override
	protected boolean include(PropertyWriter propertyWriter) {
		return true;
	}

	@Override
	public void serializeAsField(Object post, JsonGenerator jgen, SerializerProvider prov, BeanPropertyWriter writer) throws Exception {
		if (!writer.getName().equals("type")) {
			writer.serializeAsField(post, jgen, prov);
		}
	}
}
