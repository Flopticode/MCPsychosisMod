package com.teamlos.psychosis.events;

import com.teamlos.psychosis.PsychosisMod;
import com.teamlos.psychosis.client.model.AngelModel;
import com.teamlos.psychosis.client.model.JoghurtModel;
import com.teamlos.psychosis.client.renderer.AngelRenderer;
import com.teamlos.psychosis.client.renderer.JoghurtRenderer;
import com.teamlos.psychosis.entities.EntityRegistry;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = PsychosisMod.MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientModEvents
{
	@SubscribeEvent
	public static void registerRenderers(EntityRenderersEvent.RegisterRenderers event)
	{
		event.registerEntityRenderer(EntityRegistry.JOGHURT.get(), JoghurtRenderer::new);
		event.registerEntityRenderer(EntityRegistry.ANGEL.get(), AngelRenderer::new);
	}
	
	@SubscribeEvent
	public static void registerLayerDefinitions(EntityRenderersEvent.RegisterLayerDefinitions event)
	{
		event.registerLayerDefinition(JoghurtModel.LAYER_LOCATION, JoghurtModel::createBodyLayer);
		event.registerLayerDefinition(AngelModel.LAYER_LOCATION, AngelModel::createBodyLayer);
	}
}
