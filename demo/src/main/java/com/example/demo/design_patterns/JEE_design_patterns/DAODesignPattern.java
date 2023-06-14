package com.example.demo.design_patterns.JEE_design_patterns;


import java.util.ArrayList;
import java.util.List;

class Developer {
    private String name;
    private int DeveloperId;

    Developer(String name, int DeveloperId) {
        this.name = name;
        this.DeveloperId = DeveloperId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDeveloperId() {
        return DeveloperId;
    }

    public void setDeveloperId(int DeveloperId) {
        this.DeveloperId = DeveloperId;
    }
}

//DeveloperDAO  ##################################################
interface DeveloperDao {
    public List<Developer> getAllDevelopers();

    public Developer getDeveloper(int DeveloperId);

    public void updateDeveloper(Developer Developer);

    public void deleteDeveloper(Developer Developer);
}

//DeveloperDaoImpl ##################################################
class DeveloperDaoImpl implements DeveloperDao {
    List<Developer> Developers;

    public DeveloperDaoImpl() {
        Developers = new ArrayList<>();
        Developer Developer1 = new Developer("Kiran", 101011);
        Developer Developer2 = new Developer("Vikrant", 124122);
        Developers.add(Developer1);
        Developers.add(Developer2);
    }

    public void deleteDeveloper(Developer Developer) {
        Developers.remove(Developer.getDeveloperId());
        System.out.println("DeveloperId " + Developer.getDeveloperId() + ", deleted from database");
    }

    public List<Developer> getAllDevelopers() {
        return Developers;
    }

    public Developer getDeveloper(int DeveloperId) {
        return Developers.get(DeveloperId);
    }

    public void updateDeveloper(Developer Developer) {
        Developers.get(Developer.getDeveloperId()).setName(Developer.getName());
        System.out.println("DeveloperId " + Developer.getDeveloperId() + ", updated in the database");
    }
}

//DaoPatternDemo ##################################################

public class DAODesignPattern {
    public static void main(String[] args) {
        DeveloperDao DeveloperDao = new DeveloperDaoImpl();
        for (Developer Developer : DeveloperDao.getAllDevelopers()) {
            System.out.println("DeveloperId : " + Developer.getDeveloperId() + ", Name : " + Developer.getName());
        }
        Developer Developer = DeveloperDao.getAllDevelopers().get(0);
        Developer.setName("Lokesh");
        DeveloperDao.updateDeveloper(Developer);
        DeveloperDao.getDeveloper(0);
        System.out.println("DeveloperId : " + Developer.getDeveloperId() + ", Name : " + Developer.getName());
    }
}
