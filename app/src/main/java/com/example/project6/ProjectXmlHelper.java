package com.example.project6;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import org.xml.sax.EntityResolver;
import org.xml.sax.ErrorHandler;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

public abstract class ProjectXmlHelper implements ErrorHandler, EntityResolver {
    private final String publicId;
    private final String systemId;
    private final String dtdFileName;

    protected ProjectXmlHelper(String publicId, String systemId, String dtdFileName) {
        this.publicId = publicId;
        this.systemId = systemId;
        this.dtdFileName = dtdFileName;
    }

    public void warning(SAXParseException exception) throws SAXException {
        throw exception;
    }

    public void error(SAXParseException exception) throws SAXException {
        throw exception;
    }

    public void fatalError(SAXParseException exception) throws SAXException {
        throw exception;
    }

    public InputSource resolveEntity(String publicId, String systemId) throws SAXException, IOException {
        if (this.publicId.equals(publicId) || this.systemId.equals(systemId)) {
            InputStream stream = ProjectXmlHelper.class.getResourceAsStream(this.dtdFileName);
            if (stream != null) {
                return new InputSource(stream);
            }
        }

        try {
            URL url = new URL(systemId);
            InputStream stream = url.openStream();
            return new InputSource(stream);
        } catch (Exception var5) {
            return null;
        }
    }
}

