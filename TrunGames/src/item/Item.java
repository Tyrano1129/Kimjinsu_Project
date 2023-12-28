package item;

public abstract class Item {
	private int att;
	private int def;
	private int hp;
	private String name;
	private int Kind;
	private int price;
	public Item(int att, int def, int hp, String name,int kind,int price) {
		this.att = att;
		this.def = def;
		this.hp = hp;
		this.name = name;
		this.Kind = kind;
		this.price = price;
	}
	public int getKind() {
		return Kind;
	}
	public void setKind(int kind) {
		Kind = kind;
	}
	public int getAtt() {
		return att;
	}
	public void setAtt(int att) {
		this.att = att;
	}
	public int getDef() {
		return def;
	}
	public void setDef(int def) {
		this.def = def;
	}
	public int getHp() {
		return hp;
	}
	public void setHp(int hp) {
		this.hp = hp;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	@Override
	public String toString() {
		String data = String.format("[장비 이름 : %s] [공격력 : %d] [방어력 : %d] [체력 : %d] ",this.name,this.att,this.def,this.hp);
		return data;
	}
	
	
}
