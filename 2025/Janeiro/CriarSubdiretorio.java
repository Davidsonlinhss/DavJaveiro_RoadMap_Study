import java.io.File;

public class CriarSubdiretorio {
    public static void main(String[] args) {
        
        System.out.println("Diretório atual: " + System.getProperty("user.dir"));
        // Diretório pai
        File dir = new File("c:\\Users\\Davidson\\Desktop\\DavJaveiro_RoadMap_Study\\2025\\Janeiro");
        
        System.out.println((dir.exists()) 
            ? " O diretório atual existe" 
                : "o diretório não existe");

        File subdir = new File(dir, "sub2");

        if(!subdir.exists()) {
            subdir.mkdir();
            System.out.println("Diretório " + subdir + " criado com sucesso!");
        } else 
            System.out.println(subdir + " Já existe");
    }
}
