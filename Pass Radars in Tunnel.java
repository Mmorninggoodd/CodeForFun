/*
	Given a 2-D zone in a tunnel of width 1, and a list of radar that has radius and center coordinate.
	Return whether a car can pass the tunnel from y = 1 to y = 0 without being detected by any radar. 
*/

/*
	Union find
*/
static class Radar {
	double x, y, radius;
	Radar(double x, double y, double radius) {
		this.x = x;
		this.y = y;
		this.radius = radius;
	}
	public boolean equals(Radar o2) {
		return this.x == o2.x && this.y == o2.y && this.radius == o2.radius;
	}
}
static class ConnectedComponent {
	double left, right;
	Set<Radar> radarList;
	ConnectedComponent(Radar radar){
		radarList = new HashSet<>();
		radarList.add(radar);
		this.left = radar.x - radar.radius;
		this.right = radar.x + radar.radius;
	}
}
static class RadarEvent {
	enum EventType {
		LEFT, RIGHT
	}
	EventType type;
	Radar radar;
	double x, y;
	RadarEvent(EventType type, Radar radar, double x, double y) {
		this.type = type;
		this.radar = radar;
		this.x = x;
		this.y = y;
	}
}
private static boolean isOverlap(Radar r1, Radar r2) {
	double sqDist = (r1.x - r2.x) * (r1.x - r2.x) + (r1.y - r2.y) * (r1.y - r2.y);
	return (r1.radius + r2.radius) * (r1.radius + r2.radius) >= sqDist;
}
private static boolean union(Radar r1, Radar r2, Map<Radar, ConnectedComponent> components) {
	ConnectedComponent comp1 = components.get(r1), comp2 = components.get(r2);
	if(comp1.equals(comp2)) return false;
	comp1.left = Math.min(comp1.left, comp2.left);
	comp2.right = Math.max(comp1.right, comp2.right);
	comp1.radarList.addAll(comp2.radarList);
	for(Radar radar : comp2.radarList) {
		components.put(radar, comp1);
	}
	return comp1.left <= 0 && comp2.right >= 1;
}
public static boolean canPass(List<Radar> radars) {
	List<RadarEvent> events = new ArrayList<>();
	Map<Radar, ConnectedComponent> components = new HashMap<>();
	for(Radar radar : radars) {
		events.add(new RadarEvent(RadarEvent.EventType.LEFT, radar, radar.x - radar.radius, radar.y));
		events.add(new RadarEvent(RadarEvent.EventType.RIGHT, radar, radar.x + radar.radius, radar.y));
		components.put(radar, new ConnectedComponent(radar));
	}
	Collections.sort(events, ((o1, o2) -> {
		if(o1.x == o2.x && o1.type == RadarEvent.EventType.LEFT) return -1;
		else return Double.compare(o1.x, o2.x);
	}));
	Set<Radar> activeRadars = new HashSet<>();
	for(RadarEvent event : events) {
		Radar curRadar = event.radar;
		if(event.type == RadarEvent.EventType.LEFT) {
			for(Radar candidate : activeRadars) {
				if(!candidate.equals(curRadar) && isOverlap(candidate, curRadar)) {
					if(union(candidate, curRadar, components)) return false;
				}
			}
			activeRadars.add(curRadar);
		}
		else activeRadars.remove(curRadar);
	}
	return true;
}