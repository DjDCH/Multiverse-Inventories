package com.onarandombox.multiverseinventories.api.share;

import com.fernferret.allpay.multiverse.GenericBank;
import com.onarandombox.multiverseinventories.api.DataStrings;
import com.onarandombox.multiverseinventories.api.Inventories;
import com.onarandombox.multiverseinventories.api.PlayerStats;
import com.onarandombox.multiverseinventories.api.profile.PlayerProfile;
import com.onarandombox.multiverseinventories.api.profile.WorldGroupProfile;
import com.onarandombox.multiverseinventories.util.MinecraftTools;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * The Sharables class is where all the default Sharable instances are located as constants as well as a factory class
 * for generating Shares.
 */
public final class Sharables implements Shares {

    private static final Shares ALL_SHARABLES = new Sharables(new LinkedHashSet<Sharable>());

    /**
     * The map used to lookup a Sharable or set of Sharables by their name.
     */
    static final Map<String, Shares> LOOKUP_MAP = new HashMap<String, Shares>();

    private static Inventories inventories = null;

    /**
     * Initialize this class with the instance of Inventories.
     *
     * @param inventories the instance of Inventories.
     */
    public static void init(Inventories inventories) {
        if (Sharables.inventories == null) {
            Sharables.inventories = inventories;
        }
    }

    /**
     * Sharing Inventory.
     */
    public static final Sharable<ItemStack[]> INVENTORY = new Sharable.Builder<ItemStack[]>("inventory_contents",
            ItemStack[].class, new SharableHandler<ItemStack[]>() {
                @Override
                public void updateProfile(PlayerProfile profile, Player player) {
                    profile.set(INVENTORY, player.getInventory().getContents());
                }

                @Override
                public boolean updatePlayer(Player player, PlayerProfile profile) {
                    ItemStack[] value = profile.get(INVENTORY);
                    if (value == null) {
                        player.getInventory().setContents(MinecraftTools.fillWithAir(
                                new ItemStack[PlayerStats.INVENTORY_SIZE]));
                        player.updateInventory();
                        return false;
                    }
                    player.getInventory().setContents(value);
                    player.updateInventory();
                    return true;
                }
            }).serializer(new ProfileEntry(false, DataStrings.PLAYER_INVENTORY_CONTENTS),
                    new InventorySerializer(PlayerStats.INVENTORY_SIZE)).build();

    /**
     * Sharing Armor.
     */
    public static final Sharable<ItemStack[]> ARMOR = new Sharable.Builder<ItemStack[]>("armor_contents",
            ItemStack[].class, new SharableHandler<ItemStack[]>() {
                @Override
                public void updateProfile(PlayerProfile profile, Player player) {
                    profile.set(ARMOR, player.getInventory().getArmorContents());
                }

                @Override
                public boolean updatePlayer(Player player, PlayerProfile profile) {
                    ItemStack[] value = profile.get(ARMOR);
                    if (value == null) {
                        player.getInventory().setArmorContents(MinecraftTools.fillWithAir(
                                new ItemStack[PlayerStats.ARMOR_SIZE]));
                        player.updateInventory();
                        return false;
                    }
                    player.getInventory().setArmorContents(value);
                    player.updateInventory();
                    return true;
                }
            }).serializer(new ProfileEntry(false, DataStrings.PLAYER_ARMOR_CONTENTS),
                    new InventorySerializer(PlayerStats.ARMOR_SIZE)).altName("armor").build();

    /**
     * Sharing Health.
     */
    public static final Sharable<Integer> HEALTH = new Sharable.Builder<Integer>("hit_points", Integer.class,
            new SharableHandler<Integer>() {
                @Override
                public void updateProfile(PlayerProfile profile, Player player) {
                    profile.set(HEALTH, player.getHealth());
                }

                @Override
                public boolean updatePlayer(Player player, PlayerProfile profile) {
                    Integer value = profile.get(HEALTH);
                    if (value == null) {
                        player.setHealth(PlayerStats.HEALTH);
                        return false;
                    }
                    player.setHealth(value);
                    return true;
                }
            }).stringSerializer(new ProfileEntry(true, DataStrings.PLAYER_HEALTH))
            .altName("health").altName("hp").altName("hitpoints").build();

