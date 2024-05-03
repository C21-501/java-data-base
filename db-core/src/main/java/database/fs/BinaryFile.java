package database.fs;

import lombok.Data;

/**
 * Represents a binary file containing header, metadata, and data pages.
 */
@Data
public class BinaryFile {
    /** The header of the binary file. */
    private Header header;

    /** The metadata of the binary file. */
    private MetaData metaData;

    /** The array of pages containing data blocks. */
    private Page[] pages;

    /**
     * Represents metadata associated with the binary file.
     *
     * @param metadata the metadata information
     */
    public record MetaData(String metadata){}

    /**
     * Represents the header of the binary file.
     *
     * @param magicNumber    the magic number identifying the file format
     * @param version        the version of the file format
     * @param blockSize      the size of each block
     * @param numberOfBlocks the total number of blocks
     * @param metadataSize   the size of the metadata
     */
    public record Header(
            byte[] magicNumber,
            short version,
            int blockSize,
            int numberOfBlocks,
            int metadataSize
    ) {}

    /**
     * Represents a page containing data blocks.
     */
    @Data
    public static class Page {
        /** The header of the page. */
        private PageHeader pageHeader;

        /** The array of data blocks within the page. */
        private DataBlock[] dataBlocks;

        /**
         * Represents the header of a page.
         *
         * @param pageSize    the size of the page
         * @param addressSize the size of the address
         */
        public record PageHeader(int pageSize, int addressSize) {}

        /**
         * Represents a block of data within a page.
         *
         * @param data     the data contained within the block
         * @param dataSize the size of the data
         */
        public record DataBlock(Object data, int dataSize) {}
    }
}
