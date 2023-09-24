package assignment2;

public class Type {

    private final PokemonType type;

    public Type(PokemonType type) {
        this.type = type;
    }

    public double calculateDamage(Pokemon target) {
        switch (this.type) {
            case WATER -> {
                return switch (PokemonType.WATER) { // returning multipliers
                    case FIRE -> 2; //double damage
                    case GRASS -> 0.5;
                    case WATER -> 0.5; //half damage
                    default -> 1; //normal damage
                };
            }
            case FIRE -> {
                return switch (PokemonType.FIRE) {
                    case GRASS -> 2;
                    case WATER -> 0.5;
                    case FIRE -> 0.5;
                    default -> 1;
                };
            }
            case GRASS -> {
                return switch (PokemonType.GRASS) {
                    case WATER -> 2;
                    case FIRE -> 0.5;
                    case GRASS -> 0.5;
                    default -> 1;
                };
            }
            default -> {
                return 1;
            }
        }
    }


}
