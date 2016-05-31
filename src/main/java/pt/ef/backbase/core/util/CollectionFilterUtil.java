package pt.ef.backbase.core.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author jcfs
 */
public class CollectionFilterUtil {

	private CollectionFilterUtil() {
		// private constructor
	}

	/**
	 * Filter a given collections acording to a given and argument and a list of predicates
	 * 
	 * @param target the target collection to be filtered
	 * @param argument the argument
	 * @param predicates the predicates
	 * @return
	 */
	public static <T, S> Collection<T> filter(Collection<T> target, S argument, List<Predicate<T, S>> predicates) {
		Collection<T> result = new ArrayList<>(target);

		for (Predicate<T, S> predicate : predicates) {
			result = filter(result, argument, predicate);
		}
		
		return result;
	}

	/**
	 * Filter a given collections acording to a given and argument and a predicates
	 * 
	 * @param target the target collection to be filtered
	 * @param argument the argument
	 * @param predicates the predicates
	 * @return
	 */
	public static <T, S> Collection<T> filter(Collection<T> target, S argument, Predicate<T, S> predicate) {
		Collection<T> result = new ArrayList<>();

		for (T element : target) {
			if (predicate.validate(element, argument)) {
				result.add(element);
			}
		}

		return result;
	}

}
