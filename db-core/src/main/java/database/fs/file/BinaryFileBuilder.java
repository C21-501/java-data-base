package database.fs.file;

/**
 * Concrete builder class for constructing the header of a BinaryFile.
 */
public class BinaryFileBuilder implements BinaryFileBuilderInterface {
    public static final int PAGE_COUNT = 10;

    private BinaryFile.Header header;
    private BinaryFile.MetaData metaData;
    private final BinaryFile.Page[] pages;
    private int currentIndex = 0;

    public BinaryFileBuilder() {
        this.header = new BinaryFile.Header((byte[]) null, (short) 0,0,0,0);
        this.metaData = new BinaryFile.MetaData(null);
        this.pages = new BinaryFile.Page[PAGE_COUNT];
    }

    @Override
    public BinaryFileBuilder setHeader(byte[] magicNumber, short version, int blockSize, int numberOfBlocks, int metadataSize) {
        this.header = new BinaryFile.Header(magicNumber, version, blockSize, numberOfBlocks, metadataSize);
        return this;
    }


    @Override
    public BinaryFileBuilder setMetaData(String metadata) {
        this.metaData = new BinaryFile.MetaData(metadata);
        return this;
    }

    @Override
    public BinaryFileBuilder setPage(int pageSize, int addressSize, BinaryFile.Page.DataBlock<?>[] dataBlocks) {
        if(currentIndex >= pages.length) throw new IllegalStateException("Page count exceeded");
        pages[currentIndex++] = new BinaryFile.Page(new BinaryFile.Page.PageHeader(pageSize, addressSize), dataBlocks);
        return this;
    }

    @Override
    public BinaryFile build() {
        return new BinaryFile(header, metaData, pages);
    }
}
