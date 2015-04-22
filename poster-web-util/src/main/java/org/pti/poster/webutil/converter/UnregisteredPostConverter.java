package org.pti.poster.webutil.converter;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.pti.poster.dto.post.UnregisteredPostDto;
import org.pti.poster.webutil.config.JacksonFilterConfig;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.AbstractHttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;

import java.io.IOException;
import java.util.Map;

public class UnregisteredPostConverter extends AbstractHttpMessageConverter<UnregisteredPostDto> {
	private JacksonFilterConfig filterConfig;

	public UnregisteredPostConverter(JacksonFilterConfig filterConfig, MediaType supportedType) {
		super(supportedType);
		this.filterConfig = filterConfig;
	}

	@Override
	protected boolean supports(Class<?> aClass) {
		return UnregisteredPostDto.class.equals(aClass);
	}

	@Override
	protected UnregisteredPostDto readInternal(Class<? extends UnregisteredPostDto> aClass, HttpInputMessage httpInputMessage) throws IOException, HttpMessageNotReadableException {
		ObjectMapper mapper = new ObjectMapper();
		Map jsonMap = mapper.readValue(httpInputMessage.getBody(), Map.class);
		String text = (String) jsonMap.get("text");
		String userId = (String) jsonMap.get("userId");
		return new UnregisteredPostDto(userId, text);
	}

	@Override
	protected void writeInternal(UnregisteredPostDto post, HttpOutputMessage httpOutputMessage) throws IOException, HttpMessageNotWritableException {

	}
}