    /**
     * Sharing Health.
     */
    public static final Sharable<Integer> REMAINING_AIR = new Sharable.Builder<Integer>("remaining_air", Integer.class,
            new SharableHandler<Integer>() {
                @Override
                public void updateProfile(PlayerProfile profile, Player player) {
                    profile.set(REMAINING_AIR, player.getRemainingAir());
                }

                @Override
                public boolean updatePlayer(Player player, PlayerProfile profile) {
                    Integer value = profile.get(REMAINING_AIR);
                    if (value == null) {
                        player.setRemainingAir(PlayerStats.REMAINING_AIR);
                        return false;
                    }
                    player.setRemainingAir(value);
                    return true;
                }
            }).stringSerializer(new ProfileEntry(true, DataStrings.PLAYER_REMAINING_AIR)).build();

    /**
     * Sharing Health.
     */
    public static final Sharable<Integer> MAXIMUM_AIR = new Sharable.Builder<Integer>("maximum_air", Integer.class,
            new SharableHandler<Integer>() {
                @Override
                public void updateProfile(PlayerProfile profile, Player player) {
                    profile.set(MAXIMUM_AIR, player.getMaximumAir());
                }

                @Override
                public boolean updatePlayer(Player player, PlayerProfile profile) {
                    Integer value = profile.get(MAXIMUM_AIR);
                    if (value == null) {
                        player.setMaximumAir(PlayerStats.MAXIMUM_AIR);
                        return false;
                    }
                    player.setMaximumAir(value);
                    return true;
                }
            }).stringSerializer(new ProfileEntry(true, DataStrings.PLAYER_MAX_AIR)).build();

    /**
     * Sharing Health.
     */
    public static final Sharable<Float> FALL_DISTANCE = new Sharable.Builder<Float>("fall_distance", Float.class,
            new SharableHandler<Float>() {
                @Override
                public void updateProfile(PlayerProfile profile, Player player) {
                    profile.set(FALL_DISTANCE, player.getFallDistance());
                }

                @Override
                public boolean updatePlayer(Player player, PlayerProfile profile) {
                    Float value = profile.get(FALL_DISTANCE);
                    if (value == null) {
                        player.setFallDistance(PlayerStats.FALL_DISTANCE);
                        return false;
                    }
                    player.setFallDistance(value);
                    return true;
                }
            }).stringSerializer(new ProfileEntry(true, DataStrings.PLAYER_FALL_DISTANCE))
            .altName("falling").build();

    /**
     * Sharing Health.
     */
    public static final Sharable<Integer> FIRE_TICKS = new Sharable.Builder<Integer>("fire_ticks", Integer.class,
            new SharableHandler<Integer>() {
                @Override
                public void updateProfile(PlayerProfile profile, Player player) {
                    profile.set(FIRE_TICKS, player.getFireTicks());
                }

                @Override
                public boolean updatePlayer(Player player, PlayerProfile profile) {
                    Integer value = profile.get(FIRE_TICKS);
                    if (value == null) {
                        player.setFireTicks(PlayerStats.FIRE_TICKS);
                        return false;
                    }
                    player.setFireTicks(value);
                    return true;
                }
            }).stringSerializer(new ProfileEntry(true, DataStrings.PLAYER_FIRE_TICKS))
            .altName("fire").altName("burning").build();


    /**
     * Sharing Experience.
     */
    public static final Sharable<Float> EXPERIENCE = new Sharable.Builder<Float>("exp", Float.class,
            new SharableHandler<Float>() {
                @Override
                public void updateProfile(PlayerProfile profile, Player player) {
                    profile.set(EXPERIENCE, player.getExp());
                }

                @Override
                public boolean updatePlayer(Player player, PlayerProfile profile) {
                    Float value = profile.get(EXPERIENCE);
                    if (value == null) {
                        player.setExp(PlayerStats.EXPERIENCE);
                        return false;
                    }
                    player.setExp(value);
                    return true;
                }
            }).stringSerializer(new ProfileEntry(true, DataStrings.PLAYER_EXPERIENCE))
            .altName("xp").build();

    /**
     * Sharing Experience.
     */
    public static final Sharable<Integer> LEVEL = new Sharable.Builder<Integer>("level", Integer.class,
            new SharableHandler<Integer>() {
                @Override
                public void updateProfile(PlayerProfile profile, Player player) {
                    profile.set(LEVEL, player.getLevel());
                }

                @Override
                public boolean updatePlayer(Player player, PlayerProfile profile) {
                    Integer value = profile.get(LEVEL);
                    if (value == null) {
                        player.setLevel(PlayerStats.LEVEL);
                        return false;
                    }
                    player.setLevel(value);
                    return true;
                }
            }).stringSerializer(new ProfileEntry(true, DataStrings.PLAYER_LEVEL))
            .altName("lvl").build();

