package kass.concurrente.hash;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Clase que implementa pruebas para la clase Hash.java.
 * @author Alberto Natanael Medel Piña
 * @version 1.0
 */
public class HashTets {
    Hash h;

    @BeforeEach
    void setuo() {
        h = new Hash();
    }

    private String generaSHA1(String cadenaPlana) {
        StringBuilder sha1 = new StringBuilder();
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-1");
            byte[] hash = md.digest(path.getBytes());

            for (byte b : hash)
                sha1.append(String.format("%02x", b));

        } catch (NoSuchAlgorithmException nsae) {
            nsae.printStackTrace();
        }

        return sha1.toString();
    }

    @Test
    void testGeneraHashDistintos() {
        String s1 = "Turing";
        String s2 = "Completo";

        String s3 = "turing";
        String s4 = "completo";

        String h1 = h.generaHash(s1, s2);
        String h2 = h.generaHash(s3, s4);

        assertNotEquals(h1, h2, "Dispersiones idénticas!");
    }

    @Test
    void testCreaDirectorio() {
        String path = "directorio/inexistente";
        String hash = generaSHA1(path);
        h.guardaHash(hash, path);

        File f1 = new File(path);
        File f2 = new File(path + "/" + hash + ".txt");
        assertTrue(f1.exists());
        assertTrue(f2.exists());
    }
}
