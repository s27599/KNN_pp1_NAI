public enum Results {
    Iris_setosa("Iris-setosa"),
    Iris_versicolor("Iris-versicolor"),
    Iris_virginica("Iris-virginica");

    private final String name;
    private Results(String name){
        this.name = name;
    }
    private String getName(){
        return name;
    }


}
