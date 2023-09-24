package assignment2;

public enum PokemonTypeExtended {
    WATER("Water"),
    FIRE("Fire"),
    GRASS("Grass"),
    NORMAL("Normal");

    final String TYPE;

    PokemonTypeExtended(String type) {
        this.TYPE = type;
    }
    @Override
    public String toString() {
        return this.TYPE;
    }



}
