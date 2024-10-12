public class PowerManagement {
	private final int maxBattery;
	private int battery;

	public PowerManagement(int maxBattery) {
		this.battery = maxBattery;
		this.maxBattery = maxBattery;
	}

	public int consumePower(SurfaceType surfaceType) {
		int powerConsumed;
		if (surfaceType == SurfaceType.BARE_FLOOR) {
			powerConsumed = 1;
		} else if (surfaceType == SurfaceType.LOW_PILE_CARPET) {
			powerConsumed = 2;
		} else if (surfaceType == SurfaceType.HIGH_PILE_CARPET) {
			powerConsumed = 3;
		} else {
			powerConsumed = 1;
		}

		battery -= powerConsumed;
		System.out.println("Power remaining: " + battery);

		return powerConsumed;
	}

	public void recharge() {
		battery = maxBattery;
		System.out.println("Battery recharged to full.");
	}

	public int getBatteryLevel() {
		return battery;
	}
}
