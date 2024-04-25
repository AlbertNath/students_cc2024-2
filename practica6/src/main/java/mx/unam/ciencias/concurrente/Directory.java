package mx.unam.ciencias.concurrente;

import java.util.ArrayList;
import java.util.List;

public class Directory {
    public String path;
    public String name;
    private List<Directory> childDirs;
    private List<FFile> files;

    public Directory(String path, String name){
        this.name = name;
        this.path = path+name+"/";//Cambiar por \ si lo requieren
        childDirs = new ArrayList<>();
        files     = new ArrayList<>();
    }

}
