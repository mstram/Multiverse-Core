/******************************************************************************
 * Multiverse 2 Copyright (c) the Multiverse Team 2011.                       *
 * Multiverse 2 is licensed under the BSD License.                            *
 * For more information please check the README.md file included              *
 * with this project.                                                         *
 ******************************************************************************/

package com.onarandombox.MultiverseCore.api;

import com.onarandombox.MultiverseCore.configuration.MVConfigProperty;
import com.onarandombox.MultiverseCore.exceptions.PropertyDoesNotExistException;

import org.bukkit.ChatColor;
import org.bukkit.Difficulty;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.CommandSender;
import org.bukkit.permissions.Permission;

import java.util.List;

/**
 * The API for a Multiverse Handled World.
 * <p>
 * Currently INCOMPLETE
 */
public interface MultiverseWorld {

    /**
     * Returns the Bukkit world object that this world describes.
     *
     * @return A {@link World}
     */
    World getCBWorld();

    /**
     * Adds the property to the given value.
     * It will throw a PropertyDoesNotExistException if the property is not found.
     *
     * @param property The name of a world property to set.
     * @param value    A value in string representation, it will be parsed to the correct type.
     * @param sender   The sender who wants this value to be set.
     * @return True if the value was set, false if not.
     * @throws PropertyDoesNotExistException Thrown if the property was not found in the world.
     */
    boolean setProperty(String property, String value, CommandSender sender) throws PropertyDoesNotExistException;

    /**
     * Gets the actual MVConfigProperty from this world.
     * It will throw a PropertyDoesNotExistException if the property is not found.
     *
     * @param property The name of a world property to get.
     * @return A valid MVWorldProperty.
     * @throws PropertyDoesNotExistException Thrown if the property was not found in the world.
     * @deprecated Use {@link #getProperty(String, Class)} instead
     */
    @Deprecated
    MVConfigProperty<?> getProperty(String property) throws PropertyDoesNotExistException;

    /**
     * Gets the string representation of a property.
     * It will throw a PropertyDoesNotExistException if the property is not found.
     *
     * @param property The name of a world property to get.
     * @return A valid MVWorldProperty.
     * @throws PropertyDoesNotExistException Thrown if the property was not found in the world.
     */
    String getPropertyValue(String property) throws PropertyDoesNotExistException;

    /**
     * Gets the actual MVConfigProperty from this world.
     * It will throw a PropertyDoesNotExistException if the property is not found.
     *
     * @param property The name of a world property to get.
     * @param expected The type of the expected property. Use Object.class if this doesn't matter for you.
     * @param <T> The type of the expected property.
     *
     * @return A valid MVWorldProperty.
     *
     * @throws PropertyDoesNotExistException Thrown if the property was not found in the world.
     */
    <T> MVConfigProperty<T> getProperty(String property, Class<T> expected) throws PropertyDoesNotExistException;

    /**
     * Removes all values from the given property. The property must be a {@link com.onarandombox.MultiverseCore.enums.AddProperties}.
     *
     * @param property The name of a {@link com.onarandombox.MultiverseCore.enums.AddProperties} to clear.
     * @return True if it was cleared, false if not.
     */
    boolean clearVariable(String property);

    /**
     * Adds a value to the given property. The property must be a {@link com.onarandombox.MultiverseCore.enums.AddProperties}.
     *
     * @param property The name of a {@link com.onarandombox.MultiverseCore.enums.AddProperties} to add a value to.
     * @param value    A value in string representation, it will be parsed to the correct type.
     * @return True if the value was added, false if not.
     */
    boolean addToVariable(String property, String value);

    /**
     * Removes a value from the given property. The property must be a {@link com.onarandombox.MultiverseCore.enums.AddProperties}.
     *
     * @param property The name of a {@link com.onarandombox.MultiverseCore.enums.AddProperties} to remove a value
     *                 from.
     * @param value    A value in string representation, it will be parsed to the correct type.
     * @return True if the value was removed, false if not.
     */
    boolean removeFromVariable(String property, String value);

    /**
     * Gets the environment of this world.
     *
     * @return A {@link org.bukkit.World.Environment}.
     */
    World.Environment getEnvironment();

    /**
     * Sets the environment of a world.
     * <p>
     * Note: This will ONLY take effect once the world is unloaded/reloaded.
     *
     * @param environment A {@link org.bukkit.World.Environment}.
     */
    void setEnvironment(World.Environment environment);

    /**
     * Gets the world seed of this world.
     *
     * @return The Long version of the seed.
     */
    Long getSeed();

    /**
     * Sets the seed of this world.
     *
     * @param seed A Long that is the seed.
     */
    void setSeed(Long seed);

    /**
     * Gets the name of this world. This cannot be changed.
     *
     * @return The name of the world as a String.
     */
    String getName();

    /**
     * Gets the alias of this world.
     * <p>
     * This alias allows users to have a world named "world" but show up in the list as "FernIsland"
     *
     * @return The alias of the world as a String.
     */
    String getAlias();

    /**
     * Sets the alias of the world.
     *
     * @param alias A string that is the new alias.
     */
    void setAlias(String alias);

