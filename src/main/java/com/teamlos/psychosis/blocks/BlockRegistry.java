package com.teamlos.psychosis.blocks;

import com.teamlos.psychosis.PsychosisMod;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class BlockRegistry
{
	/* Create registers */
	public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, PsychosisMod.MODID);
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, PsychosisMod.MODID);
	
	/* Empty pizza box */
	public static final RegistryObject<Block> EMPTY_PIZZA_BOX = BLOCKS.register("pizza_box_empty", () -> new Block(
			BlockBehaviour.Properties.of()
			.mapColor(MapColor.COLOR_BROWN)
			.strength(1)
			.sound(SoundType.MOSS_CARPET)
			.pushReaction(PushReaction.DESTROY)));
	
	public static final RegistryObject<Item> EMPTY_PIZZA_BOX_ITEM = ITEMS.register("pizza_box_empty", ()-> new BlockItem(EMPTY_PIZZA_BOX.get(),
			new Item.Properties()
			.stacksTo(64)
			.food(
					new FoodProperties.Builder()
					.nutrition(5)
					.saturationMod(.2f)
					.build()
			)
			.rarity(Rarity.COMMON)));
	
	/* Full pizza box*/
	public static final RegistryObject<Block> FULL_PIZZA_BOX = BLOCKS.register("pizza_box_full", () -> new Block(
			BlockBehaviour.Properties.of()
			.mapColor(MapColor.COLOR_BROWN)
			.strength(1)
			.sound(SoundType.MOSS_CARPET)
			.pushReaction(PushReaction.DESTROY)));
	
	public static final RegistryObject<Item> FULL_PIZZA_BOX_ITEM = ITEMS.register("pizza_box_full", ()->new BlockItem(FULL_PIZZA_BOX.get(),
			new Item.Properties()
			.stacksTo(64)
			.food(
					new FoodProperties.Builder()
					.nutrition(1)
					.saturationMod(0f)
					.effect(() -> new MobEffectInstance(MobEffects.POISON), 1)
					.build()
			)
			.rarity(Rarity.COMMON)));
}
