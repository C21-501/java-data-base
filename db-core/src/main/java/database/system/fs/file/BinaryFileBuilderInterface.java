package database.system.fs.file;

/**
 * Interface for building BinaryFile objects.
 */
public interface BinaryFileBuilderInterface {
    /**
     * Sets the header of the binary file being built.
     *
     * @param magicNumber    the magic number identifying the file format
     * @param version        the version of the file format
     * @param blockSize      the size of each block
     * @param numberOfBlocks the total number of blocks
     * @param metadataSize   the size of the metadata
     * @return the builder instance
     */
    BinaryFileBuilderInterface setHeader(byte[] magicNumber, short version, int blockSize, int numberOfBlocks, int metadataSize);

    /**
     * Sets the metadata of the binary file being built.
     *
     * @param metadata the metadata information
     * @return the builder instance
     */
    BinaryFileBuilderInterface setMetaData(String metadata);

    /**
     * Adds a page with the specified page size, address size, and data blocks to the binary file being built.
     *
     * @param pageSize    the size of the page
     * @param addressSize the size of the address
     * @param dataBlocks  the array of data blocks within the page
     * @return the builder instance
     */
    BinaryFileBuilderInterface setPage(int pageSize, int addressSize, BinaryFile.Page.DataBlock<?>[] dataBlocks);

    /**
     * Constructs the BinaryFile object based on the provided information.
     *
     * @return the constructed BinaryFile object
     */
    BinaryFile build();
}
