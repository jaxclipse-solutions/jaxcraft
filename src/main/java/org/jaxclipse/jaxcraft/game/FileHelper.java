package org.jaxclipse.jaxcraft.game;

import java.io.InputStream;
import java.net.URI;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileHelper {

	public InputStream fileAsStream(String filename) {
		InputStream stream = null;
		try {
			// Path path = FileSystems.getDefault().getPath(filename);
			// Path path = Paths.get(new URI(this.getClass().getResource(filename).toString()));
			// stream = Files.newInputStream(path);
			stream = this.getClass().getClassLoader().getResourceAsStream(filename);
		} catch (Exception e) {
			throw new IllegalArgumentException("File (" + filename
					+ ") not found or cannot read");
		}

		return stream;
	}

}
