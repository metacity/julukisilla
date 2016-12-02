package fi.metacity.julukisilla.dto.rest;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * Enumeration of areas supported by Julukisilla
 */
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum Area {
	KUOPIO("Kuopio");

	private final String displayName;

	private Area(String displayName) {
		this.displayName = displayName;
	}

	public String getId() {
		return name();
	}

	public String getDisplayName() {
		return displayName;
	}

}
