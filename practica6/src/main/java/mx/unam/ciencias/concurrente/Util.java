package mx.unam.ciencias.concurrente;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase utilitaria.
 * @version 1.0
 */
public class Util {
    private static void err(String msg) {
        System.err.println(msg);
        System.exit(1);
    }

    private static List<String> processLines(BufferedReader buff) throws IOException {
        List<String> result = new ArrayList<>();
        String tmp;
        while ((tmp = buff.readLine()) != null) 
            result.add(tmp);

        return result;
    }

    private static void wrtieContent(List<String> content, BufferedWriter buff) throws IOException {
        for(String i : content) {
            String l = i + "\n";
            buff.write(l);
        }        
    }

    public static List<String> readFile(String path) {
        List<String> result = new ArrayList<>();
        try {
            FileInputStream fileIn = new FileInputStream(path);
            InputStreamReader isIn = new InputStreamReader(fileIn);
            BufferedReader in = new BufferedReader(isIn);
            result = processLines(in);
            in.close();
        } catch (IOException ioe) {
            err("No se pudo leer el archivo " + path);
        }

        return result;
    }

    public static void saveFile(List<String> content, String path) {
        try {
            FileOutputStream fileOut = new FileOutputStream(path);
            OutputStreamWriter osOut = new OutputStreamWriter(fileOut);
            BufferedWriter out = new BufferedWriter(osOut);
            wrtieContent(content, out);
            out.close();
        } catch (IOException e) {
            err("No se pudo guardar el archivo " + path);
        }        
    }

    public static void mkdir(Directory dir) {
        try {
            Path path = Paths.get(dir.path);
            Files.createDirectory(path);
        } catch (IOException ioe) {
            err("Error al escribir la carpeta " + dir.name);
        }
    }
}