    /**
     * Sharing Experience.
     */
    public static final Sharable<Integer> TOTAL_EXPERIENCE = new Sharable.Builder<Integer>("total_exp", Integer.class,
            new SharableHandler<Integer>() {
                @Override
                public void updateProfile(PlayerProfile profile, Player player) {
                    profile.set(TOTAL_EXPERIENCE, player.getTotalExperience());
                }

                @Override
                public boolean updatePlayer(Player player, PlayerProfile profile) {
                    Integer value = profile.get(TOTAL_EXPERIENCE);
                    if (value == null) {
                        player.setTotalExperience(PlayerStats.TOTAL_EXPERIENCE);
                        return false;
                    }
                    player.setTotalExperience(value);
                    return true;
                }
            }).stringSerializer(new ProfileEntry(true, DataStrings.PLAYER_TOTAL_EXPERIENCE))
            .altName("total_xp").altName("totalxp").build();

    /**
     * Sharing Hunger.
     */
    public static final Sharable<Integer> FOOD_LEVEL = new Sharable.Builder<Integer>("food_level", Integer.class,
            new SharableHandler<Integer>() {
                @Override
                public void updateProfile(PlayerProfile profile, Player player) {
                    profile.set(FOOD_LEVEL, player.getFoodLevel());
                }

                @Override
                public boolean updatePlayer(Player player, PlayerProfile profile) {
                    Integer value = profile.get(FOOD_LEVEL);
                    if (value == null) {
                        player.setFoodLevel(PlayerStats.FOOD_LEVEL);
                        return false;
                    }
                    player.setFoodLevel(value);
                    return true;
                }
            }).stringSerializer(new ProfileEntry(true, DataStrings.PLAYER_FOOD_LEVEL))
            .altName("food").build();

    /**
     * Sharing Hunger.
     */
    public static final Sharable<Float> EXHAUSTION = new Sharable.Builder<Float>("exhaustion", Float.class,
            new SharableHandler<Float>() {
                @Override
                public void updateProfile(PlayerProfile profile, Player player) {
                    profile.set(EXHAUSTION, player.getExhaustion());
                }

                @Override
                public boolean updatePlayer(Player player, PlayerProfile profile) {
                    Float value = profile.get(EXHAUSTION);
                    if (value == null) {
                        player.setExhaustion(PlayerStats.EXHAUSTION);
                        return false;
                    }
                    player.setExhaustion(value);
                    return true;
                }
            }).stringSerializer(new ProfileEntry(true, DataStrings.PLAYER_EXHAUSTION))
            .altName("exhaust").altName("exh").build();

    /**
     * Sharing Hunger.
     */
    public static final Sharable<Float> SATURATION = new Sharable.Builder<Float>("saturation", Float.class,
            new SharableHandler<Float>() {
                @Override
                public void updateProfile(PlayerProfile profile, Player player) {
                    profile.set(SATURATION, player.getSaturation());
                }

                @Override
                public boolean updatePlayer(Player player, PlayerProfile profile) {
                    Float value = profile.get(SATURATION);
                    if (value == null) {
                        player.setSaturation(PlayerStats.SATURATION);
                        return false;
                    }
                    player.setSaturation(value);
                    return true;
                }
            }).stringSerializer(new ProfileEntry(true, DataStrings.PLAYER_SATURATION))
            .altName("sat").build();

    /**
     * Sharing Bed Spawn.
     */
    public static final Sharable<Location> BED_SPAWN = new Sharable.Builder<Location>("bed_spawn", Location.class,
            new SharableHandler<Location>() {
                @Override
                public void updateProfile(PlayerProfile profile, Player player) {
                    profile.set(BED_SPAWN, player.getBedSpawnLocation());
                }

                @Override
                public boolean updatePlayer(Player player, PlayerProfile profile) {
                    Location loc = profile.get(BED_SPAWN);
                    if (loc == null) {
                        player.setBedSpawnLocation(player.getWorld().getSpawnLocation());
                        return false;
                    }
                    player.setBedSpawnLocation(loc);
                    return true;
                }
            }).serializer(new ProfileEntry(false, DataStrings.PLAYER_BED_SPAWN_LOCATION), new LocationSerializer())
            .altName("bedspawn").altName("bed").altName("beds").altName("bedspawns").build();

