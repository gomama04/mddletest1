package kadai7;

public class Monster {
	
	String name;
	int hp;
	int at;
	int df;
	
	void attack() {
		System.out.println(this.name + "の攻撃");
		System.out.println(this.at + "のダメージ");
	}
	
	void defense() {
		System.out.println(this.name + "は守りを固めた");
		this.df = this.df + 10;
		System.out.println("防御力が１０upし" + this.df + "になった");
		
		
	}
	
	

}
