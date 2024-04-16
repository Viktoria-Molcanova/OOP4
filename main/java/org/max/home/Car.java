
package org.max.home;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "car_info", schema = "main", catalog = "")
public class CarInfoEntity {
    private short carId;
    private String brand;
    private String model;
    private int year;
    private String color;

    @Id
    @Column(name = "car_id")
    public short getCarId() {
        return carId;
    }

    public void setCarId(short carId) {
        this.carId = carId;
    }

    @Column(name = "brand")
    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    @Column(name = "model")
    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    @Column(name = "year")
    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    @Column(name = "color")
    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CarInfoEntity that = (CarInfoEntity) o;
        return carId == that.carId && Objects.equals(brand, that.brand) && Objects.equals(model, that.model) && year == that.year && Objects.equals(color, that.color);
    }

    @Override
    public int hashCode() {
        return Objects.hash(carId, brand, model, year, color);
    }
}