public enum Direction {
	NORTH, SOUTH, EAST, WEST;

	public int[] move(int x, int y) {
		if (this == NORTH) {
			return new int[] { x, y - 1 };
		} else if (this == SOUTH) {
			return new int[] { x, y + 1 };
		} else if (this == EAST) {
			return new int[] { x + 1, y };
		} else if (this == WEST) {
			return new int[] { x - 1, y };
		} else { // Don't move
			return new int[] { x, y };
		}
	}
}
