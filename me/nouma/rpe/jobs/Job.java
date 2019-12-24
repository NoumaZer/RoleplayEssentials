package me.nouma.rpe.jobs;

public class Job {

    private String rawName, name, color, desc;
    private double salary;
    //private boolean idDefault;

    public Job(String rawName, String name, String color, String desc, double salary) {
        this.name = name;
        this.rawName = rawName;
        this.color = color;
        this.desc = desc;
        this.salary = salary;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRawName() {
        return rawName;
    }

    public void setRawName(String clearName) {
        this.rawName = clearName;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }
}
