package model;

import java.util.Objects;

public class Bacteria {
    private final int dayOfBirthday;
    private boolean life;

    public Bacteria(int dayOfBirthday) {
        this.dayOfBirthday = dayOfBirthday;
        this.life = true;
    }

    public boolean isLife() {
        return life;
    }

    public void setLife(boolean life) {
        this.life = life;
    }

    public boolean needReproduce(int day){
        return day - this.dayOfBirthday > 0 && day - this.dayOfBirthday <= 14;
    }

    boolean needDeath(int day){
        if (this.isLife())
            return day - this.dayOfBirthday > 7;
        return false;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Bacteria bacteria = (Bacteria) o;
        return dayOfBirthday == bacteria.dayOfBirthday &&
                life == bacteria.life;
    }

    @Override
    public int hashCode() {
        return Objects.hash(dayOfBirthday, life);
    }

    @Override
    public String toString() {
        return "Bacteria{" +
                "dayOfBirthday=" + dayOfBirthday +
                ", life=" + life +
                '}';
    }

}
