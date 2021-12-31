package com.demo.effective;


/**
 * @author lz
 * @date 2021-12-06 10:38
 */
public class NutitionFacts {
    private int servingSize ;
    private int servings;
    private int calories;
    private int fat;
    private int sodium;
    private int carbohydrate;

    public static class Builder{
        private int servingSize ;
        private int servings;

        private int calories        = 0;
        private int fat             = 0;
        private int sodium          = 0;
        private int carbohydrate    = 0;
        public Builder(int servingSize,int servings){
            this.servings = servings;
            this.servingSize = servingSize;
        }
        public Builder calories(int calories){
            this.calories = calories;
            return this;
        }
        public Builder fat(int fat){
            this.fat = fat;
            return this;
        }
        public Builder sodium(int sodium){
            this.sodium = sodium;
            return this;
        }
        public Builder carbohydrate(int carbohydrate){
            this.carbohydrate = carbohydrate;
            return this;
        }
        public NutitionFacts build(){
            return new NutitionFacts(this);
        }
    }

    private NutitionFacts(Builder builder){
        this.calories = builder.calories;
        this.carbohydrate = builder.carbohydrate;
        this.fat = builder.fat;
        this.servings = builder.servings;
        this.servingSize = builder.servingSize;
        this.sodium = builder.sodium;
    }

    @Override
    public String toString() {
        return "NutitionFacts{" +
                "servingSize=" + servingSize +
                ", servings=" + servings +
                ", calories=" + calories +
                ", fat=" + fat +
                ", sodium=" + sodium +
                ", carbohydrate=" + carbohydrate +
                '}';
    }

    public static void main(String[] args) {
        NutitionFacts nutitionFacts = new Builder(5,6)
                .calories(2)
                .carbohydrate(2)
                .fat(20)
                .sodium(23)
                .build();
        System.out.println(nutitionFacts);
    }
}
