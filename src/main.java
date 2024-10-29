import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

class FileSplitter {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Masukkan nama file (misal: file.txt): ");
        String fileName = scanner.nextLine();

        System.out.print("Masukkan jumlah karakter per bagian: ");
        int chunkSize = scanner.nextInt();

        Queue<String> queue = new LinkedList<>();

        // Membaca file dan memotongnya
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            StringBuilder currentChunk = new StringBuilder();
            int character;
            int charCount = 0;

            // Membaca setiap karakter dari file
            while ((character = reader.read()) != -1) {
                currentChunk.append((char) character);
                charCount++;

                // Jika jumlah karakter mencapai chunkSize, tambahkan ke queue
                if (charCount >= chunkSize) {
                    queue.add(currentChunk.toString());
                    currentChunk.setLength(0); // Reset StringBuilder
                    charCount = 0; // Reset hitungan karakter
                }
            }
            // Menambahkan sisa karakter yang tersisa ke dalam queue
            if (currentChunk.length() > 0) {
                queue.add(currentChunk.toString());
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
            return;
        }

        // Menampilkan bagian-bagian yang telah dipotong
        System.out.println("\nBagian-bagian yang dipotong:");
        while (!queue.isEmpty()) {
            System.out.println(queue.poll());
        }

        scanner.close();
    }
}
