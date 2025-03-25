import java.util.Scanner;
import java.util.Stack;

public class TextEditor {
    private Stack<String> undoStack = new Stack<>();
    private Stack<String> redoStack = new Stack<>();
    private String document = "";

    public void addText(String text) {
        undoStack.push(document); // Zapisz aktualny stan przed zmianą
        document += text; // Dodaj nowy tekst
        redoStack.clear(); // Po dodaniu nowego tekstu resetujemy redo
    }

    public void undo() {
        if (!undoStack.isEmpty()) {
            redoStack.push(document); // Zapisz bieżący stan do redo
            document = undoStack.pop(); // Przywróć ostatni zapisany stan
        } else {
            System.out.println("Brak operacji do cofnięcia.");
        }
    }

    public void redo() {
        if (!redoStack.isEmpty()) {
            undoStack.push(document); // Zapisz bieżący stan do undo
            document = redoStack.pop(); // Przywróć cofniętą operację
        } else {
            System.out.println("Brak operacji do przywrócenia.");
        }
    }

    public void displayDocument() {
        System.out.println("Aktualna zawartość dokumentu: \"" + document + "\"");
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        TextEditor editor = new TextEditor();

        while (true) {
            System.out.println("\nWybierz opcję:");
            System.out.println("1 - Dodaj tekst");
            System.out.println("2 - Cofnij (Undo)");
            System.out.println("3 - Przywróć (Redo)");
            System.out.println("4 - Wyświetl dokument");
            System.out.println("5 - Wyjście");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Wyczyść bufor

            switch (choice) {
                case 1:
                    System.out.print("Wpisz tekst do dodania: ");
                    String text = scanner.nextLine();
                    editor.addText(text);
                    break;
                case 2:
                    editor.undo();
                    break;
                case 3:
                    editor.redo();
                    break;
                case 4:
                    editor.displayDocument();
                    break;
                case 5:
                    System.out.println("Zamykanie edytora...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Nieprawidłowa opcja, spróbuj ponownie.");
            }
        }
    }
}
