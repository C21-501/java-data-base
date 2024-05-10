package database.fs.file;

import database.system.fs.file.BinaryFile;
import database.system.fs.file.BinaryFileBuilder;
import database.system.fs.file.BinaryFileBuilderInterface;
import database.system.fs.file.BinaryFileDirector;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BinaryFileDirectorTest {

    @Test
    void test_construct_binary_file_with_header() {
        // Arrange
        BinaryFileBuilderInterface builder = new BinaryFileBuilder();
        BinaryFileDirector director = new BinaryFileDirector(builder);

        byte[] magicNumber = {0x12, 0x34};
        short version = 1;
        int blockSize = 1024;
        int numberOfBlocks = 10;
        int metadataSize = 128;

        // Act
        BinaryFile binaryFile = director.constructBinaryFile(
                magicNumber,
                version,
                blockSize,
                numberOfBlocks,
                metadataSize,
                "Sample metadata",
                new BinaryFile.Page.PageHeader[]{},
                new BinaryFile.Page.DataBlock<?>[][]{}
        );

        // Assert
        assertNotNull(binaryFile);
        assertNotNull(binaryFile.getHeader());
        assertArrayEquals(magicNumber, binaryFile.getHeader().magicNumber());
        assertEquals(version, binaryFile.getHeader().version());
        assertEquals(blockSize, binaryFile.getHeader().blockSize());
        assertEquals(numberOfBlocks, binaryFile.getHeader().numberOfBlocks());
        assertEquals(metadataSize, binaryFile.getHeader().metadataSize());
    }

    @Test
    void test_construct_binary_file_with_metadata() {
        // Arrange
        BinaryFileBuilderInterface builder = new BinaryFileBuilder();
        BinaryFileDirector director = new BinaryFileDirector(builder);

        String metadata = "Sample metadata";

        // Act
        BinaryFile binaryFile = director.constructBinaryFile(
                new byte[]{},
                (short) 0,
                0,
                0,
                0,
                metadata,
                new BinaryFile.Page.PageHeader[]{},
                new BinaryFile.Page.DataBlock<?>[][]{}
        );

        // Assert
        assertNotNull(binaryFile);
        assertNotNull(binaryFile.getMetaData());
        assertEquals(metadata, binaryFile.getMetaData().metadata());
    }

    @Test
    void test_construct_binary_file_with_page() {
        // Arrange

        BinaryFileBuilderInterface builder = new BinaryFileBuilder();
        BinaryFileDirector director = new BinaryFileDirector(builder);

        BinaryFile.Page.PageHeader pageHeader = new BinaryFile.Page.PageHeader(4096, 4);

        // Act
        BinaryFile binaryFile = director.constructBinaryFile(
                new byte[]{},
                (short) 0,
                0,
                0,
                0,
                "",
                new BinaryFile.Page.PageHeader[]{pageHeader},
                new BinaryFile.Page.DataBlock<?>[][]{{}}
        );

        // Assert
        assertNotNull(binaryFile);
        assertNotNull(binaryFile.getPages());
        assertEquals(10, binaryFile.getPages().length);
        assertEquals(pageHeader, binaryFile.getPages()[0].getPageHeader());
    }
}
