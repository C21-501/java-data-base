package database.fs.file;

/**
 * Director class responsible for constructing BinaryFile objects using a builder.
 */
public class BinaryFileDirector {
    private final BinaryFileBuilderInterface binaryFileBuilder;

    /**
     * Constructs a BinaryFileDirector with the specified builders.
     *
     * @param binaryFileBuilder   the builder for constructing the header
     * @param metaDataBuilder the builder for constructing the metadata
     * @param pageBuilder     the builder for constructing the pages
     */
    public BinaryFileDirector(BinaryFileBuilderInterface binaryFileBuilder) {
        this.binaryFileBuilder = binaryFileBuilder;
    }

    /**
     * Constructs a BinaryFile object based on the provided parameters.
     *
     * @param magicNumber     the magic number identifying the file format
     * @param version         the version of the file format
     * @param blockSize       the size of each block
     * @param numberOfBlocks  the total number of blocks
     * @param metadataSize    the size of the metadata
     * @param metadata        the metadata information
     * @param pageHeaders     the array of page headers
     * @param dataBlocksArray the array of arrays of data blocks for each page
     * @return the constructed BinaryFile object
     */
    public BinaryFile constructBinaryFile(
            byte[] magicNumber,
            short version,
            int blockSize,
            int numberOfBlocks,
            int metadataSize,
            String metadata,
            BinaryFile.Page.PageHeader[] pageHeaders,
            BinaryFile.Page.DataBlock<?>[][] dataBlocksArray
    ) {
        binaryFileBuilder
                .setHeader(magicNumber, version, blockSize, numberOfBlocks, metadataSize)
                .setMetaData(metadata);
        for (int i = 0; i < pageHeaders.length; i++) {
            binaryFileBuilder.setPage(pageHeaders[i].pageSize(), pageHeaders[i].addressSize(), dataBlocksArray[i]);
        }
        return binaryFileBuilder.build();
    }
}
