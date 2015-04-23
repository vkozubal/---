package org.pti.poster.webutil.config;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import org.pti.poster.webutil.filter.PostFilter;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class JacksonFilterConfig {
	FilterProvider filters;

	@PostConstruct
	public void init() {
		PostFilter postFilter = new PostFilter();
		filters = new SimpleFilterProvider().addFilter("postFilter", postFilter);
	}

	public FilterProvider getFilters() {
		return filters;
	}
}
