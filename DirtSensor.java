public class DirtSensor {
	private FloorPlan floorPlan;
	private PowerManagement powerManagement;

	public DirtSensor(FloorPlan floorPlan, PowerManagement powerManagement) {
		this.floorPlan = floorPlan;
		this.powerManagement = powerManagement;
	}

	public boolean isDirty(int x, int y) {
		return floorPlan.isDirty(x, y);
	}

	public void cleanTile(int x, int y) {
		SurfaceType surfaceType = floorPlan.getSurfaceType(x, y);
		floorPlan.cleanTile(x, y);
		powerManagement.consumePower(surfaceType);
	}
}
