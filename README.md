# Программа Магазин ноутбуков:
Программа состоит из двух файлов: Program с основной логикой и класса Laptop с описанием полей и методов класса ноутбуков.
## 1. Реализация структуры класса Ноутбук для магазина техники - выделение полей и методов.
```
import java.util.Objects;

public class Laptop {
    private int id;
    private String model;
    private String color;
    private int ram;
    private int ssd;
    private String os;


    public Laptop (int id, String model, String color, int ram, int ssd, String os) {
        this.id = id;
        this.model = model;
        this.color = color;
        this.ram = ram;
        this.ssd = ssd;
        this.os = os;
    }

    public int getId() {
        return id;
    }
    public String getModel() {
        return model;
    }
    public String getColor() {
        return color;
    }
    public int getRam() {
        return ram;
    }
    public int getSsd() {
        return ssd;
    }
    public String getOs() {
        return os;
    }

    public String setColor() {
        return color;
    }
    public int setRam() {
        return ram;
    }
    public int setSsd() {
        return ssd;
    }

    @Override
    public String toString() {
        return String.format("ID: %d, Модель: %s, Цвет: %s, Операционная память: %dMb, Объем накопителей SSD: %dMb, Операционная система: %s", id, model, color, ram, ssd, os);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, model, color, ram, ssd, os);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        Laptop l = (Laptop) obj;
        return id == l.id && model.equals(l.model) && color.equals(l.color) && ram == l.ram && ssd == l.ssd && os.equals(l.os);
    }
}
```

## 2. Создание множества ноутбуков
```
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class Program {

    public static Set<Laptop> creatingObjectsLaptops() {
        Laptop laptop1 = new Laptop(1, "MacBook", "grey", 8, 512, "MacOS");
        Laptop laptop2 = new Laptop(2, "MacBook", "white", 8, 1024, "MacOS");
        Laptop laptop3 = new Laptop(2, "MacBook", "white", 8, 1024, "MacOS");
        Laptop laptop4 = new Laptop(3, "Lenovo", "silver", 16, 256, "Windows");
        Laptop laptop5 = new Laptop(4, "Lenovo", "black", 16, 512, "Windows");
        Laptop laptop6 = new Laptop(5, "HP", "grey", 8, 256, "Windows");
        Laptop laptop7 = new Laptop(6, "Aser", "silver", 16, 1024, "Linux");
        Laptop laptop8 = new Laptop(7, "Asus", "grey", 16, 512, "Windows");
        
        Set<Laptop> laptopsArr = new HashSet<>(Arrays.asList(laptop1, laptop2, laptop3, laptop4, laptop5, laptop6, laptop7, laptop8));
        return laptopsArr;
    }
}
```
## 3. Метод запроса критериев для поиска у пользователя, а так же сохрание минимальных значений для этих критериев в HashMap.
```
    public static HashMap<String, String> filterParameter = new HashMap<>();

    public static HashMap<String, String> userData() {
        HashMap<Integer, String> characteristics = new HashMap<>();
        characteristics.put(0, "stop");    
        characteristics.put(1, "model");
        characteristics.put(2, "color");
        characteristics.put(3, "ram");
        characteristics.put(4, "ssd");
        characteristics.put(5, "os");

        Scanner sc = new Scanner(System.in);
        boolean flag = true;

        while (flag) {
            for (int i : characteristics.keySet()) {
                System.out.println(i + ". " + characteristics.get(i));     
            }
            System.out.println("Enter a number of filter parametr or choose 0 to stop: ");

            int parameter = sc.nextInt();
            sc.nextLine();

            switch (parameter) {
                case 0:
                    flag = false;
                    break;
                case 1:
                    System.out.println("Enter a model (MacBook, Lenovo, HP, Aser, Asus): ");
                    String valueParam = sc.nextLine();
                    filterParameter.put("model", valueParam);
                    break;
                case 2:
                    System.out.println("Enter a color (grey, white, black, silver): ");
                    String valueParam2 = sc.nextLine();
                    filterParameter.put("color", valueParam2);
                    break;
                case 3:
                    System.out.println("Enter a minimal value for the parametr (8Gb, 16Gb): ");
                    String valueParam3 = sc.nextLine();
                    filterParameter.put("ram", valueParam3);
                    break;
                case 4:
                    System.out.println("Enter a minimal value for the parametr (256Gb, 512Gb, 1024Gb): ");
                    String valueParam4 = sc.nextLine();
                    filterParameter.put("ssd", valueParam4);
                    break;
                case 5:
                    System.out.println("Enter a minimal an os: (MacOS, Windows, Linux)");
                    String valueParam5 = sc.nextLine();
                    filterParameter.put("os", valueParam5);
                    break;
            }
        }
        sc.close();
        return filterParameter;
    }
```

## 3. Метод фильтрации изначального множества ноутбуков согласно критериям, запрошенным пользователем.
```
    public static Set<Laptop> filterResult(Set<Laptop> laptopsArr){
        Map<String, String> filterParameter = userData();
        Set<Laptop> filterLaptop = new HashSet<>();
        for (Laptop laptop: laptopsArr){
            if(laptop.getModel().equals(filterParameter.get("model")) || filterParameter.get("model") == null) {
                if (laptop.getColor().equals(filterParameter.get("color")) || filterParameter.get("color") == null) {
                    if (laptop.getOs().equals(filterParameter.get("os")) || filterParameter.get("os") == null) {
                        if (filterParameter.get("ram") == null || (filterParameter.get("ram") != null && laptop.getRam() >= Integer.parseInt(filterParameter.get("ram")))) {
                            if (filterParameter.get("ssd") == null || (filterParameter.get("ssd") != null && laptop.getRam() >= Integer.parseInt(filterParameter.get("ssd")))) {
                                filterLaptop.add(laptop);
                            }
                        }
                    }
                }
            } 
        }
        if (filterLaptop.isEmpty()) {
            System.out.println("There is no laptops with such parametrs!");
        }
        return filterLaptop;
    }   
```
## 4. Метод вывода множества изначальных и отфильтрованных ноутбуков на экран.
```
    public static void printObjectsLaptops(Set<Laptop> laptopsArr) {
        for (Laptop laptop : laptopsArr) {
            System.out.println(laptop);
        }
    }
```
## 5. Вызов методов в main.
```
    public static void main(String[] args) {
        Set<Laptop> laptopsArr = creatingObjectsLaptops();
        // printObjectsLaptops(laptopsArr); // распечатка всех имеющихся ноутбуков
        Set<Laptop> filterLaptops = filterResult(laptopsArr);
        printObjectsLaptops(filterLaptops); // распечатка отфильтрованных ноутбуков
    } 
```