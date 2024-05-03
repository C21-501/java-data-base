package database.fs;

import database.types.Text;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BinaryFileTest {

    @Test
    void test_get_header() {
        // Arrange
        BinaryFile binaryFile = new BinaryFile();
        BinaryFile.Header header = new BinaryFile.Header(new byte[]{0x12, 0x34}, (short) 1, 1024, 10, 128);
        binaryFile.setHeader(header);

        // Act
        BinaryFile.Header result = binaryFile.getHeader();

        // Assert
        assertEquals(header, result);
    }

    @Test
    void test_get_meta_data() {
        // Arrange
        BinaryFile binaryFile = new BinaryFile();
        BinaryFile.MetaData metaData = new BinaryFile.MetaData("Sample metadata");
        binaryFile.setMetaData(metaData);

        // Act
        BinaryFile.MetaData result = binaryFile.getMetaData();

        // Assert
        assertEquals(metaData, result);
    }

    @Test
    void test_get_pages() {
        // Arrange
        BinaryFile binaryFile = new BinaryFile();
        BinaryFile.Page[] pages = new BinaryFile.Page[2];
        binaryFile.setPages(pages);

        // Act
        BinaryFile.Page[] result = binaryFile.getPages();

        // Assert
        assertArrayEquals(pages, result);
    }

    @Test
    void test_set_header() {
        // Arrange
        BinaryFile binaryFile = new BinaryFile();
        BinaryFile.Header header = new BinaryFile.Header(new byte[]{0x12, 0x34}, (short) 1, 1024, 10, 128);

        // Act
        binaryFile.setHeader(header);

        // Assert
        assertEquals(header, binaryFile.getHeader());
    }

    @Test
    void test_set_meta_data() {
        // Arrange
        BinaryFile binaryFile = new BinaryFile();
        BinaryFile.MetaData metaData = new BinaryFile.MetaData("Sample metadata");

        // Act
        binaryFile.setMetaData(metaData);

        // Assert
        assertEquals(metaData, binaryFile.getMetaData());
    }

    @Test
    void test_set_pages() {
        // Arrange
        BinaryFile binaryFile = new BinaryFile();
        BinaryFile.Page[] pages = new BinaryFile.Page[2];

        // Act
        binaryFile.setPages(pages);

        // Assert
        assertArrayEquals(pages, binaryFile.getPages());
    }

    @Test
    void test_equals() {
        // Arrange
        BinaryFile binaryFile1 = new BinaryFile();
        BinaryFile binaryFile2 = new BinaryFile();

        // Act & Assert
        assertEquals(binaryFile1, binaryFile2);
    }

    @Test
    void test_hash_code() {
        // Arrange
        BinaryFile binaryFile = new BinaryFile();

        // Act
        int hashCode = binaryFile.hashCode();

        // Assert
        assertEquals(binaryFile.hashCode(), hashCode);
    }

    @Test
    void test_to_string() {
        // Arrange
        BinaryFile binaryFile = new BinaryFile();

        // Act
        String toStringResult = binaryFile.toString();

        // Assert
        assertNotNull(toStringResult);
    }

    @Test
    void test_page_equals() {
        // Arrange
        BinaryFile.Page.PageHeader pageHeader = new BinaryFile.Page.PageHeader(4096, 4);
        BinaryFile.Page page1 = new BinaryFile.Page(pageHeader, null);
        BinaryFile.Page page2 = new BinaryFile.Page(pageHeader, null);

        // Act & Assert
        assertEquals(page1, page2);
    }

    @Test
    void test_data_block_equals() {
        // Arrange
        BinaryFile.Page.DataBlock<Text> dataBlock1 = new BinaryFile.Page.DataBlock<>(new Text("Test"));
        BinaryFile.Page.DataBlock<Text> dataBlock2 = new BinaryFile.Page.DataBlock<>(new Text("Test"));

        // Act & Assert
        assertEquals(dataBlock1, dataBlock2);
    }

    @Test
    void test_page_to_string() {
        // Arrange
        BinaryFile.Page.PageHeader pageHeader = new BinaryFile.Page.PageHeader(4096, 4);
        BinaryFile.Page page = new BinaryFile.Page(pageHeader, null);

        // Act
        String toStringResult = page.toString();
        // Assert
        assertNotNull(toStringResult);
    }

    @Test
    void test_data_block_to_string() {
        // Arrange
        BinaryFile.Page.DataBlock<Text> dataBlock = new BinaryFile.Page.DataBlock<>(new Text("Test"));

        // Act
        String toStringResult = dataBlock.toString();

        // Assert
        assertNotNull(toStringResult);
    }
}
