package org.expframework.xml;

import java.util.StringTokenizer;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * A utility class for working with XML DOM implementation.
 * 
 * @author ShriKant
 * 
 * @version 1.0
 * @see org.expframework.xml.XmlParser
 */
public class XmlUtil {

    /**
     * This method takes Node object as argument and return the value of the
     * first child of the node.
     * <p>
     * If there is no such node, this will return null.
     * 
     * @param node
     *            Node object
     * @return <code>String</code> returns the value of the node
     *  
     */
    public static String getTagValue(Node node) {
        Node cld = node.getFirstChild();
        if (cld == null)
            return null;
        return cld.getNodeValue();
    }

    /**
     * Gives the value of a tag based on <code>tagName</code> and <code>doc</code> parameters.
     * @param doc
     *            Element object in which the tag may exist.
     * @param tagName
     *            Name of the tag.
     * @return <code>String</code> Value of the tag.
     *  
     */
    public static String getTagValue(Element doc, String tagName) {

        if (doc == null)
            return null;
        NodeList nodes = doc.getElementsByTagName(tagName.toLowerCase());
        if (nodes == null || nodes.getLength() <= 0)
            return null;
        return getTagValue(nodes.item(0));
    }

    /**
     * Gives the value of a tag based on <code>tagName</code> and <code>doc</code> parameters.
     * @param doc
     *            <code>Document</code> object in which the tag may exist.
     * @param tagName
     *            the name of tage
     * @return <code>String</code> value of the tag.
     *  
     */
    public static String getTagValue(Document doc, String tagName) {
        if (doc == null)
            return null;
        return getTagValue(doc.getDocumentElement(), tagName);
    }

    private static Element locateElement(NodeList nodes) {
        if (nodes == null)
            return null;
        int len = nodes.getLength();
        if (len == 0)
            return null;
        Element elt = null;
        for (int i = 0; i < len; i++) {
            Node n = nodes.item(i);
            if (n.getNodeType() == Node.ELEMENT_NODE) {
                elt = (Element) n;
            } else {
                continue;
            }
        }
        return elt;
    }

    /**
     * This method is used for locating the Element by the given key.
     * 
     * @param key
     *            Name of the tag.
     * @param doc
     *            Document object.
     * @return <code>Element</code> Returns the corresponding Element object.
     *  
     */
    public static Element getElement(String key, Document doc) {
        return getElement(key, doc.getDocumentElement());
    }

    /**
     * This method is used for locating the Element by the given key.
     * 
     * @param key
     *            Name of the key to which the <code>Element</code> object is
     *            to be searched.
     * @param doc
     *            Element object.
     * @return <code>Element</code> Returns the <code>Element</code> object.
     *  
     */
    public static Element getElement(String key, Element doc) {
        Element contentElement = doc;

        StringTokenizer tokenizer = new StringTokenizer(key, ".");

        NodeList nodes = null;

        while (tokenizer.hasMoreTokens()) {
            String tagName = tokenizer.nextToken();
            nodes = contentElement.getElementsByTagName(tagName);
            contentElement = locateElement(nodes);
            if (contentElement == null)
                return null;
        }
        return contentElement;
    }

    /**
     * This method is used to search all the tags with given key in the Document
     * object.
     * 
     * 
     * @param tagName
     *            Name of the tag as String.
     * @param input
     *            Document object.
     * @return <code>Element[]</code> Returns the array of elements.
     *  
     */
    public static Element[] getElements(String tagName, Document input) {
        return getElements(tagName, input.getDocumentElement());
    }

    /**
     * This method is used to search all the tags with given key in the Element
     * object.
     * 
     * @param tagName
     *            Name of the tag as String.
     * @param input
     *            Element object.
     * @return <code>Element[]</code> Returns the array of elements.
     *  
     */
    public static Element[] getElements(String tagName, Element input) {

        NodeList nodes = null;

        nodes = input.getElementsByTagName(tagName);

        if (nodes == null)
            return null;
        int len = nodes.getLength();
        if (len == 0)
            return null;
        Element[] elt = new Element[len];
        int j = 0;
        for (int i = 0; i < len; i++) {
            Node n = nodes.item(i);
            if (n.getNodeType() == Node.ELEMENT_NODE) {
                elt[j] = (Element) n;
                j++;
            } else {
                continue;
            }
        }

        return elt;
    }
}