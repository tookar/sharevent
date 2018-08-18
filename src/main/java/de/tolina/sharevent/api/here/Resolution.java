package de.tolina.sharevent.api.here;

/**
 * Resolution to be used.
 * 
 * Note: Only works with the map scheme types t=0 normal.day, t=2 terrain.day, t=3 hybrid.day, t=4 normal.day.transit, t=5 normal.day.grey, t=13 pedestrian.day, t=14
 * pedestrian.night
 * 
 */
@SuppressWarnings("javadoc")
public enum Resolution {

	DEFAULT(72), MOBILE(250), HI_RES(320), OTHER(500);

	private final int ppi;

	private Resolution(int ppi) {
		this.ppi = ppi;
	}

	/**
	 * @return the resolution
	 */
	public int getPpi() {
		return ppi;
	}
}
