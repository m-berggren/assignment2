public class task4 {

    class Pokemon { // creating pokemon class

        private final pokemonClass type;

        public Pokemon(pokemonClass type) {
            this.type = type;
        }

        public double calculateDamage(Pokemon target) {
            switch (this.type) {
                case BUG:
                    switch (target.type) {
                        case FIRE:
                            return 0.5;
                        case GRASS:
                            return 2;
                        default:
                            return 1;
                    }
                case DRAGON:
                    switch (target.type) {
                        case DRAGON:
                            return 2;
                        default:
                            return 1;
                    }
                case ELECTRIC:
                    switch (target.type) {
                        case DRAGON:
                            return 0.5;
                        case ELECTRIC:
                            return 0.5;
                        case GRASS:
                            return 0.5;
                        case WATER:
                            return 2;
                        default:
                            return 1;
                    }

                case FIRE:
                    switch (target.type) {
                        case BUG:
                            return 2;
                        case DRAGON:
                            return 0.5;
                        case FIRE:
                            return 0.5;
                        case GRASS:
                            return 2;
                        case ICE:
                            return 2;
                        case WATER:
                            return 0.5;
                        default:
                            return 1;
                    }
                case GRASS:
                    switch (target.type) {
                        case BUG:
                            return 0.5;
                        case DRAGON:
                            return 0.5;
                        case FIRE:
                            return 0.5;
                        case GRASS:
                            return 0.5;
                        case WATER:
                            return 2;
                        default:
                            return 1;
                    }
                case ICE:
                    switch (target.type) {
                        case DRAGON:
                            return 2;
                        case FIRE:
                            return 0.5;
                        case GRASS:
                            return 2;
                        case ICE:
                            return 0.5;
                        case WATER:
                            return 0.5;
                        default:
                            return 1;
                    }
                case WATER:
                    switch (target.type) { // returning multipliers
                        case DRAGON:
                            return 0.5;
                        case FIRE:
                            return 2; // double damage
                        case GRASS:
                            return 0.5;
                        case WATER:
                            return 0.5; // half damage

                        default:
                            return 1; // normal damage
                    }
                default:
                    return 1;
            }
        }
    }
}
