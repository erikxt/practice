package test;

public class BitOperatorTest {

	public static void main(String[] args) {
		// 整型
		byte b1 = 10, b2 = 20;
		System.out.println("(byte)10 & (byte)20 = " + (b1 & b2));
		// 字符串型
		char c1 = 'a', c2 = 'A';
		System.out.println("(char)a | (char)A = " + (c1 | c2));
		// 基本类型的包装器类
		Long l1 = new Long(555), l2 = new Long(666);
		System.out.println("(Long)555 ^ (Long)666 = " + (l1 ^ l2));
		// 浮点型
		float f1 = 0.8F, f2 = 0.5F;
		// 编译报错，按位运算符不能用于浮点数类型
		// System.out.println("(float)0.8 & (float)0.5 = " + (f1 & f2));
	}
}
