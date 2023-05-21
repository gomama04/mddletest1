package kadai7;

public class Magician {
    String name;
    int hp;
    int at;
    int at2;
    int mp;

    void attack() {
        System.out.println(this.name + "の攻撃");
        System.out.println("敵にダメージを" + this.at + "与えた");
    }

    void magic() {
        System.out.println("魔法使いが魔法攻撃をした");
        this.mp = this.mp - 5;
        System.out.println("敵にダメージを" + this.at2 + "与えた");
        System.out.println("MPが5減った");
    }

    void cure() {
        System.out.println("魔法使いが回復魔法を使用した");
        this.hp = this.hp + 10;
        this.mp = this.mp - 10;
        System.out.println("HPが10回復し" + this.hp + "になった");
        System.out.println("MPが10減った");
    }

    void heal() {
        System.out.println("魔法使いが休憩した");
        this.hp = this.hp + 10;
        System.out.println("HPが10回復し" + this.hp + "になった");
    }
}