    /**
     * Sets the color that this world's name/alias will display as.
     *
     * @param color A valid color name.
     * @return True if the color was set, false if not.
     */
    boolean setColor(String color);

    /**
     * Gets the color that this world's name/alias will display as.
     *
     * @return The color of this world.
     */
    ChatColor getColor();

    /**
     * Tells you if someone entered a valid color.
     *
     * @param color A string that may translate to a color.
     * @return True if it is a color, false if not.
     */
    boolean isValidAliasColor(String color);

    /**
     * Returns a very nicely colored string (using Alias and Color if they are set).
     *
     * @return A nicely colored string.
     */
    String getColoredWorldString();

    /**
     * Gets whether or not animals are allowed to spawn in this world.
     *
     * @return True if ANY animal can, false if no animals can spawn.
     */
    boolean canAnimalsSpawn();

    /**
     * Gets whether or not monsters are allowed to spawn in this world.
     *
     * @return True if ANY monster can, false if no monsters can spawn.
     */
    boolean canMonstersSpawn();

    /**
     * Turn pvp on or off. This setting is used to set the world's PVP mode, and thus relies on fakePVP
     *
     * @param pvpMode True to enable PVP damage, false to disable it.
     */
    void setPVPMode(boolean pvpMode);

    /**
     * Turn pvp on or off. This setting is used to set the world's PVP mode, and thus relies on fakePVP
     *
     * @return True if this world has fakepvp on
     * @deprecated This is deprecated.
     */
    @Deprecated
    boolean getFakePVP();

    /**
     * Gets whether or not PVP is enabled in this world in some form (fake or not).
     *
     * @return True if players can take damage from other players.
     */
    boolean isPVPEnabled();

    /**
     * Gets whether or not this world will display in chat, mvw and mvl regardless if a user has the
     * access permissions to go to this world.
     *
     * @return True if the world will be hidden, false if not.
     */
    boolean isHidden();

    /**
     * Sets whether or not this world will display in chat, mvw and mvl regardless if a user has the
     * access permissions to go to this world.
     *
     * @param hidden Set
     */
    void setHidden(boolean hidden);

    /**
     * Sets whether or not there will be weather events in a given world.
     * If set to false, Multiverse will disable the weather in the world immediately.
     *
     * @param enableWeather True if weather events should occur in a world, false if not.
     */
    void setEnableWeather(boolean enableWeather);

    /**
     * Gets whether weather is enabled in this world.
     *
     * @return True if weather events will occur, false if not.
     */
    boolean isWeatherEnabled();

    /**
     * If true, tells Craftbukkit to keep a worlds spawn chunks loaded in memory (default: true)
     * If not, CraftBukkit will attempt to free memory when players have not used that world.
     * This will not happen immediately.
     *
     * @param keepSpawnInMemory If true, CraftBukkit will keep the spawn chunks loaded in memory.
     */
    void setKeepSpawnInMemory(boolean keepSpawnInMemory);

    /**
     * Gets whether or not CraftBukkit is keeping the chunks for this world in memory.
     *
     * @return True if CraftBukkit is keeping spawn chunks in memory.
     */
    boolean isKeepingSpawnInMemory();

    /**
     * Sets the difficulty of this world and returns true if success.
     * Valid string values are either an integer of difficulty(0-3) or
     * the name that resides in the Bukkit enum, ex. PEACEFUL
     *
     * @param difficulty The difficulty to set the world to as a string.
     * @return True if success, false if the provided string
     *         could not be translated to a difficulty.
     */
    boolean setDifficulty(String difficulty);

    /**
     * Gets the difficulty of this world.
     *
     * @return The difficulty of this world.
     */
    Difficulty getDifficulty();

    /**
     * Sets the spawn location for a world.
     *
     * @param spawnLocation The spawn location for a world.
     */
    void setSpawnLocation(Location spawnLocation);

    /**
     * Gets the spawn location of this world.
     *
     * @return The spawn location of this world.
     */
    Location getSpawnLocation();

    /**
     * Sets whether or not the hunger level of players will go down in a world.
     *
     * @param hungerEnabled True if hunger will go down, false to keep it at
     *                      the level they entered a world with.
     */
    void setHunger(boolean hungerEnabled);

    /**
     * Gets whether or not the hunger level of players will go down in a world.
     *
     * @return True if it will go down, false if it will remain steady.
     */
    boolean getHunger();

    /**
     * Sets the game mode of this world.
     *
     * @param gameMode A valid game mode string (either
     *                 an int ex. 0 or a string ex. creative).
     * @return True if the game mode was successfully changed, false if not.
     */
    boolean setGameMode(String gameMode);

    /**
     * Gets the GameMode of this world.
     *
     * @return The GameMode of this world.
     */
    GameMode getGameMode();

    /**
     * Gets the permission required to enter this world.
     *
     * @return The permission required to be exempt from charges to/from this world.
     */
    Permission getAccessPermission();

    /**
     * Gets the permission required to be exempt when entering.
     *
     * @return The permission required to be exempt when entering.
     */
    Permission getExemptPermission();

