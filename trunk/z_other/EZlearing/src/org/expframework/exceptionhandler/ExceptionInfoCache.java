package org.expframework.exceptionhandler;

import java.util.HashMap;

import org.expframework.data.ExceptionInfoDTO;
import org.expframework.xml.XmlParser;
import org.expframework.xml.XmlUtil;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 * This is a singleton which acts as a cache for <code>ExceptionInfoDTO</code>
 * objects.
 * 
 * <p>
 * <b>Overview: </b>
 * <p>
 * It reads a file called <code>exceptionInfo.xml</code> which contains a
 * mapping of the exception type with message key and context indicator. It
 * parses the <code>exceptionInfo.xml</code> file and creates
 * <code>ExceptionInfoDTO</code> objects out of that. The sample XML file will
 * be something like this.
 * 
 * <pre>
 *  &lt;?xml	version='1.0'?&gt;
 *  &lt;exceptioninfo&gt;
 *  &lt;exception name=&quot;UniqueConstraintException&quot;&gt;
 *  &lt;messagecode&gt;errorcode.uniqueconstraint&lt;/message&gt;
 *  &lt;contextind&gt;true&lt;/contextindi&gt;
 *  &lt;/exception&gt;
 *  &lt;exception name=&quot;XmlParseException&quot;&gt;
 *  &lt;messagecode&gt;errorcode.xmlparse&lt;/message&gt;
 *  &lt;contextind&gt;false&lt;/contextindi&gt;
 *  &lt;/exception&gt;
 *  &lt;/exceptionInfo&gt;
 * </pre>
 * 
 * Above exceptioninfo.xml is based on following DTD
 * 
 * <pre>
 *  &lt;!ELEMENT exceptioninfo(exception+) &gt;
 *  &lt;!ELEMENT exception(messagecode, contextind?, confirmationind ?, loggingtype? &gt;
 *  &lt;!ATTLIST exception name CDATA #REQUIRED&gt;
 *  &lt;!ELEMENT messagecode (#PCDATA)&gt;
 *  &lt;!ELEMENT contextind (#PCDATA)&gt;
 *  &lt;!ELEMENT confirmationind (#PCDATA)&gt;
 *  &lt;!ELEMENT loggingtype (#PCDATA)&gt;
 * </pre>
 * 
 * In above DTD, contextind, confirmationind and loggingtype tags are
 * optional.While creating ExceptionInfoDTO objects, following values are taken
 * as default for these tags if not specified in the XML.
 * 
 * <pre>
 *  contextind ---&gt; false
 *  confirmationind --&gt; false
 *  loggingtype --&gt; error (other possible values are &quot;warning&quot;, &quot;info&quot; and &quot;nologging&quot; if specified).
 * </pre>
 * 
 * It's mandatory to specify "nologging" as logging type for confirmation
 * exception (confirmationind = true) in <code>exceptioninfo.xml</code>.
 * 
 * @author ShriKant
 * 
 * @version 1.0
 */
public class ExceptionInfoCache {

	private static final ExceptionInfoCache SINGLETON = new ExceptionInfoCache();

	private HashMap ecache = new HashMap();

	/**
	 * It's a private constructor for singleton instance.
	 */
	private ExceptionInfoCache() {

		try {
			Document doc = XmlParser.parse("/expinfo.xml");
			Element[] elements = XmlUtil.getElements("exception", doc);
			if (elements != null && elements.length > 0) {
				for (int i = 0; i < elements.length; i++) {
					Element exElement = elements[i];
					addException(exElement);
				}
			}
		} catch (Throwable t) {
			ecache = null;
		}
	}

	private void addException(Element exElement) {
		String exName = exElement.getAttribute("name");
		String context = XmlUtil.getTagValue(exElement, "contextind");
		String messageCode = XmlUtil.getTagValue(exElement, "messagecode");
		boolean contextInd = false;
		boolean confirmationInd = false;
		if (context != null)
			contextInd = Boolean.valueOf(context).booleanValue();
		String confirmation = XmlUtil.getTagValue(exElement, "confirmationind");
		if (confirmation != null)
			confirmationInd = Boolean.valueOf(confirmation).booleanValue();
		String loggingType = XmlUtil.getTagValue(exElement, "loggingtype");
		ExceptionInfoDTO exInfo = new ExceptionInfoDTO(messageCode, contextInd,
				confirmationInd, loggingType);
		ecache.put(exName, exInfo);
	}

	/**
	 * Gets the singleton instance of <code>ExceptionInfoCache</code> class.
	 * 
	 * @return <code>ExceptionInfoCache</code> instance
	 * 
	 */
	public static ExceptionInfoCache getInstance() {
		return SINGLETON;
	}

	/**
	 * Based on exception type passed it returns the
	 * <code>ExceptionInfoDTO</code> object from cache.
	 * 
	 * @param errorId
	 *            error id String.
	 * @return <code>ExceptionInfoDTO</code>
	 * 
	 */
	public ExceptionInfoDTO getExceptionInfo(String errorId) {
		if (ecache == null)
			return null;
		return (ExceptionInfoDTO) ecache.get(errorId);
	}
}