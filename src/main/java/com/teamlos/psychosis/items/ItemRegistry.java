package com.teamlos.psychosis.items;

import com.teamlos.psychosis.PsychosisMod;

import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ItemRegistry
{
	public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, PsychosisMod.MODID);

	public static final RegistryObject<Item> GIGABYTE_GPU = ITEMS.register("gigabyte_gpu", ()-> new Item(
			new Item.Properties()
			.stacksTo(1)));

	public static final RegistryObject<Item> BANANA = ITEMS.register("banana", ()-> new Item(
			new Item.Properties()
			.stacksTo(64)
			.food(
					new FoodProperties.Builder()
					.nutrition(5)
					.saturationMod(.2f)
					.build()
			)
			.rarity(Rarity.COMMON)));
}
