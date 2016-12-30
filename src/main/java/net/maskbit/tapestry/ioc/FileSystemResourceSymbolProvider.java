package net.maskbit.tapestry.ioc;

import java.net.MalformedURLException;
import java.net.URL;

import org.apache.tapestry5.internal.services.UrlResource;
import org.apache.tapestry5.ioc.internal.services.ResourceSymbolProvider;

public class FileSystemResourceSymbolProvider extends ResourceSymbolProvider {

	public FileSystemResourceSymbolProvider(String path) {
		super(new UrlResource(initialize(path)));
	}

	private static URL initialize(String path) {
		try {
			return new URL("file", null, path);
		} catch (MalformedURLException e) {
			throw new RuntimeException(e);
		}
	}
}
