package org.nanotek.spring.converter;

import java.nio.file.Path;

import org.springframework.core.convert.converter.Converter;

public class StringToPathConverter implements Converter<String,Path>{

	public StringToPathConverter() {}

	@Override
	public Path convert(String source) {
		return Path.of(source);
	}
	
	
}
