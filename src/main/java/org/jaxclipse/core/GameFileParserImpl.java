package org.jaxclipse.core;

import java.io.InputStream;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

import org.jaxclipse.core.exception.GameFileParseException;
import org.jaxclipse.core.model.GameInitModel;

import com.google.inject.Inject;

public class GameFileParserImpl {

	@Inject
	private FileHelper fileHelper;

	public GameInitModel parse(String filename) throws GameFileParseException {
		try {
			JAXBContext context = JAXBContext.newInstance(GameInitModel.class);
			Unmarshaller unmarshaller = context.createUnmarshaller();
			InputStream streamContent = fileHelper.fileAsStream(filename);
			return (GameInitModel) unmarshaller.unmarshal(streamContent);
		} catch (Exception e) {
			e.printStackTrace();
			throw new GameFileParseException(e);
		}
	}
}
