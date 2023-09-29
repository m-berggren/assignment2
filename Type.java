package assignment2;

public class Type {

    private final PokemonType type;

    public Type(String type) {
        //this.type = mapType(type);
        this.type = PokemonType.valueOf(String.format("%S", type));
    }

    public double calculateDamage(Type target) {
        switch (this.type) {
            case BUG:
                return switch (target.type) {
                    case FIRE -> 0.5;
                    case GRASS -> 2;
                    default -> 1;
                };
            case DRAGON:
                return switch (target.type) {
                    case DRAGON -> 2;
                    default -> 1;
                };
            case ELECTRIC:
                return switch (target.type) {
                    case DRAGON -> 0.5;
                    case ELECTRIC -> 0.5;
                    case GRASS -> 0.5;
                    case WATER -> 2;
                    default -> 1;
                };

            case FIRE:
                return switch (target.type) {
                    case BUG -> 2;
                    case DRAGON -> 0.5;
                    case FIRE -> 0.5;
                    case GRASS -> 2;
                    case ICE -> 2;
                    case WATER -> 0.5;
                    default -> 1;

                };
            case GRASS:
                return switch (target.type) {
                    case BUG -> 0.5;
                    case DRAGON -> 0.5;
                    case FIRE -> 0.5;
                    case GRASS -> 0.5;
                    case WATER -> 2;
                    default -> 1;

                };
            case ICE:
                return switch (target.type) {

                    case DRAGON -> 2;
                    case FIRE -> 0.5;
                    case GRASS -> 2;
                    case ICE -> 0.5;
                    case WATER -> 0.5;
                    default -> 1;
                };
            case WATER:
                return switch (target.type) { // returning multipliers
                    case DRAGON -> 0.5;
                    case FIRE -> 2; // double damage
                    case GRASS -> 0.5;
                    case WATER -> 0.5; // half damage

                    default -> 1; // normal damage
                };
            default: {
                return 1;
            }
        }
    }
}