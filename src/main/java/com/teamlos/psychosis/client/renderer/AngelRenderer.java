package com.teamlos.psychosis.client.renderer;

import com.teamlos.psychosis.PsychosisMod;
import com.teamlos.psychosis.client.model.AngelModel;
import com.teamlos.psychosis.entities.Angel;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class AngelRenderer extends MobRenderer<Angel, AngelModel<Angel>> {

	private static final ResourceLocation TEXTURE = new ResourceLocation(PsychosisMod.MODID, "textures/entity/joghurt.png");
	
	public AngelRenderer(EntityRendererProvider.Context ctx)
	{
		super(ctx, new AngelModel(ctx.bakeLayer(AngelModel.LAYER_LOCATION)), 0.3f);
	}

	@Override
	public ResourceLocation getTextureLocation(Angel entity)
	{
		return TEXTURE;
	}
	
}
