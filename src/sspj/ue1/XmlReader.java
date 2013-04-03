package sspj.ue1;

import java.io.IOException;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

/**
 *
 * @author ccwelich
 */
public abstract class XmlReader {

	public void read(String xmlPath) throws JDOMException, IOException {
		SAXBuilder builder = new SAXBuilder();
		Document doc = builder.build(xmlPath);
		readElements(doc.getRootElement());
	}

	protected abstract void readElements(Element root);
}
