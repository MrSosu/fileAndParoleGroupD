import java.io.File;
import java.io.IOException;
import java.nio.file.Path;

public class Main {

    public static void main(String[] args) throws IOException {
        Path file1 = new File("resources/roba.txt").toPath();
        Path file2 = new File("resources/alice_in_wonderland.txt").toPath();
        Path file3 = new File("resources/promessiSposi").toPath();
        // FileAndParole.stampaParole(file1);
        // System.out.println(FileAndParole.contaParoleTesto(file2));
        System.out.println(FileAndParole.paroleInRima(file3));
    }

}
