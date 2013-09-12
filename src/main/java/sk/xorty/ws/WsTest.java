package sk.xorty.ws;

import org.springframework.ws.client.core.WebServiceTemplate;
import org.springframework.ws.soap.client.core.SoapActionCallback;
import org.springframework.ws.soap.saaj.SaajSoapMessageFactory;

import javax.xml.soap.MessageFactory;
import javax.xml.soap.SOAPConstants;
import javax.xml.soap.SOAPException;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.StringReader;

public class WsTest {

    private static final String REQUEST_PERIODIC = "<web:GetAtomicWeight xmlns:web=\"http://www.webserviceX.NET\">\n" +
            "         <!--Optional:-->\n" +
            "         <web:ElementName>Aluminium</web:ElementName>\n" +
            "      </web:GetAtomicWeight>";

    private static final String URL_PERIODIC = "http://www.webservicex.net/periodictable.asmx";

    private final WebServiceTemplate webServiceTemplate = new WebServiceTemplate();

    // send to an explicit URI
    public void customSendAndReceive() throws SOAPException {
        MessageFactory messageFactory = MessageFactory.newInstance(SOAPConstants.SOAP_1_2_PROTOCOL);
        SaajSoapMessageFactory newSoapMessageFactory = new SaajSoapMessageFactory(messageFactory);
        webServiceTemplate.setMessageFactory(newSoapMessageFactory);

        StreamSource source = new StreamSource(new StringReader(REQUEST_PERIODIC));
        StreamResult result = new StreamResult(System.out);
        webServiceTemplate.sendSourceAndReceiveToResult(URL_PERIODIC, source,
                new SoapActionCallback("http://www.webserviceX.NET/GetAtomicWeight"), result);
    }

    public static void main(String[] args) throws SOAPException {
        new WsTest().customSendAndReceive();
    }
}
