package com.github.barneygale.PlumpXP;

import java.util.List;

public class Configuration {
	private final PlumpXP plugin;
	public double PLUMP_MULTIPLIER;
	public double BLAZE_MULTIPLIER;
	public boolean PLAYER_OVERRIDE;
	public double PLAYER_MULTIPLIER;
	public double ORE_MULTIPLIER;
    public double FURNACE_MULTIPLIER;
    public double FISHING_MULTIPLIER;
    public boolean PLUMP_OUTSIDE_RADIUS;
    public double PLUMP_RADIUS;
    public List<Double> PLUMP_RADIUS_CENTER;
    public List<String> PLUMP_RADIUS_WORLDS;
	
	public Configuration(PlumpXP instance) {
		plugin = instance;
	}
	public void save() {
		plugin.saveConfig();
	}
	public void load() {
		plugin.reloadConfig();
		PLUMP_MULTIPLIER = plugin.getConfig().getDouble("plump-multiplier");
		BLAZE_MULTIPLIER = plugin.getConfig().getDouble("blaze-multiplier");
		PLAYER_OVERRIDE = plugin.getConfig().getBoolean("player-override");
		PLAYER_MULTIPLIER = plugin.getConfig().getDouble("player-multiplier");
		ORE_MULTIPLIER = plugin.getConfig().getDouble("ore-multiplier");
        FURNACE_MULTIPLIER = plugin.getConfig().getDouble("furnace-multiplier");
        FISHING_MULTIPLIER = plugin.getConfig().getDouble("fishing-multiplier");
        PLUMP_OUTSIDE_RADIUS = plugin.getConfig().getBoolean("plump-outside-radius.enabled");
        PLUMP_RADIUS = plugin.getConfig().getDouble("plump-outside-radius.radius");
        PLUMP_RADIUS_CENTER = plugin.getConfig().getDoubleList("plump-outside-radius.centerpoint");
        PLUMP_RADIUS_WORLDS = plugin.getConfig().getStringList("plump-outside-radius.worlds");
	}

}
