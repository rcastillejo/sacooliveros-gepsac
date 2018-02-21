/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sacooliveros.gepsac.service.rs.interceptor;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.InputStream;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.ext.Provider;
import javax.ws.rs.ext.ReaderInterceptor;
import javax.ws.rs.ext.ReaderInterceptorContext;
import javax.ws.rs.ext.WriterInterceptor;
import javax.ws.rs.ext.WriterInterceptorContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Ricardo
 */
@Provider
public class LoggingInterceptor implements
        ReaderInterceptor,
        WriterInterceptor {

    private final static Logger log = LoggerFactory.getLogger(LoggingInterceptor.class);

    @Override
    public Object aroundReadFrom(ReaderInterceptorContext ric) throws IOException, WebApplicationException {
        final InputStream old = ric.getInputStream();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        int c;
        while ((c = old.read()) != -1) {
            baos.write(c);
        }

        log.debug(">> {}", new Object[]{baos.toString()});

        ric.setInputStream(new ByteArrayInputStream(baos.toByteArray()));

        return ric.proceed();
    }

    @Override
    public void aroundWriteTo(WriterInterceptorContext wic) throws IOException, WebApplicationException {

        wic.setOutputStream(new FilterOutputStream(wic.getOutputStream()) {

            final ByteArrayOutputStream baos = new ByteArrayOutputStream();

            @Override
            public void write(int b) throws IOException {
                baos.write(b);
                super.write(b);
            }

            @Override
            public void close() throws IOException {
                log.debug("<< {}", new Object[]{baos.toString()});
                super.close();
            }
        });
        wic.proceed();
    }

}
