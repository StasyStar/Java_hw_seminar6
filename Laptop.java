package hw_6seminar;

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

  