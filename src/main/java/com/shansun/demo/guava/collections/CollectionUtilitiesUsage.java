package com.shansun.demo.guava.collections;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.google.common.base.Function;
import com.google.common.base.Predicate;
import com.google.common.base.Strings;
import com.google.common.base.Supplier;
import com.google.common.collect.FluentIterable;
import com.google.common.collect.HashMultimap;
import com.google.common.collect.ImmutableListMultimap;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.google.common.collect.MapDifference;
import com.google.common.collect.Maps;
import com.google.common.collect.Multimaps;
import com.google.common.collect.SetMultimap;
import com.google.common.collect.Sets;
import com.google.common.collect.Sets.SetView;
import com.google.common.collect.Table;
import com.google.common.collect.Tables;
import com.google.common.primitives.Ints;

/**
 * ���ü��Ϲ�����
 * 
 * @author: lanbo <br>
 * @version: 1.0 <br>
 * @date: 2012-7-2
 */
public class CollectionUtilitiesUsage {

	static List<String>			list;
	static Map<String, Object>	map;
	static Set<String>			set;

	static List<String>			exactly100;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		useStaticConstructors();

		useIterables();

		useFluentIterable();

		useLists();

		useSets();

		useMaps();

		useMultimaps();

		useTables();
	}

	static void useStaticConstructors() {
		// ����ʹ�þ�̬���췽��������ָ�����϶����������
		list = Lists.newArrayList();
		map = Maps.newLinkedHashMap();
		set = Sets.newHashSet();
	}

	static void useIterables() {
		Iterable<Integer> concatenated = Iterables.concat(Ints.asList(1, 2, 3), Ints.asList(4, 5, 6), Ints.asList(7, 8, 9));

		System.out.println(concatenated);

		Integer last = Iterables.getLast(concatenated);

		System.out.println(last);

		Predicate<Integer> greaterThanFive = new Predicate<Integer>() {

			@Override
			public boolean apply(Integer input) {
				return input > 5;
			}
		};

		System.out.println(Iterables.all(concatenated, greaterThanFive));

		System.out.println(Iterables.any(concatenated, greaterThanFive));

		// ����
		System.out.println(Iterables.filter(concatenated, greaterThanFive));

		// ����Ƶ��
		System.out.println(Iterables.frequency(concatenated, 5));
	}

	/**
	 * ʹ�������ĵ�����
	 */
	static void useFluentIterable() {
		final Collection<String> filtered = FluentIterable //
				.from(Lists.newArrayList("hello", "world", "lanbo", "shansun", "xujun")) //
				.transform(new Function<String, String>() { //

							@Override
							public String apply(String input) {
								return input == null ? "" : Strings.repeat(input, 3);
							}
						}) //
				.filter(new Predicate<String>() { //

					@Override
					public boolean apply(String input) {
						return !input.contains("r");
					}
				}) //
				.limit(3) //
				.skip(1) //
				.toImmutableList();

		System.out.println(filtered);
	}

	static void useLists() {
		List<Integer> countUp = Ints.asList(1, 2, 3, 4, 5);

		List<Integer> countDown = Lists.reverse(countUp);

		System.out.println(countDown);

		List<List<Integer>> parts = Lists.partition(countUp, 2);

		System.out.println(parts);
	}

	static void useSets() {
		Set<String> wordsWithPrimeLength = ImmutableSet.of("one", "two", "three", "six", "seven", "eight");
		Set<String> primes = ImmutableSet.of("two", "three", "five", "seven");

		// ���Ͻ����ļ���
		SetView<String> intersection = Sets.intersection(primes, wordsWithPrimeLength);

		ImmutableSet<String> immutableSet = intersection.immutableCopy();

		System.out.println(immutableSet);

		Set<String> animals = ImmutableSet.of("gerbil", "hamster");
		Set<String> fruits = ImmutableSet.of("apple", "orange", "banana");

		// ���ϵĲ������� Sets.union

		// ���ϵĲ������� Sets.different

		// ���ϵѿ���������
		@SuppressWarnings("unchecked")
		Set<List<String>> cartesianProduct = Sets.<String> cartesianProduct(animals, fruits);

		System.out.println(cartesianProduct);

		// �����Ӽ�����
		Set<Set<String>> powerSet = Sets.powerSet(animals);

		System.out.println(powerSet);
	}

	static void useMaps() {
		List<String> strings = Lists.newArrayList("hello", "world!", "lan", "bo", "shansun");

		// һ�����󼯺ϣ�����֪��������һЩ���أ����໥���֡�Ψһ�ԣ������ԣ���������ϣ��ͨ����Щ���Ե�ֵ�ҵ���صĶ���ʱ
		// ����ѡ��ʹ��Maps.uniqueIndexȥ����һ��keyΪ����ָ���������ɵ�Ψһֵ
		ImmutableMap<Integer, String> uniqueIndex = Maps.uniqueIndex(strings, new Function<String, Integer>() {

			@Override
			public Integer apply(String input) {
				return input.length();
			}
		});

		System.out.println(uniqueIndex);

		ImmutableMap<String, Integer> left = ImmutableMap.<String, Integer> of("a", 1, "b", 2, "c", 3);
		ImmutableMap<String, Integer> right = ImmutableMap.<String, Integer> of("b", 2, "c", 4, "d", 5);

		// �Ƚ�����Map����
		MapDifference<String, Integer> difference = Maps.difference(left, right);

		// ���߶��е���Ŀ
		System.out.println(difference.entriesInCommon());

		// ����ֵ��ͬ����Ŀ
		System.out.println(difference.entriesDiffering());

		// ֻ������е���Ŀ
		System.out.println(difference.entriesOnlyOnLeft());

		// ֻ���ұ��е���Ŀ
		System.out.println(difference.entriesOnlyOnRight());
	}

	static void useMultisets() {
		// NOTHING
	}

	/**
	 * Multimap����һ��key��Ӧ���value��Ҳ������key��Ӧһ��value
	 */
	static void useMultimaps() {
		ImmutableSet<String> immutableSet = ImmutableSet.of("zero", "one", "two", "three", "four");

		// ���ַ�����������
		Function<String, Integer> lengthFunc = new Function<String, Integer>() {

			@Override
			public Integer apply(String input) {
				return input == null ? -1 : input.length();
			}
		};

		// Multimaps.index��Maps.uniqueIndex��ͬ���ǣ�ǰ�߲�Ҫ���������Ψһ��
		ImmutableListMultimap<Integer, String> multimap = Multimaps.index(immutableSet, lengthFunc);

		System.out.println(multimap);

		Map<String, Integer> map = ImmutableMap.of("a", 1, "b", 1, "c", 2);

		// ����ͨmapתΪMultimap
		SetMultimap<String, Integer> multimap2 = Multimaps.forMap(map);

		// ��mapִ�п�������key��value��λ
		HashMultimap<Integer, String> inverse = Multimaps.invertFrom(multimap2, HashMultimap.<Integer, String> create());

		System.out.println(inverse);
	}

	static void useTables() {
		// �½�һ��Table
		Table<String, Character, Integer> table = Tables.newCustomTable(Maps.<String, Map<Character, Integer>> newLinkedHashMap(), new Supplier<Map<Character, Integer>>() {

			@Override
			public Map<Character, Integer> get() {
				return Maps.newLinkedHashMap();
			}
		});

		table.put("Row#1", 'A', 2);

		System.out.println(table.get("Row#1", 'A'));
	}
}
