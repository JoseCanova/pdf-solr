package org.nanotek.pattern;

import java.util.regex.Pattern;

public class PatternDescriptor {

	private Pattern pattern; 
	
	public PatternDescriptor() {}
	
	public PatternDescriptor (Pattern thePattern) { 
		this.pattern = thePattern;
	}

	public Pattern getPattern() {
		return pattern;
	}

	public void setPattern(Pattern pattern) {
		this.pattern = pattern;
	}
	
}
