import ca.screenshot.endlessscorpion.beans.Subscription;
import org.junit.Test;

import javax.xml.bind.JAXBException;

/**
 * Created by plaguemorin on 11/07/15.
 */

public class XmlSerializationTest {


	@Test
	public void testConvertToXML_Order() throws JAXBException {
		final String theOrder = "<subscription>\n" +
				"    <id>ba2a7918-bb43-4429-a256-f6d729c71033</id>\n" +
				"    <company id=\"d15bb36e-5fb5-11e0-8c3c-00262d2cda03\" href=\"http://www.appdirect.com/account/v1/companies/d15bb36e-5fb5-11e0-8c3c-00262d2cda03\" />\n" +
				"    <user id=\"a11a7918-bb43-4429-a256-f6d729c71033\" href=\"http://www.appdirect.com/account/v1/companies/d15bb36e-5fb5-11e0-8c3c-00262d2cda03/users/ba2a7918-bb43-4429-a256-f6d729c71033\" />\n" +
				"    <status>FREE_TRIAL</status>\n" +
				"    <externalAccountIdentifier>1</externalAccountIdentifier>\n" +
				"    <product id=\"27\" href=\"https://www..appdirect.com/api/marketplace/v1/products/27\" />\n" +
				"    <maxUsers>1</maxUsers>\n" +
				"    <order>\n" +
				"        <id>1</id>\n" +
				"        <status>FREE_TRIAL</status>\n" +
				"        <paymentPlan href=\"\" />\n" +
				"        <frequency>MONTHLY</frequency>\n" +
				"        <startDate>2013-01-01</startDate>\n" +
				"        <endDate>2013-02-01</endDate>\n" +
				"        <totalPrice>2</totalPrice>\n" +
				"        <currency>USD</currency>\n" +
				"        <type>FREE_TRIAL</type>\n" +
				"        <orderLines>\n" +
				"            <orderLine>\n" +
				"                <type>ITEM</type>\n" +
				"                <unit>FLAT_RATE</unit>\n" +
				"                <price>2</price>\n" +
				"                <quantity>1</quantity>\n" +
				"                <totalPrice>2</totalPrice>\n" +
				"                <description>Monthly fee</description>\n" +
				"            </orderLine>\n" +
				"        </orderLines>\n" +
				"    </order>\n" +
				"</subscription>";

		final Subscription anOrder = (Subscription) JaxbUtils.unmarshal(theOrder);
		System.out.println(JaxbUtils.marshal(anOrder));
	}
}
