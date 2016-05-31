package pt.ef.backbase.core.util;

/**
 * @author jcfs
 *
 * @param <T>
 */
public interface Predicate<T, S> {
	/**
	 * @param type
	 * @return
	 */
	boolean validate(T source, S argument);
}
