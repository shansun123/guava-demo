package com.shansun.demo.guava.collections;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBasedTable;
import com.google.common.collect.HashBiMap;
import com.google.common.collect.HashMultimap;
import com.google.common.collect.HashMultiset;
import com.google.common.collect.Lists;
import com.google.common.collect.Multimap;
import com.google.common.collect.Multiset;
import com.google.common.collect.Table;

/**
 * Guava���������һЩ��������
 * 
 * @author: lanbo <br>
 * @version: 1.0 <br>
 * @date: 2012-7-2
 */
public class NewCollectionTypesUsage {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		useMultiset();

		System.out.println("______________________________________________\r\n");

		useMultimap();

		System.out.println("______________________________________________\r\n");

		useBiMap();

		System.out.println("______________________________________________\r\n");

		useTable();
	}

	/**
	 * Multiset�����һ��ֵ��Ӷ�Σ���������������ͳ��. ע��Multiset����Map. <br>
	 * ������JDK��map��Ӧ��Multiset��<br>
	 * HashMap ~ HashMultiset <br>
	 * TreeMap ~ TreeMultiset <br>
	 * LinkedHashMap ~ LinkedHashMultiset <br>
	 * CocurrentHashMap ~ ConcurrentHashMultiset <br>
	 * ImmutableMap ~ ImmutableMultiset <br>
	 */
	static void useMultiset() {
		Multiset<String> wordsMultiset = HashMultiset.<String> create();
		wordsMultiset.add("hello");
		wordsMultiset.add("world");
		wordsMultiset.add("where'r u");
		wordsMultiset.add("hello");
		wordsMultiset.add("guava");

		System.out.println(wordsMultiset);

		System.out.println(wordsMultiset.count("hello"));

		System.out.println(wordsMultiset.size());

		// Remove 2 hello
		wordsMultiset.remove("hello", 2);
		System.out.println(wordsMultiset);
	}

	/**
	 * Multimap�����һ������Ӷ��ֵ����������������ͳ�Ƶȡ�<br>
	 */
	static void useMultimap() {
		Multimap<String, String> multimap = HashMultimap.<String, String> create();
		multimap.put("hello", "world");
		multimap.put("hello", "guava");
		multimap.put("hello", "java");
		multimap.put("lanbo", "shansun");

		System.out.println(multimap);

		System.out.println(multimap.get("hello"));

		// ��MultimapתΪMap<K, Collection<V>>
		System.out.println(multimap.asMap());

		System.out.println(multimap.values().size());

		// ��ָ��key��ֵ�滻��, ����ֵΪ��ֵ
		System.out.println(multimap.replaceValues("lanbo", Lists.newArrayList("xujun")));
	}

	/**
	 * BiMap(˫��Map)��ֵ֤Ҳ��Ψһ�ģ�ͬʱ֧��key��value��ת����
	 */
	static void useBiMap() {
		BiMap<String, String> dict = HashBiMap.<String, String> create();
		dict.put("aubergine", "egglant");
		dict.put("jam", "jelly");
		dict.put("courgette", "zucchini");
		// java.lang.IllegalArgumentException: value already present: jelly
		// dict.put("jam2", "jelly");

		System.out.println(dict.get("jam"));

		// ��BiMap��key��value��ת
		dict = dict.inverse();

		System.out.println(dict.get("jelly"));

		// ��BiMap��key��value����
		dict = dict.inverse();

		// ǿ������value�����value�Ѿ����ڣ��򸲸�ԭ��ֵ
		dict.forcePut("jam3", "jelly");

		System.out.println(dict.get("jam"));

		System.out.println(dict.inverse().get("jelly"));
	}

	/**
	 * Table��ǿ��: HashBasedTable��TreeBasedTable��ImmutableTable��ArrayTable
	 */
	static void useTable() {
		Table<Character, Integer, String> aTable = HashBasedTable.<Character, Integer, String> create();

		// ����һ�����
		for (char a = 'A'; a <= 'C'; a++) {
			for (int b = 1; b <= 3; b++) {
				// ��һ��������rowKey���ڶ���������columnKey��������������ֵ
				aTable.put(a, b, String.format("%c%d", a, b));
			}
		}

		// ��ȡ��Ϊ2�����ݣ� ����ֵΪMap<Character, String>
		System.out.println(aTable.column(2));

		// ��ȡ��ΪA�����ݣ�����ֵΪMap<Integer, String>
		System.out.println(aTable.row('A'));

		// ��ȡ��Ϊ2����ΪB������
		System.out.println(aTable.get('B', 2));

		// �����Ƿ����
		System.out.println(aTable.contains('N', 4));

		// �����Ƿ������
		System.out.println(aTable.containsColumn(3));

		// �����Ƿ������
		System.out.println(aTable.containsRow('G'));

		// ��Tableת��ΪMap<K1, Map<K2, V>>
		System.out.println(aTable.columnMap());

		// ��Tableת��ΪMap<K1, Map<K2, V>>
		System.out.println(aTable.rowMap());

		// �Ƴ�һ��ֵ������ֵΪ��ǰֵ
		System.out.println(aTable.remove('B', 3));
	}
}