    /**
     * Sharing Bed Spawn.
     */
    public static final Sharable<Location> LAST_LOCATION = new Sharable.Builder<Location>("last_location", Location.class,
            new SharableHandler<Location>() {
                @Override
                public void updateProfile(PlayerProfile profile, Player player) {
                    //profile.set(LAST_LOCATION, player.getLocation());
                }

                @Override
                public boolean updatePlayer(Player player, PlayerProfile profile) {
                    Location loc = profile.get(LAST_LOCATION);
                    if (loc == null) {
                        return false;
                    }
                    player.teleport(loc);
                    return true;
                }
            }).serializer(new ProfileEntry(false, DataStrings.PLAYER_LAST_LOCATION), new LocationSerializer())
            .altName("loc").altName("location").altName("pos").altName("position").optional().build();

    /**
     * Sharing Economy.
     */
    public static final Sharable<Double> ECONOMY = new Sharable.Builder<Double>("economy", Double.class,
            new SharableHandler<Double>() {
                @Override
                public void updateProfile(PlayerProfile profile, Player player) {
                    profile.set(ECONOMY, inventories.getCore().getBank().getBalance(player, -1));
                }

                @Override
                public boolean updatePlayer(Player player, PlayerProfile profile) {
                    Double money = profile.get(ECONOMY);
                    GenericBank bank = inventories.getCore().getBank();
                    if (money == null) {
                        bank.setBalance(player, -1, 0);
                        return false;
                    }
                    bank.setBalance(player, -1, money);
                    return true;
                }
            }).stringSerializer(new ProfileEntry(false, "balance")).optional()
            .altName("money").altName("econ").altName("cash").altName("balance").build();

    /**
     * Sharing Potions.
     */
    public static final Sharable<PotionEffect[]> POTIONS = new Sharable.Builder<PotionEffect[]>("potion_effects", PotionEffect[].class,
            new SharableHandler<PotionEffect[]>() {
                @Override
                public void updateProfile(PlayerProfile profile, Player player) {
                    Collection<PotionEffect> potionEffects = player.getActivePotionEffects();
                    profile.set(POTIONS, potionEffects.toArray(new PotionEffect[potionEffects.size()]));
                }

                @Override
                public boolean updatePlayer(Player player, PlayerProfile profile) {
                    PotionEffect[] effects = profile.get(POTIONS);
                    Iterator<PotionEffect> it = player.getActivePotionEffects().iterator();
                    while (it.hasNext()) {
                        player.removePotionEffect(it.next().getType());
                    }
                    if (effects == null) {
                        return false;
                    }
                    for (PotionEffect effect : effects) {
                        player.addPotionEffect(effect);
                    }
                    return true;
                }
            }).serializer(new ProfileEntry(false, "potions"), new PotionEffectSerializer())
            .altName("potion").altName("potions").build();

    /**
     * Grouping for inventory sharables.
     */
    public static final SharableGroup ALL_INVENTORY = new SharableGroup("inventory",
            fromSharables(INVENTORY, ARMOR), "inv", "inventories");

    /**
     * Grouping for experience sharables.
     */
    public static final SharableGroup ALL_EXPERIENCE = new SharableGroup("experience",
            fromSharables(EXPERIENCE, TOTAL_EXPERIENCE, LEVEL));

    /**
     * Grouping for air/breath related sharables.
     */
    public static final SharableGroup AIR = new SharableGroup("air",
            fromSharables(REMAINING_AIR, MAXIMUM_AIR), "breath");

    /**
     * Grouping for hunger related sharables.
     */
    public static final SharableGroup HUNGER = new SharableGroup("hunger",
            fromSharables(FOOD_LEVEL, SATURATION, EXHAUSTION));

    /**
     * Grouping for player health related sharables.
     */
    public static final SharableGroup ALL_HEALTH = new SharableGroup("health",
            fromSharables(HEALTH, REMAINING_AIR, MAXIMUM_AIR, FALL_DISTANCE, FIRE_TICKS));

    /**
     * Grouping for player stat related sharables not including inventory.
     */
    public static final SharableGroup STATS = new SharableGroup("stats",
            fromSharables(HEALTH, FOOD_LEVEL, SATURATION, EXHAUSTION, EXPERIENCE, TOTAL_EXPERIENCE, LEVEL,
                    REMAINING_AIR, MAXIMUM_AIR, FALL_DISTANCE, FIRE_TICKS, POTIONS));

