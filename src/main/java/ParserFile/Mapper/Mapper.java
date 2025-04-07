package ParserFile.Mapper;

/**
 *
 *зачем писать маппер, если есть функциональный интерфейс???
 * илья потом скажет еще
 * почитать про спаггети код, почитать про принципы kiss, dry
 */
public interface Mapper<F, T> {
    T map(F object);



}