    /**
     * Sets the price for entry to this world.
     * You can think of this like an amount.
     * The type can be set with {@link #setCurrency(int)}
     *
     * @param price The Amount of money/item to enter the world.
     */
    void setPrice(double price);

    /**
     * Gets the amount of currency it requires to enter this world.
     *
     * @return The amount it costs to enter this world.
     */
    double getPrice();

    /**
     * Sets the type of item that will be required given the price is not 0.
     * Use -1 to use an AllPay economy, or any valid itemid
     *
     * @param item The Type of currency that will be used when users enter this world.
     */
    void setCurrency(int item);

    /**
     * Gets the Type of currency that will be used when users enter this world.
     *
     * @return The Type of currency that will be used when users enter this world.
     */
    int getCurrency();

    /**
     * Sets the world players will respawn in if they die in this one.
     * Returns true upon success, false upon failure.
     *
     * @param respawnWorld The name of a world that exists on the server.
     * @return True if respawnWorld existed, false if not.
     */
    boolean setRespawnToWorld(String respawnWorld);

    /**
     * Gets the world players will respawn in if they die in this one.
     *
     * @return A world that exists on the server.
     */
    World getRespawnToWorld();

    /**
     * Sets the scale of this world. Really only has an effect if you use
     * Multiverse-NetherPortals.
     *
     * @param scaling A scaling value, cannot be negative or 0.
     * @return Whether the scale was set successfully.
     */
    boolean setScaling(double scaling);

    /**
     * Gets the scaling value of this world.Really only has an effect if you use
     * Multiverse-NetherPortals.
     *
     * @return This world's non-negative, non-zero scale.
     */
    double getScaling();

    /**
     * Gets a list of all the worlds that players CANNOT travel to from this world,
     * regardless of their access permissions.
     *
     * @return A List of world names.
     */
    List<String> getWorldBlacklist();

    /**
     * Returns a list of animals. This list always negates the {@link #canAnimalsSpawn()} result.
     *
     * @return A list of animals that will spawn if {@link #canAnimalsSpawn()} is false.
     */
    List<String> getAnimalList();

    /**
     * Sets whether or not animals can spawn.
     * If there are values in {@link #getAnimalList()} and this is false,
     * those animals become the exceptions, and will spawn
     *
     * @param allowAnimalSpawn True to allow spawning of monsters, false to prevent.
     */
    void setAllowAnimalSpawn(boolean allowAnimalSpawn);

    /**
     * Returns a list of monsters. This list always negates the {@link #canMonstersSpawn()} ()} result.
     *
     * @return A list of monsters that will spawn if {@link #canMonstersSpawn()} is false.
     */
    List<String> getMonsterList();

    /**
     * Sets whether or not monsters can spawn.
     * If there are values in {@link #getMonsterList()} and this is false,
     * those monsters become the exceptions, and will spawn
     *
     * @param allowMonsterSpawn True to allow spawning of monsters, false to prevent.
     */
    void setAllowMonsterSpawn(boolean allowMonsterSpawn);

    /**
     * Clears a list property (sets it to []).
     *
     * @param property The property to clear.
     * @return True if success, false if fail.
     */
    boolean clearList(String property);

    /**
     * Sets whether or not a world will auto-heal players if the difficulty is on peaceful.
     *
     * @param heal True if the world will heal.
     */
    void setAutoHeal(boolean heal);

    /**
     * Gets whether or not a world will auto-heal players if the difficulty is on peaceful.
     *
     * @return True if the world should heal (default), false if not.
     */
    boolean getAutoHeal();

    /**
     * Sets whether or not Multiverse should auto-adjust the spawn for this world.
     *
     * @param adjust True if multiverse should adjust the spawn, false if not.
     */
    void setAdjustSpawn(boolean adjust);

    /**
     * Gets whether or not Multiverse should auto-adjust the spawn for this world.
     *
     * @return True if Multiverse should adjust the spawn, false if not.
     */
    boolean getAdjustSpawn();

    /**
     * Sets whether or not Multiverse should auto-load this world.
     * <p>
     * True is default.
     *
     * @param autoLoad True if multiverse should autoload this world the spawn, false if not.
     */
    void setAutoLoad(boolean autoLoad);

    /**
     * Gets whether or not Multiverse should auto-load this world.
     *
     * @return True if Multiverse should auto-load this world.
     */
    boolean getAutoLoad();

    /**
     * Sets whether or not a player who dies in this world will respawn in their
     * bed or follow the normal respawn pattern.
     * <p>
     * True is default.
     *
     * @param autoLoad True if players dying in this world respawn at their bed.
     */
    void setBedRespawn(boolean autoLoad);

    /**
     * Gets whether or not a player who dies in this world will respawn in their
     * bed or follow the normal respawn pattern.
     *
     * @return True if players dying in this world should respawn at their bed.
     */
    boolean getBedRespawn();

    /**
     * Gets all the names of all properties that can be SET.
     *
     * @return All property names, with alternating colors.
     */
    String getAllPropertyNames();
}
