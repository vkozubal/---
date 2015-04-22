package org.pti.poster.webutil.converter;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.pti.poster.dto.post.RegisteredPostDto;
import org.pti.poster.webutil.config.JacksonFilterConfig;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.AbstractHttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;

import java.io.IOException;

public class RegisteredPostConverter extends AbstractHttpMessageConverter<RegisteredPostDto> {
	private JacksonFilterConfig filterConfig;

	public RegisteredPostConverter(JacksonFilterConfig filterConfig, MediaType supportedType) {
		super(supportedType);
		this.filterConfig = filterConfig;
	}

	@Override
	protected boolean supports(Class<?> aClass) {
		return RegisteredPostDto.class.equals(aClass);
	}

	@Override
	protected RegisteredPostDto readInternal(Class<? extends RegisteredPostDto> aClass, HttpInputMessage httpInputMessage) throws IOException, HttpMessageNotReadableException {
		return null;
	}

	@Override
	protected void writeInternal(RegisteredPostDto post, HttpOutputMessage httpOutputMessage) throws IOException, HttpMessageNotWritableException {
		ObjectMapper mapper = new ObjectMapper();
		mapper.writer(filterConfig.getFilters()).writeValue(httpOutputMessage.getBody(), post);
	}
}