    /**
     * Grouping for ALL default sharables.
     * TODO: make this really mean all, including 3rd party.
     */
    public static final SharableGroup ALL_DEFAULT = new SharableGroup("all", fromSharables(HEALTH, ECONOMY,
            FOOD_LEVEL, SATURATION, EXHAUSTION, EXPERIENCE, TOTAL_EXPERIENCE, LEVEL, INVENTORY, ARMOR, BED_SPAWN,
            MAXIMUM_AIR, REMAINING_AIR, FALL_DISTANCE, FIRE_TICKS, POTIONS, LAST_LOCATION), "*",
            "everything");


    /**
     * Registers a Sharable, which is required for it to function properly.  This method is called automatically when
     * using the {@link Sharable.Builder} class and should not be called manually.
     *
     * @param sharable The sharable to register.
     * @return True if the sharable is not already registered.
     */
    static boolean register(Sharable sharable) {
        if (!ALL_SHARABLES.contains(sharable)) {
            // If the plugin has been enabled, we need to add this sharable to the existing groups with all sharables.
            if (inventories != null) {
                for (WorldGroupProfile group : inventories.getGroupManager().getGroups()) {
                    if (group.getShares().isSharing(Sharables.all())) {
                        group.getShares().setSharing(sharable, true);

                    }
                }
            }
        }
        if (ALL_SHARABLES.add(sharable)) {
            for (String name : sharable.getNames()) {
                String key = name.toLowerCase();
                Shares shares = LOOKUP_MAP.get(key);
                if (shares == null) {
                    shares = noneOf();
                    LOOKUP_MAP.put(key, shares);
                }
                shares.add(sharable);
            }
            return true;
        }
        return false;
    }

    /**
     * Looks up a sharable by one of the acceptable names.
     *
     * @param name Name to look up by.
     * @return Sharable by that name or null if none by that name.
     */
    public static Shares lookup(String name) {
        return LOOKUP_MAP.get(name.toLowerCase());
    }

    /**
     * @return A {@link Shares} collection containing ALL registered {@link Sharable}s.  This is NOT to be modified and
     * serves only as a reference.  For a version you can do what you want with, see {@link #allOf()}.
     */
    public static Shares all() {
        return ALL_SHARABLES;
    }

    /**
     * @return A new {@link Shares} instance containing ALL registered {@link Sharable}s for your own devices.
     */
    public static Shares allOf() {
        return new Sharables(new LinkedHashSet<Sharable>(ALL_SHARABLES));
    }

    /**
     * @return A new empty {@link Shares} instance for your own devices.
     */
    public static Shares noneOf() {
        return new Sharables(new LinkedHashSet<Sharable>(ALL_SHARABLES.size()));
    }

    /**
     * @param shares Shares to compare against.
     * @return A new {@link Shares} instance containing all {@link Sharable}s present in {@link #all()} that are NOT
     * contained in the shares argument.
     */
    public static Shares complimentOf(Shares shares) {
        Set<Sharable> compliment = Sharables.allOf();
        compliment.removeAll(shares);
        return new Sharables(compliment);
    }

    /**
     * Creates a new instance of {@link Shares} containing all Sharables contained in shares.
     *
     * @param shares Shares instance to effectively copy.
     * @return A new Shares instance containing all Sharables in shares.
     */
    public static Shares fromShares(Shares shares) {
        return new Sharables(shares);
    }

    /**
     * Creates a new instance of {@link Shares} containing all Sharables contained in sharesCollection.
     *
     * @param sharesCollection A collection of Sharables to effectively copy.
     * @return A new Shares instance containing all Sharables in sharesCollection.
     */
    public static Shares fromCollection(Collection<Sharable> sharesCollection) {
        Shares shares = noneOf();
        shares.addAll(sharesCollection);
        return shares;
    }

    /**
     * Creates a new instance of {@link Shares} containing the listed {@link Sharable}s.
     *
     * @param sharables Sharables to fill new Shares with.
     * @return A new Shares instance containing all Sharables passed in.
     */
    public static Shares fromSharables(Sharable... sharables) {
        Shares shares = noneOf();
        shares.addAll(Arrays.asList(sharables));
        return shares;
    }

