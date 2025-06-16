public enum Direction {
    NORTH,EAST,SOUTH,WEST;

    public static Direction next(Direction direction) {
          return values()[(direction.ordinal() + 1) % values().length];
    }
}
