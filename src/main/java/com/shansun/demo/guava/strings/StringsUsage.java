package com.shansun.demo.guava.strings;

import java.util.Arrays;

import com.google.common.base.CaseFormat;
import com.google.common.base.CharMatcher;
import com.google.common.base.Charsets;
import com.google.common.base.Joiner;
import com.google.common.base.Splitter;

/**
 * <p>
 * </p>
 * 
 * @author: lanbo <br>
 * @version: 1.0 <br>
 * @date: 2012-7-4
 */
public class StringsUsage {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		useJoiner();

		useSplitter();

		useCharMatcher();
		
		useCharsets();
		
		useCaseFormat();
	}

	static void useJoiner() {
		// ����null����
		Joiner joiner = Joiner.on("; ").skipNulls();
		String join = joiner.join("Harry", null, "Ron", "Hermione");
		System.out.println(join);

		// �û�nullΪָ���ַ���
		String join2 = Joiner.on(",").useForNull("not-number").join(Arrays.asList(1, 4, 6, null, 8));
		System.out.println(join2);
	}

	static void useSplitter() {
		Iterable<String> split = Splitter.on(",") //
				.trimResults() // ȥ������ַ������ߵĿհ׷�
				.omitEmptyStrings() // ���Կ��ַ���
				.limit(4) // ���ַ����ָ�Ϊָ����Ŀ���Ӵ�
				.split("foo,bar,,  qux");

		for (String s : split) {
			System.out.print(s);
		}

		System.out.println();

		// �̶����ȷָ�
		Iterable<String> split2 = Splitter.fixedLength(3).split("foobarqux");

		for (String s : split2) {
			System.out.println(s);
		}
	}

	static void useCharMatcher() {
		// �滻��ָ���ַ�
		String replaceFrom = CharMatcher.JAVA_DIGIT.replaceFrom("hello1234", '*');
		System.out.println(replaceFrom);

		// ��������һЩ����������removeFrom��retainFrom��trimFrom��
	}
	
	static void useCharsets() {
		byte[] bytes = "hello1234".getBytes(Charsets.UTF_8);
		System.out.println(bytes);
	}
	
	static void useCaseFormat() {
		System.out.println(CaseFormat.UPPER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, "CONSTANT_NAME"));
	}
}