    /**
     * Creates a new instance of {@link Shares} containing all shares negated in the given list.
     *
     * @param sharesList A list whose elements are to be strings which will be parsed with {@link #lookup(String)} if
     *                   they start with "-" indicating they are "negated" shares.
     * @return A new instance of {@link Shares} containing all shares negated in the given list.
     */
    public static Shares negativeFromList(List sharesList) {
        Shares shares = noneOf();
        for (Object shareStringObj : sharesList) {
            String shareString = shareStringObj.toString();
            if (!shareString.startsWith("-") || shareString.length() <= 1) {
                continue;
            }
            shareString = shareString.substring(1);
            Shares sharables = Sharables.lookup(shareString);
            if (sharables != null) {
                shares.mergeShares(sharables);
            } else {
                if (shareString.equals("*") || shareString.equalsIgnoreCase("all")
                        || shareString.equalsIgnoreCase("everything")) {
                    shares = allOf();
                    break;
                }
            }
        }
        return shares;
    }

    /**
     * Creates a new instance of {@link Shares} containing all shares not negated in the given list.
     *
     * @param sharesList A list whose elements are to be strings which will be parsed with {@link #lookup(String)} if
     *                   they do not start with "-" indicating they are normal shares.
     * @return A new instance of {@link Shares} containing all shares non-negated in the given list.
     */
    public static Shares fromList(List sharesList) {
        Shares shares = noneOf();
        for (Object shareStringObj : sharesList) {
            String shareString = shareStringObj.toString();
            Shares sharables = Sharables.lookup(shareString);
            if (sharables != null) {
                shares.mergeShares(sharables);
            } else {
                if (shareString.equals("*") || shareString.equalsIgnoreCase("all")
                        || shareString.equalsIgnoreCase("everything")) {
                    shares = allOf();
                    break;
                }
            }
        }
        return shares;
    }

    private Set<Sharable> sharables;

    private Sharables(Set<Sharable> sharableSet) {
        this.sharables = sharableSet;
    }

    private Sharables(Shares shares) {
        this.sharables = new LinkedHashSet<Sharable>(ALL_SHARABLES.size());
        this.sharables.addAll(shares);
    }

    @Override
    public int size() {
        return this.sharables.size();
    }

    @Override
    public boolean isEmpty() {
        return this.sharables.isEmpty();
    }

    @Override
    public boolean contains(Object o) {
        return this.sharables.contains(o);
    }

    @Override
    public Iterator<Sharable> iterator() {
        return this.sharables.iterator();
    }

    @Override
    public Object[] toArray() {
        return this.sharables.toArray();
    }

    @Override
    public <T> T[] toArray(T[] a) {
        return this.sharables.toArray(a);
    }

    @Override
    public boolean add(Sharable sharable) {
        return this.sharables.add(sharable);
    }

    @Override
    public boolean remove(Object o) {
        return this.sharables.remove(o);
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return this.sharables.containsAll(c);
    }

    @Override
    public boolean addAll(Collection<? extends Sharable> c) {
        return this.sharables.addAll(c);
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return this.sharables.retainAll(c);
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return this.sharables.removeAll(c);
    }

    @Override
    public void clear() {
        this.sharables.clear();
    }

    @Override
    public boolean equals(Object o) {
        return o instanceof Shares && ((Shares) o).isSharing(this);
    }

    @Override
    public int hashCode() {
        return this.sharables.hashCode();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void mergeShares(Shares newShares) {
        this.addAll(newShares);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setSharing(Sharable sharable, boolean sharing) {
        if (sharing) {
            this.add(sharable);
        } else {
            this.remove(sharable);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setSharing(Shares sharables, boolean sharing) {
        for (Sharable sharable : sharables) {
            if (sharing) {
                this.add(sharable);
            } else {
                this.remove(sharable);
            }
        }
    }

    @Override
    public Shares compare(Shares shares) {
        Shares bothSharing = Sharables.noneOf();
        for (Sharable sharable : shares) {
            if (this.contains(sharable)) {
                bothSharing.add(sharable);
            }
        }
        return bothSharing;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isSharing(Sharable sharable) {
        return this.contains(sharable);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isSharing(Shares shares) {
        boolean isSharing = this.sharables.equals(shares);
        if (!isSharing) {
            for (Sharable sharable : shares) {
                if (!this.isSharing(sharable)) {
                    return false;
                }
            }
            isSharing = true;
        }
        return isSharing;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<String> toStringList() {
        List<String> list = new LinkedList<String>();
        if (this.isSharing(Sharables.allOf())) {
            list.add("all");
        } else {
            for (Sharable sharable : this) {
                list.add(sharable.toString());
            }
        }
        return list;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (Sharable sharable : this) {
            if (!stringBuilder.toString().isEmpty()) {
                stringBuilder.append(", ");
            }
            stringBuilder.append(sharable);
        }
        return stringBuilder.toString();
    }
}
