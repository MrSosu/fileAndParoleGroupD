import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class FileAndParole {

    public static void stampaParole(Path file) throws IOException {
        /* FileReader fileReader = new FileReader(file.toFile());
        BufferedReader br = new BufferedReader(fileReader);
        while (br.ready()) {
            Arrays.stream(br.readLine().split(" ")).forEach(System.out::println);
        }
        br.close(); */
        Files.lines(file).forEach(riga -> Arrays.stream(riga.split(" ")).forEach(System.out::println));
    }

    public static HashMap<String, Integer> contaParole(Path file) throws IOException {
        /* FileReader fileReader = new FileReader(file.toFile());
        BufferedReader br = new BufferedReader(fileReader);
        HashMap<String, Integer> occorrenze = new HashMap<>();
        while (br.ready()) {
            String[] parole = br.readLine().split(" ");
            for (String parola : parole) {
                if (occorrenze.containsKey(parola)) {
                    occorrenze.put(parola, occorrenze.get(parola) + 1);
                }
                else {
                    occorrenze.put(parola, 1);
                }
            }
        }
        br.close();
        return occorrenze; */
        HashMap<String, Integer> occorrenze = new HashMap<>();
        Files.lines(file).forEach(riga -> Arrays.stream(riga.split(" ")).forEach(parola -> {
            if (occorrenze.containsKey(parola))  occorrenze.put(parola, occorrenze.get(parola) + 1);
            else occorrenze.put(parola, 1);
        }));
        return occorrenze;
    }

    public static HashMap<String, Integer> contaParoleTesto(Path file) throws IOException {
        FileReader fileReader = new FileReader(file.toFile());
        BufferedReader br = new BufferedReader(fileReader);
        HashMap<String, Integer> occorrenze = new HashMap<>();
        while (br.ready()) {
            String[] parole = br.readLine().toLowerCase().split("\\W+");
            for (String parola : parole) {
                if (parola.length() == 0) continue;
                if (occorrenze.containsKey(parola)) {
                    occorrenze.put(parola, occorrenze.get(parola) + 1);
                }
                else {
                    occorrenze.put(parola, 1);
                }
            }
        }
        br.close();
        return occorrenze;
    }

    public static HashMap<String, ArrayList<String>> paroleInRima(Path file) throws IOException {
        FileReader fileReader = new FileReader(file.toFile());
        BufferedReader br = new BufferedReader(fileReader);
        HashMap<String, ArrayList<String>> rime = new HashMap<>();
        while (br.ready()) {
            String[] parole = br.readLine().toLowerCase().replaceAll("\\d+", "").split("\\W+");
            for (String parola : parole) {
                if (parola.length() < 4) continue;
                char ultimo = parola.charAt(parola.length()-1);
                if (! (ultimo == 'a' || ultimo == 'e' || ultimo == 'i' || ultimo == 'o' || ultimo == 'u')) continue;
                String dittongo;
                char terzultimo = parola.charAt(parola.length() - 3);
                if (terzultimo == 'a' || terzultimo == 'e' || terzultimo == 'i' || terzultimo == 'o' || terzultimo == 'u') {
                    dittongo = parola.substring(parola.length()-3);
                }
                else {
                    char quartultimo = parola.charAt(parola.length() - 4);
                    if (! (quartultimo == 'a' || quartultimo == 'e' || quartultimo == 'i' || quartultimo == 'o' || quartultimo == 'u')) continue;
                    dittongo = parola.substring(parola.length()-4);
                }
                ArrayList<String> paroleDittongo = rime.getOrDefault(dittongo, new ArrayList<>());
                if (!paroleDittongo.contains(parola)) {
                    paroleDittongo.add(parola);
                    rime.put(dittongo, paroleDittongo);
                }
            }
        }
        br.close();
        return rime;
    }

}
