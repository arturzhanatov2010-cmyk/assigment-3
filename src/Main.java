import java.util.Random;

public class Main {
    public static void main(String[] args) {

        System.out.println(" Тестирование HashTable ");
        MyHashTable<MyTestingClass, String> friendsTable = new MyHashTable<>();
        Random rand = new Random();


        for (int i = 0; i < 10000; i++) {
            String nick = "UserX" + rand.nextInt(500);
            int code = rand.nextInt(99999);
            friendsTable.put(new MyTestingClass(nick, code), "Value_" + i);
        }

        friendsTable.showBucketDistribution();

        System.out.println("\n=== Тестирование BST ===");
        BST<Integer, String> myTree = new BST<>();
        myTree.put(10, "Десять");
        myTree.put(4, "Четыре");
        myTree.put(15, "Пятнадцать");
        myTree.put(2, "Два");
        myTree.put(7, "Семь");

        System.out.println("Элементы по возрастанию :");
        for (var elem : myTree) {
            System.out.println("Ключ: " + elem.getKey() + " | Значение: " + elem.getValue());
        }

        System.out.println("Размер: " + myTree.size());

        System.out.println("Удаляем ключ 4...");
        myTree.delete(4);
        System.out.println("Новый размер: " + myTree.size());
    }
}
