package com.demo.provider.domain;

public class Country {
    private String code;

    private String name;

    private String continent;

    private String region;

    private Float surfacearea;

    private Short indepyear;

    private Integer population;

    private Float lifeexpectancy;

    private Float gnp;

    private Float gnpold;

    private String localname;

    private String governmentform;

    private String headofstate;

    private Integer capital;

    private String code2;

    public Country(String code, String name, String continent, String region, Float surfacearea, Short indepyear, Integer population, Float lifeexpectancy, Float gnp, Float gnpold, String localname, String governmentform, String headofstate, Integer capital, String code2) {
        this.code = code;
        this.name = name;
        this.continent = continent;
        this.region = region;
        this.surfacearea = surfacearea;
        this.indepyear = indepyear;
        this.population = population;
        this.lifeexpectancy = lifeexpectancy;
        this.gnp = gnp;
        this.gnpold = gnpold;
        this.localname = localname;
        this.governmentform = governmentform;
        this.headofstate = headofstate;
        this.capital = capital;
        this.code2 = code2;
    }

    public Country() {
        super();
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getContinent() {
        return continent;
    }

    public void setContinent(String continent) {
        this.continent = continent == null ? null : continent.trim();
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region == null ? null : region.trim();
    }

    public Float getSurfacearea() {
        return surfacearea;
    }

    public void setSurfacearea(Float surfacearea) {
        this.surfacearea = surfacearea;
    }

    public Short getIndepyear() {
        return indepyear;
    }

    public void setIndepyear(Short indepyear) {
        this.indepyear = indepyear;
    }

    public Integer getPopulation() {
        return population;
    }

    public void setPopulation(Integer population) {
        this.population = population;
    }

    public Float getLifeexpectancy() {
        return lifeexpectancy;
    }

    public void setLifeexpectancy(Float lifeexpectancy) {
        this.lifeexpectancy = lifeexpectancy;
    }

    public Float getGnp() {
        return gnp;
    }

    public void setGnp(Float gnp) {
        this.gnp = gnp;
    }

    public Float getGnpold() {
        return gnpold;
    }

    public void setGnpold(Float gnpold) {
        this.gnpold = gnpold;
    }

    public String getLocalname() {
        return localname;
    }

    public void setLocalname(String localname) {
        this.localname = localname == null ? null : localname.trim();
    }

    public String getGovernmentform() {
        return governmentform;
    }

    public void setGovernmentform(String governmentform) {
        this.governmentform = governmentform == null ? null : governmentform.trim();
    }

    public String getHeadofstate() {
        return headofstate;
    }

    public void setHeadofstate(String headofstate) {
        this.headofstate = headofstate == null ? null : headofstate.trim();
    }

    public Integer getCapital() {
        return capital;
    }

    public void setCapital(Integer capital) {
        this.capital = capital;
    }

    public String getCode2() {
        return code2;
    }

    public void setCode2(String code2) {
        this.code2 = code2 == null ? null : code2.trim();
    }
}