package ca.screenshot.endlessscorpion.beans;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

/**
 * Created by plaguemorin on 11/07/15.
 * <p/>
 * For a lack of a better class name
 */
@XmlAccessorType(XmlAccessType.NONE)
public abstract class RemoteBean {
	private String id;

	@XmlAttribute(required = false, name = "href")
	private String remoteHref;

	public String getId() {
		return this.id;
	}

	public void setId(final String id) {
		this.id = id;
	}

	@XmlAttribute(name = "id")
	private void setIdAttribute_JAXB(final String id) {
		this.id = id;
	}

	private String getIdAttribute_JAXB() {
		return this.remoteHref != null && !this.remoteHref.isEmpty() ? this.id : null;
	}

	@XmlElement(name = "id")
	private String getIdElement_JAXB() {
		return this.remoteHref == null || this.remoteHref.isEmpty() ? this.id : null;
	}

	private void setIdElement_JAXB(final String id) {
		this.id = id;
	}
}
