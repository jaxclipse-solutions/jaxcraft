package org.jaxclipse.core.model.jaxb.adapters;

import javax.xml.bind.annotation.adapters.XmlAdapter;

import org.jaxclipse.core.model.TriggerType;

public class TriggerTypeAdapter extends XmlAdapter<String, TriggerType> {

	@Override
	public String marshal(TriggerType arg0) throws Exception {
		return null;
	}

	@Override
	public TriggerType unmarshal(String value) throws Exception {
		return TriggerType.getByTypeName(value);
	}
}
