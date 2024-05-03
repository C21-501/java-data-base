//package database.fs;
//
//import database.fs.file.BinaryFile;
//import database.types.Text;
//
//import java.io.*;
//import java.util.Arrays;
//
//public class BinaryFileWriterReader {
//
//    // Запись данных в бинарный файл
//
//    public static void writeToFile(BinaryFile binaryFile, String filename) {
//        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(filename))) {
//            outputStream.writeObject(binaryFile);
//            System.out.println(STR."Binary file has been successfully written to: \{filename}");
//        } catch (IOException e) {
//            System.err.println(STR."Error occurred while writing binary file: \{e.getMessage()}");
//        }
//    }
//
//
//    // Чтение данных из бинарного файла
//    public static BinaryFile readFromFile(String filename) {
//        BinaryFile binaryFile = null;
//        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(filename))) {
//            binaryFile = (BinaryFile) inputStream.readObject();
//            System.out.println(STR."Binary file has been successfully read from: \{filename}");
//        } catch (IOException | ClassNotFoundException e) {
//            System.err.println(STR."Error occurred while reading binary file: \{Arrays.toString(e.getStackTrace())}");
//        }
//        return binaryFile;
//    }
//
//    public static void main(String[] args) {
//        // Создание экземпляра BinaryFile и заполнение данными
//        BinaryFile binaryFile = new BinaryFile();
//        // Установка заголовка
//        BinaryFile.Header header = new BinaryFile.Header(new byte[]{0x12, 0x34}, (short) 1, 1024, 10, 128);
//        binaryFile.setHeader(header);
//        // Установка метаданных
//        binaryFile.setMetaData(new BinaryFile.MetaData("Sample metadata"));
//        // Создание массива страниц
//        BinaryFile.Page[] pages = new BinaryFile.Page[1];
//        // Заполнение страниц данными
//        pages[0] = new BinaryFile.Page(
//                new BinaryFile.Page.PageHeader(4096, 4),
//                new BinaryFile.Page.DataBlock[]{
//                        new BinaryFile.Page.DataBlock<>(new Text("Test1")),  // Размер данных - 5 байт
//                        new BinaryFile.Page.DataBlock<>(new Text("Test2")),
//                        new BinaryFile.Page.DataBlock<>(new Text("Test3"))
//                }
//        );
//        binaryFile.setPages(pages);
//
//        // Запись данных в бинарный файл
//        String filename = "db-core/src/main/resources/data.bin";
//        writeToFile(binaryFile, filename);
//
//        // Чтение данных из бинарного файла
//        BinaryFile readBinaryFile = readFromFile(filename);
//
//        // Вывод информации о прочитанных данных
//        if (readBinaryFile != null) {
//            System.out.println(STR."Metadata: \{readBinaryFile.getMetaData().metadata()}");
//            System.out.println(STR."Magic Number: \{new String(readBinaryFile.getHeader().magicNumber())}");
//            System.out.println(STR."Version: \{readBinaryFile.getHeader().version()}");
//            System.out.println(STR."Block Size: \{readBinaryFile.getHeader().blockSize()}");
//            System.out.println(STR."Number of Blocks: \{readBinaryFile.getHeader().numberOfBlocks()}");
//            System.out.println(STR."Metadata Size: \{readBinaryFile.getHeader().metadataSize()}");
//
//            for (BinaryFile.Page page : readBinaryFile.getPages()) {
//                System.out.println(STR."Page Size: \{page.getPageHeader().pageSize()}");
//                System.out.println(STR."Address Size: \{page.getPageHeader().addressSize()}");
//                for (BinaryFile.Page.DataBlock<?> dataBlock : page.getDataBlocks()) {
//                    System.out.println(STR."String Data: \{dataBlock.getData()}, Data Size: \{dataBlock.getDataSize()}");
//                }
//            }
//        }
//    }
//}
