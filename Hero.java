package kadai7;

public class Hero {
	
	
	String name;
	int hp;
	int at;
	int df;
	
	
	
	void attack() {
		System.out.println(this.name + "の攻撃");
		System.out.println("敵にダメージを" + this.at + "与えた");
	}
	
	void defense() {
		System.out.println("勇者が防御した");
		this.df = this.df + 10;
		System.out.println("防御力が１０upし" + this.df + "になった");
		
		
	}
	
	void heal() {
		System.out.println("勇者が休憩した");
		this.hp = this.hp + 10;
		System.out.println("ｈｐが１０回復し" + this.hp + "になった");
		
	}


}
