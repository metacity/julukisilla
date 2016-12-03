package fi.metacity.koodistopalvelung.dto.rest;

public class CodeSystem {

	private final String oid;

	private final String name;

	public CodeSystem(String oid, String name) {
		this.oid = oid;
		this.name = name;
	}

	public String getOid() {
		return oid;
	}

	public String getName() {
		return name;
	}
}
