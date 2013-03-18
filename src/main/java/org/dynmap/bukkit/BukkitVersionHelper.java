package org.dynmap.bukkit;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Collections;
import java.util.Map;
import org.bukkit.Bukkit;
import org.bukkit.Chunk;
import org.bukkit.ChunkSnapshot;
import org.bukkit.Server;
import org.bukkit.World;
import org.dynmap.Log;

/**
 * Helper for isolation of bukkit version specific issues
 */
public abstract class BukkitVersionHelper {

    private static BukkitVersionHelper helper = null;
    
    public static final BukkitVersionHelper getHelper() {
        if(helper == null) {
            if(Bukkit.getServer().getVersion().contains("MCPC")) {
                Log.info("Loader version helper for MCPC");
                helper = new BukkitVersionHelperMCPC();
            }
            else if(Bukkit.getServer().getVersion().contains("BukkitForge")) {
                Log.info("Loader version helper for BukkitForge");
                helper = new BukkitVersionHelperBukkitForge();
            }
            else {
                helper = new BukkitVersionHelperCB();
            }
        }
        return helper;
    }
    protected BukkitVersionHelper() {
        
    }
    /**
     * Get list of defined biomebase objects
     */
    public abstract Object[] getBiomeBaseList();
    /** 
     * Get temperature from biomebase
     */
    public abstract float getBiomeBaseTemperature(Object bb);
    /** 
     * Get humidity from biomebase
     */
    public abstract float getBiomeBaseHumidity(Object bb);
    /**
     * Get ID string from biomebase
     */
    public abstract String getBiomeBaseIDString(Object bb);
    /** 
     * Get ID from biomebase
     */
    public abstract int getBiomeBaseID(Object bb);
    /** 
     * Get net.minecraft.server.world for given world
     */
    public abstract Object getNMSWorld(World w);
    /**
     *  Get unload queue for given NMS world
     */
    public abstract Object getUnloadQueue(Object nmsworld);
    /**
     *  For testing unload queue for presence of givne chunk
     */
    public abstract boolean isInUnloadQueue(Object unloadqueue, int x, int z);
    /**
     * Read raw biome ID from snapshot
     */
    public abstract Object[] getBiomeBaseFromSnapshot(ChunkSnapshot css);
    /**
     * Test if normal chunk snapshot
     */
    public abstract boolean isCraftChunkSnapshot(ChunkSnapshot css);
    /** 
     * Remove entities from given chunk
     */
    public abstract void removeEntitiesFromChunk(Chunk c);
    /** 
     * Get tile entities map from chunk
     */
    public abstract Map getTileEntitiesForChunk(Chunk c);
    /**
     * Get X coordinate of tile entity
     */
    public abstract int getTileEntityX(Object te);
    /**
     * Get Y coordinate of tile entity
     */
    public abstract int getTileEntityY(Object te);
    /**
     * Get Z coordinate of tile entity
     */
    public abstract int getTileEntityZ(Object te);
    /**
     * Read tile entity NBT
     */
    public abstract Object readTileEntityNBT(Object te);
    /**
     * Get field value from NBT compound
     */
    public abstract Object getFieldValue(Object nbt, String field);
    /**
     * Unload chunk no save needed
     */
    public abstract void unloadChunkNoSave(World w, int cx, int cz);
}