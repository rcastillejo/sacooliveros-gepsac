/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.novatronic.sca.encripta;

import com.novatronic.pscabas.core.implementacion.SeguridadCore;
import com.novatronic.pscabas.core.model.Usuario;
import org.apache.commons.codec.binary.Base64;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author galcala
 */

public class Encrypt {
    /**
     * Tamaño de la sal apendizada al payload para generar el diges de
     * validación de la clave.
     */
    public static final int SALT_LENGTH = 8;
    /**
     * Codificación de caracteres para la conversión entre Strings y Bytes.
     */
    public static final String CHARACTER_ENCODING = "UTF-8";
    /**
     * Algoritmo de generación de resumen.
     */
    public static final String DIGEST_ALGORITHM = "SHA-512";
    
    //<editor-fold defaultstate="collapsed" desc="metodos de soporte para el proceso de generación de clave">

    /**
     * Crea el secreto que será usado para validar el password del usuario.
     *
     * @param empresa
     * @param nombreUsuario
     * @param clave
     * @param usuario
     * @return
     */
    public String createSecret(String empresa, String clave, Usuario usuario) {
        String payload = empresa + usuario.getUsuario() + clave;
        String hash = createSecretValidation(payload, usuario.getContrasena(), SALT_LENGTH);
        return hash;
    }

    /**
     * Crea un hash para inicio de sesion.
     *
     * @param empresa
     * @param nombreUsuario
     * @param clave
     * @return
     */
    public String createSecret(String empresa, String nombreUsuario, String clave) {
        String payload = empresa + nombreUsuario + clave;
        byte[] salt = new byte[SALT_LENGTH];
        String hash = "";
        try {
            hash = createhash(payload, salt);
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(SeguridadCore.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(SeguridadCore.class.getName()).log(Level.SEVERE, null, ex);
        }
        return hash;
    }
    
    /*Este método genera la MAC para la tabla TP_Usuario, la cual está compuesta por los siguientes campos:
     *- Código de la empresa
     *- Usuario
     *- Contraseña
     *- Correo
     *- Estado del usuario
     *- DNI
     *- Fecha de cambio de clave
     */
    public void createSecret(Usuario usuario) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        String mac = usuario.getEmpresa() + usuario.getUsuario() + usuario.getContrasena() + usuario.getCorreo()
                + usuario.getEstado() + usuario.getNumeroDocumento() + sdf.format(usuario.getFechaCambioClave());
        byte[] salt = new byte[SALT_LENGTH];
        try {
            usuario.setMac(createhash(mac, salt));
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(SeguridadCore.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(SeguridadCore.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Crea un hash para inicio de sesion.
     *
     * @param payload
     * @param saltLength
     * @return
     * @throws UnsupportedEncodingException
     * @throws NoSuchAlgorithmException
     */
    public String createSecret(String payload, int saltLength) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        byte[] salt = salAleatoria(saltLength);
        return createhash(payload, salt);
    }

    public String createSecretValidation(String payload, String hash, int saltLength) {
        try {
            byte[] salt = new byte[saltLength];
            {
                byte[] decoded = Base64.decodeBase64(hash);
                System.arraycopy(decoded, decoded.length - saltLength, salt, 0, saltLength);
            }
            return createhash(payload, salt);
        } catch (UnsupportedEncodingException ex) {
            throw new RuntimeException(ex);
        } catch (NoSuchAlgorithmException ex) {
            throw new RuntimeException(ex);
        }
    }

    public String createhash(String payload, byte[] salt) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        byte[] data = (payload).getBytes(CHARACTER_ENCODING);
        MessageDigest digest = MessageDigest.getInstance(DIGEST_ALGORITHM);
        byte[] pwd = data;
        for (int i = 0; i < 1000; i++) {
            digest.reset();
            digest.update(pwd);
            digest.update(salt);
            pwd = digest.digest();
        }
        pwd = Arrays.copyOf(pwd, pwd.length + salt.length);
        System.arraycopy(salt, 0, pwd, pwd.length - salt.length, salt.length);
        return Base64.encodeBase64String(pwd);
    }

    /**
     * Generador aleatorio de sales para los algoritmos de generación de
     * resumenes de claves/passphrases.
     *
     * @param saltLength longitud de sal a generar
     * @return arreglo de bytes con la sal generada
     */
    public byte[] salAleatoria(int saltLength) {
        Random random = new Random();
        byte[] salt = new byte[saltLength];
        random.nextBytes(salt);
        return salt;
    }
    //</editor-fold>

}
