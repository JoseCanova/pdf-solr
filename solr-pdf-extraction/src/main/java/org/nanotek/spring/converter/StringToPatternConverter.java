package org.nanotek.spring.converter;

import java.util.regex.Pattern;

import org.springframework.boot.context.properties.ConfigurationPropertiesBinding;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
@ConfigurationPropertiesBinding
public class StringToPatternConverter implements Converter<String,Pattern>{

	@Override
	public Pattern convert(String source) {
		return Pattern.compile(source);
	}

}
