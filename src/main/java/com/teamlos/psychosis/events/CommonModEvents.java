package com.teamlos.psychosis.events;

import com.teamlos.psychosis.PsychosisMod;
import com.teamlos.psychosis.entities.Angel;
import com.teamlos.psychosis.entities.EntityRegistry;
import com.teamlos.psychosis.entities.Joghurt;
import com.teamlos.psychosis.entities.Sloth;

import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.event.entity.SpawnPlacementRegisterEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = PsychosisMod.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class CommonModEvents
{
	@SubscribeEvent
	public static void entityAttributes(EntityAttributeCreationEvent event)
	{
		event.put(EntityRegistry.JOGHURT.get(), Joghurt.createAttributes().build());
		event.put(EntityRegistry.ANGEL.get(), Angel.createAttributes().build());
		event.put(EntityRegistry.SLOTH.get(), Angel.createAttributes().build());
	}
	
	@SubscribeEvent
	public static void registerSpawnPlacements(SpawnPlacementRegisterEvent event)
	{
		event.register(EntityRegistry.JOGHURT.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.WORLD_SURFACE, Joghurt::canSpawn, SpawnPlacementRegisterEvent.Operation.OR);
		event.register(EntityRegistry.ANGEL.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.WORLD_SURFACE, Angel::canSpawn, SpawnPlacementRegisterEvent.Operation.OR);
		event.register(EntityRegistry.SLOTH.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.WORLD_SURFACE, Sloth::canSpawn, SpawnPlacementRegisterEvent.Operation.OR);
	}
}
