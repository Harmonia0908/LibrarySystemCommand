package LibrarySystem;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class LibrarySystem {

    private static Catalog catalog = null;

    public static void main(String[] args) {
        catalog = new Catalog();
        loadCatalogFromFile("catalog.dat");
        Scanner in = new Scanner(System.in);

        do {
            System.out.println("Please input a command(input 'h' for help):");
            String cmd = in.next();
            if (cmd.equals("h")) {
                System.out.println("" +
                        "input 'a' display all catalogitems;\n" +
                        "input 'b' display detail info of one item by title user input;\n" +
                        "input 'c' add a new Book/Record;\n" +
                        "input 'd' delete a Book/Record by title or index number user input;\n" +
                        "input 'e' save all catalogitems to file with the file name user input;\n" +
                        "input 'q' quit the system.");
            } else if (cmd.equals("a")) {
                displayCatalogItems();
            } else if (cmd.equals("b")) {
                System.out.println("Please input the title:");
                String input = in.next();
                CatalogItem item = catalog.getItem(input);
                if (item == null) {
                    System.out.println("Catalog item not found!");
                } else {
                    System.out.println(item);
                }
            } else if (cmd.equals("c")) {
                System.out.println("Please input the type of the new item(Book or Recording):");
                String type = in.next();
                if (type.equals("Book")) {
                    System.out.println("Please input the info of the new book with format like this(" +
                            "Book_code_title_year-month-day_author_numberOfPages):");
                    String line = in.nextLine();
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd");
                    String[] infos = line.split("_", 6);
                    Date date = null;
                    try {
                        date = sdf.parse(infos[3]);
                    } catch (ParseException e) {
                        throw new RuntimeException(e);
                    }
                    Book book = new Book(infos[1], infos[2], date, infos[4], Integer.parseInt(infos[5]));
                    catalog.addItem(book);
                } else if (type.equals("Recording")) {
                    System.out.println("Please input the info of the new recording with format like this(" +
                            "Recording_code_title_year-month-day_performer_format):");
                    String line = in.nextLine();
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd");
                    String[] infos = line.split("_", 6);
                    Date date = null;
                    try {
                        date = sdf.parse(infos[3]);
                    } catch (ParseException e) {
                        throw new RuntimeException(e);
                    }
                    Recording recording = new Recording(infos[1], infos[2], date, infos[4], infos[5]);
                    catalog.addItem(recording);
                    System.out.println("New recording added!");
                } else {
                    System.out.println("No such type of item!");
                }
            } else if (cmd.equals("d")) {
                System.out.println("Please input the filename you want to delete:");
                String input = in.next();
                CatalogItem item = catalog.getItem(input);
                if (item == null) {
                    System.out.println("No such an item!");
                } else {
                    catalog.removeItem(item);
                }
            } else if (cmd.equals("e")) {
                System.out.println("Please input the filename:");
                String input = in.next();
                saveCatalogToFile(input);
                System.out.println("Catalog items saved!");
            } else if (cmd.equals("q")) {
                System.out.println("System quit!Thanks for using our system!");
                break;
            } else {
                System.out.println("Error command!");
            }
        } while (true);

    }


    static void loadCatalogFromFile(String fileName) {
        File file = new File(fileName);
        try {
            InputStreamReader read = new InputStreamReader(new FileInputStream(file));
            BufferedReader bufferedReader = new BufferedReader(read);
            //判断文件是否存在
            if (file.exists()) {
                String line = null;
                while ((line = bufferedReader.readLine()) != null) {
//                    System.out.println(line);
                    String[] infos = line.split("_", 6);
                    if (infos[0].equals("Book")) {
                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd");
                        Date date = sdf.parse(infos[3]);
                        Book book = new Book(infos[1], infos[2], date, infos[4], Integer.parseInt(infos[5]));
                        catalog.addItem(book);
                    } else if (infos[0].equals("Recording")) {
                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd");
                        Date date = sdf.parse(infos[3]);
                        Recording recording = new Recording(infos[1], infos[2], date, infos[4], infos[5]);
                        catalog.addItem(recording);
                    } else {
                        System.out.println("Undefined item!");
                    }
                }
            } else {
                System.out.println("File not found!");
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    static void saveCatalogToFile(String fileName) {
        File file = new File(fileName);
        FileOutputStream outputStream = null;
        try {
            outputStream = new FileOutputStream(file);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        OutputStreamWriter writer = new OutputStreamWriter(outputStream);

        for (int i = 0; i < catalog.getNumberOfItems(); ++i) {
            try {
                writer.write(catalog.getItem(i).toString() + "\n");
                writer.flush();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    static void displayCatalogItems() {
        CatalogItem item = null;
        if (catalog.getNumberOfItems() == 0) {
            System.out.println("No catalog items yet!");
        } else {
            for (int i = 0; i < catalog.getNumberOfItems(); ++i) {
                item = catalog.getItem(i);
                System.out.println(item.code + " " + item.title);
            }
        }
    }
}
