package test;

public class Parcel8 {
	public Wrapping wrapping(int x)
	{
		return new Wrapping(x) {
			public int value() {
				return super.value() * 47;
			}
		};  //semicolon required
	}
	public static void main(String[] args) {
		Parcel8 p = new Parcel8();
		Wrapping w = p.wrapping(10);
		System.out.println(w.value());
	}
}
