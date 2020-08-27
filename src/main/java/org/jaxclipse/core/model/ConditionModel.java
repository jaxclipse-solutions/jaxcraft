package org.jaxclipse.core.model;

import org.jaxclipse.core.model.jaxb.adapters.BooleanAdapter;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

public class ConditionModel {

	private boolean has;

	private String object;

	private String owner;

	private String status;

	public Boolean isHas() {
		return has;
	}

	@XmlElement
	@XmlJavaTypeAdapter(BooleanAdapter.class)
	public void setHas(Boolean has) {
		this.has = has;
	}
	
	public String getObject() {
		return object;
	}

	public void setObject(String object) {
		this.object = object;
	}

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public int hashCode() {
		return new org.apache.commons.lang3.builder.HashCodeBuilder().append(has).append(object).append(owner)
				.append(status).toHashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof ConditionModel) {
			final ConditionModel other = (ConditionModel) obj;
			return new org.apache.commons.lang3.builder.EqualsBuilder().append(has, other.has)
					.append(object, other.object).append(owner, other.owner)
					.append(status, other.status).isEquals();
		} else {
			return false;
		}
	}
}
