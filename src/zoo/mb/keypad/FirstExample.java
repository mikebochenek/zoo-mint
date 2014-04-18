package zoo.mb.keypad;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import com.google.common.io.Files;

public class FirstExample {

	public static void main(String[] args) {
		List<String> filtered = readWords();
		System.out.println(filtered.size() + " left after filtering.");
	}

	public static List<String> readWords() {

		try {
			List<String> filtered = new ArrayList<String>();
			// http://stackoverflow.com/questions/4716503/best-way-to-read-a-text-file
			List<String> all = Files.readLines(new File(
					"/usr/share/dict/american-english"), Charset
					.defaultCharset());
			for (String s : all) {
				if (valid(s))
					filtered.add(s);
			}
			for (String s : filtered) {
				System.out.println(s);
			}

			System.out.println(all.size() + " total words.");
			return filtered;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	private static boolean valid(String s) {

		if (s.length() > 10)
			return false; // too long

		if (s.length() < 2)
			return false; // too short

		if (s.contains("'") || s.contains("é")) //TODO or better yet, filter out all non-standard [a-z]
			return false;

		return true;
	}

}
