package mx.unam.ciencias.concurrente;

public class FFile {
    private String name;
    private String path;

    public FFile(String path, String name){
        this.name = name;
        this.path = path+name;
    }       
}
