package assignment2;

public class Type {

    private final pokemonType type;

    public Type(pokemonType type) {
        this.type = type;
    }

    public double calculateDamage(Pokemon target) {
        switch (this.type) {
            case WATER:
                switch (pokemonType.WATER) { // returning multipliers
                    case FIRE:
                        return 2; //double damage
                    case GRASS:
                        return 0.5;
                    case WATER:
                        return 0.5; //half damage
                    default:
                        return 1; //normal damage
                }
            case FIRE:
                switch (pokemonType.FIRE) {
                    case GRASS:
                        return 2;
                    case WATER:
                        return 0.5;
                    case FIRE:
                        return 0.5;
                    default:
                        return 1;
                }
            case GRASS:
                switch (pokemonType.GRASS) {
                    case WATER:
                        return 2;
                    case FIRE:
                        return 0.5;
                    case GRASS:
                        return 0.5;
                    default:
                        return 1;
                }
            default:
                return 1;
        }
    }


}
