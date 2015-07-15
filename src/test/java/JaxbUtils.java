import ca.screenshot.endlessscorpion.beans.*;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.ByteArrayOutputStream;
import java.io.StringReader;

/**
 * Created by plaguemorin on 11/07/15.
 */
public final class JaxbUtils {
	private JaxbUtils() {
	}

	public static String marshal(final Object obj) throws JAXBException {
		final JAXBContext context = JAXBContext.newInstance(obj.getClass());

		final Marshaller marshaller = context.createMarshaller();
		marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

		final ByteArrayOutputStream output = new ByteArrayOutputStream();
		marshaller.marshal(obj, output);

		return output.toString();
	}

	public static Object unmarshal(final String theOrder) throws JAXBException {
		final JAXBContext context = JAXBContext.newInstance(Company.class, Order.class, Product.class, RemoteBean.class, Subscription.class, User.class);
		final Unmarshaller unmarshaller = context.createUnmarshaller();

		return unmarshaller.unmarshal(new StringReader(theOrder));
	}
}
