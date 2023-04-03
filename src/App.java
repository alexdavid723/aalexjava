import java.util.HashMap;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
public class App {
    private static final int NUM_HILOS = 4; // número de hilos
    private static final HashMap<String, Integer> cuentaPalabras = new HashMap<>();
    public static void main(String[] args) throws InterruptedException {
        Scanner scanner = new Scanner(App.class.getResourceAsStream("C:/Users/gladi/Desktop/alex.txt"));


        // Crear hilos para contar las palabras en cada parte
        ExecutorService executor = Executors.newFixedThreadPool(NUM_HILOS);

        while (scanner.hasNext()) {
            String linea = scanner.nextLine();
            executor.execute(() -> contarPalabras(linea));
        }

        executor.shutdown();
        executor.awaitTermination(1, TimeUnit.MINUTES);

        // Imprimir los resultados
        System.out.println(cuentaPalabras);
    }

    private static void contarPalabras(String linea) {
        String[] palabras = linea.split("\\W+");
        for (String palabra : palabras) {
            cuentaPalabras.merge(palabra, 1, Integer::sum);
        }
    }
}