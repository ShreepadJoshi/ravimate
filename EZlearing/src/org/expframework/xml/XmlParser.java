package org.expframework.xml;

import java.io.InputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.expframework.exceptions.XmlParseException;
import org.w3c.dom.Document;

/**
 * Creates a DOM based Document object after parsing the XML file.
 * <p>
 * <b>Overview: </b>
 * <p>
 * Parses a <code>InputStream</code>, xml file, or a URL and creates a
 * <code>Document</code> object
 * 
 * @author ShriKant
 * 
 * @version 1.0
 * @see org.expframework.xml.XmlUtil
 */
public class XmlParser {

    /**
     * Parse the Input Stream passed and create a <code>Document</code>
     * object.
     * 
     * @param in
     *            InputStream object
     * @return A parsed <code>Document</code> object.
     * @throws XmlParseException
     *             XmlParseException is thrown when not able to parse the xml
     *             input stream.
     *  
     */
    public static Document parse(InputStream in) throws XmlParseException {
        Document document = null;
        if (in == null)
            return null;
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory
                    .newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            document = builder.parse(in);
        } catch (Throwable th) {
            throw new XmlParseException("Exception occured while parsing XML",
                    th);
        }
        return document;
    }

    /**
     * Given an XML document pointed to by a file path expression, this method
     * attemps to read the file and parse it as XML.
     * 
     * @param xmlPath
     *            An absolute or relative file path expression.
     * @return A parsed XML document
     * @exception XmlParseException
     *                Thrown if the file is unreadable or the file does not
     *                contain a valid XML document
     */
    public static Document parse(String xmlPath) throws XmlParseException {
        InputStream stream = XmlParser.class.getResourceAsStream(xmlPath);
        return parse(stream);
    }

}