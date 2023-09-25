package assignment2;

public class Type {

    private final PokemonType type;

    public Type(String type) {
        this.type = mapType(type);
    }

    public PokemonType mapType(String typeName) {
        return switch(typeName.toLowerCase()) {
            case "water" -> PokemonType.WATER;
            case "fire" -> PokemonType.FIRE;
            case "grass" -> PokemonType.GRASS;
            default -> PokemonType.NORMAL;
        };
    }

    public double calculateDamage(Type target) {
        switch (this.type) {
            case WATER -> {
                return switch (target.type) { // returning multipliers
                    case FIRE -> 2; //double damage
                    case GRASS -> 0.5;
                    case WATER -> 0.5; //half damage
                    default -> 1; //normal damage
                };
            }
            case FIRE -> {
                return switch (target.type) {
                    case GRASS -> 2;
                    case WATER -> 0.5;
                    case FIRE -> 0.5;
                    default -> 1;
                };
            }
            case GRASS -> {
                return switch (target.type) {
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
