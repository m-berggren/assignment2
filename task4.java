public class task4 {

    class Pokemon { // creating pokemon class

        private final pokemonClass type;

        public Pokemon(pokemonClass type) {
            this.type = type;
        }

        public double calculateDamage(Pokemon target) {
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
                default:
                    return 1;
            }
        }
    }
}
