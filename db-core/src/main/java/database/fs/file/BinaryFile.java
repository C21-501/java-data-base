package database.fs.file;

import database.types.AbstractDataType;
import lombok.Data;
import lombok.Setter;

import java.io.Serializable;

/**
 * Represents a binary file containing header, metadata, and data pages.
 */
@Data
public class BinaryFile implements Serializable {
    /** The header of the binary file. */
    private Header header;

    /** The metadata of the binary file. */
    private MetaData metaData;

    /** The array of pages containing data blocks. */
    private Page[] pages;

    /**
     * Constructs a BinaryFile object with the specified header, metadata, and pages.
     *
     * @param header   the header of the binary file
     * @param metaData the metadata of the binary file
     * @param pages    the array of pages containing data blocks
     */
    public BinaryFile(Header header, MetaData metaData, Page[] pages) {
        this.header = header;
        this.metaData = metaData;
        this.pages = pages;
    }

    /**
     * Represents metadata associated with the binary file.
     *
     * @param metadata the metadata information
     */
    public record MetaData(String metadata) implements Serializable{}

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
    ) implements Serializable {}

    /**
     * Represents a page containing data blocks.
     */
    @Data
    public static class Page implements Serializable {
        /** The header of the page. */
        private PageHeader pageHeader;

        /** The array of data blocks within the page. */
        private DataBlock<AbstractDataType>[] dataBlocks;

        @SuppressWarnings("unchecked")
        public Page(PageHeader pageHeader, Object[] o) {
            this.pageHeader = pageHeader;
            if (o != null) {
                for (Object obj : o) {
                    if (!(obj instanceof DataBlock)) {
                        throw new IllegalArgumentException("Invalid object type in dataBlocks array.");
                    }
                }
                this.dataBlocks = (DataBlock<AbstractDataType>[]) o;
            } else {
                this.dataBlocks = null;
            }
        }


        /**
         * Represents the header of a page.
         *
         * @param pageSize    the size of the page
         * @param addressSize the size of the address
         */

        public record PageHeader(int pageSize, int addressSize) implements Serializable{}

        /**
         * Represents a block of data within a page.
         *
         * @param <T>       the type of data contained within the block
         */
        @Data
        public static class DataBlock<T extends AbstractDataType> implements Serializable {
            private final T data;
            private final int dataSize;

            public DataBlock(T data) {
                this.data = data;
                this.dataSize = data.getByteLength();
            }
        }
    }
}
