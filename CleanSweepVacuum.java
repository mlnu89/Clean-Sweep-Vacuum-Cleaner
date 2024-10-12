public class CleanSweepVacuum {
	private int currentX;
	private int currentY;
	private DirtSensor dirtSensor;
	private PowerManagement powerManagement;
	private FloorPlan floorPlan;

	public CleanSweepVacuum(int startX, int startY, FloorPlan floorPlan) {
		this.currentX = startX;
		this.currentY = startY;
		this.powerManagement = new PowerManagement(250);
		this.dirtSensor = new DirtSensor(floorPlan, powerManagement);
		this.floorPlan = floorPlan;
	}

	public boolean canMove(Direction direction) {
		return floorPlan.isPathClear(direction, currentX, currentY);
	}

	public boolean detectObstacle(Direction direction) {
		SurfaceType surfaceType = floorPlan.getSurfaceTypeInDirection(direction, currentX, currentY);
		return surfaceType == SurfaceType.OBSTACLE;
	}

	public boolean detectStairs(Direction direction) {
		SurfaceType surfaceType = floorPlan.getSurfaceTypeInDirection(direction, currentX, currentY);
		return surfaceType == SurfaceType.STAIRS; // Assuming STAIRS is defined in SurfaceType
	}

	public boolean detectUnknownPath(Direction direction) {
		SurfaceType surfaceType = floorPlan.getSurfaceTypeInDirection(direction, currentX, currentY);
		return surfaceType == SurfaceType.UNKNOWN;
	}

	public void move(Direction direction) {
		if (canMove(direction)) {
			int[] newCoords = direction.move(currentX, currentY);
			SurfaceType surfaceType = floorPlan.getSurfaceType(newCoords[0], newCoords[1]);
			int powerUsed = powerManagement.consumePower(surfaceType);

			this.currentX = newCoords[0];
			this.currentY = newCoords[1];
			System.out.println("Moving to: (" + currentX + ", " + currentY + ")");
			System.out.println("Power consumed for move: " + powerUsed);
		} else {
			System.out.println("Obstacle or stairs detected. Cannot move " + direction);
		}
	}

	public void clean() {
		SurfaceType surfaceType = floorPlan.getSurfaceType(currentX, currentY);
		if (dirtSensor.isDirty(currentX, currentY)) {
			dirtSensor.cleanTile(currentX, currentY);
			powerManagement.consumePower(surfaceType);
		} else {
			System.out.println("No dirt detected at: (" + currentX + ", " + currentY + ")");
		}

	}

	public void recharge() {
		powerManagement.recharge();
	}
}
