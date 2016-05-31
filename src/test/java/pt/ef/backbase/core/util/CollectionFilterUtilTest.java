package pt.ef.backbase.core.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.util.Assert;

public class CollectionFilterUtilTest {

	private List<Predicate<String, String>> equalsPredicates;
	private List<Predicate<String, String>> notEqualsPredicates;

	@Before
	public void setup() {
		equalsPredicates = new ArrayList<>();

		equalsPredicates.add(new Predicate<String, String>() {
			@Override
			public boolean validate(String source, String argument) {
				return source.equals(argument);
			}
		});

		notEqualsPredicates = new ArrayList<>();

		notEqualsPredicates.add(new Predicate<String, String>() {
			@Override
			public boolean validate(String source, String argument) {
				return !source.equals(argument);
			}
		});
	}

	@Test
	public void testCollectionFilter() {
		List<String> list = new ArrayList<>();
		list.add("alice");
		list.add("bob");
		list.add("tom");

		Collection<String> filtered = CollectionFilterUtil.filter(list, "alice", equalsPredicates);
		Assert.notEmpty(filtered);
		Assert.isTrue(filtered.size() == 1);

		filtered = CollectionFilterUtil.filter(list, "bob", notEqualsPredicates);
		Assert.notEmpty(filtered);
		Assert.isTrue(filtered.size() == 2);
	}
}
