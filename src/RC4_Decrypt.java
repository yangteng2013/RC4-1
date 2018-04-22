import java.util.Scanner;

public class RC4_Decrypt {
	private static int[] s_box;
	private static StringBuffer plain;
	private static String cipher;

	private static void swap(int[] s_box, int i, int j) {
		int temp = s_box[i];
		s_box[i] = s_box[j];
		s_box[j] = temp;

	}

	private static void init_Box(String key, int key_length) {
		s_box = new int[256];
		for (int i = 0; i < 256; i++)
			s_box[i] = i;
		int j = 0;
		for (int i = 0; i < 256; i++) {
			j = (s_box[i] + key.charAt(i % key_length) + j) % 256;
			swap(s_box, i, j);
		}
	}

	private static void generate_chiper() {
		int index = 0;
		int i = 0;
		int j = 0;
		plain = new StringBuffer();
		while (index < cipher.length() / 2) {
			i = (++i) % 256;
			j = (s_box[i] + j) % 256;
			swap(s_box, i, j);
			int temp = s_box[(s_box[i] + s_box[j]) % 256];
			StringBuffer tmp = new StringBuffer();
			tmp.append(cipher.charAt(index * 2));
			tmp.append(cipher.charAt(index * 2 + 1));
			int a = Integer.parseInt(tmp.toString(), 16);
			plain.append((char) (a ^ temp));
			index++;
		}

	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("====================RC4_decrypt===================\n============please input key:");
		String key = sc.next();
		init_Box(key, key.length());
		System.out.println("============please input chiper text(hex encode):");
		cipher = sc.next();
		generate_chiper();
		System.out.println(plain.toString());
	}
}
