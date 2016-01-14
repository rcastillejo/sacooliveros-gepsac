/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sacooliveros.gepsac.proxyws.handler;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import javax.xml.namespace.QName;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;
import javax.xml.ws.handler.MessageContext;
import javax.xml.ws.handler.soap.SOAPHandler;
import javax.xml.ws.handler.soap.SOAPMessageContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Ricardo
 */
public class LogHandler implements SOAPHandler<SOAPMessageContext> {

    private static final Logger log = LoggerFactory.getLogger(LogHandler.class);
    private ByteArrayOutputStream output;
    private Boolean isRequest;

    @Override
    public Set<QName> getHeaders() {
        log.debug("getHeader method - no operation");
        return null;
    }

    @Override
    public boolean handleMessage(SOAPMessageContext context) {
        isRequest = (Boolean) context.get(MessageContext.MESSAGE_OUTBOUND_PROPERTY);
        output = new ByteArrayOutputStream();

        try {
            SOAPMessage message = context.getMessage();
            message.writeTo(output);
            if (isRequest) {
                loggerHeaders(context, MessageContext.HTTP_REQUEST_HEADERS, "SOAP Request-Headers=[{}:{}]");
                log.info("SOAP Request=[{}]", output.toString());
            } else {
                loggerHeaders(context, MessageContext.HTTP_RESPONSE_HEADERS, "SOAP Response-Headers=[{}:{}]");
                log.info("SOAP Response=[{}]", output.toString());
            }
        } catch (SOAPException ex) {
            log.error("Hubo un error al externalizar el SOAPMessage", ex);
        } catch (IOException ex) {
            log.error("Hubo un error al tratar de leer SOAPMessageContext", ex);
        } catch (Exception ex) {
            log.error("Error no esperado", ex);
        }

        return true;
    }

    private void loggerHeaders(SOAPMessageContext context, String key, String pattern) {
        Map map = (Map) context.get(key);
        if (map != null && map.entrySet() != null) {
            Set entries = map.entrySet();
            Iterator iterator = entries.iterator();
            while (iterator.hasNext()) {
                Map.Entry entry = (Map.Entry) iterator.next();
                log.info(pattern, new Object[]{entry.getKey(), entry.getValue()});
            }
        }
    }

    @Override
    public boolean handleFault(SOAPMessageContext context) {
        try {
            output = new ByteArrayOutputStream();
            context.getMessage().writeTo(output);
            log.debug("SOAP Fault=[{}]", output.toString());
        } catch (SOAPException ex) {
            log.error("Hubo un error al externalizar el SOAPMessage", ex);
        } catch (IOException ex) {
            log.error("Hubo un error al tratar de leer SOAPMessageContext", ex);
        } catch (Exception ex) {
            log.error("Error no esperado", ex);
        }

        return true;
    }

    @Override
    public void close(MessageContext context) {
        log.debug("close method - no operation");
    }
}
