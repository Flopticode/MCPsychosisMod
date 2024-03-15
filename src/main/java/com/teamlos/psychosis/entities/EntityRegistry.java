package com.teamlos.psychosis.entities;

import com.teamlos.psychosis.PsychosisMod;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class EntityRegistry
{
	public static final DeferredRegister<EntityType<?>> ENTITIES = DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, PsychosisMod.MODID);
	
	public static final RegistryObject<EntityType<Joghurt>> JOGHURT = ENTITIES.register("joghurt", ()->EntityType.Builder.<Joghurt>of(Joghurt::new, MobCategory.MONSTER)
			.sized(0.3f, 1.3f)
			.build(new ResourceLocation(PsychosisMod.MODID, "joghurt").toString()));
	
	public static final RegistryObject<EntityType<Angel>> ANGEL = ENTITIES.register("angel", ()->EntityType.Builder.<Angel>of(Angel::new, MobCategory.MONSTER)
			.sized(2.5f, 0.7f)
			.build(new ResourceLocation(PsychosisMod.MODID, "angel").toString()));
}
